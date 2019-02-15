package com.aklei.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.aklei.domain.LoginLog;

@Repository //通过spring注解定义一个Dao类
public class LoginLogDao {
    private JdbcTemplate jdbcTemplate;
    @Autowired//自动注入JdbcTemplate的Bean
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String LOGIN_LOG_SQL = " insert into t_login_log(user_id,ip,login_datetime) "+
            " values(?,?,?) ";
    public void insertLoginLog(LoginLog loginLog){
        Object[] args = {loginLog.getUserId(),loginLog.getIp(),loginLog.getLoginDate()};
        jdbcTemplate.update(LOGIN_LOG_SQL,args);
    }


}
