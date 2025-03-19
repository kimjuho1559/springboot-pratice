package me.kimjuho.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;


@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/register")
    public String register(Authentication auth) {
        if (auth != null && auth.isAuthenticated())
            return "redirect:/list";
        return "register.html";
    }
    @GetMapping("/my-page")
    public String mypage(Authentication auth) {
        CustomUser result = (CustomUser) auth.getPrincipal();
        System.out.println(result.getUsername() );
        return "mypage.html";
    }
    @PostMapping("/signup")
    public String signup(@RequestParam String username, @RequestParam String password, @RequestParam String displayName) {
        memberService.signup(username, password, displayName);
        return "redirect:/list";
    }
    @GetMapping("/login")
    public String login() {
        var result = memberRepository.findByUsername("aa");
        System.out.println(result.get().getDisplayName());
        return "login.html";
    }
    @GetMapping("/user/1")
    @ResponseBody
    public MemberDTO getUser() {
        var a = memberRepository.findById(1L);
        var result = a.get();
        var data = new MemberDTO(result.getUsername(), result.getDisplayName());
        return data;
    }
}

