package be.dragoncave.service;

import be.dragoncave.domain.User;
import be.dragoncave.persistance.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by benoit on 02/11/2016.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User user(String userId) {

        User user= userRepository.findByUserID(userId);
        logger.info("Saved user"+user);
        return user;
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
