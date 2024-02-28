package com.ppp.train.member.controller;

import com.ppp.train.common.resp.CommonResp;
import com.ppp.train.member.req.MemberLoginReq;
import com.ppp.train.member.req.MemberRegisterReq;
import com.ppp.train.member.req.MemberSendCodeReq;
import com.ppp.train.member.resp.MemberLoginResp;
import com.ppp.train.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/count")
    public CommonResp<Integer> count(){
        Integer count = memberService.count();
        return new CommonResp<>(count);
    }

    @PostMapping("/register")
    public CommonResp<Long> register(@Valid MemberRegisterReq memberRegisterReq){
        long register = memberService.register(memberRegisterReq);
        return new CommonResp<>(register);
    }

    @PostMapping("/send-code")
    public CommonResp<Long> sendCode(@Valid @RequestBody MemberSendCodeReq memberSendCodeReq){
        memberService.sendCode(memberSendCodeReq);
        return new CommonResp<>();
    }

    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(@Valid @RequestBody MemberLoginReq memberLoginReq){
        MemberLoginResp resp = memberService.login(memberLoginReq);
        return new CommonResp<>(resp);
    }
}
