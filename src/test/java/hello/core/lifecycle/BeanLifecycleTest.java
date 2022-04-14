package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifecycleTest {

    @Test
    public void lifeCycleTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class); //ConfigurableApplicationContext로 쓰거나
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient(); //스프링 빈 등록할 때 생성자 호출 하겠죠
            networkClient.setUrl("http://hello-spring.dev"); //외부에서 값을 세팅하고 나서 초기화를 해줘야함... 수정자 주입을 통해서 주입
            return networkClient;
        }
    }
}
