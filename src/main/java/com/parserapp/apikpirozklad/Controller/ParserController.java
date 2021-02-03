package com.parserapp.apikpirozklad.Controller;

import com.parserapp.apikpirozklad.Entities.GroupInfo;
import com.parserapp.apikpirozklad.Entities.LessonInfo;
import com.parserapp.apikpirozklad.Parser.GroupLinksParser;
import com.parserapp.apikpirozklad.Parser.LessonsForGroupParser;
import com.parserapp.apikpirozklad.Repository.GroupRepository;
import com.parserapp.apikpirozklad.Repository.LessonRepository;
import javassist.NotFoundException;
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
public class ParserController {

    private final GroupRepository groupRepository;
    private final LessonRepository lessonRepository;

    @GetMapping("/updateGroups")
    public String updateGroups() throws IOException {
        System.out.println("Starting updating name groups and urls...!!!");
        Map<String, String> groups = new GroupLinksParser().parseGroupsWithLinks();
        groups.forEach((group, url) -> groupRepository.save(new GroupInfo(group, url)));
        return "Groups updating...";
    }

    //Запускать только после updateGroups, иначе нет смысла :(
    @GetMapping("/updateLessons")
    public String updateLessons() throws IOException {
        LessonsForGroupParser parser = new LessonsForGroupParser();
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
        return "Lessons parsing...";
    }

    @GetMapping("/getGroupAndLessonsById/{id}")
    public Object getGroupAndLessonsById(@PathVariable int id)  {
        Optional<GroupInfo> group = groupRepository.findByGroupId(id);
        if (group.isPresent()) {
            return group;
        } else {
            return new NotFoundException("Group not found");
        }
    }

    @GetMapping("/getAllGroups")
    public List<GroupInfo> getAllGroups() {
        System.out.println("Starting parsing name groups and urls...");
        return groupRepository.findAll();
    }

    @GetMapping("/updateDatabase")
    public void updateDatabase() {
        try {
            updateGroups();
            updateLessons();
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }
}
