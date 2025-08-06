// Local: src/main/java/com/seusalao/sistemadesalao/service/ClienteService.java
package com.seusalao.sistemadesalao.service;

import com.seusalao.sistemadesalao.model.Cliente;
import com.seusalao.sistemadesalao.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // ✅ CORREÇÃO AQUI: Adicionamos o método de exclusão de volta.
    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }
}