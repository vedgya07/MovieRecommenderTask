package com.example.movie.serviceTest;

import com.example.movie.entity.User;
import com.example.movie.repository.UserRepository;
import com.example.movie.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User(1L, "user1", "password1", "ROLE_USER");
        User user2 = new User(2L, "user2", "password2", "ROLE_USER");

        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        List<User> result = userService.getAllUsers();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(user1));
        assertTrue(result.contains(user2));
    }

    @Test
    public void testGetUserById() {
        User user = new User(1L, "user1", "password1", "ROLE_USER");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
    }

    @Test
    public void testGetUserById_NotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            userService.getUserById(1L);
        });
        assertEquals("User not found", thrown.getMessage());
    }

    @Test
    public void testRegisterUser() {
        User user = new User(1L, "user1", "password1", "ROLE_USER");
        String encodedPassword = "encodedPassword";

        when(passwordEncoder.encode("password1")).thenReturn(encodedPassword);
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.registerUser(user);
        assertNotNull(result);
        assertEquals(encodedPassword, result.getPassword());
        verify(userRepository).save(user);
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;

        doNothing().when(userRepository).deleteById(userId);

        userService.deleteUser(userId);

        verify(userRepository).deleteById(userId);
    }

    @Test
    public void testRegisterUser_NullPassword() {
        User user = new User(1L, "user1", null, "ROLE_USER");

        when(passwordEncoder.encode(null)).thenReturn(null);
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.registerUser(user);
        assertNotNull(result);
        assertNull(result.getPassword());
        verify(userRepository).save(user);
    }
}
