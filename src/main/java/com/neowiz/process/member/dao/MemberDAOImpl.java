package com.neowiz.process.member.dao;

import com.neowiz.process.member.domain.Member;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * User: David KIM
 * Date: 14. 9. 9.
 * Time: 오전 10:35
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class MemberDAOImpl extends SqlSessionDaoSupport implements MemberDAO {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name="sqlSessionFactory")
    public void setSqlSessionFactory(SqlSessionFactory factory) {
        super.setSqlSessionFactory(factory);
    }

    // Mybatis
    @Override
    public void addMemberInfo(Member member) throws Exception {

        logger.debug("1. SEQ : {}", "start");

        getSqlSession().insert("memberDAO.INSERT_MEMBER_INFO", member);
        int seq = member.getSeq();

        logger.debug("1. SEQ : {} / NAME : {}", seq, member.getName());
        logger.debug("1. SEQ : {}", "end");

        getSqlSession().insert("memberDAO.INSERT_MEMBER2_INFO", member);


    }

    // Insert > Select
    @Override
    public void addMemberInfo2(Member member) throws Exception {
        logger.debug("2. SEQ : {}", "start");

        getSqlSession().insert("memberDAO.INSERT_MEMBER_INFO_2", member);
        int seq = member.getSeq();

        logger.debug("2. SEQ : {} / NAME : {}", seq, member.getName());
        logger.debug("2. SEQ : {}", "end");

        getSqlSession().insert("memberDAO.INSERT_MEMBER2_INFO", member);

    }

    // Max()
    @Override
    public void addMemberInfo3(Member member) throws Exception {

        logger.debug("3. SEQ : {}", "start");
        getSqlSession().insert("memberDAO.INSERT_MEMBER_INFO_3", member);
        int seq = member.getSeq();

        logger.debug("3. SEQ : {} / NAME : {}", seq, member.getName());
        logger.debug("3. SEQ : {}", "end");

        getSqlSession().insert("memberDAO.INSERT_MEMBER2_INFO", member);

    }
}
