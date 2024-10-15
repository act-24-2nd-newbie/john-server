package com.sds.todolist.service;

import com.sds.todolist.domain.Member;
import com.sds.todolist.dto.MemberCreateRequestDTO;
import com.sds.todolist.dto.MemberResponseDto;
import com.sds.todolist.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Boolean checkEmailExists(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    public void signUp(MemberCreateRequestDTO memberCreateRequestDTO) {
        Member member = Member.builder().email(memberCreateRequestDTO.getEmail()).userName(memberCreateRequestDTO.getUserName()).build();
        memberRepository.save(member);
    }

    public MemberResponseDto getMember(String email) {
        return memberRepository.findByEmail(email).map(Member::toResponse).orElseThrow(() -> new NoSuchElementException("Member email: " + email));
    }

    public Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException("Member id: " + memberId));
    }
}
