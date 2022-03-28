package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.Account;
import model.ErrorObj;
import model.Login;

public class AccountDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	ErrorObj error;
	
	public AccountDAO() {
		// TODO 自動生成されたコンストラクター・スタブ
		con = null;
		pstmt = null;
		rs = null;
		error = new ErrorObj();
	}
	
	public Account findByLogin(Login login) {
		Account account =null;
		Context ctx;
		
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/chatRoom");
			con = ds.getConnection();
			
			String sql = "select * from Account where id = ? and pass=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, login.getId());
			pstmt.setString(2, login.getPass());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String pass = rs.getString("pass");
				
				account = new Account(id, name, pass);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				if(con!=null) {
					con.close();
				}
				if(pstmt!=null) {
					pstmt.close();
				}
				if(rs!=null) {
					rs.close();
				}
				
				
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		return account;
	}

	
	public ErrorObj isDuplication(String value,String columnsName){
		Context ctx = null;
		try {
			ctx = new InitialContext();
			DataSource ds = 
				(DataSource)ctx.lookup("java:comp/env/jdbc/chatRoom");
			con = ds.getConnection();
			String baseStr = "select * from Account ";
			String conditionStr = "where " + columnsName + " = ?";
			String sql = 
				baseStr + conditionStr;
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, value);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()!=false) {
				
				error.setError(true);
				error.setMsg("すでに使用されています。");
			}
			
		}catch(Exception e) {
			error.setError(true);
			error.setMsg("データベース操作エラー");
		}finally {
			try {
				con.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		return error;
	}
	
	public boolean insertAccount(Account account) {
		Context ctx = null;
		try {
			ctx = new InitialContext();
			DataSource ds = 
				(DataSource)ctx.lookup("java:comp/env/jdbc/chatRoom");
			con = ds.getConnection();
			System.out.println("id: " + account.getId());
			System.out.println("name: " + account.getName());
			System.out.println("pass: " + account.getPass());
			String sql = 
				"INSERT INTO `account`(`id`, `name`, `pass`) VALUES (?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,account.getId());
			pstmt.setString(2,account.getName());
			pstmt.setString(3,account.getPass());
			
			int result = pstmt.executeUpdate();
			return (result==1);
		}catch(Exception e) {
			System.out.println("insertError");
			System.out.println(e);
			return false;
		}finally {
			try {
				
				con.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}
	
}
