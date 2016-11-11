package be.dragoncave.service;

import be.dragoncave.domain.Role;
import be.dragoncave.domain.UserDetail;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Created by benoit on 11/11/2016.
 */
public interface SecurityService extends UserDetailsService {
    UserDetail getUserDetail(String userName, String password);

    UserDetail addUserDetail(UserDetail userDetail);

    void deleteUserDetail(UserDetail userDetail);

    Role addRole(Role role);

    List<Role> getRoles();

    void deleteRole(Role role);
}
