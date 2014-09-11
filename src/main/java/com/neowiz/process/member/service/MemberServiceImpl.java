package com.neowiz.process.member.service;

import com.neowiz.process.member.dao.MemberDAO;
import com.neowiz.process.member.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: David KIM
 * Date: 14. 9. 9.
 * Time: 오전 10:35
 * To change this template use File | Settings | File Templates.
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDAO memberDAO;

    // Mybatis
    @Transactional(propagation= Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void addMemberInfo(Member member) throws Exception {
        memberDAO.addMemberInfo(member);
    }

    // Insert > Select
    @Transactional(propagation= Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void addMemberInfo2(Member member) throws Exception {
        memberDAO.addMemberInfo2(member);
    }

    // MAX
    @Transactional(propagation= Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void addMemberInfo3(Member member) throws Exception {
        memberDAO.addMemberInfo3(member);
    }
}
