package com.parserapplication.apikpirozklad.Parser;



import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;


public class ParserKpiGroupNameAndLinksTest {
    @DataProvider(name = "groups")
    public Object[][] dataProviderMethod() {
        return new Object[][]{
                {"iв-82", new Object[]{"\"group_full_name\" : \"iв-82\"", "\"group_prefix\" : \"iв\"", "\"group_type\" : \"daily\"", "\"group_degree\" : \"bachelor\""}},
                {"іс-з91", new Object[]{"\"group_full_name\" : \"іс-з91\"", "\"group_prefix\" : \"іс\"", "\"group_type\" : \"extramural\"", "\"group_degree\" : \"bachelor\""}},
                {"іс-82мп", new Object[]{"\"group_full_name\" : \"іс-82мп\"", "\"group_prefix\" : \"іс\"", "\"group_type\" : \"daily\"", "\"group_degree\" : \"master\""}},
                {"іс-з91мп", new Object[]{"\"group_full_name\" : \"іс-з91мп\"", "\"group_prefix\" : \"іс\"", "\"group_type\" : \"extramural\"", "\"group_degree\" : \"master\""}},
        };
    }

    @Test(dataProvider = "groups")
    public void testProcessGroupString(String group, Object[] expectedResult) throws IOException {
        ParserKpiGroupNameAndLinks parserKpiGroupNameAndLinks = new ParserKpiGroupNameAndLinks();
        String[] actualResult = parserKpiGroupNameAndLinks.processGroupString(group);
        Assert.assertEquals(actualResult, expectedResult, "Arrays differs");
    }




}
