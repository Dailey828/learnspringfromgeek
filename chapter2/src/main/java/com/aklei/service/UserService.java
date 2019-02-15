package com.aklei.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aklei.domain.LoginLog;
import com.aklei.domain.User;
import com.aklei.dao.LoginLogDao;
import com.aklei.dao.UserDao;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private UserDao userDao;
    private LoginLogDao loginLogDao;
    //注入Dao Bean
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }

    public boolean hasMatchUser(String username,String password){
        int matchCount = userDao.getMatchCount(username,password);
        return matchCount>0;
    }

    public User findUserByUserName(String username){
        return userDao.findUserByUserName(username);
    }

    @Transactional
    public void loginSuccess(User user){
        user.setCredits(5+user.getCredits());
        LoginLog loginLog = new LoginLog();
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        loginLog.setUserId(user.getUserId());
        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }

}
