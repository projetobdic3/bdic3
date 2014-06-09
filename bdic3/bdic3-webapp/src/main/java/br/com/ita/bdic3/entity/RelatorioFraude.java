package br.com.ita.bdic3.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "relatorio_fraude")
public class RelatorioFraude {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "relf_id")
	private Long id;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "relatorioFraude",fetch=FetchType.EAGER)
	private List<SuspeitaFraudeVO> suspeitasFraudes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<SuspeitaFraudeVO> getSuspeitasFraudes() {
		return suspeitasFraudes;
	}

	public void setSuspeitasFraudes(List<SuspeitaFraudeVO> suspeitasFraudes) {
		for (SuspeitaFraudeVO suspeitaFraudeVO : suspeitasFraudes) {
			suspeitaFraudeVO.setRelatorioFraude(this);
		}
		this.suspeitasFraudes = suspeitasFraudes;
	}
	
	

}
