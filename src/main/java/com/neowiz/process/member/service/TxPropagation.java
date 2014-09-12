package com.neowiz.process.member.service;

import com.neowiz.process.member.domain.Member;

/**
 * User: David KIM
 * Date: 14. 9. 12.
 * Time: 오후 1:45
 * To change this template use File | Settings | File Templates.
 */
public interface TxPropagation {

    public void testRequired(Member member) throws Exception;

    public void testRequiresNew(Member member) throws Exception;

}
