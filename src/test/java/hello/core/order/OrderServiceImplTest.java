package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
//스프링 없이 순수하게 자바 코드로만 테스트해서 조립을하는거. 테스트 코드에서 필요한 구현체의 조합을 이용해서 테스트 코드를 만드는거.
    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);// 왜 에러 나냐면, MemberRepository, DiscountPolicy 넣어 줘야함
        //order 만 하고싶다고 하더라도 저 두개 넣어줘야함.
        assertThat(order.getDiscountPrice()).isEqualTo(1000);



    }

}