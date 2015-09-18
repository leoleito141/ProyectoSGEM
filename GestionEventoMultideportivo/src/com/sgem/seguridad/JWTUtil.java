package com.sgem.seguridad;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;

import com.sgem.dominio.Usuario;

public class JWTUtil {

	private static final Key clave = MacProvider.generateKey();
	
	public static Token generarToken(Usuario u){

		String jwt = Jwts.builder().setSubject(u.getEmail()).signWith(SignatureAlgorithm.HS256, clave).compact();
		Token token = new Token();
		token.setToken(jwt);
		
		
		return token;
		
	}

	public static Key getClave() {
		return clave;
	}
	
}
