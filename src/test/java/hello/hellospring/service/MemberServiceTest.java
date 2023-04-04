package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");
        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 회원가입_중복_회원_예외() {
        Member member = new Member();
        member.setName("spring");

        Member memberSameName = new Member();
        memberSameName.setName("spring");

        Long saveId = memberService.join(member);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(memberSameName));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
//        try {
//            Long saveId2 = memberService.join(memberSameName);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
//        }


    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}