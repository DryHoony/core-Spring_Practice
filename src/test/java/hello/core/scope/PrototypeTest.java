package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {
    // 프로토 타입 : (스프링 컨테이너는 프로토타입 빈의) 생성과 의존관계 주입까지만 관여, 만들고 버림, 매우 짧은 범위의 스코프

    // 스프링 컨테이너에서 빈을 조회(요청)할 때 생성된다. 이때 (싱글톤이 아니므로) 새로운 스프링 빈이 생성된다.
    // 스프링 컨테이너가 종료되도 @PreDestroy 종료 메서드 실행X , 종료(를 포함환 관리)는 클라이언트가 직접해야 한다

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find prototypeBean1"); // 프로토타입 빈은 조회하기 직전에 생성된다. >> 해당 출력문 먼저, 그 뒤 prototype.init 메서드 작동
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2"); //
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2); // 참조값 서로 다름 확인 ok
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

//        prototypeBean1.destroy(); // 직접 종료
//        prototypeBean2.destroy(); // 직접 종료
        ac.close(); // 스프링 컨테이너는 종료했는데 빈은 destroy 안됬다
    }

    @Scope("prototype") // @Component 가 없어도 AnnotationConfigApplicationContext(PrototypeBean.class) 로 빈 등록된다
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("prototype.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("prototype.destroy");
        }
    }


}
