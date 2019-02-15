package com.aklei.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import com.aklei.domain.User;

@Repository //通过spring注解定义一个Dao类
public class UserDao {
    private JdbcTemplate jdbcTemplate;
    private final static String MATCH_COUNT_SQL = "select count(*) from t_user "+
            "where user_name =? and password =?";
    private final static String UPDATE_LOGIN_INFO_SQL = "update t_user set "+
            "last_visit=?,last_ip=?,credits=? where user_id=?";
    private final static String QUERY_BY_USERNAME_SQL = " select user_id,user_name,credits "+
            " from t_user where user_name=? ";
    @Autowired//自动注入JdbcTemplate的Bean
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMatchCount(String username,String password){
        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL,new Object[]{username,password},Integer.class);
    }

    public User findUserByUserName(final String username){
        final User user = new User();
        jdbcTemplate.query(QUERY_BY_USERNAME_SQL,new Object[]{username},
                new RowCallbackHandler(){
                    public void processRow(ResultSet rs) throws SQLException{
                        user.setUserId(rs.getInt("user_id"));
                        user.setUserName(rs.getString("user_name"));
                        user.setCredits(rs.getInt("credits"));
                    }
                });
        return user;
    }
    public void updateLoginInfo(User user){
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL,new Object[]{user.getLastVisit(),user.getLastIp(),user.getCredits(),
        user.getUserId()});
    }

}
