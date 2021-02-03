package com.parserapp.apikpirozklad.Parser;

import com.parserapp.apikpirozklad.Entities.LessonInfo;
import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LessonsForGroupParser {
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
            {"20:20", "21:35"}
    };

    public List<LessonInfo> parseLessons(String url) throws IOException {
        String htmlBody="";
        try {
            htmlBody = getResponse(url);
        }catch (Exception e){
            System.out.println("Connection timeout on lesson!");
        }
        String[] scheduleTables = getScheduleTables(htmlBody);
        List<LessonInfo> twoWeeks = new ArrayList<>();
        twoWeeks.addAll(convertHtmlTableToArray(scheduleTables[0], 1));
        twoWeeks.addAll(convertHtmlTableToArray(scheduleTables[1], 2));
       // twoWeeks.sort(new SortByDayOfWeek());
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
        return new String[]{String.valueOf(tables.first()).replaceAll("<?br>", "@"),
                String.valueOf(tables.last()).replaceAll("<?br>", "@")};
    }

    private List<LessonInfo> convertHtmlTableToArray(String htmlTable, int lessonWeek) {
        List<LessonInfo> table = new ArrayList<>();
        Document doc = Jsoup.parse(htmlTable);
        Elements lines = doc.getElementsByTag("tr");

        for (int i = 1; i < lines.size(); i++) {
            Elements rows = lines.get(i).getElementsByTag("td");

            for (int j = 1; j < rows.size(); j++) {
                String contentOfRow = rows.get(j).text();

                if (!contentOfRow.equals("")) {
                    LessonInfo row = new LessonInfo();
                    row.setLessonInfo(parseLessonFromString(contentOfRow));
                    row.setDayNumber(String.valueOf(j));
                    row.setNameDay(daysOfWeek[j - 1]);
                    row.setLessonNumber(String.valueOf(i));
                    row.setNumberWeek(String.valueOf(lessonWeek));
                    row.setTimeStartLesson(timeOfLesson[j - 1][0]);
                    row.setTimeEndLesson(timeOfLesson[j - 1][1]);
                    table.add(row);
                }
            }
        }
        return table;
    }

    private ArrayList<String> parseLessonFromString(String contentOfRow) {
        return new ArrayList<>(Arrays.asList(contentOfRow.split("@")));
    }

}
