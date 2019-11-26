package com.pessoa.spring_app_gradle.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.pessoa.spring_app_gradle.models.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
   /**
    *  São metódos de procura 
    * */
	public Funcionario findById(long id);
	
	public Funcionario findByCpf(String cpf);

	public Funcionario findByNome(String name);

	

}
