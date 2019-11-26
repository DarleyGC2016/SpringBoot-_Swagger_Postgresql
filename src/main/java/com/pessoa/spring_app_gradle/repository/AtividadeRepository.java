package com.pessoa.spring_app_gradle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pessoa.spring_app_gradle.models.Atividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
   /**
    *  São metódos de procura 
    * */
	public Atividade findById(long id);
	
	public Atividade findByDescricao(String name);
}
