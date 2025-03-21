package me.kimjuho.shop.sales;

import lombok.RequiredArgsConstructor;
import me.kimjuho.shop.item.ItemRepository;
import me.kimjuho.shop.member.CustomUser;
import me.kimjuho.shop.member.Member;
import me.kimjuho.shop.member.MemberRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesService {
    private final SalesRepository salesRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    /* Exception 종류에 따라 @Transactional 동작 여부에 따라 다름
        1. checked Exception
        2. unchecked Exception -> 이때만 롤백됨
        @Test 시에도 사용가능함 -> testDB 자동 롤백
    */
    @Transactional
    public void addOrder(String title, Integer price, Integer count, Authentication auth, Long id) {
        var result = itemRepository.findById(id);
        if (result.isPresent()) {
            var item = result.get();
            item.setCount(item.getCount() - count);
            itemRepository.save(item);
        }
        else {

        }
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
