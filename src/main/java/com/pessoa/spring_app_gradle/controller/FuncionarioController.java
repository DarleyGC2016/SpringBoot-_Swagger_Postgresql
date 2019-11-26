package com.pessoa.spring_app_gradle.controller;


import java.util.List;

import javax.validation.Valid;

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
import org.springframework.http.MediaType;
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
@Api(
		value="API REST Funcionarios",
		produces = MediaType.APPLICATION_JSON_VALUE,
		tags="Funcion�rio",
		description="API - Funcion�rio"
	)
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
    		       response = Funcionario.class
    		    )
    		)
    )    
    public Funcionario cadastrarFuncionario(@ApiParam(value= "Funcion�rio",
                                                      name="funcion�rio",
                                                       required=true)
                                            @RequestBody @Valid Funcionario func) { 
    	
       
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
    		{
    		 @ApiResponse(
	    		  code= 201,
	    		  message= "Listar funcion�rios",
	    		  response = Funcionario.class,
	    		  responseHeaders = 
	    		    @ResponseHeader(
	    		       name = "Funcion�rios",
	    		       description = "uri de lista funcion�rios",
	    		       response = Funcionario.class
	    		    )
                ),
	    		@ApiResponse(
		  	    		  code= 500,
		  	    		  message= "N�o foi encontrado nenhum funcion�rio",
		  	    		  response = String.class,
		  	    		  responseHeaders = 
		  	    		    @ResponseHeader(
		  	    		       name = "Erro",
		  	    		       description = "uri de uma pesquisa para obter um funcion�rio",
		  	    		       response = String.class
		  	    		    )
		  	    		)
	    		}
    )    
    public List<Funcionario> findAll(){
    	funcionarios = repository.findAll();
    	return(!funcionarios.isEmpty())?funcionarios: null;
    }
    
	/**
	 *   Objetivo: Pesquisa um Funcion�rio pelo seu ID.
	 *   entrada : id do funcion�rio.
	 *   Sa�da   : Um funcion�rio.
	 **/
    @GetMapping(path="/funcionario/{id}")
    @ApiOperation(value="Pesquisa um Funcion�rio pelo seu ID ")
    @ApiResponses(
    		{
	    		@ApiResponse(
	    		  code= 201,
	    		  message= "Pesquisou um funcion�rio pelo seu ID",
	    		  response = String.class,
	    		  responseHeaders = 
	    		    @ResponseHeader(
	    		       name = "ID",
	    		       description = "uri de uma pesquisa para obter um funcion�rio",
	    		       response = String.class
	    		    )
	    		),
	    		@ApiResponse(
	  	    		  code= 500,
	  	    		  message= "Erro: N�o foi encontrado nenhum funcion�rio pelo ID",
	  	    		  response = String.class,
	  	    		  responseHeaders = 
	  	    		    @ResponseHeader(
	  	    		       name = "Erro",
	  	    		       description = "uri de uma pesquisa para obter um funcion�rio",
	  	    		       response = String.class
	  	    		    )
	  	    		)
    		}
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
    public void delete(@RequestBody @Valid Funcionario funcionario) {
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
       {
    		@ApiResponse(
    		  code= 201,
    		  message= "Pesquisou um funcion�rio pelo seu CPF",
    		  response = String.class,
    		  responseHeaders = 
    		    @ResponseHeader(
    		       name = "CPF",
    		       description = "uri do funcion�rio",
    		       response = String.class
    		    )
    		),
    		@ApiResponse(
	  	    		  code= 500,
	  	    		  message= "CPF: N�o foi encontrado nenhum funcion�rio",
	  	    		  response = String.class,
	  	    		  responseHeaders = 
	  	    		    @ResponseHeader(
	  	    		       name = "Erro",
	  	    		       description = "uri de uma pesquisa para obter um funcion�rio",
	  	    		       response = String.class
	  	    		    )
	  	    		)
         }
    )
    public Funcionario findCpf (@PathVariable String cpf) {
    	return (!cpf.isEmpty()&&cpf.length()>0)?repository.findByCpf(cpf):null;
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
    		{
    		@ApiResponse(
    		  code= 201,
    		  message= "Pesquisou um funcion�rio pelo seu nome",
    		  response = String.class,
    		  responseHeaders = 
    		    @ResponseHeader(
    		       name = "Nome do Funcion�rio",
    		       description = "uri de um funcion�rio",
    		       response = String.class
    		    )
    		),
    		@ApiResponse(
	  	    		  code= 500,
	  	    		  message= "Erro: Nome do Funcion�rio",
	  	    		  response = String.class,
	  	    		  responseHeaders = 
	  	    		    @ResponseHeader(
	  	    		       name = "Erro",
	  	    		       description = "uri de uma pesquisa para obter o nome funcion�rio",
	  	    		       response = String.class
	  	    		    )
	  	    		)
    		}
    )
    public Funcionario findNome(@PathVariable String nome) {
		return (!nome.isEmpty()&&nome.length()>3)?repository.findByNome(nome):null;
    }

    /**
	 *   Objetivo: Atualizar um funcion�rio.
	 *   entrada : Dados do funcion�rio.
	 *   Sa�da   : Os Dados do funcion�rio.
	 **/
    @ApiResponses(
    	       
    	    		@ApiResponse(
    	    		  code= 201,
    	    		  message= "Atualizou um funcion�rio",
    	    		  response = String.class,
    	    		  responseHeaders = 
    	    		    @ResponseHeader(
    	    		       name = "CPF",
    	    		       description = "uri do funcion�rio",
    	    		       response = String.class
    	    		    )
    	    		)
    )
    @PutMapping(path = "/funcionario")
    @ApiOperation(value="Atualiza um Funcion�rio")
    public Funcionario update(@RequestBody @Valid Funcionario funcionario) {
            return repository.save(funcionario);
    }     

}
