package com.ionic.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ionic.cursomc.domain.Cliente;
import com.ionic.cursomc.repositories.ClienteRepository;
import com.ionic.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	public Optional<Cliente> find(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		// is null
		if (!obj.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado! ID: " + 
					id + ", Tipo: " + Cliente.class.getName());
		}
		return obj;
	}
	
}
