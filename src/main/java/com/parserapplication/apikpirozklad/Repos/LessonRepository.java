package com.parserapplication.apikpirozklad.Repos;

import com.parserapplication.apikpirozklad.Entities.GroupInfo;
import com.parserapplication.apikpirozklad.Entities.LessonInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Lesson Repository
 */
@Repository
public interface LessonRepository extends JpaRepository<LessonInfo, Integer> {
    Optional<List<LessonInfo>> findAllByLessonOfGroupId(Optional<GroupInfo> lessonOfGroupId);
}