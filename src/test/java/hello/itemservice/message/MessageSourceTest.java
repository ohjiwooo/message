package hello.itemservice.message;


import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    void helloMessage(){
        String result = ms.getMessage("hello",null,null);
        Assertions.assertThat(result).isEqualTo("안녕");
    }

    @Test
    void nodeFoundMessageCode(){
        Assertions.assertThatThrownBy(()->ms.getMessage("no_code",null,null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void NotFoundMessageCodeDefault(){
        String result = ms.getMessage("no_code",null,"기본메세지",null);
        Assertions.assertThat(result).isEqualTo("기본메세지");
    }

    @Test
    void argumentMessage(){
        String result = ms.getMessage("hello.name",new Object[]{"spring"},null);
        Assertions.assertThat(result).isEqualTo("안녕 spring");
    }
    @Test
    void defaultLang(){
        Assertions.assertThat(ms.getMessage("hello",null,null)).isEqualTo("안녕");
        Assertions.assertThat(ms.getMessage("hello",null, Locale.KOREA)).isEqualTo("안녕");
    }
    @Test
    void enLang(){
        Assertions.assertThat(ms.getMessage("hello",null,Locale.ENGLISH)).isEqualTo("hello");
    }

}
