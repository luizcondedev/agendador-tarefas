package com.luizconde.agendadortarefas.business.mapper;

import com.luizconde.agendadortarefas.business.dto.TarefasDTO;
import com.luizconde.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasMapper {
    TarefasEntity paraTarefaEntity(TarefasDTO dto);

    TarefasDTO paraTarefaDTO(TarefasEntity entity);
}
