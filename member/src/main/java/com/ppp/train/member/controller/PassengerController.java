package com.ppp.train.member.controller;

import com.ppp.train.common.context.LoginMemberContext;
import com.ppp.train.common.resp.CommonResp;
import com.ppp.train.common.resp.PageResp;
import com.ppp.train.member.req.PassengerQueryReq;
import com.ppp.train.member.req.PassengerSaveReq;
import com.ppp.train.member.resp.PassengerQueryResp;
import com.ppp.train.member.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody PassengerSaveReq req){
        passengerService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp> queryList(@Valid PassengerQueryReq req){
        req.setMemberId(LoginMemberContext.getMemberId());
        PageResp<PassengerQueryResp> passengerQueryRespList = passengerService.queryList(req);
        return new CommonResp<>(passengerQueryRespList);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id){
        passengerService.delete(id);
        return new CommonResp<>();
    }

}
