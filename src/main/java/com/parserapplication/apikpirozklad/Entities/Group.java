package com.parserapplication.apikpirozklad.Entities;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "Gropus")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(JsonViews.defaultJsonView.class)
    private UUID Id;

    @JsonView(JsonViews.defaultJsonView.class)
    private String Group_Name;

    public Group(UUID id1, String group_name) {

        Id = id1;
        Group_Name = group_name;
    }

    public Group() {

    }
}
