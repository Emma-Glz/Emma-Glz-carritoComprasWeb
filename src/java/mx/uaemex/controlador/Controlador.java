/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaemex.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.uaemex.modelo.Carrito;
import mx.uaemex.modelo.Producto;
import mx.uaemex.modelo.ProductoDAO;

/**
 *
 * @author sug-0
 */
@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    ProductoDAO pdao = new ProductoDAO();
    Producto p=new Producto();
    List<Producto> productos= new ArrayList<>(); 
    List<Carrito>  listaCarrito = new ArrayList<>();
    int item;
    double totalPagar=0.0;
    int cantidad =1;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    String accion=request.getParameter("accion");
    productos = pdao.listar();//trae los registros
    switch(accion){
        case "AgregarCarrito":
            int idp = Integer.parseInt(request.getParameter("id"));
            p=pdao.listarId(idp);
            item=item+1;
            Carrito car=new Carrito();
            car.setItem(item);
            car.setIdProducto(p.getId());
            car.setNombres(p.getNombres());
            car.setDescripcion(p.getDescripcion());
            car.setPrecioCompra(p.getPrecio());
            car.setCantidad(cantidad);
            car.setSubTotal(cantidad*p.getPrecio());
            listaCarrito.add(car);
            request.setAttribute("contador", listaCarrito.size());
            request.getRequestDispatcher("Controlador?accion=home").forward(request, response);
            
            
        break;
        case "Carrito":
            totalPagar=0.0;            
            request.setAttribute("Carrito",listaCarrito);
            for(int i = 0; i<listaCarrito.size();i++){
               totalPagar=totalPagar+listaCarrito.get(i).getSubTotal();
            }
            request.setAttribute("totalPagar", totalPagar);
            request.getRequestDispatcher("carrito.jsp").forward(request,response);
            
        break;
        
        default:
        request.setAttribute("producto",productos);// El primero hace referencia a un nombre para pasarlo a index
        request.getRequestDispatcher("index.jsp").forward(request,response);
        
    }
    
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
