package com.ppp.train.member.service;

import cn.hutool.core.collection.CollUtil;
import com.ppp.train.member.domain.Member;
import com.ppp.train.member.domain.MemberExample;
import com.ppp.train.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;

    public Integer count(){
        return Math.toIntExact(memberMapper.countByExample(null));
    }

    public long register(String mobile){
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        if(CollUtil.isEmpty(list)) {
            Member member = new Member();
            member.setId(System.currentTimeMillis());
            member.setMobile(mobile);
            memberMapper.insert(member);
            return member.getId();
        }
        else{
//            return list.get(0).getId();
            throw new RuntimeException("手机号已注册");
        }
    }
}
