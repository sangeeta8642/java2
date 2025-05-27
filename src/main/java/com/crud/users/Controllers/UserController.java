package com.crud.users.Controllers;

import com.crud.users.CustomExceptions.EmailRequiredException;
import com.crud.users.CustomExceptions.NameRequiredException;
import com.crud.users.DTOs.CreateUserDTO;
import com.crud.users.Models.Users;
import com.crud.users.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<Users> getAllusers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public Users getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody @Valid CreateUserDTO dto) throws EmailRequiredException, NameRequiredException {
        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new EmailRequiredException("Please provide the email");
        } else if (dto.getName() == null || dto.getName().isBlank()) {
            throw new NameRequiredException("Please provide the name");
        } else {
            Users createdUser = userService.createUser(dto);  // 🟢 Accept DTO
            return ResponseEntity.status(HttpStatus.OK).body("User created successfully");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable @Valid int id, @RequestBody @Valid Users user)
            throws EmailRequiredException, NameRequiredException {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new EmailRequiredException("Please provide the email");
        } else if (user.getName() == null || user.getName().isBlank()) {
            throw new NameRequiredException("Please provide the name");
        } else {
            Users updatedUser = userService.updateUser(id, user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Users updated successfully");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        boolean deletedUser = userService.deleteUser(id);
//        userService.deleteUser(id);
        return deletedUser ? ResponseEntity.status(HttpStatus.OK).body("User Deleted") :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Deletion failed");

    }

    @GetMapping("/count/company/{companyId}")
    public ResponseEntity<Long> countUsersInCompany(@PathVariable Long companyId) {
        Long count = userService.countUsersByCompanyId(companyId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<Users>> getUsersByCompany(@PathVariable Long companyId) {
        List<Users> users = userService.getUsersByCompanyId(companyId);
        return ResponseEntity.ok(users);
    }
}
