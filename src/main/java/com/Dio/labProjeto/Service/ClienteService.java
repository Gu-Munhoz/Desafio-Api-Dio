package com.Dio.labProjeto.Service;

import com.Dio.labProjeto.model.Cliente;

public interface ClienteService {

    Iterable<Cliente> buscarTodos();

    Cliente buscarPorId(Long id);

    void inserir (Cliente cliente);

    void atualizar(Long id, Cliente cliente);

    void deletar (Long id);
}
