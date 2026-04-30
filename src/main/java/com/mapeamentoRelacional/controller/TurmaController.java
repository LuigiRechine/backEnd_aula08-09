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

import com.mapeamentoRelacional.entity.Turma;
import com.mapeamentoRelacional.service.TurmaService;

@RestController
@RequestMapping("/turma")
public class TurmaController {
	@Autowired
    private TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarTurmaId(@PathVariable Long id) {
    	Turma turma = turmaService.buscarTurmaPorId(id);
        if (turma != null) {
            return ResponseEntity.ok(turma);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/")
    public ResponseEntity<List<Turma>> buscarTodosTurma() {
        List<Turma> turma = turmaService.buscarTodosTurma();
        return ResponseEntity.ok(turma);
    }

 
    @PostMapping("/")
    public ResponseEntity<Turma> salvaTurma(@RequestBody Turma turma) {
        Turma saveTurma = turmaService.salvarTurma(turma);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveTurma);
    }
    
 
    @PutMapping("/{id}")
    public ResponseEntity<Turma> alteraTurma(@PathVariable Long id, @RequestBody Turma turma) {
        Turma atualizaTurma= turmaService.atualizarTurma(id, turma);
        if (atualizaTurma!= null) {
            return ResponseEntity.ok(atualizaTurma);
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }
    
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Turma> apagaTurma(@PathVariable Long id) {
        boolean apagaTurma= turmaService.apagarTurma(id);
        if (apagaTurma) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }
}
