package com.ionic.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ionic.cursomc.domain.Categoria;
import com.ionic.cursomc.dto.CategoriaDTO;
import com.ionic.cursomc.repositories.CategoriaRepository;
import com.ionic.cursomc.services.exceptions.DataIntegrityException;
import com.ionic.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	//find category
	public Optional<Categoria> find(Integer id) {
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
	
	//update category
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return categoriaRepository.save(obj);
	}

	//delete category
	public void delete(Integer id) {
		find(id);
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível exlcuir uma categoria que possui produtos!");
		}				
	}

	//list categories
	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}
	
	//pagination
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		@SuppressWarnings("deprecation")
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
}
