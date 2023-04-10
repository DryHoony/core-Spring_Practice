package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {
    // 프로토타입 스코프 - 싱글톤 빈과 함께 사용시 문제점
    // 싱글톤 빈이 의존관계 주입을 통해서 프로토타입 빈을 주입받아서 사용하는 경우



    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);


    }


    @Test
    void SingletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class); // 둘다 빈으로 등록해서 사용

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        // clientBean 이 내부에 가지고 있는 프로토타입 빈은 이미 과거에 주입이 끝난 빈이다. (주입 시점에 스프링 컨테이너에 요청해서 프로토타입 빈이 새로 생성된 것, 사용 할 때마다 새로 생성X)
        // 따라서 같은 (프로토 타입 빈을 가지고 있는)싱글톤 빈으로 연산(logic())된다.
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);

        // 이럴꺼면 걍 싱글톤을 쓰지,, 이건 의도한게 아니다(라는 걸 보여준 Test 였다)
        //



    }


    @Scope("singleton")
    static class ClientBean {

//        private final PrototypeBean prototypeBean; // 생성시점에 주입
//
//
//        @Autowired // ClientBean 생성될 때 프로토타입 빈 '내놔' 라고 요청한다
//        public ClientBean(PrototypeBean prototypeBean) { // 생성자 하나니까 @Autowired 생략가능, 생성자를 @RequiredArgsConstructor 로 대체가능
//            this.prototypeBean = prototypeBean;
//        }

        @Autowired
//        private ObjectFactory<PrototypeBean> prototypeBeanProvider;
//        private ObjectProvider<PrototypeBean> prototypeBeanProvider; // ObjectFactory<> 발전 형태(상속받음)
        // 해당 빈을 대신 찾아서 조회 - 직접 필요한 의존관계를 찾음 DL(Dependency Lookup)
        private Provider<PrototypeBean> prototypeBeanProvider;
        // 심플하게 딱 필요한 DL 정도의 기능만 제공한다. - 장단점:심플하다

//        @Autowired
//        ApplicationContext applicationContext;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private  int count = 0;

        private void addCount() {
            count ++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy // prototype 이므로 호출이 안되는게 정상
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }

}
