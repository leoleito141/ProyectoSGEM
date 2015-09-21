package com.sgem.seguridad;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
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
            
            //Si no hay informaci�n de autorizaci�n presente, bloqueo el acceso.
            if(authorization == null || authorization.isEmpty())
            {
                requestContext.abortWith(ACCESS_DENIED);
                return;
            }
                               
            /*	A partir este punto, se procesaran los datos obtenidos de los headers del m�todo GET */         
            
            final String token = getToken(authorization);          
            
            try {
                Jwts.parser().setSigningKey(clave).parseClaimsJws(token);
                //OK, we can trust this JWT
            } catch (SignatureException e) {
                //don't trust the JWT!
            	 requestContext.abortWith(ACCESS_DENIED);
                 return;
            } catch(MalformedJwtException e){
            	 requestContext.abortWith(SERVER_ERROR);
                 return;
            }
            
            
            //Verifico el acceso del usuario al m�todo seg�n su rol.
            
            //Si no hay informaci�n de autorizaci�n presente, bloqueo el acceso.
            if(headers.get(ROL_PROPERTY) == null || headers.get(ROL_PROPERTY).isEmpty())
            {
            	requestContext.abortWith(ACCESS_DENIED);
            	return;
            }
            
            //Fetch authorization header
            String rol = headers.get(ROL_PROPERTY).get(0);
            
            if(method.isAnnotationPresent(RolesAllowed.class))
            {
                RolesAllowed anotacionesRol = method.getAnnotation(RolesAllowed.class);
                Set<String> setRoles = new HashSet<String>(Arrays.asList(anotacionesRol.value()));
                 
                if( ! usuarioPermitido(rol, setRoles)) 
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
    
	/**
	 * Se chequea que el rol del del usuario a utilizar método, sea uno de los
	 * roles que el método accepte.
	 * 
	 * @param rol rol del usuario
	 * @param setRoles  set de Roles del metodo
	 * @return usuario Permitido
	 */
	private boolean usuarioPermitido(final String rol, final Set<String> setRoles) {
		boolean permitido = false;

		if (setRoles.contains(rol)) {
			permitido = true;
		}
		return permitido;
	}

}