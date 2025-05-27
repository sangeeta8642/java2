package com.crud.users.Repositories;

import com.crud.users.Models.Company;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompanyRepository extends JpaRepository<Company, Long> {

}
