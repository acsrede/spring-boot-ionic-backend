package com.ionic.cursomc.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ionic.cursomc.domain.Pedido;
import com.ionic.cursomc.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)	
	public ResponseEntity<Optional<Pedido>> find(@PathVariable Integer id) {		
		Optional<Pedido> obj = pedidoService.find(id);
		return ResponseEntity.ok().body(obj);
	}

}
