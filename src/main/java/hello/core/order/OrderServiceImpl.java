package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //final 붙은 걸 갖고 생성자를 만들어줌.
public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository  = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    private final MemberRepository memberRepository;
//    @MainDiscountPolicy
    private final DiscountPolicy discountPolicy; //인터페이스에만 의존하도록 변경. -> but nullPointerException.

    //final 되어 있으면 생성자로 할당을 받아야함
//    @Autowired -> 생성자가 딱 하나면 생략이 된다.
    public OrderServiceImpl(MemberRepository memberRepository,@MainDiscountPolicy DiscountPolicy discountPolicy) {
        //ServiceImpl 클라이언트는 누가 들어올지 전혀 모르는거임. 그냥 들어오는대로 실행이 되도록 변경이 된 것임!
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    } // @RequiedArgsConstructor로 변경!

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
