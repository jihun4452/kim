package cotest.kim.service;

import cotest.kim.domain.Member;
import cotest.kim.repository.MemberRepository; // 올바른 Repository 이름으로 수정
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository; // 오타 수정

    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member); // 오타 수정
        return member.getId();
    }

    // 중복회원 로직
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName()); // 오타 수정
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findMembers() { // 인자 제거
        return memberRepository.findAll(); // 오타 수정
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId); // 오타 수정
    }
}
