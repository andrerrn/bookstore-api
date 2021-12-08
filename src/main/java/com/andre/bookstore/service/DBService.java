package com.andre.bookstore.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andre.bookstore.domain.Categoria;
import com.andre.bookstore.domain.Livro;
import com.andre.bookstore.repositories.CategoriaRepository;
import com.andre.bookstore.repositories.LivroRepository;

@Service
public class DBService {
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private LivroRepository livroReprository;
	
	
	public void instaciaBasedeDados() {
		Categoria cat1 = new Categoria(1, "Informatica", "Livro de TI");
		Categoria cat2 = new Categoria(2, "economia", "Livro de economia");
		Categoria cat3 = new Categoria(3, "matematica", "Livro de matematica");

		Livro l1 = new Livro(1, "Clean Code", "Robert Martin", "Lorem ipsun", cat1);
		Livro l2 = new Livro(2, "desenvolvimento", "acemoglu", "texto", cat2);
		Livro l3 = new Livro(3, "calculo 1", "stuarthw", "Lorem ipsun", cat3);

		cat1.getLivros().addAll(Arrays.asList(l1));
		cat2.getLivros().addAll(Arrays.asList(l2));
		cat3.getLivros().addAll(Arrays.asList(l3));

		this.categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		this.livroReprository.saveAll(Arrays.asList(l1, l2, l3));
	}

}
