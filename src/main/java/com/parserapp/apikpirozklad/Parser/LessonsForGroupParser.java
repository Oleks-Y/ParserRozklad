package com.parserapp.apikpirozklad.Parser;

import com.parserapp.apikpirozklad.Entities.LessonInfo;
import com.parserapp.apikpirozklad.Exceptions.CellParseException;
import lombok.val;
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
            connection.header("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
//            connection.requestBody("ctl00_ToolkitScriptManager_HiddenField=&__VIEWSTATE=/wEMDAwQAgAADgEMBQAMEAIAAA4BDAUDDBACAAAOAgwFBwwQAgwPAwEEVGV4dAEb0KDQvtC30LrQu9Cw0LQg0LfQsNC90Y/RgtGMAQhDc3NDbGFzcwEPYnRuIGJ0bi1wcmltYXJ5AQRfIVNCBQIAAAAMBQ0MEAIAAA4DDAUDDBACDA8BAgAAASrQoNC+0LfQutC70LDQtCDQt9Cw0L3Rj9GC0Ywg0LTQu9GPINCG0KItOTIAAAAMBQcMEAIAAA4GDAUBDBACAAAOAQwFAQwQAgwPAgICAAENZGF5X2JhY2tsaWdodAIEAAUCAAAADAUCDBACAAAOAgwFAQwQAgwPAgICAAIGAAIEAAUCAAAADAUCDBACDA8CAgIAAQxjbG9zZXN0X3BhaXICBAAFAgAAAAwFAwwQAgAADgEMBQEMEAIMDwICAgACBgACBAAFAgAAAAwFBAwQAgAADgEMBQEMEAIMDwICAgACBgACBAAFAgAAAAwFBQwQAgAADgEMBQEMEAIMDwICAgACBgACBAAFAgAAAAwFBgwQAgAADgEMBQEMEAIMDwICAgACBgACBAAFAgAAAAwFDQwQAgwMAA8BAQVzdHlsZQEMZmxvYXQ6cmlnaHQ7DAkQAhAFAAEb0J/QtdGA0YjQuNC5INGB0LXQvNC10YHRgtGAAQExCAgQBQABG9CU0YDRg9Cz0LjQuSDRgdC10LzQtdGB0YLRgAEBMgkIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEl0rp2/iW0tBBTpgxBo9JP2c1sZ&__EVENTTARGET=ctl00$MainContent$ddlSemesterType&__EVENTARGUMENT=&ctl00$MainContent$ddlSemesterType=2&__EVENTVALIDATION=/wEdAAEAAAD/////AQAAAAAAAAAPAQAAAAYAAAAIsA3rWl3AM+6E94I5ke7WZqDu1maj7tZmCwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAANqZqakPTbOP2+koNozn1gOvqUEW");
            connection.data("ctl00_ToolkitScriptManager_HiddenField","");
            connection.data("__VIEWSTATE","/wEMDAwQAgAADgEMBQAMEAIAAA4BDAUDDBACAAAOAgwFBwwQAgwPAwEEVGV4dAEb0KDQvtC30LrQu9Cw0LQg0LfQsNC90Y/RgtGMAQhDc3NDbGFzcwEPYnRuIGJ0bi1wcmltYXJ5AQRfIVNCBQIAAAAMBQ0MEAIAAA4DDAUDDBACDA8BAgAAASrQoNC+0LfQutC70LDQtCDQt9Cw0L3Rj9GC0Ywg0LTQu9GPINCG0KItOTIAAAAMBQcMEAIAAA4GDAUBDBACAAAOAQwFAQwQAgwPAgICAAENZGF5X2JhY2tsaWdodAIEAAUCAAAADAUCDBACAAAOAgwFAQwQAgwPAgICAAIGAAIEAAUCAAAADAUCDBACDA8CAgIAAQxjbG9zZXN0X3BhaXICBAAFAgAAAAwFAwwQAgAADgEMBQEMEAIMDwICAgACBgACBAAFAgAAAAwFBAwQAgAADgEMBQEMEAIMDwICAgACBgACBAAFAgAAAAwFBQwQAgAADgEMBQEMEAIMDwICAgACBgACBAAFAgAAAAwFBgwQAgAADgEMBQEMEAIMDwICAgACBgACBAAFAgAAAAwFDQwQAgwMAA8BAQVzdHlsZQEMZmxvYXQ6cmlnaHQ7DAkQAhAFAAEb0J/QtdGA0YjQuNC5INGB0LXQvNC10YHRgtGAAQExCAgQBQABG9CU0YDRg9Cz0LjQuSDRgdC10LzQtdGB0YLRgAEBMgkIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEl0rp2/iW0tBBTpgxBo9JP2c1sZ");
            connection.data("__EVENTTARGET","ctl00$MainContent$ddlSemesterType");
            connection.data("__EVENTARGUMENT","");
            connection.data("ctl00$MainContent$ddlSemesterType","2");
            connection.data("__EVENTVALIDATION","/wEdAAEAAAD/////AQAAAAAAAAAPAQAAAAYAAAAIsA3rWl3AM+6E94I5ke7WZqDu1maj7tZmCwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAANqZqakPTbOP2+koNozn1gOvqUEW");
            connection.timeout(50000)
                    .ignoreHttpErrors(true);
            connection.header("Cookie", "kpiGroupScheduleCookie=7,130,102,96,174,39,48,237,5,70,251,22,145,57,201,201,149,184,155,160,183,217,235,246,41,79,66,174,228,27,222,218,30,26,140,118,74,66,171,60,164,170,132,175,151,34,173,27,27,115,250,159,37,91,64,162,47,250,3,229,165,118,191,39");
            connection.method(Connection.Method.POST);
        } catch (Exception e) {
            e.getMessage();
        }
        return connection.post().outerHtml();
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
                    // here split up rows and
