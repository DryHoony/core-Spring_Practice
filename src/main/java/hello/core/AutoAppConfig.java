package hello.core;


import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


@Configuration
@ComponentScan(
        basePackages = "hello.core", // 탐색할 패키지의 시작 위치를 지정(없으면 모든 자바 코드 다 뒤짐,,오래걸림)
        // basePackages 를 지정하지 않으면 (default 로) @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.
        // 따라서 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것을 권장
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {
    // 기존의 AppConfig와 다르게 @Bean 으로 등록한 클래스가 하나도 없다
    // 컴포넌트 스캔을 사용하면 @Configuration 이 붙은 설정 정보도 자동으로 등록(앞서 만든 AppConfig 함께 등록), 하지만 AppConfig 를 수정하지 않았으니 현 클래스에서는 excludeFilters 를 통해 제외한다
    // 컴포넌트 스캔은 @Component 애노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록 (@Configuration 의 소스코드에 @Component 붙어있다.)



//    @Bean(name = "memoryMemberRepository") // 같은 이름으로 빈 등록시 (자동 빈 등록보다) 수동 빈 등록이 우선권 가진다. (자동 vs 자동 이면 Error)
//    // But!!  여러 설정들이 꼬이다 보면 개발자 의도와는 다른 결과(논리적 오류)가 발생할 수 있다. >> 따라서 최근 스프링 부트에서는 수동 빈 등록, 자동 빈 등록 충돌나면 오류가 발생하도록 기본 값 바꿈
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }

}
