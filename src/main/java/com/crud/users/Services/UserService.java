package com.crud.users.Services;

import com.crud.users.DTOs.CreateUserDTO;
import com.crud.users.Models.Company;
import com.crud.users.Models.Users;
//import com.example.practice.Models.User;
import com.crud.users.Repositories.CompanyRepository;
import com.crud.users.Repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
//    private List<Users> users = new ArrayList<>();
//    private int currentUser=0;

    private final CompanyRepository companyRepository;

    public UserService(CompanyRepository companyRepository, UserRepo userRepo) {
        this.companyRepository = companyRepository;
        this.userRepo = userRepo;
    }

    private UserRepo userRepo;

    public List<Users> getUsers() {
        return userRepo.findAll();
    }

    public Users getUser(int id) {
//       return users.stream().filter(u -> u.getId() == id).findFirst().orElse(new Users(null,0,null));
//        return null;
        return userRepo.findById(id).orElse(null);
    }

    public Users createUser(CreateUserDTO dto) {
//        user.setId(currentUser++);
//        users.add(user);
//        return user;

        Company company = companyRepository.findById(dto.getCompany_id())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        Users user = new Users();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setCompany(company);

        return userRepo.save(user);
    }

    public Users updateUser(int id, Users updatedUser) {
        Users user = getUser(id);
        if (user != null) {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            return userRepo.save(user);
        }

        return null;


    }

    public boolean deleteUser(int id) {
//        return users.removeIf(u->u.getId()==id);
        if (userRepo.existsById(id)) {  // Check if the user exists
            userRepo.deleteById(id);  // Delete the user
            return true;  // Return true if deletion succeeds
        }
        return false;
    }

    public Long countUsersByCompanyId(Long companyId) {
        return userRepo.countUsersByCompanyId(companyId);
    }

    public List<Users> getUsersByCompanyId(Long companyId) {
        return userRepo.findByCompanyId(companyId);
    }
}
