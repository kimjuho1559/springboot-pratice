package me.kimjuho.shop.item;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(String title, Integer price, String username, String imgUrl) {
        if (title.length() < 100 || price < 2000000000 && price >= 0) {
            Item item = new Item(title, price);
            item.setUsername(username);
            item.setImgUrl(imgUrl);
            item.setCount(10);
            itemRepository.save(item);
        } else {
            throw new RuntimeException("error");
        }
    }

    public List<Item> searchItems(String title) {
        return itemRepository.findAllByTitleContains(title);
    }

    public Optional<Item> findItem(Long id) {
        return itemRepository.findById(id);
    }

    public void editItem(Long id, String title, Integer price) {
        if (title.length() < 100 || price < 2000000000 && price >= 0) {
            Item item = new Item(title, price);
            item.setId(id);
            itemRepository.save(item);
        } else {
            throw new RuntimeException("error");
        }
    }
}
