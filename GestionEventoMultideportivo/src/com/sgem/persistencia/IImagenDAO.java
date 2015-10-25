package com.sgem.persistencia;

import javax.ejb.Local;

import com.sgem.dominio.Imagen;

@Local
public interface IImagenDAO {

	public boolean guardarImagen(Imagen i);
	
}
