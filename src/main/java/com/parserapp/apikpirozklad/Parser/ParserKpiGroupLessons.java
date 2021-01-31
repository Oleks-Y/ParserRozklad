package com.parserapp.apikpirozklad.Parser;

import com.parserapp.apikpirozklad.Entities.LessonInfo;
import com.parserapp.apikpirozklad.Comparator.SortByDayOfWeek;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ParserKpiGroupLessons object
 */
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

    /**
     * This method parses two weeks schedule of the group
     *
     * @param url url of group
     * @return List of LessonInfo two weeks schedule
     * @throws IOException interrupted I/O operations
     */
    public List<LessonInfo> parseLessons(String url) throws IOException {
        String htmlBody = getResponse(url);
        String[] scheduleTables = getScheduleTables(htmlBody);
        List<LessonInfo> twoWeeks = new ArrayList<>();
        twoWeeks.addAll(convertHtmlTableToArray(scheduleTables[0], 1));
        twoWeeks.addAll(convertHtmlTableToArray(scheduleTables[1], 2));
        twoWeeks.sort(new SortByDayOfWeek());
        return twoWeeks;
    }

    /**
     * This method gets HTML response of group url
     *
     * @param url url of group
     * @return HTML response
     * @throws IOException interrupted I/O operations
     */
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

    /**
     * This method gets HTML body of group lessons
     *
     * @param htmlBody - HTML body
     * @return String array of two weeks schedule
     */
    private String[] getScheduleTables(String htmlBody) {
        Document doc = Jsoup.parse(htmlBody);
        Elements tables = doc.select("table");
        return new String[]{String.valueOf(tables.first()).replaceAll("<?br>", "@"), String.valueOf(tables.last()).replaceAll("<?br>", "@")};
    }

    /**
     * This method gets HTML body and number of week as params,
     * then sets info from HTML rows to array
     * @param htmlTable HTML body
     * @param lessonWeek number of week
     * @return List of one week schedule
     */
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


    /**
     * This method gets row content as a param
     *
     * @param contentOfRow content row
     * @return splitted String of main info of lesson
     */
    private ArrayList<String> parseLessonFromString(String contentOfRow) {
        return new ArrayList<>(Arrays.asList(contentOfRow.split("@")));
    }
}

