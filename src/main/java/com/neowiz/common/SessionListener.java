package com.neowiz.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * User: David KIM
 * Date: 14. 9. 13.
 * Time: 오후 3:37
 * To change this template use File | Settings | File Templates.
 */
public class SessionListener implements HttpSessionListener {

    public static SessionListener sessionManager = null;
    public static Hashtable sessionMonitor;
    public static Hashtable loginSessionMonitor;
    public static int maxSessionValidCount;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public SessionListener() {
        if (sessionMonitor == null) sessionMonitor = new Hashtable();
        if (loginSessionMonitor == null) loginSessionMonitor = new Hashtable();
        //sessionManager = this;

        maxSessionValidCount = 2;
    }

    public static synchronized SessionListener getInstance() {
        if (sessionManager == null)
            sessionManager = new SessionListener();
        return sessionManager;
    }

    /** 현재 활성화 된 session의 수를 반환한다. */
    public int getActiveSessionCount() {
        return sessionMonitor.size();
    }

    /** 현재 등록된 session의 id목록을 반환한다. */
    public Enumeration getIds() {
        return sessionMonitor.keys();
    }

    /** 전체 세션갯수를 측정하여 로그인(대기)상태 메세지 창 호출 */
    public boolean isMaxLoginSessions() {
        boolean retVal = false;

        if(maxSessionValidCount <= getActiveLoginSessionCount()) {
            retVal = true;
        }

        return retVal;
    }

    /** 현재 활성화 된 session의 수를 반환한다. */
    public int getActiveLoginSessionCount() {
        return loginSessionMonitor.size();
    }

    /** 로그인한 Session Put */
    public void setLoginSession(HttpSession session) {
        synchronized (loginSessionMonitor) {
            loginSessionMonitor.put(session.getId(), session);

            logger.error("###############################################################################");
            //logger.error("# 1 (로그인 허용인원수) : {} #", maxSessionValidCount);
            //logger.error("# 2 (사이트 접속자수) : {} #", getActiveSessionCount());
            logger.error("# 1 (로그인 허용인원수) : {} #", maxSessionValidCount);
            logger.error("# 3 (로그인 사용자수) : {} #", getActiveLoginSessionCount());
            logger.error("###############################################################################");
        }
    }

    /** 로그아웃한 Session Remove */
    public void setLogoutSession(HttpSession session) {
        synchronized (loginSessionMonitor) {
            loginSessionMonitor.remove(session.getId());
        }
    }


    /** 세션 생성시 이벤트 처리 **/
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        synchronized (sessionMonitor) {
            sessionMonitor.put(session.getId(), session);

            logger.error("############################ Create ############################################");
            logger.error("# 1 (로그인 허용인원수) : {} #", maxSessionValidCount);
            logger.error("# 2 (사이트 접속자수) : {} #", getActiveSessionCount());
            logger.error("############################ Create ############################################");
        }
    }

    /** 세션 소멸(종료)시 이벤트 처리 **/
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        synchronized (sessionMonitor) {
            sessionMonitor.remove(session.getId());
            loginSessionMonitor.remove(session.getId());

            logger.error("################################# Destory ########################################");
            logger.error("# 1 (로그인 허용인원수) : {} #", maxSessionValidCount);
            logger.error("# 2 (사이트 접속자수) : {} #", getActiveSessionCount());
            logger.error("# 3 (로그인 사용자수) : {} #", getActiveLoginSessionCount());
            logger.error("################################# Destory ########################################");

        }
    }
}