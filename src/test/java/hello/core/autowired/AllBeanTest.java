package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    @Test
    void findAllBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> pocliyMap;
        private final List<DiscountPolicy> policies;

        //생성자 하나라 @autowired 생략가능.
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.pocliyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = "+policyMap);
            System.out.println("poslices = "+policies);
        }


        public int discount(Member member, int price, String discountCode) {
            //편리하게 다형성 코드로 만들 수 있다. -> 동적으로 빈을 선택해야할 때 다형성을 유지하면서 설명할 수 있따. 우리 잘 쓸 수 있찌 않으까 리소스 벤더..ㅎ
            DiscountPolicy discountPolicy = pocliyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
