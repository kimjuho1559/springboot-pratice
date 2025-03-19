package me.kimjuho.shop.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
@Table(indexes = @Index(columnList = "title", name = "작명")) // index 생성
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column 컬럼별 설정하기
    private String title;
    private Integer price;
    private String username;
    private String imgUrl;
    public Item(String title, Integer price) {
        this.title = title;
        this.price = price;
    }

    public Item() {

    }
}
