package cotest.kim.service;

import cotest.kim.domain.Member;
import cotest.kim.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional

public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired EntityManager em;
    @Test
    public void 회원가입() throws Exception {
        //Given
        Member member = new Member();
        member.setName("testUser");
        //When
        Long saveId = memberService.join(member);
        //Then
        em.flush();
        assertEquals(member,memberRepository.findOne(saveId));
    }
    @Test
    @Transactional
    public void 중복_회원_예외() {
        // Given
        Member member = new Member();
        member.setName("testUser");
        memberService.join(member); // 첫 번째 회원 가입

        // When & Then
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> {
            memberService.join(member); // 동일한 회원 가입 시도
        });

        assertEquals("이미 존재하는 회원입니다.", thrown.getMessage());
    }
}