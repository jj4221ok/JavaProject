package Naver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {

	public static Connection DBConnect() {
		
		Connection con = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		
		String user = "EUNJU";
		String password = "1111";
		
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection(url, user, password);
			
			System.out.println("DB접속 성공!");
			
		} catch (ClassNotFoundException cne) {
			cne.printStackTrace();
			System.out.println("DB접속 실패 : 드라이버 로딩 실패!");
			
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("DB접속 실패 : 계정 정보 확인!");
		}

		
		return con;
	}
	
	
	
}





