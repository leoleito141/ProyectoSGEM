package com.sgem.seguridad;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;

import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.util.Base64;


/**
 * Este interceptador verifica el accesso con JWT, a nivel de permisos basado en roles 
 * para un usuario identificado proveniente del request.
 * */
@Provider
@ServerInterceptor
public class SecurityInterceptor implements javax.ws.rs.container.ContainerRequestFilter
{
    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
    private static final ServerResponse ACCESS_DENIED = new ServerResponse("Access denied for this resource", 401, new Headers<Object>());
    private static final ServerResponse ACCESS_FORBIDDEN = new ServerResponse("Nobody can access this resource", 403, new Headers<Object>());
    private static final ServerResponse SERVER_ERROR = new ServerResponse("INTERNAL SERVER ERROR", 500, new Headers<Object>());
     
	 // We need a signing key, so we'll create one just for this example. Usually
	 // the key would be read from your application configuration instead.
	private Key clave = MacProvider.generateKey();
//    private final String key = "ClaveSecreta";
    
    @Override
    public void filter(ContainerRequestContext requestContext)
    {
        ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker) requestContext.getProperty("org.jboss.resteasy.core.ResourceMethodInvoker");
        Method method = methodInvoker.getMethod();
        
        //Acceso siempre permitido para todo tipo de rol.
        if( ! method.isAnnotationPresent(PermitAll.class))
        {
            //Acceso denegado denegado para todo tipo de rol.
            if(method.isAnnotationPresent(DenyAll.class))
            {
                requestContext.abortWith(ACCESS_FORBIDDEN);
                return;
            }
             
            //Get request headers
            final MultivaluedMap<String, String> headers = requestContext.getHeaders();
             
            //Fetch authorization header
            final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);
            
            //Si no hay información de autorización presente, bloqueo el acceso.
            if(authorization == null || authorization.isEmpty())
            {
                requestContext.abortWith(ACCESS_DENIED);
                return;
            }
                               
            /*	A partir este punto, se procesaran los datos obtenidos de los headers del método GET */
                        
            //Get encoded username and password
            final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
             
            //Decode username and password
            String usernameAndPassword = null;
            try {
                usernameAndPassword = new String(Base64.decode(encodedUserPassword));
            } catch (IOException e) {
                requestContext.abortWith(SERVER_ERROR);
                return;
            }
 
            //Split username and password tokens
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();
             
            System.out.println("Usuario ingresado: " + username);
            System.out.println("Contrasenia ingresada: " +password);
             
            //Verifico el acceso del usuario
            if(method.isAnnotationPresent(RolesAllowed.class))
            {
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
                 
                //Es valido?
                if( ! isUserAllowed(username, password, rolesSet))
                {
                    requestContext.abortWith(ACCESS_DENIED);
                    return;
                }
            }
        }
    }
    private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet)
    {
        boolean isAllowed = false;
        
/////////////////////////////////////////////////////////////////////////////////////////////////////////
//       Paso 1. obtenemos el rol a partir del username, el password se supone que es solo 
//        si se quiere hacer login en este nivel, pero en este momento se supone que el 
//        usuario ya va a estar logueado en el sistema, ya que este método es para seguridad en nuestros servicios.
/////////////////////////////////////////////////////////////////////////////////////////////////////////
    
        //Step 1. Fetch password from database and match with password in argument
        //If both match then get the defined role for user from database and continue; else return isAllowed [false]
        //Access the database and do this part yourself
        //String userRole = userMgr.getUserRole(username);
        
        // Se supone que el rol, para el usuario con username es :
        String userRole = "ADMIN";
         
        //Paso 2. verificamos si el rol del usuario esta contenido en los roles que permite el método.
        if(rolesSet.contains(userRole))
        {
            isAllowed = true;
        }
        return isAllowed;
    }

    
    private String createJWT(String id, String issuer, String subject, long ttlMillis) {

		//The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		//We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(clave.toString());
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		  //Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(id)
		                                .setIssuedAt(now)
		                                .setSubject(subject)
		                                .setIssuer(issuer)
		                                .signWith(signatureAlgorithm, signingKey);

		 //if it has been specified, let's add the expiration
		if (ttlMillis >= 0) {
		    long expMillis = nowMillis + ttlMillis;
		    Date exp = new Date(expMillis);
		    builder.setExpiration(exp);
		}

		 //Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}
	
	//Sample method to validate and read the JWT
	private void parseJWT(String jwt) {
		//This line will throw an exception if it is not a signed JWS (as expected)
		Claims claims = Jwts.parser()         
		   .setSigningKey(DatatypeConverter.parseBase64Binary(clave.toString()))
		   .parseClaimsJws(jwt).getBody();
		System.out.println("ID: " + claims.getId());
		System.out.println("Subject: " + claims.getSubject());
		System.out.println("Issuer: " + claims.getIssuer());
		System.out.println("Expiration: " + claims.getExpiration());
		
	}
    
}