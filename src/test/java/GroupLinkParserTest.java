import com.parserapp.apikpirozklad.Parser.GroupLinksParser;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

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
}
