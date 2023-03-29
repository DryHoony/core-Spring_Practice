package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
    // 구성영역, 책임과 역할 분리! - AppConfig가 구현 객체를 생성하고 연결하는 역할을 담당
    // 따라서 orderService는 discountPolicy(인터페이스)에만 의존하여 DIP ok
    // (정책이 바뀌어, 저장소가 바뀌어) 수정이 필요해도 AppConfig 에서 해결 -> 클라이언트 코드를 수정하지 않아도 됨 OCP ok

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }

    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() { // 할인정책을 변경할려면 여기만 수정하면 된다
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }



}
