package com.backend.repositotory;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.ColaboradorEntity;

public interface ColaboradorRepository extends JpaRepository<ColaboradorEntity, Integer> {

	Optional<ColaboradorEntity> findByNome(String username);

}
