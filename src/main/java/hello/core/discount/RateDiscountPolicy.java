package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy") // 컴파일타임의 오류를 잡을 수 없다.
@MainDiscountPolicy // 직접 만들어서 해결
//@Primary // (Autowired 조회할때) 같은 타입의 빈이 있을 때, 우선권을 가진다(default처럼). 그외 지정 조회하고 싶은 곳에는 @Qualifier 이용
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10; //10% 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
