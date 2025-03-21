package me.kimjuho.shop;

import me.kimjuho.shop.item.ItemRepository;
import me.kimjuho.shop.item.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ShopApplicationTests {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void contextLoads() {
    }

    @Test
    @Transactional
    public void testAddItem() {
        itemService.saveItem("테스트아이템", 1000, "테스트", "테스트");
        var result = itemRepository.findById(1L);
        assertEquals(1L, result.get().getId());
    }

}
