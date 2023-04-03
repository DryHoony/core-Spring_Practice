package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig { // 프로그램 제어의 흐름을 담당 >> IoC 컨테이너 또는 DI 컨테이너
    // 구성영역, 책임과 역할 분리! - AppConfig가 구현 객체를 생성하고 연결하는 역할을 담당
    // 따라서 orderService는 discountPolicy(인터페이스)에만 의존하여 DIP ok
    // (정책이 바뀌어, 저장소가 바뀌어) 수정이 필요해도 AppConfig 에서 해결 -> 클라이언트 코드를 수정하지 않아도 됨 OCP ok


    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService"); // 호출 로그를 남겨 실험
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy()); // 잠시 주석처리
//        return null;
    }

    @Bean
    public MemberRepository memberRepository(){
        System.out.println("call AppConfig.memberRepository"); // 3번 호출되지 않게 스프링이 관리해줌 (바이트코드 조작하는 라이브러리 사용해서)
        return new MemoryMemberRepository();
    }
    // new 로 되어있지만 매번 새롭게 생성하는 것이 아니라 싱글톤으로 스프링이 관리해줌(으로 추정 ConfigurationSingletonTest 여기서 확인해 보자)


    @Bean
    public DiscountPolicy discountPolicy() { // 할인정책을 변경할려면 여기만 수정하면 된다
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }



}
