package Models;

import java.io.Serializable;


import json.JSONObject;

public class Schedule implements Serializable{
	private static final long serialVersionUID = 1L;
	private int tipo;
	private String horario;
	
	public Schedule(JSONObject j) {
		this.tipo = j.getInt("tipo");
		this.horario = j.getString("horario");
	}
	
	public Schedule() {}	
	
	public Schedule(int tipo, String horario) {
		this.tipo = tipo;
		this.horario = horario;
	}
	
	public json.JSONObject toJson() {
		json.JSONObject json = new json.JSONObject();
		json.put("tipo", this.tipo);
		json.put("horario", this.horario);
		return json;
	}

	public int getTipo() {
		return tipo;
	}


	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public String getHorario() {
		return horario;
	}


	public void setHorario(String horario) {
		this.horario = horario;
	}


}
