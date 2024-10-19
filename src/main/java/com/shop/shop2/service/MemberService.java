package com.shop.shop2.service;

import com.shop.shop2.entity.Member;
import com.shop.shop2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
// final 이나 @NonNull 인 필드에 생성자를 생성해준다.
// 빈에 생상자가 한 개이고 빈으로 등록가능한 타입이면 @Autowired 어노테이션 생략 가능
@RequiredArgsConstructor
public class MemberService {

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
}
