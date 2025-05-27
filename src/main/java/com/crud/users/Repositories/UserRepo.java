package com.crud.users.Repositories;

import com.crud.users.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
    @Query("SELECT COUNT(u) FROM Users u WHERE u.company.id = :companyId")
    Long countUsersByCompanyId(@Param("companyId") Long companyId);

    List<Users> findByCompanyId(Long companyId);
}
