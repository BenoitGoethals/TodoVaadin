package be.dragoncave.service;

import be.dragoncave.domain.Role;
import be.dragoncave.domain.UserDetail;
import be.dragoncave.persistance.RoleRepository;
import be.dragoncave.persistance.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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
class SecurityServiceImpl implements SecurityService {

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
        return null;
    }


    @Override
    public void deleteUserDetail(UserDetail userDetail){
        UserDetailRepository.delete(userDetail);
    }

    @Override
    public Role addRole(Role role) {

      return   roleRepository.save(role);
    }

    @Override
    public List<Role> getRoles() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    public void deleteRole(Role role) {

        roleRepository.delete(role);
    }



    @Transactional(readOnly=true)
    @Override
    public User loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        UserDetail userDetail= UserDetailRepository.findByUserName(username);
        Set<Role> roles=new HashSet<>();
        roles.add(userDetail.getRole());
        List<GrantedAuthority> authorities =
                buildUserAuthority(roles);

        return buildUserForAuthentication(userDetail, authorities);

    }

    // Converts com.mkyong.users.model.User user to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(UserDetail user, List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(),
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
