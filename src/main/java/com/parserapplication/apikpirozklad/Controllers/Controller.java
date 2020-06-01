package com.parserapplication.apikpirozklad.Controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.parserapplication.apikpirozklad.Entities.GroupInfo;
import com.parserapplication.apikpirozklad.Entities.JsonViews;
import com.parserapplication.apikpirozklad.Entities.LessonInfo;
import com.parserapplication.apikpirozklad.Exception.NotFoundException;
import com.parserapplication.apikpirozklad.Exception.ParserException;
import com.parserapplication.apikpirozklad.Parser.ParserKpiGroupLessons;
import com.parserapplication.apikpirozklad.Parser.ParserKpiGroupNameAndLinks;
import com.parserapplication.apikpirozklad.Repos.GroupRepository;
import com.parserapplication.apikpirozklad.Repos.LessonRepository;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
@RestController
@RequestMapping
/**
 * Controller object
 */
public class Controller {

    private final GroupRepository groupRepository;
    private final LessonRepository lessonRepository;

    /**
     * This method returns greeting message
     *
     * @return greeting message
     */
    @GetMapping
    public String getMainPage() {
        return "KPI-API-ROZKLAD";
    }

    /**
     * This method searches in DB id that matches group
     *
     * @param id id of group
     * @return info and lessons of one group
     * @throws ParserException throws exception if instance doesn't exist
     */
    @JsonView(JsonViews.extendJsonView.class)
    @GetMapping("/getGroupAndLessonsById/{id}")
    public Object getGroupAndLessonsById(@PathVariable int id) throws ParserException {
        Optional<GroupInfo> group = groupRepository.findByGroupId(id);
        if (group.isPresent()) {
            return group;
        } else {
            return new NotFoundException("Group not found");
        }
    }

    /**
     * This method searches in DB name that matches group
     *
     * @param nameOfGroup name of group
     * @return info and lessons of one group
     * @throws ParserException throws exception if instance doesn't exist
     */
    @JsonView(JsonViews.extendJsonView.class)
    @GetMapping("/getGroupAndLessonsByName/{nameOfGroup}")
    public Optional<GroupInfo> getGroupAndLessonsByName(@PathVariable String nameOfGroup) throws ParserException {
        Optional<GroupInfo> group = groupRepository.findByGroupName(nameOfGroup);
        if (group.isPresent()) {
            return group;
        } else {
            throw new NotFoundException("Group not found");
        }
    }

    /**
     * This method fetches all groups with urls from DB
     *
     * @return all groups with urls
     */
    @JsonView(JsonViews.defaultJsonView.class)
    @GetMapping("/getAllGroups")
    public List<GroupInfo> getAllGroups() {
        System.out.println("Starting parsing name groups and urls...");
        System.out.println("sucksex");
        return groupRepository.findAll();
    }

    /**
     * This method updates lesson info in DB
     *
     * @return String success
     * @throws IOException interrupted I/O operations
     */
    @GetMapping("/updateLessons")
    public String updateLessons() throws IOException {
        ParserKpiGroupLessons parser = new ParserKpiGroupLessons();
        System.out.println("Starting updating lessons...");
        List<GroupInfo> groups = getAllGroups();
        for (GroupInfo groupInfo : groups) {
            List<LessonInfo> lessons = parser.parseLessons(groupInfo.getGroupUrl());
            for (LessonInfo info : lessons) {
                info.setLessonOfGroupId(groupInfo);
                lessonRepository.save(info);
            }
            groupRepository.save(groupInfo);
        }
        System.out.println("sucksex");
        return "sucksex";
    }

    /**
     * This method updates group info in DB
     *
     * @return String success
     * @throws IOException interrupted I/O operations
     */
    @GetMapping("/updateGroups")
    public String updateGroups() throws IOException {
        System.out.println("Starting updating name groups and urls...");
        Map<String, String> groups = new ParserKpiGroupNameAndLinks().parseGroups();
        groups.forEach((group, url) -> groupRepository.save(new GroupInfo(group, url)));
        System.out.println("sucksex");
        return "sucksex";
    }

    /**
     * This method updates group and lesson info in DB
     *
     * @throws IOException interrupted I/O operations
     */
    @GetMapping("/updateDatabase")
    public void updateDatabase() throws IOException {
        updateGroups();
        updateLessons();
    }
}
