package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    //App전체를 설정하고 구성한다는 뜻.

    public MemberService memberService() { //멤버 서비스를 여기서 만듦.
        //생성자 주입 방식으로 변경.
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() { //구체적인 것은 여기서 결정.
        return  new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

}
