package com.neowiz.process.member.web;

import com.neowiz.process.member.domain.Member;
import com.neowiz.process.member.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * User: David KIM
 * Date: 14. 9. 9.
 * Time: 오전 10:31
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class MemberController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/member.do", method= RequestMethod.GET)
    public ModelAndView handleData(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView mav = new ModelAndView();

        String name = ServletRequestUtils.getStringParameter(request, "name", "");
        try{
        Member member = new Member();
        member.setName(name);


        memberService.addMemberInfo(member);

        mav.setViewName("index");
        }catch(Exception e) {
            logger.error("{}", e);
        }

        return mav;
    }

    // 금액 TX
    @RequestMapping(value = "/money.do", method= RequestMethod.GET)
    public ModelAndView handleData2(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView mav = new ModelAndView();

        int money = ServletRequestUtils.getIntParameter(request, "money", 0);
        int seq = ServletRequestUtils.getIntParameter(request, "seq", 0);

        try{

            Member member = new Member();
            member.setSeq(seq);
            member.setMoney(money);
            memberService.updateMemberMoney(member);

            mav.setViewName("index");

        }catch(Exception e) {
            logger.error("{}", e);
        }

        return mav;
    }



}