package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUsername(String username);
    User findById(long id);
    void deleteById(long id);
}
