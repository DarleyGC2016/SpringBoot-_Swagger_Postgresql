package com.pessoa.spring_app_gradle.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pessoa.spring_app_gradle.models.EpiAtividade;
import com.pessoa.spring_app_gradle.models.EpiAtividadePK;

public interface EpiAtividadeRepository extends JpaRepository<EpiAtividade, EpiAtividadePK> {
   /**
    *  São metódos de procura 
    * */
	public Optional<EpiAtividade> findById(EpiAtividadePK id);
	

}
