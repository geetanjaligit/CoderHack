package com.project.CoderHack.Service;

import java.util.Comparator;

import com.project.CoderHack.entity.User;

public class UserScoreComparator implements Comparator<User>{

    @Override
    public int compare(User u1, User u2) {
        return Integer.compare(u2.getScore(), u1.getScore()); 
    }
}
