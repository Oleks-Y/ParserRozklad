package com.parserapplication.apikpirozklad.Parser;


import com.parserapplication.apikpirozklad.Comparator.SortByDayOfWeek;
import com.parserapplication.apikpirozklad.Entities.LessonInfo;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParserKpiGroupLessons {

    private static final String[] daysOfWeek = new String[]{
            "Понеділок",
            "Вівторок",
            "Середа",
            "Четвер",
            "П’ятниця",
            "Субота"
    };

    private static final String[][] timeOfLesson = new String[][]{
            {"08:30", "10:05"},
            {"10:25", "12:00"},
            {"12:20", "13:55"},
            {"14:15", "15:50"},
            {"16:10", "17:45"},
            {"18:05", "19:35"},
            {"18:05", "19:35"},
            {"20:20", "21:35"},
    };

//    public static void main(String[] args) throws IOException {
//        String url = "http://rozklad.kpi.ua/Schedules/ViewSchedule.aspx?g=da7cc0da-31bb-4729-a727-14fe4b9a9851";
//        parseLessons(url);
//    }

    public List<LessonInfo> parseLessons(String url) throws IOException {
        String htmlBody = getResponse(url);
        String[] scheduleTables = getScheduleTables(htmlBody);
        List<LessonInfo> twoWeeks = new ArrayList<>();
        twoWeeks.addAll(convertHtmlTableToArray(scheduleTables[0], 1));
        twoWeeks.addAll(convertHtmlTableToArray(scheduleTables[1], 2));
//        ArrayList<ArrayList<String>> firstWeek = convertHtmlTableToArray(scheduleTables[0], 1);
        twoWeeks.sort(new SortByDayOfWeek());
////        for (ArrayList<String> row: firstWeek) {
////            System.out.println(row);
////        }
////        System.out.println("______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
//        ArrayList<ArrayList<String>> secondWeek = convertHtmlTableToArray(scheduleTables[1], 2);
//        secondWeek.sort(new SortByDayOfWeek());
////        for (ArrayList<String> row: secondWeek) {
//////            System.out.println(row);
//////        }
//        firstWeek.addAll(secondWeek);
//        List<LessonInfo> info = new ArrayList<>();
//        for (ArrayList<String> row : firstWeek) {
//            LessonInfo currentLesson =  new LessonInfo(row);
//            info.add(currentLesson.get);
//            System.out.println(row);
//        }
//        System.out.println(info);
//        return ;
//        for (LessonInfo hui : twoWeeks) {
//            System.out.println(hui);
//        }
        return twoWeeks;
    }

    private String getResponse(String url) throws IOException {
        Connection connection = null;
        try {
            connection = Jsoup.connect(url);
            connection.timeout(50000).ignoreHttpErrors(true);
            connection.get().outerHtml();
        } catch (ConnectException e) {
            e.getMessage();
        }
        return connection.get().outerHtml();
    }

    private String[] getScheduleTables(String htmlBody) {
        Document doc = Jsoup.parse(htmlBody);
        Elements tables = doc.select("table");
        return new String[]{String.valueOf(tables.first()).replaceAll("<?br>", "@"), String.valueOf(tables.last()).replaceAll("<?br>", "@")};
    }

    private List<LessonInfo> convertHtmlTableToArray(String htmlTable, int lessonWeek) {
//        ArrayList<ArrayList<String>> tableData = new ArrayList<>();
        List<LessonInfo> table = new ArrayList<>();
        Document doc = Jsoup.parse(htmlTable);
        Elements lines = doc.getElementsByTag("tr");
        for (int i = 1; i < lines.size(); i++) {
//            System.out.println(lines.size());
            Elements rows = lines.get(i).getElementsByTag("td");
            for (int j = 1; j < rows.size(); j++) {
//                System.out.println(rows.size());
                String contentOfRow = rows.get(j).text();
                if (!contentOfRow.equals("")) {
                    LessonInfo row = new LessonInfo();

//                    if (StringUtils.isNumeric(parseLessonFromString(contentOfRow).get(0))) {
//                        continue;
//                    }
                    row.setLessonInfo(parseLessonFromString(contentOfRow));
                    row.setDayNumber(String.valueOf(j));
                    row.setNameDay(daysOfWeek[j - 1]);
                    row.setLessonNumber(String.valueOf(i));
                    row.setNumberWeek(String.valueOf(lessonWeek));
                    row.setTimeStartLesson(timeOfLesson[j - 1][0]);
                    row.setTimeEndLesson(timeOfLesson[j - 1][1]);
                    table.add(row);
//                    ArrayList<String> rowData = new ArrayList<>();
//                    rowData.add(String.valueOf(j));             //dayNumber
//                    rowData.add(daysOfWeek[j - 1]);             //dayName
//                    rowData.add(String.valueOf(j));             //lessonNumber
//                    rowData.add(String.valueOf(lessonWeek));    //lessonWeek
//                    rowData.add(timeOfLesson[i - 1][0]);        //timeStart
//                    rowData.add(timeOfLesson[i - 1][1]);        //timeEnd
//                    rowData.addAll(parseLessonFromString(contentOfRow));
//                    tableData.add(rowData);
                }
            }
        }
        return table;
    }


    private ArrayList<String> parseLessonFromString(String contentOfRow) {
        return new ArrayList<>(Arrays.asList(contentOfRow.split("@")));
        //"lesson_type"
        //"teacher_name"
        //"room_name"
        //"room_type"
    }
}
