package be.dragoncave.service;

import be.dragoncave.domain.User;

import java.util.List;

/**
 * Created by benoit on 02/11/2016.
 */
public interface UserService {
    User user(String userId);

    User user(int id);

    List<User> users();
}
