package com.github.ryanlove.coordinator.springboot;

import com.github.ryanlove.coordinator.annotations.CoordinatorInterface;
import com.github.ryanlove.coordinator.annotations.CoordinatorMethod;
import com.github.ryanlove.coordinator.configuration.MethodInfo;
import com.github.ryanlove.coordinator.core.CoordinatorBean;
import com.github.ryanlove.coordinator.core.CoordinatorRegister;
import com.github.ryanlove.coordinator.core.Identify;
import com.github.ryanlove.coordinator.core.IdentifyResolverStrategy;
import com.github.ryanlove.coordinator.reducer.Reducer;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author ryan
 */
public class CoordinatorAnnotationInterceptor implements MethodInterceptor {

    private GroupTagProperties groupTagProperties;
    private IdentifyResolverStrategy identifyResolverStrategy;
    private CoordinatorRegister coordinatorRegister;

   public CoordinatorAnnotationInterceptor(GroupTagProperties groupTagProperties,IdentifyResolverStrategy identifyResolverStrategy,CoordinatorRegister coordinatorRegister){
        this.coordinatorRegister = coordinatorRegister;
        this.identifyResolverStrategy = identifyResolverStrategy;
        this.groupTagProperties = groupTagProperties;
   }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        CoordinatorMethod coordinatorMethod = AnnotationUtils.findAnnotation(invocation.getMethod(), CoordinatorMethod.class);

        String executeMethodName = invocation.getMethod().getName();

        Object[] args = invocation.getArguments();

        try {//obj之前可以写目标方法执行前的逻辑
            if (coordinatorMethod == null) {
                return invocation.proceed();
            }
            Class<?> targetClass = (invocation.getThis() != null ? AopUtils.getTargetClass(invocation.getThis()) : null);

            if(targetClass == null) {
                invocation.proceed();
            }

            Type[] interfaces = targetClass.getInterfaces();
            if(interfaces == null) {
                return invocation.proceed();
            }
            Type bizInterface= findCoordinatorInterfaceType(interfaces);
            if(bizInterface == null) {
                return invocation.proceed();
            }
            Object targetDO = args[0];

            Identify identify = identifyResolverStrategy.findIdentifier(targetDO);
            String identifyBizTagName = identify.getBizTagName();
            String identifyBizTagGroupName = identify.getBizTagGroupName();

            //无任何标签执行默认bean处理
            if(StringUtils.isEmpty(identifyBizTagName) && StringUtils.isEmpty(identifyBizTagGroupName)) {
                return invocation.proceed();
            }

            //无冲突标签组，由对应单个标签实现bean处理
            if(StringUtils.isEmpty(identifyBizTagGroupName)) {
                Map<String, CoordinatorBean> implClass = coordinatorRegister.findByClassName(bizInterface.getTypeName());
                Object coordinatorBean =  getCglibProxyTargetObject(implClass.get(identifyBizTagName));
                return MethodUtils.invokeExactMethod(coordinatorBean, executeMethodName, args);
            }
            Optional<GroupTagProperties.BizTagGroupConfig> bizTagGroupConfig = groupTagProperties.getGroups().stream()
                    .filter(bizTagGroupConfig1 -> {
                        return bizTagGroupConfig1.getGroupName().equals(identifyBizTagGroupName);
                    }).findFirst();

            //未找到配置冲突项
            if (!bizTagGroupConfig.isPresent()) {
                return invocation.proceed();
            }

            Optional<GroupTagProperties.Conflict> matchConflict = bizTagGroupConfig.get().getConflictList().stream()
                    .filter(conflict -> conflict.getEntryMethod().equals(executeMethodName)).findFirst();

            if (!matchConflict.isPresent()) {
                return invocation.proceed();
            }


            Map<String, CoordinatorBean> implClass = coordinatorRegister.findByClassName(matchConflict.get().getClassName());
            if (implClass == null) {
                return invocation.proceed();
            }


            Reducer reducer = coordinatorMethod.reducer().newInstance();
            List<MethodInfo> methodInfos = new ArrayList<>();
            for (String s : matchConflict.get().getRank()) {
                Object targetClazz = implClass.get(s);
                MethodInfo methodInfo = new MethodInfo();
                try {
                    List<? extends Class<?>> parameterTypes = Arrays.stream(args).map(Object::getClass).collect(Collectors.toList());
                    Class<?>[] parameterClazzes = parameterTypes.stream().toArray(Class<?>[]::new);
                    Method executeMethod = targetClazz.getClass().getDeclaredMethod(matchConflict.get().getEntryMethod(), parameterClazzes);
                    MethodUtils.getAccessibleMethod(targetClazz.getClass().getSuperclass(), matchConflict.get().getEntryMethod());
                    methodInfo.setTargetMethod(executeMethod);

                    methodInfo.setCoordinatorBean(getCglibProxyTargetObject(targetClazz));
                    methodInfos.add(methodInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            reducer.setMethodInfoList(methodInfos);
            return reducer.reduce(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw throwable;
        }
    }

    private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(true);
        Object dynamicAdvisedInterceptor = h.get(proxy);

        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);

        return ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
    }

    private Type findCoordinatorInterfaceType(Type[] interfaces){
        return Arrays.stream(interfaces).filter(type -> {
            Annotation[] coordinatorInterface = ((Class) type).getAnnotationsByType(CoordinatorInterface.class);
            return coordinatorInterface !=null;
        }).findFirst().orElse(null);
    }

}
