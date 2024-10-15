package com.sds.todolist.controller;

import com.sds.todolist.dto.MemberCreateRequestDTO;
import com.sds.todolist.dto.MemberResponseDto;
import com.sds.todolist.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/check")
    public Boolean checkEmailExists(@RequestParam(required = false) String email) {
        return memberService.checkEmailExists(email);
    }

    @PostMapping
    public void signUp(@RequestBody MemberCreateRequestDTO memberCreateRequestDTO) {
        memberService.signUp(memberCreateRequestDTO);
    }

    @GetMapping
    public MemberResponseDto getMember(@RequestParam String email) {
        return memberService.getMember(email);
    }
}
