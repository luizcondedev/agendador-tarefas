package com.luizconde.agendadortarefas.infrastructure.client;

import com.luizconde.agendadortarefas.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuarios", url = "${usuario.url}")
public interface UsuarioClient {
    @GetMapping
    UsuarioDTO buscarPorEmail(@RequestParam("email") String email,
                              @RequestHeader("Authorization") String token);
}
