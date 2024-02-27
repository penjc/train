package com.ppp.train.member.controller;

import com.ppp.train.common.resp.CommonResp;
import com.ppp.train.member.req.MemberRegisterReq;
import com.ppp.train.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public CommonResp<Long> register(MemberRegisterReq memberRegisterReq){
        long register = memberService.register(memberRegisterReq);
        return new CommonResp<Long>(register);
    }
}
