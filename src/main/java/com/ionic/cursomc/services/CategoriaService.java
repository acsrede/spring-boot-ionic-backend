package com.ionic.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ionic.cursomc.domain.Categoria;
import com.ionic.cursomc.repositories.CategoriaRepository;
import com.ionic.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	//find category
	public Optional<Categoria> buscaPorId(Integer id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		//is null
		if (!obj.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado! ID: " + 
					id + ", Tipo: " + Categoria.class.getName());
		}
		return obj;
	}
	
	//insert new category
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}
	
}
