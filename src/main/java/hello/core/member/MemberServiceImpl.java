package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //MemberServiceImpl은 추상화(MemberRepository)에도 의존하고 구체화(MemoryMemberRepository에도 의존하고 -> not good.. dip 위반.. -> 아래와 같이 변경

    private final MemberRepository memberRepository;


    @Autowired //ac.getBaen(MemberRepository.class) 자동으로 이 코드가 들어간다고 보면 됨.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
