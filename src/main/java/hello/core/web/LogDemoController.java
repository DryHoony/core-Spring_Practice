package hello.core.web;

import hello.core.common.MyLogger;
import hello.core.logdemo.LogDemoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor // final 키워드 붙은 필드로 생성자 (의존관계) 주입
public class LogDemoController {

    private final LogDemoService logDemoService;
//    private final ObjectProvider<MyLogger> myLoggerProvider; // proxy 사용으로 해결
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody // view 화면 없이 문자 바로 반환
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString();
//        MyLogger myLogger = myLoggerProvider.getObject(); // proxy 사용으로 해결

        System.out.println("myLogger = " + myLogger.getClass()); // 확인용
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        Thread.sleep(1000); // 확인용
        logDemoService.logic("testId");
        return "ok";
    }


}
