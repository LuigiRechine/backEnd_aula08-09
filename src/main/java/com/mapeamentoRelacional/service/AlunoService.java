package com.mapeamentoRelacional.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.mapeamentoRelacional.entity.Aluno;
import com.mapeamentoRelacional.repository.AlunoRepository;

@Service
public class AlunoService {
	final private AlunoRepository alunoRepository;

	public AlunoService(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}
	
	public List<Aluno> buscarTodosAluno(){
		return alunoRepository.findAll();
	}
	
	public Aluno buscarAlunoPorId(Long id){
		  Optional <Aluno> aluno = alunoRepository.findById(id);
	        return aluno.orElse(null);
	}
	
	public Aluno salvarAluno(Aluno atAluno) {
		return alunoRepository.save(atAluno);
	}
	
	public Aluno atualizarAluno(Long id, Aluno atAluno) {
		Optional <Aluno> exeAluno = alunoRepository.findById(id);
		if(exeAluno.isPresent()) {
			Aluno aluno =exeAluno.get();
			BeanUtils.copyProperties(atAluno, aluno, "id");
			return alunoRepository.save(aluno);
		}
		
			return null;
		
	}
	
	public Boolean apagarAluno(Long id) {
		Optional<Aluno> exeAluno = alunoRepository.findById(id);
		if(exeAluno.isPresent()) {
			alunoRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public List<Aluno> buscarAlunosPorCidade (String cidade) {
		return alunoRepository.findByCidade(cidade);
	}
}
