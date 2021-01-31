package com.parserapp.apikpirozklad.Repository;

import com.parserapp.apikpirozklad.Entities.LessonInfo;
import com.parserapp.apikpirozklad.Entities.GroupInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<LessonInfo, Integer> {
    Optional<List<LessonInfo>> findAllByLessonOfGroupId(Optional<GroupInfo> lessonOfGroupId);
}
