package com.parserapplication.apikpirozklad.Comparator;

import com.parserapplication.apikpirozklad.Entities.LessonInfo;

import java.util.Comparator;

public class SortByDayOfWeek implements Comparator<LessonInfo> {

    @Override
    public int compare(LessonInfo o1, LessonInfo o2) {
        return o1.getDayNumber().compareTo(o2.getDayNumber());
    }
}
