package com.sgem.seguridad.excepciones;

import java.io.Serializable;

public class UsuarioNoEncontradoException extends Exception implements Serializable
{
  	private static final long serialVersionUID = 7249634148880709899L;
  	
	public UsuarioNoEncontradoException() {
        super();
    }
    public UsuarioNoEncontradoException(String msg)   {
        super(msg);
    }
    public UsuarioNoEncontradoException(String msg, Exception e)  {
        super(msg, e);
    }
}