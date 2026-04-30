package com.mapeamentoRelacional.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.mapeamentoRelacional.entity.Turma;
import com.mapeamentoRelacional.repository.TurmaRepository;

@Service
public class TurmaService {
	final private TurmaRepository turmaRepository;

	public TurmaService(TurmaRepository turmaRepository) {
		this.turmaRepository = turmaRepository;
	}
	
	public List<Turma> buscarTodosTurma(){
		return turmaRepository.findAll();
	}
	
	public Turma buscarTurmaPorId(Long id){
		  Optional <Turma> turma = turmaRepository.findById(id);
	        return turma.orElse(null);
	}
	
	public Turma salvarTurma(Turma atTurma) {
		return turmaRepository.save(atTurma);
	}
	
	public Turma atualizarTurma(Long id, Turma atTurma) {
		Optional <Turma> exeTurma = turmaRepository.findById(id);
		if(exeTurma.isPresent()) {
			Turma turma =exeTurma.get();
			BeanUtils.copyProperties(atTurma, turma, "id");
			return turmaRepository.save(turma);
		}
		
			return null;
		
	}
	
	public Boolean apagarTurma(Long id) {
		Optional<Turma> exeTurma = turmaRepository.findById(id);
		if(exeTurma.isPresent()) {
			turmaRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
