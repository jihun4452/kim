package cotest.kim.service;

import cotest.kim.domain.Member;
import cotest.kim.repository.MemberReposiroty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberReposiroty memberReposiroty;

    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberReposiroty.save(member);
        return member.getId();
    }

    //종복회원 로직
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberReposiroty.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findMembers(Member member) {
        return memberReposiroty.findAll();
    }

    public Member findOne(Long memberId){
        return memberReposiroty.findOne(memberId);
    }
}
