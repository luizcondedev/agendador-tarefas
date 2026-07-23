package com.luizconde.agendadortarefas.controller;

import com.luizconde.agendadortarefas.business.TarefasService;
import com.luizconde.agendadortarefas.business.dto.TarefasDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {
    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO> salvaTarefa(@RequestBody TarefasDTO dto,
                                                  @RequestHeader("Authorization") String token){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                tarefasService.salvarTarefa(dto, token)
        );
    }
}
