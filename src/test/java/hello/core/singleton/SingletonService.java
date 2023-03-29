package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonService {
    // 싱글톤 패턴 - 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴
    // private 생성자를 사용해서 외부에서 임의로 new 키워드를 사용하지 못하게 막아야 한다.
    // 싱글톤 패텬을 구현하는 여러가지 방법 중 가장 단순하고 안전한 방법

    // 1. static 영역에 객체를 딱 1개만 생성한다 (미리 하나 생성해서 올려둔다)
    private static final SingletonService instance = new SingletonService();

    // 2. public 으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허락
    public static SingletonService getInstance(){ // 조회
        return instance; // 항상 같은 (미리 생성된)인스턴스를 반환한다.
    }

    // 3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletonService() { // 외부에서 new 로 객체 인스턴스가 생성되는 것을 막는다.
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }





}
