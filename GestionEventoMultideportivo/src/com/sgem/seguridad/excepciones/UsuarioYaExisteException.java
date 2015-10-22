package com.sgem.seguridad.excepciones;

import java.io.Serializable;

public class UsuarioYaExisteException extends Exception implements Serializable
{
	private static final long serialVersionUID = -1500479777018334449L;
	
	public UsuarioYaExisteException() {
        super();
    }
    public UsuarioYaExisteException(String msg)   {
        super(msg);
    }
    public UsuarioYaExisteException(String msg, Exception e)  {
        super(msg, e);
    }
}