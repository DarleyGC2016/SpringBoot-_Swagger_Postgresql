package com.pessoa.spring_app_gradle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pessoa.spring_app_gradle.models.EPI;

public interface EPIRepository extends JpaRepository<EPI, Long> {
   /**
    *  São metódos de procura 
    * */
	 EPI findById(long id);
	
	 EPI findByDescricao(String descricao);

	 EPI findByCa(long ca);

}
