<%-- 
    Document   : new
    Created on : Jan 25, 2024, 10:53:35 AM
    Author     : huypd
--%>

<%@page import="Model.Fruit"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add new</title>
    </head>
    <body>
        <form action="update">
            
        </form>
        <h1>Update a product</h1>
        <%
            if (request.getAttribute("fruit") != null) {
                Fruit f = (Fruit) request.getAttribute("fruit");
        %>
        <form action="update" method="post">
            Enter id: <input type="number" readonly name="id" value="<%= f.getProductId() %>"/> </br>
            Enter name: <input type="text" name="name" value="<%= f.getProductName()%>"/> </br>
            Enter image: <input type="text" name="image" value="<%= f.getProductImage()%>"/> </br>
            Enter describe: <input type="text" name="describe" value="<%= f.getDescription()%>"/> </br>
            Enter price: <input type="text" name="price" value="<%= f.getPrice()%>"/> </br>
            <input type="submit" value="UPDATE"/>
        </form>
        <% 
            }
        %>
    </body>
</html>
