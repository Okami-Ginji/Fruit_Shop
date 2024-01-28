/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author huypd
 */
public class OrderDetails {
    private int OrderDetailID;
    private int OrderID;
    private int ProductID;
    private int quantity;
    private double subtotal;

    public OrderDetails() {
    }
    
    public OrderDetails(int OrderDetailID, int OrderID, int ProductID, int quantity, double subtotal) {
        this.OrderDetailID = OrderDetailID;
        this.OrderID = OrderID;
        this.ProductID = ProductID;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public int getOrderDetailID() {
        return OrderDetailID;
    }

    public void setOrderDetailID(int OrderDetailID) {
        this.OrderDetailID = OrderDetailID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
}
