package hello.core.autowired;

import hello.core.member.Member;
import hello.core.member.MemberRepository;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean{
        // Member 는 스프링 빈이 아닌 상황!

        @Autowired(required = false) // true(default) 로 하면 예외 터진다 - Member 가 Spring Bean 으로 등록되지 않았음
        // 지금처럼 의존관계가 없으면 호출 자체가 안된다. (required = false 라서 예외는 안터짐)
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2){ // 자동 주입할 대상이 없으면 null이 입력
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3){ // 자동 주입할 대상이 없으면 Optional.empty 가 입력
            System.out.println("noBean3 = " + noBean3);
        }


    }


}
