package com.sgem.seguridad.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import com.sgem.datatypes.DataUsuario;
import com.sgem.dominio.Usuario;

public class JWTUtil {

	private static final Key clave = MacProvider.generateKey();
	
	public static Token generarToken(DataUsuario du){

		Map<String, Object> mapeo = new HashMap<String, Object>();
		 
		mapeo.put("dataUsuario", du);
//		mapeo.put("rol", rol);

//		String jwt = Jwts.builder().setPayload("tenant 1").signWith(SignatureAlgorithm.HS256, clave).compact();
		String jwt = Jwts.builder().setClaims(mapeo).signWith(SignatureAlgorithm.HS256, clave).compact();
		Token token = new Token();
		token.setToken(jwt);
		
		
		return token;
		
	}

	public static Key getClave() {
		return clave;
	}

}
