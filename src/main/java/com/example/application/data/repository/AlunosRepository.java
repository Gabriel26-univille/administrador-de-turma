package com.example.application.data.repository;

import com.example.application.data.entity.Alunos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface  AlunosRepository
        extends
            JpaRepository<Alunos, Long>,
            JpaSpecificationExecutor<Alunos> {

}
