package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {

//    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    //ApplicationContext로 하면 getBeanDefinition을 못함 그래서 AnnotationConfigApplicationContext로.. (ApplicationContext에는 이런 복잡한 기능 정의 안되어 있음)
    GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");


    //자바 코드로 빈을 등록하는거 : AppConfig라는 펙토리 메서드를 통해서 등록하는 방식. xml에서는 이 부분이 null로 나옴.
    @Test
    @DisplayName("빈 설정 메타 정보 확인")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = "+ beanDefinitionName + "beanDefinition = "+beanDefinition);
            }
        }
    }
}
