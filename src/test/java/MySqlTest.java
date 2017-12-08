import com.xiaolugo.config.JpaConfiguration;
import com.xiaolugo.entity.Deparment;
import com.xiaolugo.entity.Role;
import com.xiaolugo.entity.User;
import com.xiaolugo.repository.DeparmentRepository;
import com.xiaolugo.repository.RoleRepository;
import com.xiaolugo.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * Created by chinaD on 2017/11/28.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class})
@SpringBootTest
public class MySqlTest {

    Logger logger = LoggerFactory.getLogger(MySqlTest.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    DeparmentRepository deparmentRepository;

    @Autowired
    RoleRepository roleRepository;

    @Before
    public void initData(){
        userRepository.deleteAll();
        deparmentRepository.deleteAll();
        roleRepository.deleteAll();

        Deparment deparment = new Deparment();
        deparment.setName("开发部");
        deparmentRepository.save(deparment);
        Assert.assertNotNull(deparment.getId());

        Role role = new Role();
        role.setName("admin");
        roleRepository.save(role);
        Assert.assertNotNull(role.getId());

        User user = new User();
        user.setName("xiaolu");
        user.setCreateData(new Date());
        user.setDeparment(deparment);
        List<Role> roles = roleRepository.findAll();
        Assert.assertNotNull(roles);
        user.setRoles(roles);

        userRepository.save(user);
        Assert.assertNotNull(user.getId());
    }

    @Test
    public void findPage(){
        Pageable pageable = (Pageable) new PageRequest(0,10,new Sort(Sort.Direction.ASC,"id"));

        Page<User> page = userRepository.findAll(pageable);

        Assert.assertNotNull(page);
        for (User user: page.getContent()){
            logger.info("======user======= user name : {}, department name : {}, role name : {}",
                    user.getName(),user.getDeparment().getName(), user.getRoles().get(0).getName());
        }
    }
}
