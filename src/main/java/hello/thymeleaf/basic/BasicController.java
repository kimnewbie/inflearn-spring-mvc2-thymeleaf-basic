package hello.thymeleaf.basic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("/text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "Hello <b>Spring</b>");
        return "basic/text-basic";
    }

    /**
     * Unescape
     */
    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model) {
        model.addAttribute("data", "Hello <b>Spring</b>");
        return "basic/text-unescaped";
    }

    // 변수 - SpringEL
    @GetMapping("/variable")
    public String variable(Model model) {

        User userA = new User("김유진", 35);
        User userB = new User("변새롬", 34);

        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);

        return "basic/variable";
    }

    @Data
    static class User {
        private String username;
        private int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }
    // 변수 - SpringEL

    // 기본 객체들
    @GetMapping("/basic-objects")
    public String basicObjects(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        // session 추가
        session.setAttribute("sessionData", "Hello Session");

        model.addAttribute("request", request);
        model.addAttribute("response", response);
        model.addAttribute("servletContext", request.getServletContext());

        return "basic/basic-objects";
    }

    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "Hello " + data;
        }
    }
    // 기본 객체들

    // 유틸리티 객체와 날짜
    @GetMapping("/date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }
    // 유틸리티 객체와 날짜

    // URL 링크
    @GetMapping("/link")
    public String link(Model model) {
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "basic/link";
    }
    // URL 링크

    // 리터럴
    @GetMapping("/literal")
    public String literal(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/literal";
    }
    // 리터럴

    // 연산
    @GetMapping("/operation")
    public String operation(Model model) {
        model.addAttribute("nullData", null);
        model.addAttribute("data", "Spring!");
        return "basic/operation";
    }
    // 연산

    // 속성 값 설정
    @GetMapping("/attribute")
    public String attribute() {
        return "basic/attribute";
    }
    // 속성 값 설정

    // 반복
    @GetMapping("/each")
    public String each(Model model) {
        addUsers(model);
        return "basic/each";
    }
    private void addUsers(Model model) {
        List<User> list = new ArrayList<>();
        list.add(new User("userA", 10));
        list.add(new User("userB", 20));
        list.add(new User("userC", 30));
        model.addAttribute("users", list);
    }
    // 반복

    // 조건부 평가
    @GetMapping("/condition")
    public String condition(Model model) {
        addUsers(model);
        return "basic/condition";
    }
    // 조건부 평가

    // 주석
    @GetMapping("/comments")
    public String comments(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/comments";
    }
    // 주석

    // 블록
    @GetMapping("/block")
    public String block(Model model) {
        addUsers(model);
        return "basic/block";
    }
    // 블록

    // 자바스크립트 인라인
    @GetMapping("/javascript")
    public String javascript(Model model) {
        model.addAttribute("user", new User("userA", 10));
        addUsers(model);
        return "basic/javascript";
    }
    // 자바스크립트 인라인
}
