package com.pessoa.spring_app_gradle.models;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;

@Entity
@Table(name = "TB_ATIVIDADE_EPI")
@ApiModel(value = "Associativa de Ativiade e EPI", description = " ")
public class EpiAtividade {

	@EmbeddedId
	private EpiAtividadePK id;
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="funcionario_id")
	private Funcionario funcionario;*/
	
	public EpiAtividadePK getId() {
		return id;
	}

	public void setId(EpiAtividadePK id) {
		this.id = id;
	}

	/*public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}*/

	public EpiAtividade() {
		super();
	}
	
}
