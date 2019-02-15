package com.aklei.service;
import java.util.Date;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import com.aklei.domain.User;
import static org.testng.Assert.*;

@ContextConfiguration("classpath*:/aklei-context.xml")
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests{
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void testHasMatchUser() throws Exception{
        boolean b1 = userService.hasMatchUser("admin","123456");
        boolean b2 = userService.hasMatchUser("admin","1111");
        assertTrue(b1);
        assertTrue(!b2);
    }

    @Test
    public void testFindUserByUserName() throws Exception{
        User user  = userService.findUserByUserName("admin");
        assertEquals(user.getUserName(),"admin");
    }
}
