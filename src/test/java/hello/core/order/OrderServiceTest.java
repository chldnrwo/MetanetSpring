package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class OrderServiceTest {
  MemberService memberService;
      //= new MemberServiceImpl();
  OrderService orderService;
      //= new OrderServiceImpl();
      @BeforeEach
      public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
      }
  long memberId = 0L;



  @Test
  @DisplayName("vip 고객은 10% 할인 적용")
  void VIP_주문생성테스트(){
    //given
    Member member2
        = new Member(2L, "memberB", Grade.VIP);
    memberService.join(member2);

    //when
    Order order2
        = orderService.createOrder(2L, "itemB", 10000);
    Order order3
        = orderService.createOrder(2L, "itemB", 20000);

    //then
    Assertions.assertThat(1000).isEqualTo(order2.getDiscountPrice());
    Assertions.assertThat(2000).isEqualTo(order3.getDiscountPrice());
  }

  @Test
  @DisplayName("일반 고객은 할인 적용 안됨")
  void 주문생성테스트(){
    //given
    Member member
        = new Member(1L, "memberA", Grade.BASIC);
    memberService.join(member);

    //when
    Order order
        = orderService.createOrder(1L, "itemA", 10000);

    //then
    Assertions.assertThat(0).isEqualTo(order.getDiscountPrice());
  }
}
