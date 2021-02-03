//package com.parserapp.apikpirozklad.Parser;
//
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.ConnectException;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.toilelibre.libe.curl.Curl.$;
//
//
///**
// * ParserKpiGroupNameAndLinks object
// */
//public class ParserKpiGroupNameAndLinks {
//    private final char[] uaAlphabet = {'а', 'б', 'в', 'г', 'д', 'е', 'є', 'ж', 'з', 'и', 'і',
//            'ї', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т',
//            'у', 'ф', 'ч', 'ц', 'ч', 'ш', 'щ', 'ю', 'я', 'ь'};
//
//    private final char[] engAlphabet = {
//            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
//            'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
//            'w', 'x', 'y', 'z'
//    };
//
//    private final String targetGroupUrl = "http://rozklad.kpi.ua/Schedules/ScheduleGroupSelection.aspx";
//    private final String targetUrl = "http://rozklad.kpi.ua/Schedules/ScheduleGroupSelection.aspx/GetGroups";
//    private final String groupUrlPostKey;
//
//    /**
//     * Creates a new entity of ParserKpiGroupNameAndLinks
//     * initializing groupUrlPostKey
//     *
//     * @throws IOException interrupted I/O operations
//     */
//    public ParserKpiGroupNameAndLinks() throws IOException {
//        groupUrlPostKey = initializeGroupUrlPostData();
//    }
//
//    /**
//     * This method invokes parsing groups and links
//     *
//     * @return Map of group and String
//     * @throws IOException interrupted I/O operations
//     */
//    public Map<String, String> parseGroups() throws IOException {
//        return getGroupNameAndLink();
//    }
//
//    /**
//     * This method parsers all groups and all of them links
//     *
//     * @return Map of group and String
//     * @throws IOException interrupted I/O operations
//     */
//    public Map<String, String> getGroupNameAndLink() throws IOException {
//        String[] groups = getNamesOfAllGroups();
//        Map<String, String> groupNameAndLinkMap = new LinkedHashMap<>();
//        List<String> groupsFailed = new ArrayList<String>();
//        for (String group : groups) {
//            String response = "";
//            try {
//                response = getResponse(group.substring(1, group.length() - 1));
//            } catch (Exception e){
//                System.out.println("Connection timeout on group : " + group);
//                groupsFailed.add(group);
//                continue;
//            }
//            Document doc = Jsoup.parse(response);
//            String partialLink = "";
//            String linkGroup = "";
//            if(doc==null){
//                partialLink = null;
//                linkGroup = null;
//            }else {
//                partialLink = doc.select("a[href*=View]").first().attr("href");
//                linkGroup = "http://rozklad.kpi.ua/Schedules/" + partialLink.substring(partialLink.indexOf("View"));
//            }
//            groupNameAndLinkMap.put(group.substring(1, group.length() - 1), linkGroup);
//        }
//
//        float failedPercent = groupsFailed.size() /groupNameAndLinkMap.size();
//        System.out.println("Parsing failed for " + failedPercent + " % groups");
//        return groupNameAndLinkMap;
//    }
//
//    /**
//     * This method parsers all groups names
//     *
//     * @return String array with all group names
//     */
//    private String[] getNamesOfAllGroups() {
//        StringBuilder contentString = new StringBuilder();
//        for (char letter : uaAlphabet) {
//            String content = "";
//            try {
//                content = getResponseWithJson(letter);
//            }catch (java.net.ConnectException e ){
//                System.out.println("Failed for letter "+ letter);
//                continue;
//            }
//            if (content.contains("null") || content.contains("науки")) {
//                continue;
//            }
//            contentString.append(content, 6, content.length() - 2);
//            contentString.append(",");
//        }
//        return contentString.toString().split(",");
//    }
//
//
//    /**
//     * This method parsers post key of search form
//     *
//     * @return String post key
//     * @throws IOException interrupted I/O operations
//     */
//    // todo crush
//    private String initializeGroupUrlPostData() throws IOException {
//        Document doc = Jsoup.connect(targetGroupUrl).get();
//        Element groupUrlValue = doc.getElementById("__EVENTVALIDATION");
//        String groupUrlPostKey = groupUrlValue.attr("value");
//        try {
//            groupUrlPostKey = URLEncoder.encode(groupUrlPostKey, StandardCharsets.UTF_8.toString());
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return "%2FwEdAAEAAAD%2F%2F%2F%2F%2FAQAAAAAAAAAPAQAAAAUAAAAIsA3rWl3AM%2B6E94I5Tu9cRJoVjv0LAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHfLZVQO6kVoZVPGurJN4JJIAuaU\n";
//    }
//
//    /**
//     * This method does post request and gets string of groups
//     *
//     * @return String of groups found by letter
//     */
//    private String getResponseWithJson(char letter) throws ConnectException {
//        //  String result = unicodeEncode('і');
//        //   String line = $("-d '{'prefixText': '" + unicodeEncode('і') + "' , 'count': 10 }' -H 'Content-Type: application/json; charset=utf-8' " + targetUrl);
//        return $("-d '{'prefixText': '" + unicodeEncode(letter) + "' , 'count': 10 }' -H 'Content-Type: application/json; charset=utf-8' " + targetUrl);
//    }
//
//    /**
//     * This method process name of group
//     *
//     * @param groupFullName full name of group
//     * @return String array of group full name, prefix, type and degree
//     */
//    public String[] processGroupString(String groupFullName) {
//        groupFullName = groupFullName.toLowerCase();
//        String groupPrefix = groupFullName.substring(0, 2);
//        String groupDegree = "bachelor";
//        if (groupFullName.substring(2, groupFullName.length() - 1).indexOf('м') != -1) {
//            groupDegree = "master";
//        }
//
//        if (groupFullName.substring(2, groupFullName.length() - 1).indexOf('с') != -1) {
//            groupDegree = "specialist";
//        }
//
//        String groupType = "daily";
//        if (groupFullName.substring(2, groupFullName.length() - 1).indexOf('з') != -1) {
//            groupType = "extramural";
//        }
//
//        return new String[]{
//                "\"group_full_name\" : \"" + groupFullName + "\"",
//                "\"group_prefix\" : \"" + groupPrefix + "\"",
//                "\"group_type\" : \"" + groupType + "\"",
//                "\"group_degree\" : \"" + groupDegree + "\"",
//        };
//    }
//
//    /**
//     * This method does post request and gets HTML body of group
//     *
//     * @param groupFullName full name of group
//     * @return String of HTML body of group
//     * @throws IOException interrupted I/O operations
//     */
//    private String getResponse(String groupFullName) throws IOException {
//        String groupUrlPostData = "__EVENTTARGET=&__EVENTARGUMENT=&ctl00%24MainContent%24ctl00%24txtboxGroup=" + groupFullName.toUpperCase() +
//                "&ctl00%24MainContent%24ctl00%24btnShowSchedule=%D0%A0%D0%BE%D0%B7%D0%BA%D0%BB%D0%B0%D0%B4+%D0%B7%D0%B0%D0%BD%D1%8F%D1%82%D1%8C&__EVENTVALIDATION=" + groupUrlPostKey;
//        String request = "--connect-timeout=60 -d \"" + groupUrlPostData + "\" -H \"Content-Type: application/x-www-form-urlencoded\" \"" + targetGroupUrl + "\"";
//        return $(request);
//    }
//}
//
