package me.kimjuho.shop.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.kimjuho.shop.sales.Sales;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String displayName;

    @ToString.Exclude
    @OneToMany(mappedBy = "member")
    List<Sales> sales = new ArrayList<>();
}
