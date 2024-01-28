<%-- 
    Document   : list_product
    Created on : Dec 13, 2023, 8:48:11 AM
    Author     : Dell
--%>

<%@page import="Model.Fruit"%>
<%@page import="Model.FruitDB"%>
<%@page contentType="text/html" import="Model.*,java.util.*" pageEncoding="UTF-8"%>
<%@page import="Model.FruitDB"%>

<!-- Product Listings Section -->
<div class="container">
    <div class="row">
        <%
            List<Fruit> allFruits = (List<Fruit>) request.getAttribute("data");
            if (allFruits != null) {
                for (Fruit o : allFruits) {
                String image = o.getProductImage();
        %>
        <div class="col-md-4">
            <a href="list_product.jsp"></a>
          
            <div class="card">
                <img src=<%= image.substring(1)%> class="card-img-top" height="356px" alt="<%=o.getProductName() %>">          
                <div class="card-body">
                    <h5 class="card-title"><%=o.getProductName() %></h5>
                    <p class="card-text"><%=o.getDescription()%></p>
                    <p class="card-text">Price: $<%=o.getPrice()%>/kg</p>
                    <a href="addToCart?id=<%= o.getProductId() %>" class="btn btn-primary">Add to Cart</a>
                    <a href="update?id=<%= o.getProductId() %>" class="btn btn-primary">Update</a>
                    <a href="#" onclick="doDelete(<%= o.getProductId() %>)" class="btn btn-primary">Delete</a>
                </div>
            </div>
                <script type="text/javascript">
                    function doDelete(id) {
                        if (confirm("Are you sure to detele category with id = " + id + "?"))
                            window.location = "delete?id=" + id;
                    }
                </script>
        </div>
        <% 
                }
            } 
else {
        %>
        <p>Error</p>
        <%}%>
</div>
