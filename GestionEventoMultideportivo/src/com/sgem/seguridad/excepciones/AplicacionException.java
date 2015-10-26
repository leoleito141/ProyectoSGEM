package com.sgem.seguridad.excepciones;

import java.io.Serializable;

public class AplicacionException extends Exception implements Serializable{
	
	private static final long serialVersionUID = 8232311182730730732L;
	public AplicacionException() {
        super();
    }
    public AplicacionException(String msg)   {
        super(msg);
    }
    public AplicacionException(String msg, Exception e)  {
        super(msg, e);
    }
}