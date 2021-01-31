import com.parserapp.apikpirozklad.Parser.GroupLinksParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

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

        parser.parseGroupsWithLinks();


    }
}
