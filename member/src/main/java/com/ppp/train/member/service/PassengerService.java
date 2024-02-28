package com.ppp.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.ppp.train.common.context.LoginMemberContext;
import com.ppp.train.common.util.SnowUtil;
import com.ppp.train.member.domain.Passenger;
import com.ppp.train.member.domain.PassengerExample;
import com.ppp.train.member.mapper.PassengerMapper;
import com.ppp.train.member.req.PassengerQueryReq;
import com.ppp.train.member.req.PassengerSaveReq;
import com.ppp.train.member.resp.PassengerQueryResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {
    @Autowired
    private PassengerMapper passengerMapper;

    public void save(PassengerSaveReq req){
        DateTime now = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        passenger.setMemberId(LoginMemberContext.getMemberId());
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        passengerMapper.insert(passenger);
    }

    public List<PassengerQueryResp> queryList(PassengerQueryReq passengerQueryReq){
        PassengerExample passengerExample = new PassengerExample();
        PassengerExample.Criteria criteria = passengerExample.createCriteria();
        if(ObjectUtil.isNotNull(passengerQueryReq.getMemberId())) {
            criteria.andMemberIdEqualTo(passengerQueryReq.getMemberId());
        }
        List<Passenger> passengersList = passengerMapper.selectByExample(passengerExample);
        List<PassengerQueryResp> list = BeanUtil.copyToList(passengersList, PassengerQueryResp.class);
        return list;
    }
}
