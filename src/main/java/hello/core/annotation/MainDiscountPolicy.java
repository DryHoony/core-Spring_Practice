package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
    // 에노테이션 직접 만들기 - 코드가 깔끔해진다, 컴파일 오류에도 나타난다.(잡기 쉽다) (어디서 사용했는지) 코드 추적도 된다.
}
