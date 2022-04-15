package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class); //annotationConfigApplication 사용하면 저기 넣어지는 클래스가 컴포넌트 스캔처럼 등록되기 떄문에 따로 component안써줘도됨.
        System.out.println("find prototypeBean1"); //프로토타입 빈을 생성하기 직전에 호출한다고 함.
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = "+prototypeBean1);
        System.out.println("prototypeBean2 = "+prototypeBean2);
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        ac.close(); //close가 안됨.
        //만약에 DEstroy 호출할 필요가 있으면 직접 호출해야함
        prototypeBean1.destroy();
        prototypeBean2.destroy();

    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
