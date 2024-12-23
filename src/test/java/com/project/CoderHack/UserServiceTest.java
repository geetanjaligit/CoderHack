package com.project.CoderHack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.CoderHack.Service.UserService;
import com.project.CoderHack.entity.User;
import com.project.CoderHack.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {

@Mock
private UserRepository userRepository;

@InjectMocks
private UserService userService;

//Test for adding a user
@Test
void testAddUser() {
    User user = new User("101", "John", 0, new HashSet<>());
    Mockito.when(userRepository.save(user)).thenReturn(user);

    assertEquals("101", user.getId());
    assertEquals("John", user.getUsername());
}

// Test for Getting a User by ID
@Test
void testGetUserById() {
    User user = new User("101", "John", 50, new HashSet<>());
    Mockito.when(userRepository.findById("101")).thenReturn(Optional.of(user));

    Optional<User> fetchedUser = userService.getById("101");
    assertTrue(fetchedUser.isPresent());
    assertEquals("John", fetchedUser.get().getUsername());
}

// Test for updating a user's score
@Test
void testUpdateUserScore() {
    User user = new User("101", "John", 20, new HashSet<>());
    Mockito.when(userRepository.findById("101")).thenReturn(Optional.of(user));
    Mockito.when(userRepository.save(user)).thenReturn(user);

    User updatedUser = userService.updateUserScore(user, 40);
    assertEquals(60, updatedUser.getScore());
    assertTrue(updatedUser.getBadges().contains("Code Champ"));
}

@Test
void testBadgeAssignment() {
    User user = new User("101", "John", 0, new HashSet<>());

    userService.updateUserScore(user, 25);
    assertTrue(user.getBadges().contains("Code Ninja"));

    userService.updateUserScore(user, 35);
    assertTrue(user.getBadges().contains("Code Champ"));

    userService.updateUserScore(user, 65);
    assertTrue(user.getBadges().contains("Code Master"));
}

    
}
