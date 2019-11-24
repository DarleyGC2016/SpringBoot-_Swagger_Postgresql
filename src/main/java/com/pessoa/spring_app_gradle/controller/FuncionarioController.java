package com.pessoa.spring_app_gradle.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoa.spring_app_gradle.models.Funcionario;
import com.pessoa.spring_app_gradle.repository.FuncionarioRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@RestController
@RequestMapping(path = "/api")
@Api(value="API REST Funcionarios")
@CrossOrigin(origins="*")
public class FuncionarioController {
	
    @Autowired 
    private FuncionarioRepository repository;
	private List<Funcionario> funcionarios;

	public FuncionarioController(FuncionarioRepository repository) {
		super();
		this.repository = repository;
	}
	/**
	 *   Objetivo: Cadastra um funcion�rio.
	 *   entrada : os dados do funcion�rio.
	 *   Sa�da   : funcion�rios.
	 **/
    @PostMapping(path = "/funcionario")
    @ApiOperation(value="Cadastra um Funcion�rio ")
    @ApiResponses(
    		@ApiResponse(
    		  code= 201,
    		  message= "Novo funcion�rio criado",
    		  response = Funcionario.class,
    		  responseHeaders = 
    		    @ResponseHeader(
    		       name = "Funcion�rio",
    		       description = "uri de um novo funcion�rio",
    		       response = String.class
    		    )
    		)
    )    
    public Funcionario cadastrarFuncionario(@ApiParam(value= "Funcion�rio",
                                                      name="funcion�rio",
                                                       required=true)
                                            @RequestBody Funcionario func) { 
    	
       
    	  return repository.save(func);
    }

	/**
	 *   Objetivo: Listar todos funcion�rios que j� est�o cadastrados no banco de dados.
	 *   entrada : nenhum.
	 *   Sa�da   : funcion�rios.
	 **/
    @GetMapping(path="/funcionarios")
    @ApiOperation(value="Retorna um Lista de Funcion�rios ")
    @ApiResponses(
    		@ApiResponse(
    		  code= 201,
    		  message= "Listar funcion�rios",
    		  response = Funcionario.class,
    		  responseHeaders = 
    		    @ResponseHeader(
    		       name = "Funcion�rios",
    		       description = "uri de lista funcion�rios",
    		       response = String.class
    		    )
    		)
    )    
    public List<Funcionario> findAll(){
    	funcionarios= repository.findAll();
    	   if(!funcionarios.isEmpty()) {
    	      return funcionarios;
    	   }
    	   else {
		      return null;
    	   }
    }
    
	/**
	 *   Objetivo: Pesquisa um Funcion�rio pelo seu ID.
	 *   entrada : id do funcion�rio.
	 *   Sa�da   : Um funcion�rio.
	 **/
    @GetMapping(path="/funcionario/{id}")
    @ApiOperation(value="Pesquisa um Funcion�rio pelo seu ID ")
    @ApiResponses(
    		@ApiResponse(
    		  code= 201,
    		  message= "Pesquisou um funcion�rio pelo seu ID",
    		  response = Funcionario.class,
    		  responseHeaders = 
    		    @ResponseHeader(
    		       name = "ID",
    		       description = "uri de uma pesquisa para obter um funcion�rio",
    		       response = String.class
    		    )
    		)
    ) 
    public Funcionario listaUnicoFunc(@PathVariable long id){
    	return(repository.existsById(id))?repository.findById(id):null;
    }
    
	/**
	 *   Objetivo: Excluir um Funcion�rio pelo seu ID.
	 *   entrada : Dado do funcion�rio com seu id.
	 *   Sa�da   : Nenhum.
	 **/
    @DeleteMapping(path = "/funcionario")
    @ApiOperation(value="Excluir um Funcion�rio pelo seu ID ")
    @ApiResponses(
    		@ApiResponse(
    		  code= 201,
    		  message= "Excluiu um funcion�rio pelo seu ID",
    		  response = Funcionario.class,
    		  responseHeaders = 
    		    @ResponseHeader(
    		       name = "Excluiu um Funcion�rio",
    		       description = "uri de um funcion�rio",
    		       response = String.class
    		    )
    		)
    )
    public void delete(@RequestBody Funcionario funcionario) {
    	if(repository.existsById(funcionario.getId())==true) {
    		repository.delete(funcionario);
    	}
    }
    
	/**
	 *   Objetivo: Pesquisa um Funcion�rio pelo seu CPF.
	 *   entrada : CPF do funcion�rio.
	 *   Sa�da   : Os Dados do funcion�rio.
	 **/
    @Transactional
    @GetMapping(path = "/funcionario/cpf/{cpf}")
    @ApiOperation(value="Pesquisa um Funcion�rio pelo seu CPF ")
    @ApiResponses(
    		@ApiResponse(
    		  code= 201,
    		  message= "Pesquisou um funcion�rio pelo seu CPF",
    		  response = Funcionario.class,
    		  responseHeaders = 
    		    @ResponseHeader(
    		       name = "CPF",
    		       description = "uri do funcion�rio",
    		       response = String.class
    		    )
    		)
    )
    public Funcionario findCpf (@PathVariable String cpf) {
    	return repository.findByCpf(cpf);
    }
    
    /**
	 *   Objetivo: Pesquisa um Funcion�rio pelo seu Nome.
	 *   entrada : Nome do funcion�rio.
	 *   Sa�da   : Os Dados do funcion�rio.
	 **/
    @Transactional
    @GetMapping(path = "/funcionario/nome/{nome}")
    @ApiOperation(value="Pesquisa  um Funcion�rio pelo nome ")
    @ApiResponses(
    		@ApiResponse(
    		  code= 201,
    		  message= "Pesquisou um funcion�rio pelo seu nome",
    		  response = Funcionario.class,
    		  responseHeaders = 
    		    @ResponseHeader(
    		       name = "Nome do Funcion�rio",
    		       description = "uri de um funcion�rio",
    		       response = String.class
    		    )
    		)
    )
    public Funcionario findNome(@PathVariable String nome) {
		return repository.findByNome(nome);
    }

    /**
	 *   Objetivo: Atualizar um funcion�rio.
	 *   entrada : Dados do funcion�rio.
	 *   Sa�da   : Os Dados do funcion�rio.
	 **/
    @PutMapping(path = "/funcionario")
    @ApiOperation(value="Atualiza um Funcionario")
    public Funcionario update(@RequestBody Funcionario funcionario) {
            return repository.save(funcionario);
    }
    
    /**
   	 *   Objetivo: Pesquisa um Funcion�rio pelo seu Nome.
   	 *   entrada : Nome do funcion�rio.
   	 *   Sa�da   : Os Dados do funcion�rio.
   	 **/
    //@ApiOperation � para descrever uma opera��o no swagger.
    // @ApiResponses s�o as respostas para uma a��o no swagger.
    @Transactional 
    @GetMapping(path = "/funcionario/cargo/{cargo}")   
    @ApiOperation(value="Pesquisa os Cargos de um ou mais Funcionarios")   
    @ApiResponses(
    		@ApiResponse(
    		  code= 201,
    		  message= "Pesquisou cargo de um o mais funcion�rios",
    		  response = Funcionario.class,
    		  responseHeaders = 
    		    @ResponseHeader(
    		       name = "Pesquisou cargo",
    		       description = "uri de um ou mais funcion�rios",
    		       response = String.class
    		    )
    		)
    )
    public List<Funcionario> findCargo(@PathVariable String cargo) {
		return repository.findByCargo(cargo);
	}
}
