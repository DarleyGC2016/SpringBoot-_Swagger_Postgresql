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
		tags="Funcionário",
		description="API - Funcionário"
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
	 *   Objetivo: Cadastra um funcionário.
	 *   entrada : os dados do funcionário.
	 *   Saída   : funcionários.
	 **/
    @PostMapping(path = "/funcionario")
    @ApiOperation(value="Cadastra um Funcionário ")
    @ApiResponses(
    		@ApiResponse(
    		  code= 201,
    		  message= "Novo funcionário criado",
    		  response = Funcionario.class,
    		  responseHeaders = 
    		    @ResponseHeader(
    		       name = "Funcionário",
    		       description = "uri de um novo funcionário",
    		       response = Funcionario.class
    		    )
    		)
    )    
    public Funcionario cadastrarFuncionario(@ApiParam(value= "Funcionário",
                                                      name="funcionário",
                                                       required=true)
                                            @RequestBody @Valid Funcionario func) { 
    	
       
    	  return repository.save(func);
    }

	/**
	 *   Objetivo: Listar todos funcionários que já estão cadastrados no banco de dados.
	 *   entrada : nenhum.
	 *   Saída   : funcionários.
	 **/
    @GetMapping(path="/funcionarios")
    @ApiOperation(value="Retorna um Lista de Funcionários ")
    @ApiResponses(
    		{
    		 @ApiResponse(
	    		  code= 201,
	    		  message= "Listar funcionários",
	    		  response = Funcionario.class,
	    		  responseHeaders = 
	    		    @ResponseHeader(
	    		       name = "Funcionários",
	    		       description = "uri de lista funcionários",
	    		       response = Funcionario.class
	    		    )
                ),
	    		@ApiResponse(
		  	    		  code= 500,
		  	    		  message= "Não foi encontrado nenhum funcionário",
		  	    		  response = String.class,
		  	    		  responseHeaders = 
		  	    		    @ResponseHeader(
		  	    		       name = "Erro",
		  	    		       description = "uri de uma pesquisa para obter um funcionário",
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
	 *   Objetivo: Pesquisa um Funcionário pelo seu ID.
	 *   entrada : id do funcionário.
	 *   Saída   : Um funcionário.
	 **/
    @GetMapping(path="/funcionario/{id}")
    @ApiOperation(value="Pesquisa um Funcionário pelo seu ID ")
    @ApiResponses(
    		{
	    		@ApiResponse(
	    		  code= 201,
	    		  message= "Pesquisou um funcionário pelo seu ID",
	    		  response = String.class,
	    		  responseHeaders = 
	    		    @ResponseHeader(
	    		       name = "ID",
	    		       description = "uri de uma pesquisa para obter um funcionário",
	    		       response = String.class
	    		    )
	    		),
	    		@ApiResponse(
	  	    		  code= 500,
	  	    		  message= "Erro: Não foi encontrado nenhum funcionário pelo ID",
	  	    		  response = String.class,
	  	    		  responseHeaders = 
	  	    		    @ResponseHeader(
	  	    		       name = "Erro",
	  	    		       description = "uri de uma pesquisa para obter um funcionário",
	  	    		       response = String.class
	  	    		    )
	  	    		)
    		}
    ) 
    public Funcionario listaUnicoFunc(@PathVariable long id){
    	return(repository.existsById(id))?repository.findById(id):null;
    }
    
	/**
	 *   Objetivo: Excluir um Funcionário pelo seu ID.
	 *   entrada : Dado do funcionário com seu id.
	 *   Saída   : Nenhum.
	 **/
    @DeleteMapping(path = "/funcionario")
    @ApiOperation(value="Excluir um Funcionário pelo seu ID ")
    @ApiResponses(
    		@ApiResponse(
    		  code= 201,
    		  message= "Excluiu um funcionário pelo seu ID",
    		  response = Funcionario.class,
    		  responseHeaders = 
    		    @ResponseHeader(
    		       name = "Excluiu um Funcionário",
    		       description = "uri de um funcionário",
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
	 *   Objetivo: Pesquisa um Funcionário pelo seu CPF.
	 *   entrada : CPF do funcionário.
	 *   Saída   : Os Dados do funcionário.
	 **/
    @Transactional
    @GetMapping(path = "/funcionario/cpf/{cpf}")
    @ApiOperation(value="Pesquisa um Funcionário pelo seu CPF ")
    @ApiResponses(
       {
    		@ApiResponse(
    		  code= 201,
    		  message= "Pesquisou um funcionário pelo seu CPF",
    		  response = String.class,
    		  responseHeaders = 
    		    @ResponseHeader(
    		       name = "CPF",
    		       description = "uri do funcionário",
    		       response = String.class
    		    )
    		),
    		@ApiResponse(
	  	    		  code= 500,
	  	    		  message= "CPF: Não foi encontrado nenhum funcionário",
	  	    		  response = String.class,
	  	    		  responseHeaders = 
	  	    		    @ResponseHeader(
	  	    		       name = "Erro",
	  	    		       description = "uri de uma pesquisa para obter um funcionário",
	  	    		       response = String.class
	  	    		    )
	  	    		)
         }
    )
    public Funcionario findCpf (@PathVariable String cpf) {
    	return (!cpf.isEmpty()&&cpf.length()>0)?repository.findByCpf(cpf):null;
    }
    
    /**
	 *   Objetivo: Pesquisa um Funcionário pelo seu Nome.
	 *   entrada : Nome do funcionário.
	 *   Saída   : Os Dados do funcionário.
	 **/
    @Transactional
    @GetMapping(path = "/funcionario/nome/{nome}")
    @ApiOperation(value="Pesquisa  um Funcionário pelo nome ")
    @ApiResponses(
    		{
    		@ApiResponse(
    		  code= 201,
    		  message= "Pesquisou um funcionário pelo seu nome",
    		  response = String.class,
    		  responseHeaders = 
    		    @ResponseHeader(
    		       name = "Nome do Funcionário",
    		       description = "uri de um funcionário",
    		       response = String.class
    		    )
    		),
    		@ApiResponse(
	  	    		  code= 500,
	  	    		  message= "Erro: Nome do Funcionário",
	  	    		  response = String.class,
	  	    		  responseHeaders = 
	  	    		    @ResponseHeader(
	  	    		       name = "Erro",
	  	    		       description = "uri de uma pesquisa para obter o nome funcionário",
	  	    		       response = String.class
	  	    		    )
	  	    		)
    		}
    )
    public Funcionario findNome(@PathVariable String nome) {
		return (!nome.isEmpty()&&nome.length()>3)?repository.findByNome(nome):null;
    }

    /**
	 *   Objetivo: Atualizar um funcionário.
	 *   entrada : Dados do funcionário.
	 *   Saída   : Os Dados do funcionário.
	 **/
    @ApiResponses(
    	       
    	    		@ApiResponse(
    	    		  code= 201,
    	    		  message= "Atualizou um funcionário",
    	    		  response = String.class,
    	    		  responseHeaders = 
    	    		    @ResponseHeader(
    	    		       name = "CPF",
    	    		       description = "uri do funcionário",
    	    		       response = String.class
    	    		    )
    	    		)
    )
    @PutMapping(path = "/funcionario")
    @ApiOperation(value="Atualiza um Funcionário")
    public Funcionario update(@RequestBody @Valid Funcionario funcionario) {
            return repository.save(funcionario);
    }     

}
