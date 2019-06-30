package quizServer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Satvik
 */


import java.sql.*;

public class LogIn {
    Connection con;
    
    String name;
    String userName;
    String password;
    int techOrStu;
    
    LogIn(String userName,String password)
    {
       this.password = password;
       this.userName = userName;
    }
   
   LogIn( String userName,String password, String name, int techOrStu)
   {
       this.name = name;
       this.password = password;
       this.userName = userName;
       this.techOrStu = techOrStu;
   }
   LogIn(String userName)
   {
       this.userName = userName;
   }
   
   LogIn()
   {
       
   }
    public void connect()
    {
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/server","root","root");
        }
        catch(Exception e)
        {
            System.out.println(" Unable to connect the databese ");
        }
    }
    
    public int getCount(boolean increment)
    {
        int temp=0;
        String sql = "select *from users where userID = ? ; ";
      
        try{
            PreparedStatement ptm = con.prepareStatement(sql);
            
            ptm.setString(1,userName);
            
            ResultSet rs = ptm.executeQuery();
             
            int count = 13;
            rs.next();
            count = rs.getInt(4);
           
            temp = count;
            
            if(increment)
            {
                System.out.println("Incrementing....");
                count++;            
                System.out.println(count);
                String update = "update users set count = ? where userID = ? ;";
                PreparedStatement pt = con.prepareStatement(update);
                pt.setInt(1, count);
                pt.setString(2, userName);
                pt.executeUpdate();
            }
            
        }catch(Exception ex)
        {
            System.out.println("Error in getting and updating count ");
            System.out.println(ex.getMessage());
        }
        return temp ;
        
    }
    
    public boolean login()
    {
        String check = "select * from users where userID = ? and password = ? ;";
        
        
        try{
            PreparedStatement ptm = con.prepareStatement(check);
            ptm.setString(1, userName);
            ptm.setString(2,password);
            System.out.println("Request to Login.......");
            
            //Statement stm = con.createStatement();
            ResultSet rs = ptm.executeQuery();
            if(!rs.isBeforeFirst())
                return false;
            else 
                return true;
        }
        catch(Exception e)
        {
            System.out.println(" Error in Login");
        }
        return false;
    }
    
    public boolean register()
    {
        String check = "select *from users where userID = ?;";
        
        try{
            System.out.println("Checking duplicate entries....");
            PreparedStatement ptm = con.prepareStatement(check);
            ptm.setString(1,userName);
            
            ResultSet rs = ptm.getResultSet();
            if(rs.isBeforeFirst())
                return false;
     
        }
        catch(Exception e)
        {
            System.out.println("Error in checking for duplicate enteries");
        }
        
        
        String query = "insert into users values (?,?,?,?,?);";
        
         try{
            PreparedStatement ptm = con.prepareStatement(query);
            ptm.setString(1,userName);
            ptm.setString(2,name);
            ptm.setString(3,password);
            ptm.setInt(4,0);
            ptm.setInt(5,1);
     
            ptm.executeUpdate();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        
        return false;
    }
    
    public void insert(String contestID, String question, String answer)
    {
        String sql = "insert into question values(?,?,?);";
        
        try{
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setString(1, contestID);
            ptm.setString(2, question);
            ptm.setString(3, answer);
            ptm.executeUpdate();
            
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    public ResultSet getQuestion(String quiz_code)
    {
        String sql = "select * from question where contestID = ?;";
        try{
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setString(1, quiz_code);
            ResultSet rs = ptm.executeQuery();
            return rs;
        }catch(Exception ex ){
            System.out.println("Error in retreiving message....");
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
}
