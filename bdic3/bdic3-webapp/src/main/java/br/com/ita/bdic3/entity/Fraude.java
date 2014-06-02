package br.com.ita.bdic3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "fraude")
public class Fraude {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fra_id")
	private Long id;

	@Column(name = "fra_nome")
	private String nome;

	@Column(name = "fra_tipo")
	private String tipo;

	@Column(name = "fra_forma_deteccao")
	private String formaDeteccao;

	// bi-directional many-to-one association to Localidade
	@ManyToOne
	@JoinColumn(name = "tra_id")
	private Transacao transacao;

}
