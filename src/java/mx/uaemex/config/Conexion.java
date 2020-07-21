/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaemex.config;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

/**
 *
 * @author sug-0
 */
public class Conexion {
    private String usr = "root";// Usuario para conectar a el gestor WorkBench
    private String pass ="";//contraseña de ese usuario
    private String hostname = "localhost";//nombre del equipo para conectarnos a el.
    private String port = "3306";//atraves de el puerto
    private String database = "bdcarritocompras";//nuestra base de datos
    private String classname = "com.mysql.jdbc.Driver";//Metodos para registrar y conectar controladores 
    private String url = "jdbc:mysql://" + hostname + ":" + port + "/"+database;//URL del sitio al que queremos acceder
    private Connection conn;// variable para gestionar la conexion
    
    public Conexion(){
        try{
            Class.forName(classname);//carga dinamica de controlador que estiende DriverManager
            conn = (Connection) DriverManager.getConnection(url,usr,pass);//pasados 3 parametros obtiene la conexion  atraves 
            //del DriverManager
        }catch(Exception e){//captura una exception en el caso deque haya una
            System.err.println("no se pudo conectar" + e);
        }
    }
     public Connection getConnection(){//metodo que retorna la conexion
        return this.conn;
    }
    
}
