package com.Dio.labProjeto.Service.impl;

import com.Dio.labProjeto.Service.ClienteService;
import com.Dio.labProjeto.Service.ViaCepService;
import com.Dio.labProjeto.model.Cliente;
import com.Dio.labProjeto.model.ClienteRepository;
import com.Dio.labProjeto.model.Endereco;
import com.Dio.labProjeto.model.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
       Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco =enderecoRepository.findById(cep).orElseGet(() ->{
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {

        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()){

        }
    }

    private void salvarClientecomCep(Cliente cliente){

        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
        Endereco novoEndereco = viaCepService.consultarCep(cep);
        enderecoRepository.save(novoEndereco);
        return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);

    }
}
