package me.kimjuho.shop.member;

public class MemberDTO {
    public String username;
    public String displayName;
    Long id;
    public MemberDTO(String username, String displayName) {
        this.username = username;
        this.displayName = displayName;
    }
}
