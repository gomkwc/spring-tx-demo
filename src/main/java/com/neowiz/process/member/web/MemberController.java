package com.neowiz.process.member.web;

import com.neowiz.common.SessionListener;
import com.neowiz.process.member.domain.Member;
import com.neowiz.process.member.service.MemberService;
import com.neowiz.process.member.service.TxPropagation;
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
import javax.servlet.http.HttpSession;
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

    @Autowired
    private TxPropagation txPropagation;


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


    // 금액 TX
    @RequestMapping(value = "/txPropagation.do", method= RequestMethod.GET)
    public ModelAndView handleData3(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView mav = new ModelAndView();

        String name = ServletRequestUtils.getStringParameter(request, "name", "");
        String div = ServletRequestUtils.getStringParameter(request, "div", "1");

        try{

            Member member = new Member();
            member.setName(name);

            if("1".equals(div))  {
                txPropagation.testRequired(member);
            }else{
                txPropagation.testRequiresNew(member);
            }

            mav.setViewName("index");

        }catch(Exception e) {
            logger.error("{}", e);
        }

        return mav;
    }

    private static int ST_COUNT = 0;

    @RequestMapping(value = "/index.do", method= RequestMethod.GET)
    public ModelAndView handleData4(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView mav = new ModelAndView();
        String div = ServletRequestUtils.getStringParameter(request, "div", "1");


        /** 동시접속자 접속(대기)를 위한 Listener 생성 */
        SessionListener sessionListener = SessionListener.getInstance();

        HttpSession session = request.getSession(); // sessionCreated() is executed

        if("1".equals(div)) {
            session.setAttribute("url", "mkyong.com");
            sessionListener.setLoginSession(session);
        }else{
            session.invalidate();  // sessionDestroyed() is executed
            sessionListener.setLogoutSession(session);

        }

        //로그인 세션 갯수를 호출하는 메소드로 동시접속자를 제한하자!
        if(sessionListener.isMaxLoginSessions()) {

            /* 로그인 시 maxLoginSession 갯수보다 많을경우 예외사항 처리 */
            //ctx.makeErrorResult("현재 동시접속자수가 많습니다. 잠시후에 이용하기시 바랍니다.");
            logger.error("Login Max Over");

        }

        mav.setViewName("index");
        return mav;
    }


    @RequestMapping(value = "/index2.do", method= RequestMethod.GET)
    public String handleData5(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String div = ServletRequestUtils.getStringParameter(request, "div", "1");

        ST_COUNT++;

        logger.error("Static Count : {}", ST_COUNT);
        return "index";
    }
}