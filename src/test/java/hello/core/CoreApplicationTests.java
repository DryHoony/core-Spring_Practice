package hello.core;

import hello.core.order.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoreApplicationTests {

	// 의존 관계 주입 방법 중 필드주입 - 외부에서 변경이 불가하고, DI 프레임워크가 없으면 아무것도 할 수 없다.
	// 일반적으로 사용하지 않지만, SpringBootTest 에서 따로 사용하는건 ok -> 편함
	@Autowired
	OrderService orderService;


	@Test
	void contextLoads() {
	}

}
