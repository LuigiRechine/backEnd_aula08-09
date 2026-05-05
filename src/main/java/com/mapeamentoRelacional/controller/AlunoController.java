package com.mapeamentoRelacional.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapeamentoRelacional.entity.Aluno;
import com.mapeamentoRelacional.service.AlunoService;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
	@Autowired
    private AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarAlunoId(@PathVariable Long id) {
    	Aluno aluno = alunoService.buscarAlunoPorId(id);
        if (aluno != null) {
            return ResponseEntity.ok(aluno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/")
    public ResponseEntity<List<Aluno>> buscarTodosAluno() {
        List<Aluno> aluno = alunoService.buscarTodosAluno();
        return ResponseEntity.ok(aluno);
    }

 
    @PostMapping("/")
    public ResponseEntity<Aluno> salvaAluno(@RequestBody Aluno aluno) {
        Aluno saveAluno = alunoService.salvarAluno(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveAluno);
    }
    
 
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> alteraAluno(@PathVariable Long id, @RequestBody Aluno aluno) {
        Aluno atualizaAluno= alunoService.atualizarAluno(id, aluno);
        if (atualizaAluno!= null) {
            return ResponseEntity.ok(atualizaAluno);
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }
    
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Aluno> apagaAluno(@PathVariable Long id) {
        boolean apagaAluno= alunoService.apagarAluno(id);
        if (apagaAluno) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }
    
    @GetMapping("/cidade/{cidade}")
    public ResponseEntity<List<Aluno>> buscarAlunosPorCidade(@PathVariable String cidade){
    	List<Aluno> alunos = alunoService.buscarAlunosPorCidade(cidade);
    	return ResponseEntity.ok(alunos);
    }
}
