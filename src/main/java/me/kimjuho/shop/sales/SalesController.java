package me.kimjuho.shop.sales;

import lombok.RequiredArgsConstructor;
import me.kimjuho.shop.member.CustomUser;
import me.kimjuho.shop.member.Member;
import me.kimjuho.shop.member.MemberRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SalesController {
    private final SalesService salesService;
    private final MemberRepository memberRepository;

    @PostMapping("/order")
    public String postOrder(@RequestParam String title,
                            @RequestParam Integer price,
                            @RequestParam Integer count,
                            Authentication auth) {
        salesService.save(title,price,count,auth);
        return "list.html";
    }
    @GetMapping("/order/all")
    public String getOrderAll(Model model) {
        List<SalesDTO> result = salesService.findAll();
        System.out.println(result);
        model.addAttribute("sales", result);
/*
        var result = memberRepository.findById(1L);
        System.out.println(result.get().getSales());*/
        return "sales.html";
    }
}
