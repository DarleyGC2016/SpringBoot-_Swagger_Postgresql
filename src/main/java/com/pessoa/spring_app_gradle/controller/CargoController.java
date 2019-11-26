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

import com.pessoa.spring_app_gradle.models.Cargo;
import com.pessoa.spring_app_gradle.models.EPI;
import com.pessoa.spring_app_gradle.models.Funcionario;
import com.pessoa.spring_app_gradle.repository.CargoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@RestController
@RequestMapping(path = "/api")
@Api(
		value="API REST Cargo",
		produces = MediaType.APPLICATION_JSON_VALUE,
		description="Cargo API",
		tags= {"Cargo"}
		)
@CrossOrigin(origins="*")
public class CargoController {


	@Autowired 
	private CargoRepository repository;

	private List<Cargo> cargos;



	public CargoController(CargoRepository repository) {
		super();
		this.repository = repository;
	}

	@PostMapping(path = "/cargo")
	@ApiOperation(value="Cadastra um cargo ")
	@ApiResponses(
			@ApiResponse(
					code= 201,
					message= "Novo cargo criado",
					response = Funcionario.class,
					responseHeaders = 
					@ResponseHeader(
							name = "Cargo",
							description = "uri de um novo carga",
							response = String.class
							)
					)
			)    
	public Cargo cadastrarCargo(@ApiParam(
			                    value= "Cargo",
			                    name="cargo",
			                    required=true
			                    )
	                            @RequestBody @Valid Cargo cargo) { 

		return repository.save(cargo);
	}


	@GetMapping(path="/cargos")
	@ApiOperation(value="Retorna um Lista de cargos ")
	@ApiResponses(
			{
			@ApiResponse(
					code= 201,
					message= "Listar cargos",
					response = Funcionario.class,
					responseHeaders = 
					@ResponseHeader(
							name = "Cargos",
							description = "uri de lista cargos",
							response = String.class
							)
					),	
			@ApiResponse(
					code= 500,
					message = "Erro: Listar cargos",
					response = EPI.class,
					responseHeaders =
					@ResponseHeader(
							name = "Erro",
							description = "uri de listar cargos",
							response = EPI.class	 
							)
					)
         }
			)    
	public List<Cargo> findAll() {
		cargos = repository.findAll();
		return (!cargos.isEmpty()) ? cargos: null;
	}

	/**
	 *   Objetivo: Pesquisa o cargo.
	 *   entrada : Descrição do cargo.
	 *   Saída   : Cargo do Funcionário.
	 * @param descricao 
	 **/
	//@ApiOperation é para descrever uma operação no swagger.
	// @ApiResponses são as respostas para uma ação no swagger.
	@GetMapping(path = "/cargo/descricao/{descricao}")   
	@ApiOperation(value="Pesquisa o Cargo")   
	@ApiResponses(
			{
			@ApiResponse(
					code= 201,
					message= "Pesquisou cargo",
					response = String.class,
					responseHeaders = 
					@ResponseHeader(
							name = "Pesquisou cargo",
							description = "uri do cargo",
							response = String.class
							)
					),	
			@ApiResponse(
					code= 500,
					message = "Erro: Na pesquisa de cargo",
					response = EPI.class,
					responseHeaders =
					@ResponseHeader(
							name = "Erro",
							description = "uri da pesquisa de cargos",
							response = EPI.class	 
							)
					)
			}
			)
	public Cargo findCargo(@PathVariable String descricao) {
		return (!descricao.isEmpty()&& descricao.length()>0)?repository.findByDescricao(descricao):null;
	}

	/**
	 *   Objetivo: Pesquisa o cargo pelo ID.
	 *   entrada : Descrição do cargo.
	 *   Saída   : Cargo do Funcionário.
	 * @param descricao 
	 **/
	//@ApiOperation é para descrever uma operação no swagger.
	// @ApiResponses são as respostas para uma ação no swagger.
	@GetMapping(path = "/cargo/{id}")   
	@ApiOperation(value="Pesquisa o Cargo pelo ID")   
	@ApiResponses(
			{
				@ApiResponse(
						code= 201,
						message= "Pesquisou cargo pelo ID",
						response = String.class,
						responseHeaders = 
						@ResponseHeader(
								name = "Pesquisou cargo ID",
								description = "uri de Cargo",
								response = String.class
								)
						),
				@ApiResponse(
						code= 500,
						message = "Erro: Na pesquisa pelo ID",
						response = EPI.class,
						responseHeaders =
						@ResponseHeader(
								name = "Erro",
								description = "uri da pesquisa de cargos",
								response = EPI.class	 
								)
						)
			}
	)
	public Cargo findByIdCargo(@PathVariable long id) {
		return (id>0)?repository.findById(id):null;
	}

    /**
	 *   Objetivo: Atualizar um cargo.
	 *   entrada : Dados do cargo.
	 *   Saída   : Os Dados do cargo.
	 **/
    @PutMapping(path = "/cargo")
    @ApiOperation(value="Atualiza um Cargo")
    @ApiResponses(
			@ApiResponse(
					code= 201,
					message= "Atualização do cargo",
					response = String.class,
					responseHeaders = 
					@ResponseHeader(
							name = "Atualização cargo",
							description = "uri do Cargo",
							response = String.class
							)
					)
	      )
    public Cargo update(@RequestBody @Valid Cargo cargo) {
    	return  repository.save(cargo);
    }  
    
    /**
	 *   Objetivo: Excluir um cargo.
	 *   entrada : Id do cargo.
	 *   Saída   : Nenhum.
	 **/
    @DeleteMapping(path = "/cargo")
    @ApiOperation(value="Excluir um Cargo")
    @ApiResponses(
			@ApiResponse(
					code= 201,
					message= "Excluiu um cargo pelo ID",
					response = String.class,
					responseHeaders = 
					@ResponseHeader(
							name = "Atualização cargo",
							description = "uri de Cargo",
							response = String.class
							)
					)
			)
    public void delete (@RequestBody @Valid Cargo cargo) {
    	if(repository.existsById(cargo.getId())) {
             repository.delete(cargo);
    	}
    }  
}
