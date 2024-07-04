package com.omkar_smartcontactmanager.scm.services;

import com.omkar_smartcontactmanager.scm.entities.User;
import com.omkar_smartcontactmanager.scm.exceptions.ResourceNotFoundException;
import com.omkar_smartcontactmanager.scm.repositories.UserRepositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService{

    private final UserRepositories userRepositories;

    public UserServiceImpl(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {

        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);

        return userRepositories.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepositories.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User user1 = userRepositories.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        //update user1 from user
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setAbout(user.getAbout());
        user1.setPhoneNumber(user.getPhoneNumber());
        user1.setProfilePic(user.getProfilePic());
        user1.setEnabled(user.isEnabled());
        user1.setEmailVerified(user.isEmailVerified());
        user1.setPhoneVerified(user.isPhoneVerified());
        user1.setProvider(user.getProvider());
        user1.setProviderUserId(user.getProviderUserId());

        //save the user in database;
        User save = userRepositories.save(user1);

        return Optional.of(save);
    }

    @Override
    public void deleteUser(String id) {
        User user1 = userRepositories.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public boolean isUserExist(String userId) {
        User user1 = userRepositories.findById(userId).orElse(null);
        return user1 != null ? true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
       User user =  userRepositories.findByEmail(email).orElse(null);
       return user!=null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepositories.findAll();
    }
}
