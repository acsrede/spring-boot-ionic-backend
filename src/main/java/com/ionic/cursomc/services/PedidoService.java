package com.ionic.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ionic.cursomc.domain.Pedido;
import com.ionic.cursomc.repositories.PedidoRepository;
import com.ionic.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;

	public Optional<Pedido> find(Integer id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		// is null
		if (!obj.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado! ID: " + 
					id + ", Tipo: " + Pedido.class.getName());
		}
		return obj;
	}
	
}
