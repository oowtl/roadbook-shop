package com.shop.shop2.entity;

import com.shop.shop2.constant.Role;
import com.shop.shop2.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;


@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // Member ID

    private String name; // Member Name

    @Column(unique = true)
    private String email; // Member Email

    private String password;

    private String address; // Member Address

    @Enumerated(EnumType.STRING)
    private Role role; // Member Role

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());

        // 비밀번호 암호화
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);

//        member.setRole(Role.USER);
        member.setRole(Role.ADMIN);
        return member;
    }
}
