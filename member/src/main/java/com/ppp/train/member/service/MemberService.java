package com.ppp.train.member.service;

import com.ppp.train.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;

    public Integer count(){
        return memberMapper.count();
    }
}
