package com.crud.users.Services;

import com.crud.users.Models.Company;
import com.crud.users.Repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service

public class CompanyService {

    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public List<Company> getAllCompanies() {
        return repository.findAll();
    }

    public Optional<Company> getCompanyById(Long id) {
        return repository.findById(id);
    }

    public Company createCompany(Company company) {
        return repository.save(company);
    }

    public Company updateCompany(Long id, Company newData) {
        return repository.findById(id).map(company -> {
                    company.setName(newData.getName());
                    company.setLocation(newData.getLocation());
                    company.setIndustry(newData.getIndustry());

                    return repository.save(company);
                })
                .orElseGet(() -> {
                    newData.setId(id);

                    return repository.save(newData);
                });
    }


    public void deleteCompany(Long id) {
        repository.deleteById(id);
    }

}
