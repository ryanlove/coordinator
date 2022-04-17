package com.github.ryanlove.coordinator.springboot;


import com.github.ryanlove.coordinator.spring.YmlConfigFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;


/**
 * @author ryan
 */
@Component
@PropertySource(value = "classpath:coordinator/coordinator.yml", factory = YmlConfigFactory.class)
@ConfigurationProperties(prefix = "coordinator")
public class GroupTagProperties implements Serializable {

    private static final long serialVersionUID = 7780595665516531448L;


    private List<resolverConfig> identifyResolvers;

    private List<BizTagGroupConfig> groups;

    public List<BizTagGroupConfig> getGroups() {
        return groups;
    }

    public void setGroups(List<BizTagGroupConfig> groups) {
        this.groups = groups;
    }

    public List<resolverConfig> getIdentifyResolvers() {
        return identifyResolvers;
    }

    public void setIdentifyResolvers(List<resolverConfig> identifyResolvers) {
        this.identifyResolvers = identifyResolvers;
    }

    public static class BizTagGroupConfig {

        private String groupName;

        private List<String> applyTags;

        private List<Conflict> conflictList;

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public List<String> getApplyTags() {
            return applyTags;
        }

        public void setApplyTags(List<String> applyTags) {
            this.applyTags = applyTags;
        }

        public List<Conflict> getConflictList() {
            return conflictList;
        }

        public void setConflictList(List<Conflict> conflictList) {
            this.conflictList = conflictList;
        }
    }



    public static class Conflict{
        private String className;
        private String entryMethod;
        private List<String> rank;

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }


        public String getEntryMethod() {
            return entryMethod;
        }

        public void setEntryMethod(String entryMethod) {
            this.entryMethod = entryMethod;
        }

        public List<String> getRank() {
            return rank;
        }

        public void setRank(List<String> rank) {
            this.rank = rank;
        }
    }

    public static class resolverConfig {
        private String resolver;
        private String targetModel;


        public String getResolver() {
            return resolver;
        }

        public void setResolver(String resolver) {
            this.resolver = resolver;
        }

        public String getTargetModel() {
            return targetModel;
        }

        public void setTargetModel(String targetModel) {
            this.targetModel = targetModel;
        }
    }
}
