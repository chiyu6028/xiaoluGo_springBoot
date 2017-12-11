import com.xiaolugo.entity.Deparment;
import com.xiaolugo.entity.Role;
import com.xiaolugo.entity.User;
import com.xiaolugo.redis.RedisConfig;
import com.xiaolugo.redis.UserRedis;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chinaD on 2017/12/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RedisConfig.class, UserRedis.class})
@SpringBootTest
public class RedisTest {

    Logger logger = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    UserRedis userRedis;

    @Before
    public void setUp(){
        Deparment deparment = new Deparment();
        deparment.setName("研发部");

        Role role = new Role();
        role.setName("admin");

        User user = new User();
        user.setName("user");
        user.setCreateData(new Date());
        user.setDeparment(deparment);

        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);

        userRedis.delete(this.getClass().getName()+":userByName:"+user.getName());

        userRedis.add(this.getClass().getName()+":userByName:"+user.getName(),10L,user);
    }

    @Test
    public void getUser(){
        User user = userRedis.getUser(this.getClass().getName()+":userByName:user");
        Assert.notNull(user);
        logger.info("+++++++++user+++++++++name:{},deparment:{},role:{}",user.getName(),user.getDeparment(),user.getRoles().get(0));
    }
}
