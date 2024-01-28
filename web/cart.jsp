<%-- 
    Document   : cart
    Created on : Jan 25, 2024, 1:49:49 AM
    Author     : huypd
--%>

<%@page import="java.util.List"%>
<%@page import="model.Fruit"%>
<%@page import="model.Fruit"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CART</title>
    </head>
    <body>
        <h1>LIST OF YOUR CART</h1>
        <table border="1px" style="width: 40%">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Describe</th>
                <th>Price</th>
                <th>x</th>
            </tr>
            <% 
                List<Fruit> list = (List<Fruit>) request.getAttribute("listCart");
                if (list != null) {
                    for (Fruit f : list) {
            %>
            <tr>
                <td><%= f.getProductId() %></td>
                <td><%= f.getProductName() %></td>
                <td><%= f.getDescription() %></td>
                <td><%= f.getPrice()%></td>
                <td>
                    <a href="delete?id=<%= f.getProductId() %>"> Delete </a>
                </td>
            </tr>
            <%      }
                }
            %>
        </table>
    </body>
</html>
