package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import modelo.Compra;
import modelo.DetalleCompra;


public class ControladorCompra {

    public void Agregar(Compra p) throws SQLException, ClassNotFoundException, ErrorTienda, ErrorTienda {
        Conexion cn = new Conexion();
        
        try{
        cn.conectar();      
        cn.UID("INSERT INTO compra(IdCompra,Fecha,IdProveedor,Total) VALUES('" + p.getIdCompra() + "','" + p.getFecha() + "','" + p.getPROVEEDOR().getIdProveedor() + "','" + p.getTotal() + "')");
        cn.desconectar();
        } catch (Exception ex){
            throw new ErrorTienda("Insertar" + ex.getMessage());
        }finally{
            cn.desconectar();
        }
        
    }
    public void ActulizarInventario(Compra p) throws SQLException, ClassNotFoundException, ErrorTienda, ErrorTienda {
        Conexion cn = new Conexion();
        try{
        cn.conectar();
        
        cn.UID("UPDATE detallecompra SET IdCompra='" + p.getIdCompra() + "',producto ='" + p.getArticulos().getCantidad()+"'");
        } catch (Exception ex){
             throw new ErrorTienda("Actulizar Inventarior" + ex.getMessage());
        }finally{cn.desconectar();
        }
    }
       public Integer ObtenerIdCompra() throws SQLException, ClassNotFoundException, ErrorTienda, ErrorTienda {
        Integer latestId=0;
        Conexion cn = new Conexion();
        try{
        cn.conectar();
        ResultSet rs = null;
        rs=cn.getValores("SELECT MAX(idCompra) FROM compra");
        latestId = Integer.parseInt(rs.toString());
        
        return latestId;      
        } catch (Exception ex){
            throw new ErrorTienda("Obtener IdCompra" + ex.getMessage());            
        }finally{
            cn.desconectar();
            } 
        }

}
