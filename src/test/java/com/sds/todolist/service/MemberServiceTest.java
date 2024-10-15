package com.sds.todolist.service;

import com.sds.todolist.BasicUnitTest;
import com.sds.todolist.domain.Member;
import com.sds.todolist.dto.MemberCreateRequestDTO;
import com.sds.todolist.dto.MemberResponseDto;
import com.sds.todolist.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest extends BasicUnitTest {

    @InjectMocks
    MemberService subject;

    @Mock
    MemberRepository memberRepository;

    @Test
    void checkEmailExists() {
        Optional<Member> optionalMember = Optional.of(new Member());
        given(memberRepository.findByEmail(eq(SOME_EMAIL))).willReturn(optionalMember);

        Boolean result = subject.checkEmailExists(SOME_EMAIL);

        then(memberRepository).should().findByEmail(eq(SOME_EMAIL));
        assertEquals(result, Boolean.TRUE);
    }

    @Test
    void signUp() {
        MemberCreateRequestDTO memberCreateRequestDTO = new MemberCreateRequestDTO();

        subject.signUp(memberCreateRequestDTO);

        then(memberRepository).should(times(1)).save(any());
    }

    @Test
    void getMember() {
        Optional<Member> optionalMember = Optional.of(Member.builder().email(SOME_EMAIL).userName(SOME_USER_NAME).build());
        given(memberRepository.findByEmail(eq(SOME_EMAIL))).willReturn(optionalMember);

        MemberResponseDto result = subject.getMember(SOME_EMAIL);

        then(memberRepository).should().findByEmail(eq(SOME_EMAIL));
        assertEquals(result.getUserName(), SOME_USER_NAME);
    }
}