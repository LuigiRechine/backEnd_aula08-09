package com.mapeamentoRelacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapeamentoRelacional.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	List<Aluno> findByCidade(String cidade);
}
