package me.kimjuho.shop.sales;

import lombok.RequiredArgsConstructor;
import me.kimjuho.shop.member.CustomUser;
import me.kimjuho.shop.member.Member;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesService {
    private final SalesRepository salesRepository;

    public void save(String title, Integer price, Integer count, Authentication auth) {
        Sales sales = new Sales();
        sales.setItemName(title);
        sales.setPrice(price);
        sales.setCount(count);
        CustomUser user = (CustomUser) auth.getPrincipal();
        var member = new Member();
        member.setId(user.getId());
        sales.setMember(member);
        salesRepository.save(sales);
    }
    public List<SalesDTO> findAll() {
        var result = salesRepository.customFindAll();
        List<SalesDTO> salesDTOList = new ArrayList<>();
        for (var sale : result) {
            SalesDTO salesDTO = new SalesDTO();
            salesDTO.setItemName(sale.getItemName());
            salesDTO.setPrice(sale.getPrice());
            salesDTO.setUsername(sale.getMember().getUsername());
            salesDTOList.add(salesDTO);
        }
        return salesDTOList;
    }
}
