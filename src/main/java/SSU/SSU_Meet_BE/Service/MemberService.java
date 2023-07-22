package SSU.SSU_Meet_BE.Service;

import SSU.SSU_Meet_BE.Entity.Member;
import SSU.SSU_Meet_BE.Repository.MemberRepository;
import SSU.SSU_Meet_BE.Security.JwtAuthenticationFilter;
import SSU.SSU_Meet_BE.Security.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final TokenProvider tokenProvider;

    @Transactional(readOnly = true)
    public String getMemberInfo(HttpServletRequest request) {
        String token = jwtAuthenticationFilter.parseBearerToken(request);

        return tokenProvider.validateTokenAndGetSubject(token);
    }

    @Transactional
    public void save(Member member){
        memberRepository.save(member);
    }

    @Transactional
    public void findMember(Long memberId){
        memberRepository.findById(memberId);
    }

    @Transactional

}
