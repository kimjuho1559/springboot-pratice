package me.kimjuho.shop.comment;


import lombok.RequiredArgsConstructor;
import me.kimjuho.shop.member.CustomUser;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment")
    String postComment(@RequestParam String content, Authentication auth, @RequestParam Long parent) {
        CustomUser user = (CustomUser) auth.getPrincipal();
        var data = new Comment();
        data.setContent(content);
        data.setUsername(user.getUsername());
        data.setParentId(parent);
        commentService.save(data);
        return "redirect:/list";
    }
}
