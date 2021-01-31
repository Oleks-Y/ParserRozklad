package com.parserapp.apikpirozklad.Repository;

import com.parserapp.apikpirozklad.Entities.GroupInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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