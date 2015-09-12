package com.sgem.seguridad;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;

import com.sgem.dominio.Usuario;

public class JWTUtil {

	private static Key clave = MacProvider.generateKey();
	
	public static String generarToken(Usuario u){
				
		String jwt = Jwts.builder().setSubject(u.getEmail()).signWith(SignatureAlgorithm.HS512, clave).compact();
		return jwt;
		
	}
	
}
