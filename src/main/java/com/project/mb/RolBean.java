package com.project.mb;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import com.project.dao.RolDao;
import com.project.dao.RolDaoImpl;
import com.project.entities.Rol;

@ManagedBean
@RequestScoped
public class RolBean {
	private List<SelectItem> selectOneItemsRol;
	
	public RolBean(){
	}

	public List<SelectItem> getSelectOneItemsRol() {
		this.selectOneItemsRol = new ArrayList<SelectItem> ();
		RolDao rolDao = new RolDaoImpl();
		List<Rol> roles = rolDao.selectItems();
		for(Rol rol : roles){
			SelectItem selectItem = new SelectItem(rol.getRolId(),rol.getRolNombre());
			this.selectOneItemsRol.add(selectItem);	
			}
		return selectOneItemsRol;
	}
	
	
}
