package com.sb.NotePad.repository;

import com.sb.NotePad.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    boolean existsByEmail(String email);
}
