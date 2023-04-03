package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();


    // 1. 생성자 주입 - 불변, 필수 의존관계에 사용
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
//
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 2. 수정자 주입 - 선택, 변경 가능성이 있는 의존관계에 사용
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }
    // @Autowired 는 주입할 대상이 없으면 오류 발생 -> @Autowired(required = false)로 지정하면 해결

    // 3. 필드 주입 - 코드가 간결하지만 외부에서 변경불가!(테스트 하기 힘들다 - 치명적인 단점!!), 사용하지 말자
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;
    // 참고 - 순수한 자바 테스트 코드에선 @Autowired 가 동작X, @SpringBootTest 처럼 스프링 컨테이너 통합 테스트 경우에만 가능


    // 4. 일반 메서드 주입 - 한번에 여러 필드를 주입 받을 수 있다. 잘 사용하지 않음
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//
//    @Autowired // 아무 메서드에다가 @Autowired 사용할 수 있다.
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }


}
