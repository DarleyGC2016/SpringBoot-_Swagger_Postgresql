package com.pessoa.spring_app_gradle.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoa.spring_app_gradle.models.EPI;
import com.pessoa.spring_app_gradle.models.EpiAtividade;
import com.pessoa.spring_app_gradle.models.Funcionario;
import com.pessoa.spring_app_gradle.repository.EpiAtividadeRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@RestController
@RequestMapping(path = "/api")
@Api(
	  value="API REST Atividade e EPI",
      produces = MediaType.APPLICATION_JSON_VALUE,
      tags="Atividade e EPI",
      description="API - Atividade e Epi"
     )
@CrossOrigin(origins="*")
public class EpiAtividadeController {
	@Autowired 
    private EpiAtividadeRepository repository;
	private List<EpiAtividade> epiAtividades;
	
	
	public EpiAtividadeController(EpiAtividadeRepository repository) {
		super();
		this.repository = repository;
	}
    
	
	@PostMapping(path = "/epi_atividade")
    @ApiOperation(value="Cadastra um atividade e epi ")
    @ApiResponses(
    		@ApiResponse(
    		  code= 201,
    		  message= "Nova atividade e epi criada",
    		  response = Funcionario.class,
    		  responseHeaders = 
    		    @ResponseHeader(
    		       name = "Atividade e EPI",
    		       description = "uri de um nova atividade e EPI",
    		       response = String.class
    		    )
    		)
    )    
    public EpiAtividade cadastrarAtividade(@ApiParam(value= "Atividade",
                                                      name="atividade",
                                                       required=true)
                                             @RequestBody @Valid EpiAtividade epiAtividade) { 
    	
       
		return repository.save(epiAtividade);
    }
	
	 @GetMapping(path="/epi_atividades")
	    @ApiOperation(value="Retorna um Lista de atividades e de epis ")
	    @ApiResponses(
	    		{	
	    		@ApiResponse(
	    		  code= 201,
	    		  message= "Listar atividades e epis",
	    		  response = Funcionario.class,
	    		  responseHeaders = 
	    		    @ResponseHeader(
	    		       name = "Atividades",
	    		       description = "uri de lista atividades e epis",
	    		       response = String.class
	    		    )
	    		),
	    		@ApiResponse(
						code= 500,
						message = "Erro: Listar as atividades e epis",
						response = EPI.class,
						responseHeaders =
						@ResponseHeader(
								name = "Erro",
								description = "uri de listar atividades e epis",
								response = EPI.class	 
								)
						)
    		}
	    )    
	public List<EpiAtividade> findAll() {
		epiAtividades = repository.findAll();
		return (!epiAtividades.isEmpty())? epiAtividades:null;
	}
	 @DeleteMapping(path="/epi_atividade")
	    @ApiOperation(value="Excluir de atividades e de epis ")
	    @ApiResponses(
	    		{	
	    		@ApiResponse(
	    		  code= 201,
	    		  message= "Excluir atividades e epis",
	    		  response = Funcionario.class,
	    		  responseHeaders = 
	    		    @ResponseHeader(
	    		       name = "Atividades",
	    		       description = "uri de excluir atividades e epis",
	    		       response = String.class
	    		    )
	    		),
	    		@ApiResponse(
						code= 500,
						message = "Erro: Excluir as atividades e epis",
						response = EPI.class,
						responseHeaders =
						@ResponseHeader(
								name = "Erro",
								description = "uri de Excluir atividades e epis",
								response = EPI.class	 
								)
						)
 		}
	    )     
	    public void delete(@RequestBody EpiAtividade epiAtividade) {
		     repository.delete(epiAtividade);
	   }
}
