package com.simpleform2.simpleform2.repository;

import com.simpleform2.simpleform2.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

    Optional<UserModel> findByLoginAndPassword(String login, String password);
    Optional<UserModel> findFirstByLogin(String login);

}
