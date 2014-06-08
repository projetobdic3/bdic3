package br.com.ita.bdic3.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import br.com.ita.bdic3.util.DateUtil;

public class SuspeitaFraudeVO {

	private Integer cli_id;
	private Double loc_latitude;
	private Double loc_longitude;
	private DateTime tra_data_hora;
	private String localidade;
	private Double valor;

	private DateTimeFormatter formatador = DateTimeFormat
			.forPattern("yyyy-MM-dd HH:mm:ss.0");

	public SuspeitaFraudeVO() {

	}

	public SuspeitaFraudeVO(ResultSet rs) throws SQLException {
		setCli_id(rs.getInt("cli_id"));
		setTra_data_hora(formatador
				.parseDateTime(rs.getString("tra_data_hora")));
		setLoc_latitude(Double.parseDouble(rs.getString("loc_latitude")));
		setLoc_longitude(Double.parseDouble(rs.getString("loc_longitude")));
		setLocalidade(rs.getString("loc_cidade"));
		setValor(Double.parseDouble(rs.getString("tra_total")));
		
	}

	public Integer getCli_id() {
		return cli_id;
	}

	public void setCli_id(Integer cli_id) {
		this.cli_id = cli_id;
	}

	public Double getLoc_latitude() {
		return loc_latitude;
	}

	public void setLoc_latitude(Double loc_latitude) {
		this.loc_latitude = loc_latitude;
	}

	public Double getLoc_longitude() {
		return loc_longitude;
	}

	public void setLoc_longitude(Double loc_longitude) {
		this.loc_longitude = loc_longitude;
	}

	public DateTime getTra_data_hora() {
		return tra_data_hora;
	}

	public void setTra_data_hora(DateTime tra_datetime) {
		this.tra_data_hora = tra_datetime;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getData(){ 
		if(tra_data_hora != null){
			return DateUtil.datateTimeToString(tra_data_hora);
		}
		return null;
	}

	@Override
	public String toString() {
		return cli_id + ", " + getTra_data_hora() + ", " + loc_latitude + ", "
				+ loc_longitude;
	}

}