package com.example.movie.serviceTest;

import com.example.movie.entity.User;
import com.example.movie.repository.UserRepository;
import com.example.movie.service.CustomUserDetailsService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.Set;

public class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

	/*
	 * @Test public void testLoadUserByUsername_Success() { String username =
	 * "testuser"; com.example.movie.entity.User userEntity = new
	 * com.example.movie.entity.User(); userEntity.setUsername(username);
	 * userEntity.setPassword("encodedPassword");
	 * 
	 * // Mock the UserRepository to return a User
	 * when(userRepository.findByUsername(username)).thenReturn(userEntity);
	 * 
	 * // Create a UserDetails object manually UserDetails userDetails = new User(
	 * username, "encodedPassword", Collections.singleton(new
	 * SimpleGrantedAuthority("ROLE_USER")) );
	 * 
	 * UserDetails result = customUserDetailsService.loadUserByUsername(username);
	 * 
	 * assertNotNull(result); assertEquals(userDetails.getUsername(),
	 * result.getUsername()); assertEquals(userDetails.getPassword(),
	 * result.getPassword()); assertTrue(result.getAuthorities().contains(new
	 * SimpleGrantedAuthority("ROLE_USER"))); }
	 */


    @Test
    public void testLoadUserByUsername_UserNotFound() {
        String username = "nonexistentuser";

        when(userRepository.findByUsername(username)).thenReturn(null);

        UsernameNotFoundException thrown = assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername(username);
        });
        assertEquals("User not found", thrown.getMessage());
    }

    @Test
    public void testLoadUserByUsername_EmptyUsername() {
        String username = "";

        when(userRepository.findByUsername(username)).thenReturn(null);

        UsernameNotFoundException thrown = assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername(username);
        });
        assertEquals("User not found", thrown.getMessage());
    }

    @Test
    public void testLoadUserByUsername_NullUsername() {
        String username = null;

        when(userRepository.findByUsername(username)).thenReturn(null);

        UsernameNotFoundException thrown = assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername(username);
        });
        assertEquals("User not found", thrown.getMessage());
    }
}

