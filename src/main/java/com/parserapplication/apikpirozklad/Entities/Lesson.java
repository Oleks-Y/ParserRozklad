package com.parserapplication.apikpirozklad.Entities;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(JsonViews.defaultJsonView.class)
    private UUID Id;


    @JsonView(JsonViews.defaultJsonView.class)
    private UUID SubjectId;

    @JsonView(JsonViews.defaultJsonView.class)
    private Integer Week;

    @JsonView(JsonViews.defaultJsonView.class)
    private Integer DayOfWeek;

    @JsonView(JsonViews.defaultJsonView.class)
    private String TimeStart;

    @JsonView(JsonViews.defaultJsonView.class)
    private String Type;

}
