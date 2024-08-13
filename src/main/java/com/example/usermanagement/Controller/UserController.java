package com.example.usermanagement.Controller;

import com.example.usermanagement.Model.User;
import com.example.usermanagement.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String msg= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body("User added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String msg= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        userService.updateUser(id, user);
        return ResponseEntity.status(200).body("User updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.status(200).body("User deleted successfully");
    }

    @GetMapping("/username-password/{username}/{password}")
    public ResponseEntity getUserByUsernameAndPassword(@PathVariable String username, @PathVariable String password) {
        User user = userService.findUsernameAndPassword(username, password);
        return ResponseEntity.status(200).body("User correctly found:"+user);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email) {
        User user = userService.findUserByEmail(email);
        return ResponseEntity.status(200).body("User correctly found :"+user);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity getUserByRole(@PathVariable String role) {
        List<User> users = userService.findUserByRole(role);
        return ResponseEntity.status(200).body(users);
    }

    @GetMapping("/age/{age}")
    public ResponseEntity getUserByAge(@PathVariable int age) {
        List<User> user =userService.findUserByAgeOrAbove(age);
        return ResponseEntity.status(200).body(user);
    }
}
