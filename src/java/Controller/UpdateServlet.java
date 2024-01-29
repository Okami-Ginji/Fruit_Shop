/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Fruit;
import Model.FruitDB;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author huypd
 */
public class UpdateServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String idString = request.getParameter("id");
        try {
            int id = Integer.parseInt(idString);
            Fruit f = FruitDB.getFruit(id);
            request.setAttribute("fruit", f);
            request.getRequestDispatcher("update.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
 
        String idString = request.getParameter("id");
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        if(isValidUrl(image) && image.startsWith("/")) {
            image = image.substring(1);
        }
        else if(!isValidUrl(image)){
            image = "images/Buoi.jpg";
        }
        String describe = request.getParameter("describe");
        String priceString = request.getParameter("price");
        try {
            int id = Integer.parseInt(idString);
            double price = Double.parseDouble(priceString);
            Fruit f = new Fruit(id, name, describe, price, image);
            FruitDB.update(f);
            response.sendRedirect("list");
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }
    public boolean isValidUrl(String url) {
       String imageUrlRegex = ".*\\.(jpg|jpeg|png|gif|bmp)$";       
        Pattern pattern = Pattern.compile(imageUrlRegex);
        Matcher matcher = pattern.matcher(url);


        return matcher.matches();
    }
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
