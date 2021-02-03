import com.parserapp.apikpirozklad.Entities.LessonInfo;
import com.parserapp.apikpirozklad.Parser.GroupLinksParser;
import com.parserapp.apikpirozklad.Parser.LessonsForGroupParser;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class GroupLinkParserTest {

    @Test
    public void TestGroupsParsing(){
        GroupLinksParser parser = null;
        try{
         parser = new GroupLinksParser();
        }catch (Exception e ){
            System.out.println(e);
        }

        Map<String, String> result =  parser.parseGroupsWithLinks();

        Assert.assertTrue(result.size()>0);

    }

    @Test
    public void TestLessonParsing() throws IOException {
        LessonsForGroupParser parser = null;
        try{
            parser = new LessonsForGroupParser();
        }catch (Exception e ){
        System.out.println(e);
    }

    List<LessonInfo> result =  parser.parseLessons("http://rozklad.kpi.ua/Schedules/ViewSchedule.aspx?g=0f46c80e-d38b-40aa-b27d-ec017ecfb8be");
        Assert.assertTrue(result.size()>0);
    }
}
