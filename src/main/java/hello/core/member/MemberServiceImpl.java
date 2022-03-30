package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //MemberServiceImpl은 추상화(MemberRepository)에도 의존하고 구체화(MemoryMemberRepository에도 의존하고 -> not good.. dip 위반..
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
