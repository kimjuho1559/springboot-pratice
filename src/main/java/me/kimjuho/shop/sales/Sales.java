package me.kimjuho.shop.sales;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.kimjuho.shop.member.Member;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class Sales {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private Integer price;
    private Integer count;

    /* ManyToOne 단점
        1. Select 문이 많이 실행됨
        2. 모든 컬럼을 다 가져옴
    */
    @ManyToOne(fetch = FetchType.LAZY) // default: fetch = FetchType.EAGER (필요하건 미리 다 가져와)
    @JoinColumn(
            name="Member_Id",
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    private Member member;
    @CreationTimestamp
    private LocalDateTime created;
}

