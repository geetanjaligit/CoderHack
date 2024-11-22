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
        if (existingUser == null) {
            throw new IllegalArgumentException("User not found.");
        }
        int updatedScore=existingUser.getScore()+newScore;
        if(updatedScore>100){
            updatedScore=100;
        }
        existingUser.setScore(updatedScore);
        updatebadges(existingUser,newScore);
        return userRepository.save(existingUser);
    }

    public void updatebadges(User user,int currentScore){
        if(currentScore>=1&& currentScore<30)
            user.getBadges().add("Code Ninja");
        else if(currentScore>=30 && currentScore<60)
            user.getBadges().add("Code Champ");
        else if(currentScore>=60 && currentScore<=100)
            user.getBadges().add("Code Master");
    }

}
