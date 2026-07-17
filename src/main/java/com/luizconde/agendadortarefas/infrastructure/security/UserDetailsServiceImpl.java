package com.luizconde.agendadortarefas.infrastructure.security;


import com.luizconde.agendadortarefas.business.dto.UsuarioDTO;
import com.luizconde.agendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl{

    private UsuarioClient client;

    public UserDetails loadUserByUsername(String email, String token){
        UsuarioDTO usuario = client.buscarPorEmail(email, token);

        return User
                .withUsername(usuario.getEmail()) // Define o nome de usuário como o e-mail
                .password(usuario.getSenha()) // Define a senha do usuário
                .build(); // Constrói o objeto UserDetails
    }
}
