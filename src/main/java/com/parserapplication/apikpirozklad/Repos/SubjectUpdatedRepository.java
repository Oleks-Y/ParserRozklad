package com.parserapplication.apikpirozklad.Repos;

import com.parserapplication.apikpirozklad.Entities.GroupInfo;
import com.parserapplication.apikpirozklad.Entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubjectUpdatedRepository extends JpaRepository<Subject, UUID> {
}
