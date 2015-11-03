package com.sgem.seguridad.jwt;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.sgem.datatypes.DataComite;
import com.sgem.datatypes.DataUsuario;

public class JWTUtil {
	
	public static final String HEADER_ALG = "alg";
	public static final String HEADER_TYP = "typ";
			
	public static final String BODY_ISS = "iss";
	public static final String BODY_EXP = "exp";
	public static final String BODY_USUARIO = "dataUsuario";
	
	private static final Key clave = MacProvider.generateKey();
	
	public static Token generarToken(Object du){

		//Headers
		Map<String, Object> headers = new HashMap<String, Object>();	
		headers.put("alg", SignatureAlgorithm.HS256);
		headers.put("typ", "JWT");		
		
		//Payload/Claims
		Map<String, Object> claims = new HashMap<String, Object>();	
		claims.put("iss", "sgem.com");
		
		if(du instanceof DataComite){
			claims.put("dataUsuario", (DataComite) du);
		}else{
			claims.put("dataUsuario", (DataUsuario) du);			
		}
		
		//Expiracion
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR, 1);		
//		calendar.add(Calendar.MINUTE, 1);
		
		String jwt = Jwts.builder().setHeader(headers).setClaims(claims).setExpiration(calendar.getTime()).signWith(SignatureAlgorithm.HS256, clave).compact();
		Token token = new Token();
		token.setToken(jwt);
		
		return token;
		
		/* Como usarlo : 
		Token jwt = JWTUtil.generarToken( new DataUsuario(1, "mail@gmail.com", "f", "t", "y", "usuario", "data", 1,12345678, "123", "UsuarioComun"));
		
		Map<String, Object> header = new HashMap<String, Object>();
		Map<String, Object> body = new HashMap<String, Object>();
		
		body = JWTUtil.getBody(jwt.getToken());
		String iss = (String) body.get(JWTUtil.BODY_ISS);
		Date exp = new Date((Integer) body.get(JWTUtil.BODY_EXP));
		LinkedHashMap<String, Object> datos = (LinkedHashMap<String, Object>) body.get(JWTUtil.BODY_USUARIO);
		
		header = JWTUtil.getHeader(jwt.getToken());
		String alg = (String) header.get(JWTUtil.HEADER_ALG);
		String typ = (String) header.get(JWTUtil.HEADER_TYP);
		
		*/
		
	}
	
	public static Map<String, Object> getHeader(String token){
		Jwt t = Jwts.parser().setSigningKey(getClave()).parse(token);
		Map<String, Object> header = new HashMap<String, Object>();
		header = (Map<String, Object>) t.getHeader();	
		return header;
	}
	
	public static Map<String, Object> getBody(String token){
		Jwt t = Jwts.parser().setSigningKey(getClave()).parse(token);
		Map<String, Object> body = new HashMap<String, Object>();		
		body = (Map<String, Object>) t.getBody();
		return body;
	}
		
	public static Key getClave() {
		return clave;
	}

}
