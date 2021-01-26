package com.parserapplication.apikpirozklad.Repos;

import com.parserapplication.apikpirozklad.Entities.GroupInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Group Repository
 */
@Repository
public interface GroupRepository extends JpaRepository<GroupInfo, Integer> {

    Optional<GroupInfo> findByGroupId(int id);

    Optional<GroupInfo> findByGroupName(String nameOfGroup);
    @Procedure(value = "parsegroups")
    void parseGroups();

    @Procedure(value ="parsesubjects" )
    void parseSubjects();

    @Procedure(value = "parselessons")
    void parseLessons();

}