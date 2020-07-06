package com.dgi.dsi.winregistre.entites;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;

import javax.persistence.*;
import java.io.Serializable;

/*import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; */


//@Data @NoArgsConstructor @AllArgsConstructor
@Entity

@Table(name = "approle")
public class AppRole extends EntityBaseBean implements Serializable {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
	private String roleName;
	
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public AppRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AppRole(Long id, String roleName) {
		super();
//		this.id = id;
		this.roleName = roleName;
	}

	public AppRole(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "AppRole [ roleName=" + roleName + "]";
	}
	
	
	
	
}

