package hello.core.member;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
  MemberService memberService = new MemberServiceImpl();
  @Test
  void 회원가입테스트(){
    Member member = new Member(1L,"memberA", Grade.VIP);
    memberService.join(member);
    Member findMember = memberService.findMember(1L);
    System.out.println("new Member = " + member.getName());
    System.out.println("find Member = " + findMember.getName());

    Assertions.assertThat(member).isEqualTo(findMember);
  }
}
