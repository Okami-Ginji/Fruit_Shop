
package Model;

import java.sql.*;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FruitDB implements DatabaseInfo{
    public static Connection getConnect(){
        try{ 
            Class.forName(DRIVERNAME); 
	} catch(ClassNotFoundException e) {
            System.out.println("Error loading driver" + e);
	}
        try{            
            Connection con = DriverManager.getConnection(DBURL,USERDB,PASSDB);
            return con;
        }
        catch(SQLException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }
    /*public static Connection getConnect(){
        try{
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
                    DataSource ds = (DataSource) envContext.lookup("jdbc/mydb");
                    return ds.getConnection();
        } catch (SQLException | NamingException ex){
            System.out.println("Error: " + ex);
        }
        return null;
    }*/
    public static Fruit getFruit(int id){
        Fruit s=null;
        try(Connection con=getConnect()) {
            PreparedStatement stmt=con.prepareStatement("Select productName, description, price  from Products where productID=?");
            stmt.setInt(1, id);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                String name=rs.getString(1);
                String description=rs.getString(2);
                double price = rs.getDouble(3);
                s=new Fruit(id,name,description,price);
            }
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(FruitDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
//--------------------------------------------------------------------------------------------
    public static String login(String email) throws Exception{
        String pw=null;
        Connection con=getConnect();
        try{
            PreparedStatement stmt=con.prepareStatement("Select Password from Customert where email=?");
           
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                
                pw=rs.getString(1);                
               
            }
            
        } catch (Exception ex) {
            Logger.getLogger(FruitDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {con.close();}
        return pw;
    }
    
    public static int newFruit(Fruit s){
        int id=-1;
        try(Connection con=getConnect()) {
            PreparedStatement stmt=con.prepareStatement("Insert into Fruit(ProductID, ProductName, Description, Category, Price, StockQuantity, ProductImage, UnitOfMeasurement) output inserted.id values(?,?,?)");
            stmt.setString(1, s.getProductName());
            stmt.setString(2, s.getProductName());
          
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                id=rs.getInt(1);
            }
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(FruitDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    public static void insert(Fruit s) {
        try(Connection con=getConnect()) {
            PreparedStatement stmt=con.prepareStatement("insert into Products (ProductID, ProductName, ProductImage, Description, Price) values (?,?,?,?,?)");
            stmt.setInt(1, s.getProductId());
            stmt.setString(2, s.getProductName());
            stmt.setString(3, s.getProductImage());
            stmt.setString(4, s.getDescription());
            stmt.setDouble(5, s.getPrice());
            stmt.executeUpdate();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(FruitDB.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Invalid data");
        }
    }
//-----------------------------------------------------------------------------------
    public static Fruit update(Fruit s){
        try(Connection con=getConnect()) {
            PreparedStatement stmt=con.prepareStatement("Update Products set productName=?, description=?,price=? where productID =?");
            stmt.setString(1, s.getProductName());
            stmt.setString(2, s.getDescription());
            stmt.setDouble(3, s.getPrice());
            stmt.setInt(4, s.getProductId());
            stmt.executeUpdate();
            con.close();
            return s;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }
//--------------------------------------------------------------------------------
        public static void delete(int id){
         try(Connection con=getConnect()) {
            PreparedStatement stmt=con.prepareStatement("Delete Products where productID =?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(FruitDB.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
//--------------------------------------------------------------------------------------------
        public static ArrayList<Fruit> search(Predicate<Fruit> p){
            ArrayList<Fruit> list=listAll();
            ArrayList<Fruit> res= new ArrayList<Fruit>();
            for(Fruit s:list)
                if(p.test(s)) res.add(s);
            return res;
        }
//--------------------------------------------------------------------------------------------
        public static ArrayList<Fruit> listAll(){
          ArrayList<Fruit> list= new ArrayList<Fruit>();
          //Connection con = getConnect();
          try(Connection con=getConnect()) {
            PreparedStatement stmt=con.prepareStatement("Select productID, productName, description, price,ProductImage from Products");
            ResultSet  rs=stmt.executeQuery();
            while(rs.next()){
                list.add(new Fruit(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getDouble(4), rs.getString(5)));
            }
            con.close();
            return list;
        } catch (Exception ex) {
            Logger.getLogger(FruitDB.class.getName()).log(Level.SEVERE, null, ex);
        }   
          return null;
        }
//--------------------------------------------------------------------------------------------
    public static void main(String[] a){
        ArrayList<Fruit> list = FruitDB.listAll();
        for (Fruit item: list) 
        {
            System.out.println(item);
        }
    }
//---------------------------------------------------------------------------
}
