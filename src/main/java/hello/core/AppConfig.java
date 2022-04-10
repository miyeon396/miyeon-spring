package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //어플리케이션의 구성 정보.
public class AppConfig {

    //App전체를 설정하고 구성한다는 뜻.
    // 리팩토링 후 : 메서드명을 보면 역할이 보임. 안에 return을 보면 구현이 보임.

    @Bean //빈들은 스프링 컨테이너에 등록. 기본적으로 이름은 메서드 이름이 등록이 된다.
    public MemberService memberService() { //멤버 서비스를 여기서 만듦.
        //생성자 주입 방식으로 변경.
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        //나는 멤버 리포지토리 쓰는데 memoryMemberRepo쓸거야
        System.out.println("call AppConfig.getMemberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() { //구체적인 것은 여기서 결정.
        System.out.println("call AppConfig.orderService");
        return  new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy(); //정률할인으로 바꾸길 원하는 경우 여기만 고쳐주면 됨.
    }

}
