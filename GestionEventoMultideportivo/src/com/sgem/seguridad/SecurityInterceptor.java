package com.sgem.seguridad;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

import java.lang.reflect.Method;
import java.security.Key;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;


/**
 * Este interceptador verifica el accesso con JWT, a nivel de permisos basado en roles 
 * para un usuario identificado proveniente del request.
 * */
@Provider
@ServerInterceptor
public class SecurityInterceptor implements javax.ws.rs.container.ContainerRequestFilter
{
    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String ROL_PROPERTY = "Rol";
//    private static final String AUTHENTICATION_SCHEME = "Basic";
    private static final String AUTHENTICATION_SCHEME = "Bearer";
    private static final ServerResponse ACCESS_DENIED = new ServerResponse("Access denied for this resource", 401, new Headers<Object>());
    private static final ServerResponse ACCESS_FORBIDDEN = new ServerResponse("Nobody can access this resource", 403, new Headers<Object>());
    private static final ServerResponse SERVER_ERROR = new ServerResponse("INTERNAL SERVER ERROR", 500, new Headers<Object>());
     
	private Key clave = JWTUtil.getClave();
    
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
         
            
            final String token = getToken(authorization);
          
            
            try {
                Jwts.parser().setSigningKey(clave).parseClaimsJws(token);
                //OK, we can trust this JWT
            } catch (SignatureException e) {
                //don't trust the JWT!
            	 requestContext.abortWith(ACCESS_DENIED);
                 return;
            }
            
            
//            //Get encoded username and password
//            final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
//             
//            //Decode username and password
//            String usernameAndPassword = null;
//            try {
//                usernameAndPassword = new String(Base64.decode(encodedUserPassword));
//            } catch (IOException e) {
//                requestContext.abortWith(SERVER_ERROR);
//                return;
//            }
// 
//            //Split username and password tokens
//            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
//            final String username = tokenizer.nextToken();
//            final String password = tokenizer.nextToken();
//             
//            System.out.println("Usuario ingresado: " + username);
//            System.out.println("Contrasenia ingresada: " +password);
             
            
            
            //Verifico el acceso del usuario al método según su rol.
            
            //Si no hay información de autorización presente, bloqueo el acceso.
            if(headers.get(ROL_PROPERTY) == null || headers.get(ROL_PROPERTY).isEmpty())
            {
            	requestContext.abortWith(ACCESS_DENIED);
            	return;
            }
            
            //Fetch authorization header
            String rol = headers.get(ROL_PROPERTY).get(0);
            
            if(method.isAnnotationPresent(RolesAllowed.class))
            {
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
                 
                //Es valido?
//                if( ! isUserAllowed(username, password, rolesSet))
                if( ! isUserAllowed(rol, rolesSet)) 
                {
                    requestContext.abortWith(ACCESS_DENIED);
                    return;
                }
            }
        }
    }
    
    private String getToken(List<String> authorization) {
    	
    	return authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", ""); // pulir mas esto..
	
    }
    
    
	private boolean isUserAllowed(final String rol, final Set<String> rolesSet)
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
//        String userRole = "ADMIN";
         
        //Paso 2. verificamos si el rol del usuario esta contenido en los roles que permite el método.
        if(rolesSet.contains(rol))
        {
            isAllowed = true;
        }
        return isAllowed;
    }

}