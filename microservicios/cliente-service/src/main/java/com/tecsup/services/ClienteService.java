package com.tecsup.services;

import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Cliente;
import com.tecsup.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public List<Cliente> listar() {
        return repo.findAll();
    }

    public Cliente guardar(Cliente p) {
        return repo.save(p);
    }

    public Cliente obtener(Long id) {
        return repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Cliente no encontrado con id: " + id
                ));
    }

    public void eliminar(Long id) {
        Cliente entidad = repo.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Cliente no encontrado con id: " + id)
                );
        repo.delete(entidad);
    }
}
