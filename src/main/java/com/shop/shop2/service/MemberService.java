package com.shop.shop2.service;

import com.shop.shop2.entity.Member;
import com.shop.shop2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
4-8. 로그인 로그아웃 기능 구현하기.
spring security 는 왜 인터페이스를 만들어서 구현하도록 했을까?

 */


@Service
@Transactional
// final 이나 @NonNull 인 필드에 생성자를 생성해준다.
// 빈에 생상자가 한 개이고 빈으로 등록가능한 타입이면 @Autowired 어노테이션 생략 가능
@RequiredArgsConstructor
// UserDetailsService
// 회원 정보를 가져오는 역할을 한다.
// spring security 에서 제공하는 UserDetailsService 인터페이스를 구현하여 로그인 인증을 위한 메소드를 구현
// loadUserByUsername 메소드 만 가지고 있는 인터페이스, 다른 어떤 것도 상속받지 않음.
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    // 같은 이메일을 가진 멤버가 있는지 검사
    public void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    // UserDetails
    // 회원 정보를 담기 위해서 사용하는 인터페이스
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        if (member == null) {
            throw new UsernameNotFoundException(email);
        }

        // User
        // UserDetails 인터페이스의 구현체
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
