package com.sgem.controladores;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.sgem.datatypes.DataComite;
import com.sgem.datatypes.DataDeportista;
import com.sgem.datatypes.DataPais;
import com.sgem.dominio.ComiteOlimpico;
import com.sgem.dominio.Deportista;
import com.sgem.dominio.EventoDeportivo;
import com.sgem.dominio.Imagen;
import com.sgem.persistencia.IDeportistaDAO;
import com.sgem.persistencia.IImagenDAO;
import com.sgem.seguridad.excepciones.AplicacionException;
import com.sgem.utilidades.ImagenUtil;



@Stateless
public class DeportistaController implements IDeportistaController {

	@EJB
	private IDeportistaDAO DeportistaDAO;
	
	@EJB
	private IUsuarioController iuc;
	
	@EJB
	private IEventoDeportivoController iedc;
	
	@EJB
	private IImagenDAO ImagenDAO;
		
	@Override
	public boolean guardarDeportista(DataDeportista dataDeportista) throws AplicacionException {
		boolean guardo = false;
		Imagen foto = new Imagen(dataDeportista.getFoto().getMime(), dataDeportista.getFoto().getRuta(), dataDeportista.getFoto().getTenantId());
		
		if(ImagenDAO.guardarImagen(foto)){				
		
			Deportista deportista = new Deportista();
		
			deportista.setNombre(dataDeportista.getNombre());
			deportista.setApellido(dataDeportista.getApellido());
			deportista.setFechaNac(dataDeportista.getFechaNac());
			deportista.setSexo(dataDeportista.getSexo());
			deportista.setTenantID(dataDeportista.getTenantId());
			deportista.setFoto(foto);
			
			List<ComiteOlimpico>  comiteOlimpico = iuc.buscarComiteporPais(dataDeportista.getComite().getPais().getPaisID(),dataDeportista.getTenantId());
			
			deportista.setComiteOlimpico(comiteOlimpico.get(0));
			
			List<EventoDeportivo> eventosDep = iedc.buscarEventosDeportivos(dataDeportista.getTenantId(),dataDeportista.getDeporte(),dataDeportista.getDisciplinas(),dataDeportista.getSexo());
			
			for (int i = 0; i < eventosDep.size(); i++) {
				
				EventoDeportivo dep = eventosDep.get(i);
				
				deportista.addEventoDeportivo(dep);
				
			}	
			
			guardo = DeportistaDAO.guardarDeportista(deportista);
					
		}else{
			try {
				ImagenUtil.borrarImagen(dataDeportista.getFoto().getRuta());
			} catch (IOException e) {
				throw new AplicacionException("Error al guardar Comite Olimpico. No se pudo borrar la imagen para dicho comite.");
			}	
			throw new AplicacionException("Error al guardar imagen");
		}
		
		return guardo;
	}

	@Override
	public List<DataDeportista> listarDeportistas(int tenantID, String nombreDeporte, String sexo, String nombreDisciplina) {
		try {
					
				return convertir(DeportistaDAO.listarDeportistas(tenantID, nombreDeporte, sexo, nombreDisciplina));
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}
	
	
	
	public List<DataDeportista> convertir(List<Deportista> deportista){
		List<DataDeportista> dataDeportista = new ArrayList<DataDeportista>();
		
		for(int i = 0; i< deportista.size(); i++){
			DataDeportista ddep = new DataDeportista();
					
			ddep.setNombre(deportista.get(i).getNombre());
			ddep.setApellido(deportista.get(i).getApellido());
			DataComite dc = new DataComite(deportista.get(i).getComiteOlimpico().getEmail(), "", deportista.get(i).getComiteOlimpico().getCodigo(),
					new DataPais(deportista.get(i).getComiteOlimpico().getPais().getPaisID(), deportista.get(i).getComiteOlimpico().getPais().getPais(), deportista.get(i).getComiteOlimpico().getPais().getCiudad()),
					deportista.get(i).getComiteOlimpico().getFacebook(), deportista.get(i).getComiteOlimpico().getTwitter(), deportista.get(i).getComiteOlimpico().getPaypal(), deportista.get(i).getComiteOlimpico().getTenantID(),
					deportista.get(i).getComiteOlimpico().getId().intValue(), UsuarioController.USUARIO_COMITE);
			ddep.setComite(dc);
			ddep.setDeportistaID(deportista.get(i).getDeportistaID());
			

			dataDeportista.add(ddep);			
		}
		
		return dataDeportista;		
		
	}
	
	
	@Override
	public List<Deportista> listarDeportistas(List<DataDeportista> deportistas) {
		try {
					
			List<Deportista> listaDeportistas = new ArrayList<Deportista>();
			
			for(int i = 0; i< deportistas.size(); i++){
				
				Deportista d = new Deportista();
				
				d = DeportistaDAO.buscarDeportista(deportistas.get(i).getDeportistaID());
				
				listaDeportistas.add(d);
				
			} 
				
			return listaDeportistas;
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}

	@Override
	public Imagen subirImagenDeportista(MultipartFormDataInput input) throws AplicacionException {
		String fileName = "";
		String tenantId = "";
		String comiteId = "";
		File f = null;
		
		try {
			Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
			List<InputPart> inputParts = uploadForm.get("file");
		
			tenantId = uploadForm.get("tenantId").get(0).getBodyAsString();	
			comiteId = uploadForm.get("comiteId").get(0).getBodyAsString();	
		
			for (InputPart inputPart : inputParts) {		
	
				MultivaluedMap<String, String> header = inputPart.getHeaders();
				
				fileName = ImagenUtil.getDeportistaFilePath(header,tenantId,comiteId);
	
				InputStream inputStream;
			
				inputStream = inputPart.getBody(InputStream.class,null);				
	
				byte [] bytes = IOUtils.toByteArray(inputStream);
					
				String dir = ImagenUtil.getDeportistaDirectoryName(tenantId,comiteId);
				
				f = ImagenUtil.writeFile(bytes,fileName,dir);			
			}
		} catch (IOException e) {
			throw new AplicacionException("Error al subir la imagen");
		}catch(Exception e){
			throw new AplicacionException("Error al subir la imagen");
		}		
		
		return (new Imagen(ImagenUtil.getMimeType(f), fileName,Integer.parseInt(tenantId)));
		
	}
	
	
	
}
