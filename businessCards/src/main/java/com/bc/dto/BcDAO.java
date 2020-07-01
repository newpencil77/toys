package com.bc.dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BcDAO
{
    
    ////////////////////
    private static BcDAO dao =null;
    private BcDAO(){}
    
    public static BcDAO getDao()
    {
        if(dao==null)
        {
            dao = new BcDAO();
        }
        return dao;
    }
    
    private static final String DBURL= "jdbc:mysql://localhost:3306/testdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final  String DBUSER = "tiger";
    private static final String DBPASSWD= "0000";
    private Connection con;
    
    
    
    public void getConnection()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(DBURL,DBUSER,DBPASSWD);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }  
        
    }
    
    public void addCard(BusinessCards bc)
    {
         
        getConnection();
        String sql="INSERT INTO businesscard VALUES(?,?,?,?)";
        
        
        try(PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setInt(1, bc.getNum());
            ps.setString(2, bc.getName());
            ps.setString(3, bc.getTel());
            ps.setString(4, bc.getEmail());
            
            ps.executeUpdate();
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            closeCon(con, null);
        }
    }
    
    
    public void delCard(int num)
    {
        getConnection();
        String sql="DELETE FROM businesscard WHERE num="+num;
        
        try(Statement st= con.createStatement())
        {
            st.executeUpdate(sql);
        }
        catch (Exception e)
        {           
            e.printStackTrace();
        }
        finally
        {
            closeCon(con, null);
        }
    }
    
    
    public List<BusinessCards> searchCard(String name)
    {
        getConnection();
        
        ResultSet rs = null;
        List<BusinessCards> arr= new ArrayList< >();
        
        String sql="SELECT * FROM businesscard WHERE name LIKE '%"+name+"%'";
        
        try(Statement st = con.createStatement();)
        {
            rs = st.executeQuery(sql);
           
            while(rs.next())
            {
                BusinessCards bc = new BusinessCards();
                bc.setNum(rs.getInt("num"));
                bc.setName(rs.getString("name"));
                bc.setTel(rs.getString("tel"));
                bc.setEmail(rs.getString("email"));
                
                arr.add(bc);                
            }
            
        
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            closeCon(con, rs);
        }
        return arr;
    }
    
    
    private void closeCon(Connection con, ResultSet rs)
    {
        try
        { 
            if(rs!=null)
                rs.close();
            if(con!=null) 
                con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    
}
