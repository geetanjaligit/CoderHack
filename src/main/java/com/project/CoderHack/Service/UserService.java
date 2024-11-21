package com.project.CoderHack.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.CoderHack.entity.User;
import com.project.CoderHack.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    
    public void saveUser(User user) {
        try{
        userRepository.save(user);
        }
        catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("Error while saving entry",e);
        }
    }

    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        Collections.sort(users, new UserScoreComparator());
        return users;
    }

    public Optional<User> getById(String id){
        return userRepository.findById(id);
    }

    public void deleteUser(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User with ID " + id + " does not exist.");
        }
    }

    public User updateUserScore(User existingUser,int newScore){

        if(newScore<0 || newScore>100){
            throw new IllegalArgumentException("Score must be between 0 and 100.");
        }
        existingUser.setScore(newScore);
        updatebadges(existingUser);
        return userRepository.save(existingUser);
    }

    public void updatebadges(User user){
        if(user.getScore()>=1&& user.getScore()<30)
            user.getBadges().add("Code Ninja");
        else if(user.getScore()>=30 && user.getScore()<60)
            user.getBadges().add("Code Champ");
        else if(user.getScore()>=60 && user.getScore()<=100)
            user.getBadges().add("Code Master");
    }

    


}
