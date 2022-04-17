# 简介

coordinator-spring-boot-starter 是一个基于springboot的快速集成的业务流程代码解耦的启动器。

其支持 **Jdk 1.8+,    SpringBoot  2.x.x**。

# 特性

1. 提供业务逻辑服务多场景拆分，各场景逻辑代码分离解耦的方案

2. 提供基于自定义业务标签和标签组的冲突配置的解决方案

3. 支持多个自定义业务身份识别器

4. 支持自定义Reducer策略实现，框架提供的规约策略:

   | 策略         | 说明                                                         |
   | ------------ | ------------------------------------------------------------ |
   | FirstNotNull | 按rank排序，第一个方法不为Null 即返回该方法返回值            |
   | MaxOfInt     | 按rank排序，所有方法取返回值最大                             |
   | Recursive    | 按rank排序，所有方法依次递归调用                             |
   | AllTrue      | 按rank排序依次调用，满足所有都为True即返回，例如验证数据有效性 |



# 约定

1. 本框架只做业务对象的标签识别与执行及多标签下的执行冲突的解决，不干涉具体的业务操作。
2. 所有拆分的逻辑服务接口需继承自`CoordinatorBean`接口
3. 逻辑服务接口使用 `@CoordinatorInterface` 注解，服务方法使用`@CoordinatorMethod` 并指定Reducer策略
4. 对于服务接口的实现类，需指定对应业务身份的标签 `@CoordinatorTags(tagName=**)`
4. 业务接口多实现需指定默认实现类并添加注解@Primary以便配合Spring自动装配
5. 配置文件必须完成对业务标签身份识别器的配置 `resolver`识别器，`targetModel` 识别器对应的目标对象


# 使用方法

1. 引入coordinator-core 和 coordinator-spring-boot-starter ，版本：`1.0.1-SNAPSHOT`
``` xml
<dependency>
    <groupId>com.github.ryanlove</groupId>
    <artifactId>coordinator-core</artifactId>
    <version>${version}</version>
</dependency>
<dependency>
    <groupId>com.github.ryanlove</groupId>
    <artifactId>coordinator-spring-boot-starter</artifactId>
    <version>${version}</version>
</dependency>
```
2. resources 增加coordinator/coordinator.yml配置文件，并配置标签识别器和业务实现的冲突优先级
``` yml
coordinator:
  identifyResolvers: #业务身份识别器配置
    - resolver: ItemIdentifyResolver #设置业务身份识别器
      targetModel: ItemDO #设置参与业务身份识别的对象

  groups:
    - groupName: "GROUP_ONE_PRICE" #标签组，当一个对象同时具有多个标签时则定义出标签组
      applyTags: ONE_PRICE,PRE_SALE #改标签组包含的业务标签
      conflictList: #配置解决冲突的目标类、入口方法、执行顺序
        - className: PostPublishProcedure #目标接口或类名称
          entryMethod: postPublishExtPoint #入口方法
          rank: "ONE_PRICE,PRE_SALE" #执行入口方法时的顺序
        - className: PrePublishProcedure
          entryMethod: validate
          rank: "PRE_SALE,ONE_PRICE"

    - groupName: "GROUP_COUPON"
      applyTags: COUPON,PRE_SALE
      conflictList:
        - className: com.github.ryanlove.coordinator.itempublish.procedure.PostPublishProcedure
          entryMethod: postPublishExtPoint
          rank: "PRE_SALE,COUPON"
        - className: com.github.ryanlove.coordinator.itempublish.procedure.PrePublishProcedure
          entryMethod: validate
          rank: "COUPON,PRE_SALE"

```

3. 定义业务实现接口 Demo
``` java
@CoordinatorInterface(desc = "发布前调用") //添加接口注解
public interface PrePublishProcedure extends CoordinatorBean { //继承CoordinatorBean

    @CoordinatorMethod(reducer = FirstTrue.class, desc = "验证商品数据") //添加方法注解并指定Reducer策略
    Boolean validate(ItemDO itemDO);
}
//编写一个接口的实现
@CoordinatorTags(tagName = "ONE_PRICE") //该实现是针对一口价商品
public class OnePricePrePublishProcedure implements PrePublishProcedure {

    @Override
    public Boolean validate(ItemDO itemDO) {
        System.out.println("PrePublishProcedure.validate 验证商品数据【一口价】 ");
        return true;
    }
}
```
根据定义的不同标签，编写基于此接口的其他实现（略）。。。
5. 编写业务身份识别器
``` java
@Component("ItemIdentifyResolver")
public class ItemIdentifyResolver implements IdentifyResolver {
    @Override
    public Identify identify(Object domain) {
        Identify identify = new Identify();
        ItemDO itemDO = (ItemDO) domain;

        if(ItemTypeEnum.ONE_PRICE.getKey().equals(itemDO.getType())) {
            identify.setBizTagName(BizTag.ONE_PRICE.getName());
        }

        if(ItemTypeEnum.ONE_PRICE.getKey().equals(itemDO.getType()) && itemDO.getPreSale()) {
            identify.setBizTagGroupName(BizTagGroup.GROUP_ONE_PRICE.getName());
        }

        if(ItemTypeEnum.COUPON.getKey().equals(itemDO.getType())) {
            identify.setBizTagName(BizTag.COUPON.getName());
        }
        if(ItemTypeEnum.COUPON.getKey().equals(itemDO.getType())
                && itemDO.getPreSale()) {
            identify.setBizTagGroupName(BizTagGroup.GROUP_COUPON.getName());
        }

        if(ItemTypeEnum.COUPON.getKey().equals(itemDO.getType())) {
            identify.setBizTagName(BizTag.COUPON.getName());
        }
        if(ItemTypeEnum.COUPON.getKey().equals(itemDO.getType())
                && itemDO.getPreSale()) {
            identify.setBizTagGroupName(BizTagGroup.GROUP_COUPON.getName());
        }

        return identify;
    }
```


4. 调用示例

``` java
 	@Autowired //基于spring自动装配
    PrePublishProcedure prePublishProcedure;

	//实机调用
	boolean validateResult = prePublishProcedure.validate(itemDO);
	
```

##  配置可视化

1. 添加配置
spring.coordinator.config-view-servlet.enabled=true
2. 请求地址
http://hostname/coordinator/resolver.html