//                    ArrayList<String> lessonInfo = parseLessonFromString(contentOfRow);
                    ArrayList<LessonInfo> lessonInfoList= parseLessonFromString(contentOfRow);
                    for (LessonInfo row:
                            lessonInfoList) {
                        row.setDayNumber(String.valueOf(j));
                        row.setNameDay(daysOfWeek[j - 1]);
                        row.setLessonNumber(String.valueOf(i));
                        row.setNumberWeek(String.valueOf(lessonWeek));
                        row.setTimeStartLesson(timeOfLesson[i - 1][0]);
                        row.setTimeEndLesson(timeOfLesson[i - 1][1]);
                        table.add(row);
                    }
                }

            }
        }
        return table;
    }

    private ArrayList<LessonInfo> parseLessonFromString(String contentOfRow) {

        ArrayList<String> lessonCellInfo = new ArrayList<>(Arrays.asList(contentOfRow.split("@")));
        // first element- names
        // second element - teachers
        String roomsString = "";
        for (String row :
                lessonCellInfo) {
            if(row.contains("Лек") || row.contains("Лаб")||row.contains("Прак")){
                roomsString = row;
            }
        }
        List<String> lessonsNames = Arrays.asList(lessonCellInfo.get(0).split(",").clone());
        List<String> teachers = new ArrayList<String>();
        if(lessonCellInfo.size()>1){
            teachers = Arrays.asList(lessonCellInfo.get(1).split(","));
        }
        List<String> roomsWithRoomTypes;
        List<String> roomTypes = new ArrayList<String>();
        if(!roomsString.equals("")){
            roomsWithRoomTypes = Arrays.asList(roomsString.split(","));
            try {
                for (String roomString :
                        roomsWithRoomTypes) {
                    String[] roomStringSplited = roomString.split("-[0-9][0-9]");
                    String roomType = roomStringSplited[1];
                    roomTypes.add(roomType);
                }
            }catch (Exception e){
                System.out.println("Paring of rooms failed for lessons "+ contentOfRow);
                System.out.println(e);
            }
        }
        ArrayList<LessonInfo> lessonInfos = new ArrayList<LessonInfo>();
        if(lessonsNames.size()!= teachers.size()){
            for (String lessonsName : lessonsNames) {
                LessonInfo lessonInfo = new LessonInfo();
                lessonInfo.setLessonName(lessonsName);
                // here if this statement true, there can be wrong list of teachers
//                lessonInfo.setTeacherName(teachers[i]);
                lessonInfos.add(lessonInfo);
            }
            return lessonInfos;
        }
        for(int i=0;i<lessonsNames.size(); i++){
            LessonInfo lessonInfo = new LessonInfo();
            lessonInfo.setLessonName(lessonsNames.get(i));
            lessonInfo.setTeacherName(teachers.get(i));
            if(roomTypes.size()==lessonsNames.size()){
                lessonInfo.setRoomType(roomTypes.get(i));
            }
            lessonInfos.add(lessonInfo);
        }
        return lessonInfos;
    }

//    private ArrayList<LessonInfo> getLessonsFrom

}
