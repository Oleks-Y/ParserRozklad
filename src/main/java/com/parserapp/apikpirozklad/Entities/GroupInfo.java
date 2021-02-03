package com.parserapp.apikpirozklad.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * GroupInfo object
 */
@Data
@Entity
public class GroupInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int groupId;

    private String groupName;

    private String groupUrl;

    @OneToMany(mappedBy = "lessonOfGroupId")
    private List<LessonInfo> lessons = new ArrayList<>();

    /**
     * Creates a new entity of GroupInfo with params
     *
     * @param group unique username of the group
     * @param url   unique url of the group
     */
    public GroupInfo(String group, String url) {
        this.groupName = group;
        this.groupUrl = url;
    }

    /**
     * Creates a new entity GroupInfo without params
     */
    public GroupInfo() {
    }
}
