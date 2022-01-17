package com.n11.fourthhomeworktarikcoskun94.repository;

import com.n11.fourthhomeworktarikcoskun94.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByTcIdNumber(String tcIdNumber);

    boolean existsByTcIdNumber(String tcIdNumber);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}
