package com.parserapplication.apikpirozklad.Repos;

import com.parserapplication.apikpirozklad.Entities.GroupInfo;
import com.parserapplication.apikpirozklad.Entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LessonUpdatedRepository extends JpaRepository<Lesson, UUID> {
}
