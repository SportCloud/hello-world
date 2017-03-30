package com.baisong.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TxDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day12", "root", "123456");
			conn.setAutoCommit(false); //开启事务
			
			stmt = conn.prepareStatement("update account set money = money -100 where name = 'aaa'"); 
			stmt.executeUpdate();
			
			int i = 1 / 0;
			
			stmt = conn.prepareStatement("update account set money = money +100 where name = 'bbb'"); 
			stmt.executeUpdate();
			
			conn.commit();//提交事务
		
		} catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			if(stmt != null){
				try{
					stmt.close();
				} catch (SQLException e){
					e.printStackTrace();
				}
				stmt = null;	
			}
				
		}
		
		if(conn != null){
			try{
				conn.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
			conn = null;	
		}
		
		
	}

}
