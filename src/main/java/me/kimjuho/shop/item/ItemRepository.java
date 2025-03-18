package me.kimjuho.shop.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item, Long> { // <가져올 Repo, id 인수>
    Page<Item> findPageBy(Pageable page);
    Slice<Item> findSliceBy(Pageable page);
}
