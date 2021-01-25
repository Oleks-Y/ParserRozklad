package com.parserapplication.apikpirozklad.Entities;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "Subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID Id;

    @JsonView(JsonViews.defaultJsonView.class)
    private UUID Group_Id;


    @JsonView(JsonViews.defaultJsonView.class)
    private String  Name;

    @JsonView(JsonViews.defaultJsonView.class)
    private String Teachers ;

    @JsonView(JsonViews.defaultJsonView.class)
    private String LessonsZoom;

    @JsonView(JsonViews.defaultJsonView.class)
    private String LabsZoom;

    @JsonView(JsonViews.defaultJsonView.class)
    private Boolean IsRequired;


    @JsonView(JsonViews.defaultJsonView.class)
    private String LessonsAccessCode;

    @JsonView(JsonViews.defaultJsonView.class)
    private String LabsAccessCode;

}
