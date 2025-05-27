package com.crud.users.Controllers;

import com.crud.users.Models.Company;
import com.crud.users.Services.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }


    @GetMapping
    public List<Company> getAll() {
        return service.getAllCompanies();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Company> getById(@PathVariable Long id) {
        return service.getCompanyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping


    public Company create(@RequestBody Company company) {
        return service.createCompany(company);
    }

    @PutMapping("/{id}")


    public Company update(@PathVariable Long id, @RequestBody Company company) {
        return service.updateCompany(id, company);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }


}
