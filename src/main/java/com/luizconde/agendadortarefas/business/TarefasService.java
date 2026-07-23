package com.luizconde.agendadortarefas.business;

import com.luizconde.agendadortarefas.business.dto.TarefasDTO;
import com.luizconde.agendadortarefas.business.mapper.TarefasMapper;
import com.luizconde.agendadortarefas.business.mapper.TarefasUpdateMapper;
import com.luizconde.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.luizconde.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.luizconde.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.luizconde.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.luizconde.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.luizconde.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum.PENDENTE;

@Service
@RequiredArgsConstructor
public class TarefasService {
    private final TarefasRepository tarefasRepository;
    private final TarefasMapper mapper;
    private final TarefasUpdateMapper updateMapper;
    private final JwtUtil jwtUtil;

    @Transactional
    public TarefasDTO salvarTarefa(TarefasDTO dto, String token) {
        String email = jwtUtil.extractUsername(token.substring(7));

        TarefasEntity tarefa = mapper.paraTarefaEntity(dto);
        tarefa.setDataCriacao(LocalDateTime.now());
        tarefa.setStatusNotificacaoEnum(PENDENTE);
        tarefa.setEmailUsuario(email);

        TarefasEntity tarefaSalva = tarefasRepository.save(tarefa);

        return mapper.paraTarefaDTO(tarefaSalva);
    }

    public List<TarefasDTO> buscaTarefasPorPeriodo(LocalDateTime dataInicial,
                                                   LocalDateTime dataFinal) {
        return mapper.paraListaTarefaDTO(
                tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal)
        );
    }

    public List<TarefasDTO> buscaTarefasPorEmail(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));

        return mapper.paraListaTarefaDTO(
                tarefasRepository.findByEmailUsuario(email)
        );
    }

    public void deletaTarefaPorId(String id) {
        try {
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Tarefa não encontrada, id não existente: " + id);
        }
    }

    public TarefasDTO alteraStatus(StatusNotificacaoEnum status, String id) {
        TarefasEntity entity = tarefasRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Tarefa não encontrada, id não existente: " + id)
                );
        entity.setStatusNotificacaoEnum(status);

        return mapper.paraTarefaDTO(
                tarefasRepository.save(entity)
        );
    }

    public TarefasDTO updateTarefas(TarefasDTO dto, String id) {
        TarefasEntity tarefa = tarefasRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Tarefa não encontrada, id não existente: " + id)
                );

        updateMapper.updateTarefas(dto, tarefa);
        return mapper.paraTarefaDTO(
                tarefasRepository.save(tarefa)
        );
    }
}
