package com.dgi.dsi.winregistre.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;

@Entity
@Table(name = "service")
public class Service extends EntityBaseBean implements Serializable {


	private static final long serialVersionUID = 1L;	
	private String code;

	private String designation;
	
	@ManyToOne
	private Direction direction;

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
		return "Banque [code=" + code + ", designation=" + designation + "]";
	}

}
