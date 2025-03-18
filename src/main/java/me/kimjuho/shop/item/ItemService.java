package me.kimjuho.shop.item;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(String title, Integer price, String username) {
        if (title.length() < 100 || price < 2000000000 && price >= 0) {
            Item item = new Item(title, price);
            item.setUsername(username);
            itemRepository.save(item);
        } else {
            throw new RuntimeException("error");
        }
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
