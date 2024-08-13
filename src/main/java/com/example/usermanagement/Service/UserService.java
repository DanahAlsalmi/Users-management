package com.example.usermanagement.Service;

import com.example.usermanagement.Api.ApiException;
import com.example.usermanagement.Model.User;
import com.example.usermanagement.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void updateUser(Integer id , User user) {
        User u = userRepository.findUserById(id);
        if (u == null) {
            throw new ApiException("User not found");
        }
        u.setName(user.getName());
        u.setUsername(user.getUsername());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
        u.setRole(user.getRole());
        u.setAge(user.getAge());
        userRepository.save(u);

    }

    public void deleteUser(Integer id) {
        User u = userRepository.findUserById(id);
        if (u == null) {
            throw new ApiException("User not found");
        }
        userRepository.delete(u);
    }

    public User findUsernameAndPassword(String username, String password) {
        User u = userRepository.findUserByUsernameAndPassword(username, password);
        if (u == null) {
            throw new ApiException("incorrect username or password");
        }
        return u;
    }

    public User findUserByEmail(String email) {
        User u = userRepository.findUserByEmail(email);
        if (u == null) {
            throw new ApiException("the user doesn't exist");
        }
        return u;
    }

    public List<User> findUserByRole(String role) {
        List<User> u = userRepository.findUserByRole(role);
        if (u == null) {
            throw new ApiException("There is no user with role " + role);
        }
        return u;
    }

    public List<User> findUserByAgeOrAbove(int age) {
        List<User> u=userRepository.findAgeOrAbove(age);
        if (u == null) {
            throw new ApiException("There is no user with age " + age);
        }
        return u;
    }
}
