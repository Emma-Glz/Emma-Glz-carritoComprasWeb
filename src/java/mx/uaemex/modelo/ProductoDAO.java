/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaemex.modelo;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import static javax.swing.UIManager.getInt;
import mx.uaemex.config.Conexion;

/**
 *
 * @author sug-0
 */
public class ProductoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    //TRAE TODOS LOS REGISTROS CORRESPONDIENDO A LA COLUMNA DE LA TABLA
    public List listar(){// obtiene el producto a traves de el id y la clase Producto  
        List<Producto> productos = new ArrayList();
        String sql = "select * from producto";
        try{//gestina la consulta sql
           con = cn.getConnection();
           pst = con.prepareStatement(sql);
           rs = pst.executeQuery();
            while(rs.next()){//hay 2 formas OCUPAR EL CONSTRUCTOR DE PRODUCTO VACIO O PASARLE LOS PARAMETROS
                //utlizar numero para indentificar las columnas de la tabla producto es mejor que poner el nombre
                Producto p = new Producto();
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setFoto(rs.getBinaryStream(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));
                productos.add(p);
            }
        }catch(SQLException e){//cacha excepciones encontradas
            System.out.println("exception en la consulta");
        }
        return productos;//devulve los productos  
    }
    public void listImg(int id,HttpServletResponse response){
        String sql = "SELECT * from producto where idProducto=" + id;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream= null;
        BufferedOutputStream bufferedOutputStream=null;
        try{
            outputStream = response.getOutputStream();
            con=cn.getConnection();
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                inputStream=rs.getBinaryStream("Foto");
            }
            bufferedInputStream = new BufferedInputStream(inputStream);  
            bufferedOutputStream=new BufferedOutputStream(outputStream);
            int i=0;
            while((i = bufferedInputStream.read())!=-1){
                bufferedOutputStream.write(i);
            }
        }catch(Exception e){    
        }
        
    }
    
}
