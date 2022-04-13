package com.apirest.model;

public class Tempo {
	
	private String id;
	private int tempo;
	
	
	public Tempo(String id, int tempo) {
		super();
		this.id = id;
		this.tempo = tempo;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getTempo() {
		return tempo;
	}
	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

}
