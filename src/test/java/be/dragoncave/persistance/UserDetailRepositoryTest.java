package be.dragoncave.persistance;

import be.dragoncave.domain.Role;
import be.dragoncave.domain.UserDetail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

/**
 * Created by benoit on 11/11/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes =SpecificTestConfig.class)
@SpringBootTest
//@DataJpaTest(showSql = true)
@ActiveProfiles({"test-profile"})
//@Transactional( propagation = Propagation.REQUIRED)
public class UserDetailRepositoryTest {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addUserDetail(){
        Role role=new Role("admin");
        Role rol2=new Role("user");
        roleRepository.save(role);
        roleRepository.save(rol2);

        UserDetail userDetail=new UserDetail("dfdf","sdsf",true);
        userDetail.setRole(role);
        userDetailRepository.save(userDetail);
        assertEquals(2,roleRepository.count());
        assertEquals(1,userDetailRepository.count());
    }

}