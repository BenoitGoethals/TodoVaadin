package be.dragoncave.service;

import be.dragoncave.domain.Role;
import be.dragoncave.domain.UserDetail;
import be.dragoncave.persistance.RoleRepository;
import be.dragoncave.persistance.UserDetailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by benoit on 11/11/2016.
 */@Service
public class SecurityServiceImpl implements SecurityService {


    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
     private UserDetailRepository UserDetailRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public UserDetail getUserDetail(String userName, String password){
        UserDetail userDetail=new UserDetail(userName,password,true);
        UserDetail save = UserDetailRepository.save(userDetail);
        return save;
    }

    @Override
    public UserDetail addUserDetail(UserDetail userDetail){
        logger.info("save : "+userDetail);
        return UserDetailRepository.save(userDetail);
    }


    @Override
    public void deleteUserDetail(UserDetail userDetail){
        logger.info("delete : "+userDetail);
        UserDetailRepository.delete(userDetail);
    }

    @Override
    public Role addRole(Role role) {
        logger.info("save : "+role);
      return   roleRepository.save(role);
    }

    @Override
    public List<Role> getRoles() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    public void deleteRole(Role role) {
        logger.info("delete : "+role);
        roleRepository.delete(role);
    }

    @Override
    public long countUserDetaisl() {
        return UserDetailRepository.count();
    }


    @Transactional(readOnly=true)
    @Override
    public User loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        UserDetail userDetail= UserDetailRepository.findByuserName(username);
        if(userDetail==null) throw new UsernameNotFoundException(username);
        Set<Role> roles=new HashSet<>();
        roles.add(userDetail.getRole());
        List<GrantedAuthority> authorities =
                buildUserAuthority(roles);

        return buildUserForAuthentication(userDetail, authorities);

    }

    // Converts com.mkyong.users.model.User user to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(UserDetail user, List<GrantedAuthority> authorities) {
        return new User(user.getUserName(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Role> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
        for (Role userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRoleType()));
        }

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }
}
