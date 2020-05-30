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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping
public class Controller {

    private final GroupRepository groupRepository;
    private final LessonRepository lessonRepository;

    public Controller(GroupRepository groupRepository, LessonRepository lessonRepository) {
        this.groupRepository = groupRepository;
        this.lessonRepository = lessonRepository;
    }

    @GetMapping
    public String getMainPage() {
        return "KPI-API-ROZKLAD";
    }

    @JsonView(JsonViews.extendJsonView.class)
    @GetMapping("/findGroupAndLessonsById/{id}")
    public Object findByGroupId(@PathVariable int id) throws ParserException {
        Optional<GroupInfo> group = groupRepository.findByGroupId(id);
        if (group.isPresent()) {
            return group;
        } else {
            return new NotFoundException("Group not found");
        }
    }

    @JsonView(JsonViews.extendJsonView.class)
    @GetMapping("/findGroupAndLessonsByName/{nameOfGroup}")
    public Optional<GroupInfo> findGroupByName(@PathVariable String nameOfGroup, @PathVariable String id) throws ParserException, NotFoundException {
        Optional<GroupInfo> group = groupRepository.findByGroupName(nameOfGroup);
        if (group.isPresent()) {
            return group;
        } else {
            throw new NotFoundException("Group not found");
        }
    }

    @JsonView(JsonViews.extendJsonView.class)
    @GetMapping("/getLessonsByGroupId/{id}")
    public Optional<List<LessonInfo>> findLessonsByGroupId(@PathVariable int id) {
        Optional<List<LessonInfo>> lessons = lessonRepository.findAllByLessonOfGroupId(groupRepository.findById(id));
        if (lessons.isPresent()) {
            return lessons;
        } else {
            throw new NotFoundException("Lesson not found");
        }
    }

    @JsonView(JsonViews.extendJsonView.class)
    @GetMapping("/getLessonsByGroupName/{name}")
    public Optional<List<LessonInfo>> getLessonsByGroupName(@PathVariable String name) {
        Optional<List<LessonInfo>> lessons = lessonRepository.findAllByLessonOfGroupId(groupRepository.findByGroupName(name));
        if (lessons.isPresent()) {
            return lessons;
        } else {
            throw new NotFoundException("Lesson not found");
        }
    }

    @JsonView(JsonViews.defaultJsonView.class)
    @GetMapping("/getAllGroups")
    public List<GroupInfo> getAllGroups() {
        System.out.println("Starting parsing name groups and urls...");
        System.out.println("sucksex");
        return groupRepository.findAll();
    }

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

    @GetMapping("/updateGroups")
    public String updateGroups() throws IOException {
        System.out.println("Starting updating name groups and urls...");
        Map<String, String> groups = new ParserKpiGroupNameAndLinks().parseGroups();
        groups.forEach((group, url) -> groupRepository.save(new GroupInfo(group, url)));
        System.out.println("sucksex");
        return "sucksex";
    }

    @GetMapping("/updateDatabase")
    public void updateDatabase() throws IOException {
        updateGroups();
        updateLessons();
    }
}
