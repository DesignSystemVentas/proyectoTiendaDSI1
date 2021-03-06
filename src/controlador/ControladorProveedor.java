/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import sql.cnMYSQL;
import modelo.Proveedor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author JOSHUA
 */
public class ControladorProveedor {
  
    public ResultSet buscar(String nombre) throws Exception {
        nombre = '%' + nombre + '%';
        Connection connection = cnMYSQL.getIntance().getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM proveedor where nombre like ?");
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        return rs;

    }
       public ResultSet obtenerProveedor() throws Exception {
        Connection connection = cnMYSQL.getIntance().getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM proveedor ");
        ResultSet rs = ps.executeQuery();
        return rs;

    }

    public void agregarProveedor(Proveedor to) throws Exception {
        Connection connection = cnMYSQL.getIntance().getConnection();
        CallableStatement cs=connection.prepareCall("CALL Proveedor_insert(?,?,?,?,?)");
        cs.setInt(2, to.getIdProveedor());
        cs.setString(2, to.getNombre());
        cs.setString(3, to.getTelefono());
	cs.setString(5, to.getNIT());
        cs.execute();
    }

    public void eliminarProveedor(Proveedor to) throws Exception{
          Connection connection=cnMYSQL.getIntance().getConnection();
          CallableStatement cs=connection.prepareCall("CALL Proveedor_Eliminar(?)");
          cs.setInt(1, to.getIdProveedor());
          cs.execute();
    }
    
    public void modificarProveedor(Proveedor to) throws Exception{
          Connection connection=cnMYSQL.getIntance().getConnection();
          CallableStatement cs=connection.prepareCall("CALL Proveedor_update(?,?,?,?,?)");
          cs.setString(2, to.getNombre());
          cs.setString(3, to.getTelefono());
          cs.setString(4, to.getDireccion());
	  cs.setString(5, to.getNIT());	
          cs.execute();   

}

    
}

