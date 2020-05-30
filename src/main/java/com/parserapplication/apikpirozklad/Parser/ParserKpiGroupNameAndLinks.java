package com.parserapplication.apikpirozklad.Parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.parserapplication.apikpirozklad.Utils.Unicode.unicodeEncode;
import static org.toilelibre.libe.curl.Curl.$;

public class ParserKpiGroupNameAndLinks {
    private final char[] uaAlphabet = {
            'а', 'б', 'в', 'г', 'д', 'е', 'є', 'ж', 'з', 'и', 'і',
            'ї', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т',
            'у', 'ф', 'ч', 'ц', 'ч', 'ш', 'щ', 'ю', 'я', 'ь'
    };

    private final char[] engAlphabet = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
            'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z'
    };

    private final String targetGroupUrl = "http://rozklad.kpi.ua/Schedules/ScheduleGroupSelection.aspx";
    private final String targetUrl = "http://rozklad.kpi.ua/Schedules/ScheduleGroupSelection.aspx/GetGroups";
    private final String groupUrlPostKey;

    public ParserKpiGroupNameAndLinks() throws IOException {
        groupUrlPostKey = initializeGroupUrlPostData();
    }

//    public static void main(String[] args) throws IOException {
//        ParserKpiGroupNameAndLinks parserKpiGroupNameAndLinks = new ParserKpiGroupNameAndLinks();
//        parserKpiGroupNameAndLinks.parseGroups();
//    }


    public Map<String, String> parseGroups() throws IOException {
       return getGroupNameAndLink();
    }

    public Map<String, String> getGroupNameAndLink() throws IOException {
        String[] groups = getNamesOfAllGroups();
//        System.out.println(Arrays.toString(groups));
        Map<String, String> groupNameAndLinkMap = new LinkedHashMap<>();
//        System.out.println(getResponse("БС-83"));
        for (String group : groups) {
//            System.out.println(group);
            Document doc = Jsoup.parse(getResponse(group.substring(1, group.length() - 1)));
            String partialLink = doc.select("a[href*=View]").first().attr("href");
            String linkGroup = "http://rozklad.kpi.ua/Schedules/" + partialLink.substring(partialLink.indexOf("View"));
            groupNameAndLinkMap.put(group.substring(1, group.length() - 1), linkGroup);
        }
//        System.out.println(groupNameAndLinkMap);
        return groupNameAndLinkMap;
    }


    //todo "Компьютерні науки"
    private String[] getNamesOfAllGroups() {
        StringBuilder contentString = new StringBuilder();
        for (char letter : uaAlphabet) {
            String content = getResponseWithJson(letter);
//            System.out.println(content);
//            Pattern pattern = Pattern.compile("^[А-ЩЬЮЯҐЄІЇа-щьюяґєії]{2}\\-[А-ЩЬЮЯҐЄІЇа-щьюяґєії0-9]{2,6}$");
//            Matcher matcher = pattern.matcher(content);
            if (content.contains("null") || content.contains("науки")) {
                continue;
            }
            contentString.append(content, 6, content.length() - 2);
            contentString.append(",");
        }
        return contentString.toString().split(",");
    }

    // todo crush
    private String initializeGroupUrlPostData() throws IOException {
//        Document doc = Jsoup.connect(targetGroupUrl).get();
//        Element groupUrlValue = doc.getElementById("__EVENTVALIDATION");
//        String groupUrlPostKey = groupUrlValue.attr("value");
//        try {
//            groupUrlPostKey = URLEncoder.encode(groupUrlPostKey, StandardCharsets.UTF_8.toString());
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        return "%2FwEdAAEAAAD%2F%2F%2F%2F%2FAQAAAAAAAAAPAQAAAAUAAAAIsA3rWl3AM%2B6E94I5Tu9cRJoVjv0LAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHfLZVQO6kVoZVPGurJN4JJIAuaU\n";
    }

    private String getResponseWithJson(char letter) {
        return $("-d '{'prefixText': '" + unicodeEncode(letter) + "' , 'count': 10 }' -H 'Content-Type: application/json; charset=utf-8' " + targetUrl);
    }


    public String[] processGroupString(String groupFullName) {
        groupFullName = groupFullName.toLowerCase();
        String groupPrefix = groupFullName.substring(0, 2);
        String groupDegree = "bachelor";
        if (groupFullName.substring(2, groupFullName.length() - 1).indexOf('м') != -1) {
            groupDegree = "master";
        }

        if (groupFullName.substring(2, groupFullName.length() - 1).indexOf('с') != -1) {
            groupDegree = "specialist";
        }

        String groupType = "daily";
        if (groupFullName.substring(2, groupFullName.length() - 1).indexOf('з') != -1) {
            groupType = "extramural";
        }

        return new String[]{
                "\"group_full_name\" : \"" + groupFullName + "\"",
                "\"group_prefix\" : \"" + groupPrefix + "\"",
                "\"group_type\" : \"" + groupType + "\"",
                "\"group_degree\" : \"" + groupDegree + "\"",
        };
    }

    private String getResponse(String groupFullName) throws IOException {
        String groupUrlPostData = "__EVENTTARGET=&__EVENTARGUMENT=&ctl00%24MainContent%24ctl00%24txtboxGroup=" + groupFullName.toUpperCase() + "&ctl00%24MainContent%24ctl00%24btnShowSchedule=%D0%A0%D0%BE%D0%B7%D0%BA%D0%BB%D0%B0%D0%B4+%D0%B7%D0%B0%D0%BD%D1%8F%D1%82%D1%8C&__EVENTVALIDATION=" + groupUrlPostKey;
//        System.out.println(groupUrlPostData);
        return $("-d \"" + groupUrlPostData + "\" -H \"Content-Type: application/x-www-form-urlencoded\" \"" + targetGroupUrl + "\"");
    }
}

