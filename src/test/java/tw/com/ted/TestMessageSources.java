package tw.com.ted;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import javax.sql.DataSource;
import java.util.Locale;

@SpringBootTest
public class TestMessageSources {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }

    @Test
    public void testI18N(){
        String str1 = messageSource.getMessage("login.username.required",null, Locale.TAIWAN);
        System.out.println("str1= "+str1);

        String str2 = messageSource.getMessage("login.username.required",null, Locale.US);
        System.out.println("str2= "+str2);
    }
}
