package com.pessoa.spring_app_gradle.models;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Embeddable
public class EpiAtividadePK  implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="atividade_id")
	private Atividade atividade;
	
	@ManyToOne
	@JoinColumn(name="epi_id")
	private EPI epi;

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public EPI getEpi() {
		return epi;
	}

	public void setEpi(EPI epi) {
		this.epi = epi;
	}

	public EpiAtividadePK() {
		super();
	} 
	
	
	
}
