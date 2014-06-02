package br.com.ita.bdic3.testes.us08;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Transacao {

	private Integer cli_id;
	private Double loc_latitude;
	private Double loc_longitude;
	private DateTime tra_data_hora;

	private DateTimeFormatter formatador = DateTimeFormat
			.forPattern("yyyy-MM-dd HH:mm:ss.0");

	public Transacao() {

	}

	public Transacao(ResultSet rs) throws SQLException {
		setCli_id(rs.getInt("cli_id"));
		setTra_data_hora(formatador
				.parseDateTime(rs.getString("tra_data_hora")));
		setLoc_latitude(Double.parseDouble(rs.getString("loc_latitude")));
		setLoc_longitude(Double.parseDouble(rs.getString("loc_longitude")));
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

	@Override
	public String toString() {
		return cli_id + ", " + getTra_data_hora() + ", " + loc_latitude + ", "
				+ loc_longitude;
	}

}