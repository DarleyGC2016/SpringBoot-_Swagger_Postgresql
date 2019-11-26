package com.pessoa.spring_app_gradle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pessoa.spring_app_gradle.models.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
   /**
    *  São metódos de procura 
    * */
	 Cargo findById(long id); 
	
	 Cargo findByDescricao(String descricao);
	

}
