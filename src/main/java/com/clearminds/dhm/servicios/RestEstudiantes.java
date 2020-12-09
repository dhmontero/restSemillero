package com.clearminds.dhm.servicios;

import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.clearminds.dhm.dtos.Estudiante;
import com.clearminds.dhm.excepciones.BDDException;

@Path("estudiantes")
public class RestEstudiantes {
	
	@Path("insertar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void insertar(Estudiante e) {
		ServicioEstudiante srvEstudiante = new ServicioEstudiante();
		try {
			srvEstudiante.insertarEstudiante(e);
		} catch (BDDException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}
	
	@Path("actualizar")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void actualizarEstudiante(Estudiante est) throws BDDException {
		ServicioEstudiante srvEstudiante = new ServicioEstudiante();
		srvEstudiante.abrirConexion();
		System.out.println("Actualizando Estudiante: " + est);
		Statement stmt = null;
		try {
			stmt = srvEstudiante.getConexion().createStatement();

			String sql = "update estudiantes set nombre='"+est.getNombre()+"', apellido='"+est.getApellido()+"' where id='"+est.getNumero()+"'";
			System.out.println("Script: " + sql);

			stmt.executeUpdate(sql);
			srvEstudiante.cerrarConexion();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BDDException("Error al insertar estudiante");
		}
		
	}
}