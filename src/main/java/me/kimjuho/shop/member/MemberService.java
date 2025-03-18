package me.kimjuho.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    final private MemberRepository memberRepository;
    final private PasswordEncoder passwordEncoder;

    public void signup(String username, String password, String displayName) {
        Member member = new Member();
        var result = passwordEncoder.encode(password);
        member.setUsername(username);
        member.setPassword(result);
        member.setDisplayName(displayName);
        memberRepository.save(member);
    }
}
