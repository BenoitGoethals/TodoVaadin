package be.dragoncave.service;

import be.dragoncave.domain.User;
import be.dragoncave.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by benoit on 02/11/2016.
 */
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User user(String userId) {

        return userRepository.findByUserID(userId);
    }

    @Override
    public User user(int id) {

        return userRepository.findOne(id);
    }

    @Override
    public List<User> users() {
        return (List<User>) userRepository.findAll();
    }
}
