package com.sda.finalproject.authmvc.repositories;

import com.sda.finalproject.authmvc.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmailAndPassword(String email, String password);

}