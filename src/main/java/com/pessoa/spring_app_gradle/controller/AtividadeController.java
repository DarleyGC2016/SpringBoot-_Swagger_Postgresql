package com.pessoa.spring_app_gradle.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoa.spring_app_gradle.models.Atividade;
import com.pessoa.spring_app_gradle.models.EPI;
import com.pessoa.spring_app_gradle.repository.AtividadeRepository;
import com.pessoa.spring_app_gradle.validation.ValidaAtividade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@RestController
@RequestMapping(path = "/api")
@Api(
		value="API REST Atividade",
		produces = MediaType.APPLICATION_JSON_VALUE,
		tags="Atividade",
		description="API - Atividade"
	)
@CrossOrigin(origins="*")
public class AtividadeController {

	
	@Autowired 
    private AtividadeRepository repository;
    
	private List<Atividade> atividades;

	

	public AtividadeController(AtividadeRepository repository) {
		super();
		this.repository = repository;
	}
	
	@PostMapping(path = "/atividade")
    @ApiOperation(value="Cadastra uma atividade ")
    @ApiResponses(
    		 @ApiResponse(
    		  code= 201,
    		  message= "Nova atividade criada",
    		  response = ValidaAtividade.class,
    		  responseHeaders = 
    		    @ResponseHeader(
    		       name = "Atividade",
    		       description = "uri de um nova atividade",
    		       response = String.class
    		    )
    		 )
    )    
    public Atividade cadastrarAtividade(@ApiParam(value= "Atividade",
                                                      name="atividade",
                                                       required=true)
                                           @Valid @RequestBody Atividade atividade) { 
		  return repository.save(atividade);
    }
	
	
	 @GetMapping(path="/atividades")
	    @ApiOperation(value="Retorna uma Lista de atividades ")
	    @ApiResponses(
	    		{
		    		@ApiResponse(
		    		  code= 201,
		    		  message= "Listar atividades",
		    		  response = Atividade.class,
		    		  responseHeaders = 
		    		    @ResponseHeader(
		    		       name = "Atividades",
		    		       description = "uri de lista atividades",
		    		       response = String.class
		    		    )
		    		),
		    		@ApiResponse(
							code= 500,
							message = "Erro: Listar as atividades",
							response = EPI.class,
							responseHeaders =
							@ResponseHeader(
									name = "Erro",
									description = "uri de listar atividades",
									response = EPI.class	 
									)
							)
	    		}
	    )    
	public List<Atividade> findAll() {
		atividades = repository.findAll();
		return (!atividades.isEmpty()) ?atividades: null;
	}

	 /**
		 *   Objetivo: Pesquisa a atividade.
		 *   entrada : Descrição da atividade.
		 *   Saída   : Atividade do Funcionário.
		 * @param descricao 
		 **/
		//@ApiOperation é para descrever uma operação no swagger.
		// @ApiResponses são as respostas para uma ação no swagger.
		@GetMapping(path = "/atividade/descricao/{descricao}")   
		@ApiOperation(value="Pesquisa a Atividade")   
		@ApiResponses(
				{	
					@ApiResponse(
							code= 201,
							message= "Pesquisou ativiadade pela descrição",
							response = String.class,
							responseHeaders = 
							@ResponseHeader(
									name = "Pesquisou ativiadade",
									description = "uri do ativiadade",
									response = Atividade.class
									)
							),
					@ApiResponse(
							code= 500,
							message = "Erro: Pesquisou ativiadade pela descrição",
							response = EPI.class,
							responseHeaders =
							@ResponseHeader(
									name = "Erro",
									description = "uri da pesquisa da atividades",
									response = EPI.class	 
									)
							)
				}
		)
		public Atividade findAtividade(@PathVariable String descricao) {
			return (!descricao.isEmpty()&& descricao.length()>0)?repository.findByDescricao(descricao):null;
		}

		/**
		 *   Objetivo: Pesquisa a Atividade pelo ID.
		 *   entrada : Descrição do Atividade.
		 *   Saída   : Atividade do Funcionário.
		 * @param descricao 
		 **/
		//@ApiOperation é para descrever uma operação no swagger.
		// @ApiResponses são as respostas para uma ação no swagger.
		@GetMapping(path = "/atividade/{id}")   
		@ApiOperation(value="Pesquisa Atividade pelo ID")   
		@ApiResponses(
				{
					@ApiResponse(
							code= 201,
							message= "Pesquisou Atividade pelo ID",
							response = String.class,
							responseHeaders = 
							@ResponseHeader(
									name = "Pesquisou Atividade ID",
									description = "uri de Atividade",
									response = String.class
									)
							),
					@ApiResponse(
							code= 500,
							message = "Erro: Na pesquisa ativiadade pelo ID",
							response = Atividade.class,
							responseHeaders =
							@ResponseHeader(
									name = "Erro",
									description = "uri da pesquisa da atividades",
									response = Atividade.class	 
									)
							)
				}
		)
		public Atividade findByIdAtividade(@PathVariable long id) {
			return (id>0)?repository.findById(id):null;
		}

	    /**
		 *   Objetivo: Atualizar uma  atividade.
		 *   entrada : Dados do Atividade.
		 *   Saída   : Os Dados do Atividade.
		 **/
	    @PutMapping(path = "/atividade")
	    @ApiOperation(value="Atualiza uma Atividade")
	    @ApiResponses(
				@ApiResponse(
						code= 201,
						message= "Atualização da atividade",
						response = String.class,
						responseHeaders = 
						@ResponseHeader(
								name = "Atualização atividade",
								description = "uri da atividade",
								response = String.class
								)
						)
		      )
	    public Atividade update(@RequestBody @Valid Atividade atividade) {
	    	return repository.save(atividade);
	    }  
	    
	    /**
		 *   Objetivo: Excluir uma Atividade.
		 *   entrada : Id do Atividade.
		 *   Saída   : Nenhum.
		 **/
	    @DeleteMapping(path = "/atividade")
	    @ApiOperation(value="Excluir um Atividade")
	    @ApiResponses(
				@ApiResponse(
						code= 201,
						message= "Excluiu uma Atividade pelo ID",
						response = String.class,
						responseHeaders = 
						@ResponseHeader(
								name = "Atualização Atividade",
								description = "uri da Atividade",
								response = String.class
								)
						)
				)
	    public void delete (@RequestBody @Valid Atividade atividade) {
	    	if(repository.existsById(atividade.getId())) {
	             repository.delete(atividade);
	    	}
	    }  
}
