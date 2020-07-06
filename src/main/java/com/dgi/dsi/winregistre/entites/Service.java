package com.dgi.dsi.winregistre.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "service", schema = "winregist")
public class Service extends EntityBaseBean implements Serializable {


	private static final long serialVersionUID = 1L;	
	private String code;

	private String designation;
	
	@ManyToOne
	private Direction direction;


	@OneToMany(mappedBy = "service")
	private List<Agent> agents = new ArrayList<>();


	public List<Agent> getAgents() {
		return agents;
	}

	@JsonIgnore
	public void setAgents(List<Agent> agents) {
		this.agents = agents;
	}

	public String getCode() {
		return code;
	}

	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Service(String code, String designation) {
		super();
		this.code = code;
		this.designation = designation;
	}

	
	public Service(String code, String designation, Direction direction) {
		super();
		this.code = code;
		this.designation = designation;
		this.direction = direction;
	}

	public Service() {
		super();
	}


	@Override
	public String toString() {
		return "Service{" +
				"code='" + code + '\'' +
				", designation='" + designation + '\'' +
				", direction=" + direction +
				", agents=" + agents +
				'}';
	}
}
