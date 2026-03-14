package com.storageserver.repository;

import com.storageserver.model.Pengelola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PengelolaRepository extends JpaRepository<Pengelola, Integer> {
    
    Optional<Pengelola> findByEmail(String email);
}
