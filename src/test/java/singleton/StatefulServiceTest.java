package singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A 사용자가 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        //ThreadB : B 사용자가 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        //A가 주문하고 B가 주문한 사이에 끼어드는것.. 기대는 10000원이지만 20000원이 나왔겠지.
        //같은 객체에서 만원에서 2만원으로 교체되어 버린 것. -> 이런 장애 잡기 아주어려움.. 서비스 망하는 것 ㅋㅋ
//        int price = statefulService1.getPrice();
        System.out.println("price = "+userAPrice);

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
        Assertions.assertThat(userAPrice).isEqualTo(10000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
