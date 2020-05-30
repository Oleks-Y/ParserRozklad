package com.parserapplication.apikpirozklad.Repos;

import com.parserapplication.apikpirozklad.Entities.GroupInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface GroupRepository extends JpaRepository<GroupInfo, Integer> {

    Optional<GroupInfo> findByGroupId(int id);

    Optional<GroupInfo> findByGroupName(String nameOfGroup);
}