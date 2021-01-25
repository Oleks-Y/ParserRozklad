package com.parserapplication.apikpirozklad.Repos;

import com.parserapplication.apikpirozklad.Entities.Group;
import com.parserapplication.apikpirozklad.Entities.GroupInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GroupUpdatedRepository extends JpaRepository<Group, UUID> {
    void findGroupByGroup_Name(String Name);
}
