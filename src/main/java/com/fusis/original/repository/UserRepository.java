package com.fusis.original.repository;



import com.fusis.original.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    

    boolean existsByEmail(String email);


}
