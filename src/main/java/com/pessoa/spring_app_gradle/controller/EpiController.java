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


import com.pessoa.spring_app_gradle.models.EPI;
import com.pessoa.spring_app_gradle.repository.EPIRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;


@RestController
@RequestMapping(path = "/api")
@Api(
		value = "API Rest EPI",
		produces=MediaType.APPLICATION_JSON_VALUE,
		tags= {"EPI"},
		description="API - EPI"
	)
@CrossOrigin(origins="*")
public class EpiController {
	@Autowired
	private EPIRepository repository;
	private List<EPI> epis;

	public EpiController(EPIRepository repository) {
		super();
		this.repository = repository;
	}

	@PostMapping(path = "/epi")
	@ApiOperation(value="Cadastra um EPI") 
	@ApiResponses(
			@ApiResponse(
					code= 201,
					message = "Novo EPI criado",
					response = EPI.class,
					responseHeaders =
					@ResponseHeader(
							name = "EPI",
							description = "uri de um novo EPI",
							response = EPI.class	 
							)
					)		
			)
	public EPI cadastraEPI(@ApiParam(
			value = "EPI",
			name="epi",
			required = true
			)
	@RequestBody @Valid EPI epi) {
		return repository.save(epi);

	}

	@GetMapping(path = "/epis")
	@ApiOperation(value="Listar EPIs") 
	@ApiResponses(
			{
				@ApiResponse(
						code= 201,
						message = "Listar EPIs",
						response = EPI.class,
						responseHeaders =
						@ResponseHeader(
								name = "EPI",
								description = "uri de um novo EPI",
								response = EPI.class	 
								)
						),	
				@ApiResponse(
						code= 500,
						message = "Erro: Listar EPIs",
						response = EPI.class,
						responseHeaders =
						@ResponseHeader(
								name = "Erro",
								description = "uri de listar EPI",
								response = EPI.class	 
								)
						)
             }
		)
	public List<EPI> findAll(){
	   epis= repository.findAll();
	   return(!epis.isEmpty()) ?epis:null;
	}
	
	 /**
		 *   Objetivo: Pesquisa o EPI.
		 *   entrada : Descrição do EPI.
		 *   Saída   : EPI.
		 * @param descricao 
		 **/
		//@ApiOperation é para descrever uma operação no swagger.
		// @ApiResponses são as respostas para uma ação no swagger.
		@GetMapping(path = "/epi/descricao/{descricao}")   
		@ApiOperation(value="Pesquisa o EPI")   
		@ApiResponses(
				{
					@ApiResponse(
							code= 201,
							message= "Pesquisou a descricao do EPI",
							response = String.class,
							responseHeaders = 
							@ResponseHeader(
									name = "Pesquisou a descricao EPI",
									description = "uri do EPI",
									response = String.class
									)
							),
					@ApiResponse(
							code= 500,
							message = "Erro: Na pesquisa pela descricao do EPI",
							response = EPI.class,
							responseHeaders =
							@ResponseHeader(
									name = "Erro",
									description = "uri da pesquisa a descricao do EPI",
									response = EPI.class	 
									)
							)
				}
		)
		public EPI findEPI(@PathVariable String descricao) {
			return (!descricao.isEmpty()&& descricao.length()>0)?repository.findByDescricao(descricao):null;
		}

		/**
		 *   Objetivo: Pesquisa a a descricao pelo ID.
		 *   entrada : ID do EPI.
		 *   Saída   : EPI.
		 * @param descricao 
		 **/
		//@ApiOperation é para descrever uma operação no swagger.
		// @ApiResponses são as respostas para uma ação no swagger.
		@GetMapping(path = "/epi/{id}")   
		@ApiOperation(value="Pesquisa EPI pelo ID")   
		@ApiResponses(
				{
					@ApiResponse(
							code= 201,
							message= "Pesquisou EPI pelo ID",
							response = String.class,
							responseHeaders = 
							@ResponseHeader(
									name = "Pesquisou EPI ID",
									description = "uri de EPI",
									response = String.class
									)
							),
					@ApiResponse(
							code= 500,
							message = "Erro: Na pesquisa do EPI pelo ID",
							response = String.class,
							responseHeaders =
							@ResponseHeader(
									name = "Erro",
									description = "uri da pesquisa a descricao do EPI",
									response = String.class	 
									)
							)
				}
		)
		public EPI findByIdEPI(@PathVariable long id) {
			return (repository.existsById(id))?repository.findById(id):null;
		}

		/**
		 *   Objetivo: Pesquisa o número CA do EPI.
		 *   entrada : CA do EPI.
		 *   Saída   : EPI.
		 * @param descricao 
		 **/
		//@ApiOperation é para descrever uma operação no swagger.
		// @ApiResponses são as respostas para uma ação no swagger.
		@GetMapping(path = "/epi/ca/{ca}")   
		@ApiOperation(value="Pesquisa EPI pelo CA")   
		@ApiResponses(
				{
					@ApiResponse(
							code= 201,
							message= "Pesquisou EPI pelo ca",
							response = String.class,
							responseHeaders = 
							@ResponseHeader(
									name = "Pesquisou EPI ca",
									description = "uri do ca",
									response = String.class
									)
							),
					@ApiResponse(
							code= 500,
							message= "Erro: na pesquisa EPI pelo ca",
							response = String.class,
							responseHeaders = 
							@ResponseHeader(
									name = "Pesquisou EPI ca",
									description = "uri do ca",
									response = String.class
									)
							)
				}
				)
		public EPI findByEPICA(@PathVariable long ca) {
			return (ca==0||ca>0)?repository.findByCa(ca):null;
		}
	    /**
		 *   Objetivo: Atualizar uma  EPI.
		 *   entrada : Dados do EPI.
		 *   Saída   : Os Dados do EPI.
		 **/
	    @PutMapping(path = "/epi")
	    @ApiOperation(value="Atualiza o EPI")
	    @ApiResponses(
	    			@ApiResponse(
	    					code= 201,
	    					message= "Atualização do EPI",
	    					response = String.class,
	    					responseHeaders = 
	    					@ResponseHeader(
	    							name = "Atualização EPI",
	    							description = "uri do EPI",
	    							response = String.class
	    							)
	    					)
	     )

	    		
	    public EPI update(@RequestBody @Valid EPI epi) {
	    	return  repository.save(epi);
	    }  
	    
	    /**
		 *   Objetivo: Excluir uma EPI.
		 *   entrada : EPI.
		 *   Saída   : Nenhum.
		 **/
	    @DeleteMapping(path = "/epi")
	    @ApiOperation(value="Excluir um epi")
	    @ApiResponses(
				@ApiResponse(
						code= 201,
						message= "Excluiu um EPI",
						response = EPI.class,
						responseHeaders = 
						@ResponseHeader(
								name = "Atualização EPI",
								description = "uri da EPI",
								response = EPI.class
								)
						)
				)
	    public void delete (@RequestBody @Valid EPI epi) {
	    	if(repository.existsById(epi.getId())) {
	             repository.delete(epi);
	    	}
	    } 
}
