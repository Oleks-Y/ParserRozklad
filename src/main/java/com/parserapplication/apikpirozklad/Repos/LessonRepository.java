package com.parserapplication.apikpirozklad.Repos;

import com.parserapplication.apikpirozklad.Entities.GroupInfo;
import com.parserapplication.apikpirozklad.Entities.LessonInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface LessonRepository extends JpaRepository<LessonInfo, Integer> {
//    Optional<LessonInfo> findByLessonID(int id);
    Optional<List<LessonInfo>> findAllByLessonOfGroupId(Optional<GroupInfo> lessonOfGroupId);
//    Optional<LessonInfo> findAllByLessonID(int id);
//    Optional<LessonInfo> findAll(int id);
}