package me.kimjuho.shop.item;

import lombok.RequiredArgsConstructor;
import me.kimjuho.shop.Book;
import me.kimjuho.shop.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final BookRepository bookRepository;
    private final ItemService itemService;
    private final S3Service s3Service;

    /*
    @RequiredArgsConstructor를 안쓴다면 ?
    -> 밑에 방식을 사용
    @Autowired
    public ItemController(ItemRepository itemRepository, BookRepository bookRepository, ItemService itemService) {
        this.itemRepository = itemRepository;
        this.bookRepository = bookRepository;
        this.itemService = itemService;
    }
    DI (dependency Injection) -> 커플링을 줄일 수 있다, object를 안만들어도 됨
    */

    @GetMapping("/list")
    String list(Model model) {
        List<Item> result = itemRepository.findAll();
        model.addAttribute("items", result);
        return "list.html";
    }

    @GetMapping("/list/page/{currentPage}")
    String getListPage(Model model, @PathVariable Integer currentPage) {
        Page<Item> result = itemRepository.findPageBy(PageRequest.of(currentPage-1, 5));
        model.addAttribute("items", result.getContent());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", result.getTotalPages());
        return "list.html";
    }

    @GetMapping("/write")
    String write(Model model, Authentication auth) {
        model.addAttribute("username", auth.getName());
        return "write.html";
    }

    @PostMapping("/add")
    String addPost(@RequestParam String title, @RequestParam Integer price, @RequestParam String username) {
        try {
            itemService.saveItem(title, price, username);
            return "redirect:/list";
        }
        catch (Exception e) {
            return "error.html";
        }
    }

    @GetMapping("/presigned-url")
    @ResponseBody
    String getURL(@RequestParam String filename) {
        var result = s3Service.createPresignedUrl("test/" + filename);
        System.out.println(result);
        return result;
    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable Long id, Model model) {
        Optional<Item> item =  itemRepository.findById(id);
        try {
            model.addAttribute("item", item.get());
            return "edit.html";
        } catch (Exception e) {
            return "error.html";
        }
    }
    @PostMapping("/edit")
    String editItem(@RequestParam Long id, @RequestParam String title, @RequestParam Integer price) {
        try {
            itemService.editItem(id ,title, price);
            return "redirect:/list";
        } catch (Exception e) {
            return "error.html";
        }
    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model) {
        Optional<Item> result = itemService.findItem(id);
        if (result.isPresent()) {
            model.addAttribute("item", result.get());
            return "detail.html";
        } else {
            return "redirect:/list";
        }
    }

    @GetMapping("/test1")
    String test1(@RequestBody Map<String, Object> body) {
        System.out.println(body.get("name"));
        return "redirect:/list";
    }


    @DeleteMapping("/delete")
    ResponseEntity<String> delete(@RequestParam Long id) {
        itemRepository.deleteById(id);
        return ResponseEntity.status(200).body("삭제완료");
    }

    @GetMapping("/test2")
    String signup() {
        var result = new BCryptPasswordEncoder().encode("문자");
        System.out.println(result);
        return "redirect:/list";
    }


    /*
    @Autowired
    private ObjectMapper objectMapper; // 빈으로 주입

    @PostMapping("/add")
    String addPost(@RequestParam Map formData) {
        try {
            // Map을 Item 객체로 변환
            Item item = objectMapper.convertValue(formData, Item.class);
            itemRepository.save(item);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error"; // 변환 오류 시 예외 처리
        }
        return "redirect:/list";
    }*/

    /*@PostMapping("/add")
    String addPost(@ModelAttribute Item item) {
        itemRepository.save(item);
        return "redirect:/list";
    }*/




    @GetMapping("/book")
    String book(Model model) {
        List<Book> result = bookRepository.findAll();
        model.addAttribute("books", result);
        var a = new Book();
        System.out.println(a.toString());
        return "book.html";
    }
}
