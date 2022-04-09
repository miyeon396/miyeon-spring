package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String beanDeficitinName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDeficitinName);
            System.out.println("name = "+beanDeficitinName + " object = "+bean);

        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String beanDeficitinName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDeficitinName);

            //Role ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) { //스프링이 내부에서 등록한 빈이 아니라 내가 개발을 하기 위해 등록된 빈
                Object bean = ac.getBean(beanDeficitinName);
                System.out.println("name = "+beanDeficitinName + " object = "+bean);
            }
        }
    }
}
