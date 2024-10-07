package com.example.thymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.thymeleaf.entity.User;

@Repository
public interface IRepositoryUser extends JpaRepository<User, Long> {
    
    @Query(value = "select * from users  where username = :username", nativeQuery = true)
	User findByUsername(@Param("username") String username);  
}
