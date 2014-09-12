package com.neowiz.process.member.service;

import com.neowiz.process.member.dao.MemberDAO;
import com.neowiz.process.member.domain.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: David KIM
 * Date: 14. 9. 12.
 * Time: 오후 1:46
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TxPropagationImpl implements TxPropagation {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private MemberService memberService;

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void testRequired(Member member) throws Exception{


        try{

            memberDAO.addMemberInfo(member);
            memberService.testRequired();

        } catch(Exception e){
            logger.error("testRequired : {}", e);
        }

        // 또는  memberService.testRequired();

    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void testRequiresNew(Member member) throws Exception{


        try {

            memberDAO.addMemberInfo(member);
            memberService.testRequiresNew();

        } catch(Exception e){
            logger.error("testRequiresNew : {}", e);
            // catch 에서 exception 핸들링이 필요.. 던지면 안됨.
        }
    }

}
