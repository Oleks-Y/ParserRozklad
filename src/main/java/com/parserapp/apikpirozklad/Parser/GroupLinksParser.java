package com.parserapp.apikpirozklad.Parser;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GroupLinksParser {
    private final char[] uaAlphabet = {'а', 'б', 'в', 'г', 'д', 'е', 'є', 'ж', 'з', 'и', 'і',
            'ї', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т',
            'у', 'ф', 'ч', 'ц', 'ч', 'ш', 'щ', 'ю', 'я', 'ь'};

    private final char[] engAlphabet = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
            'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z'
    };

    private final String targetGroupUrl = "http://rozklad.kpi.ua/Schedules/ScheduleGroupSelection.aspx";
    private final String targetUrl = "http://rozklad.kpi.ua/Schedules/ScheduleGroupSelection.aspx/GetGroups";
    private final String groupUrlPostKey;
    private final HttpClient httpClient;

    public GroupLinksParser() throws IOException{
        this.httpClient = java.net.http.HttpClient.newHttpClient();
        groupUrlPostKey = initializeGroupUrlPostData();

    }

    public Map<String, String> parseGroupsWithLinks() {
        String[] groups = getNamesOfAllGroups();
        Map<String, String> groupNameAndLinkMap = new LinkedHashMap<>();
        List<String> groupsFailed = new ArrayList<String>();
        for (String group : groups) {
            String response = "";
            try {
                response = getGroups(group.substring(1, group.length() - 1));
            } catch (Exception e) {
                System.out.println("Connection timeout on group : " + group);
                groupsFailed.add(group);
                continue;
            }
            Document doc = Jsoup.parse(response);
            String partialLink = "";
            String linkGroup = "";
            if (doc == null) {
                partialLink = null;
                linkGroup = null;
            }
            if(doc.select("a[href*=View]").first()==null){
                // it`s error handling or something like that
                groupsFailed.add(group);
                continue;
            }
            else {
                partialLink = doc.select("a[href*=View]").first().attr("href");
                linkGroup = "http://rozklad.kpi.ua/Schedules/" + partialLink.substring(partialLink.indexOf("View"));
            }
            groupNameAndLinkMap.put(group.substring(1, group.length() - 1), linkGroup);
        }

        System.out.println("Parsing failed for " + groupsFailed.size() + " groups of "+groupNameAndLinkMap.size()+" groups ");
        return groupNameAndLinkMap;
    }

    /**
     * Method, that parse all groups from autoSugggest from rozklad.kpi.ua select group form
     *
     * @return
     */
    private String[] getNamesOfAllGroups() {
        StringBuilder contentString = new StringBuilder();
        for (char letter : uaAlphabet) {
            String allGroupsByLetter = "";
            try {
                allGroupsByLetter = getGroupsByLetter(letter);
            } catch (Exception e) {
                System.out.println("Error while parsing letter " + letter);
                continue;
            }
            contentString.append(allGroupsByLetter, 6, allGroupsByLetter.length() - 2);
            contentString.append(",");
        }

        return contentString.toString().split(",");
    }

    /**
     * Method, that fills one character in rozklad.kpi.ua group selection field,
     * than site autosuggest with all groups, that begins by this letter
     */
    private String getGroupsByLetter(char letter) throws ConnectException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(targetUrl))
                .header("Content-Type", "application/json; charset=utf-8 ")
                .POST(HttpRequest.BodyPublishers.ofString("{'prefixText':'" + letter + "' , 'count': 10}"))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    /**
     * This method does post request and gets HTML body of group
     *
     * @param groupFullName full name of group
     * @return String of HTML body of group
     * @throws IOException interrupted I/O operations
     */
    private String getGroups(String groupFullName) throws IOException, InterruptedException{
        String groupUrlPostData = "__EVENTTARGET=&__EVENTARGUMENT=&ctl00%24MainContent%24ctl00%24txtboxGroup=" + groupFullName.toUpperCase() +
                "&ctl00%24MainContent%24ctl00%24btnShowSchedule=%D0%A0%D0%BE%D0%B7%D0%BA%D0%BB%D0%B0%D0%B4+%D0%B7%D0%B0%D0%BD%D1%8F%D1%82%D1%8C&__EVENTVALIDATION="
                + groupUrlPostKey;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(targetGroupUrl))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(groupUrlPostData))
                .timeout(Duration.ofSeconds(50))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();

    }
    // хз шо це, чесно
    private String initializeGroupUrlPostData() throws IOException {
        Document doc = Jsoup.connect(targetGroupUrl).get();
        Element groupUrlValue = doc.getElementById("__EVENTVALIDATION");
        String groupUrlPostKey = groupUrlValue.attr("value");
        try {
            groupUrlPostKey = URLEncoder.encode(groupUrlPostKey, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "%2FwEdAAEAAAD%2F%2F%2F%2F%2FAQAAAAAAAAAPAQAAAAUAAAAIsA3rWl3AM%2B6E94I5Tu9cRJoVjv0LAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHfLZVQO6kVoZVPGurJN4JJIAuaU\n";
    }
}
