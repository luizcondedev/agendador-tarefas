package com.luizconde.agendadortarefas.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luizconde.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TarefasDTO(
     String id,
     String nomeTarefa,
     String descricao,
     LocalDateTime dataCriacao,
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
     LocalDateTime dataEvento,
     String emailUsuario,
     LocalDateTime dataAlteracao,
     StatusNotificacaoEnum statusNotificacaoEnum
) {
}
