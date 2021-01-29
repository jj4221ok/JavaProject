package Bank;

import java.sql.*;

public class BankDAO {

		// DB접속을 위한 변수 선언
		Connection conn = null;
		
		// 쿼리문 전송을 위한 변수 선언
		PreparedStatement pstmt = null;
		
		// 조회결과를 저장하기 위한 변수 선언
		ResultSet rs = null;
		
		
		// DB접속을 위한 메소드 connect()
		public void connect() {
			conn = DBC.DBConnect();
		}
		
		
		// DB접속 해제를 위한 메소드 conClose()
		public void conClose() {
			try {
				conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
		
		
		// 고객번호를 생성하기 위한 메소드 clientNumber()
		public int clientNumber() {
			String sql = "SELECT COUNT(*) FROM BANK";
			int cNumber = 0;
			
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					cNumber = rs.getInt(1);
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
			
			return cNumber;
		}


		// 고객정보를 저장하기 위한 메소드 insertClient()
		public void insertClient(BankDTO client) {
			String sql = "INSERT INTO BANK VALUES(?,?,?,?)";
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, client.getClientNumber());
				pstmt.setString(2, client.getcName());
				pstmt.setString(3, client.getAccountNumber());
				pstmt.setInt(4, client.getBalance());
				
				int result = pstmt.executeUpdate();
				
				if(result > 0) {
					System.out.println("고객 등록 성공!");
				} else {
					System.out.println("고객 등록 실패!");
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
			
		} // end insertClient()


		// 입금 메소드 deposit()
		public void deposit(BankDTO client) {
			String sql = "UPDATE BANK SET BALANCE = BALANCE + ? WHERE ACCOUNTNUMBER = ? ";
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, client.getBalance());
				pstmt.setString(2, client.getAccountNumber());
				
				int result = pstmt.executeUpdate();
				
				if(result > 0) {
					System.out.println("입금 성공!");
				} else {
					System.out.println("입금 실패!");
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		
		} // end deposit()


		// 출금 메소드 withdraw()
		public void withdraw(String accountNumber, int balance) {
			String sql = "UPDATE BANK SET BALANCE = BALANCE - ? WHERE ACCOUNTNUMBER = ? ";
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, balance );
				pstmt.setString(2, accountNumber);
				
				int result = pstmt.executeUpdate();
				
				if(result > 0) {
					System.out.println("출금 성공!");
				} else {
					System.out.println("출금 실패!");
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		
		} // end withdraw()


		// 잔액조회 메소드 checkBalance()
		public int checkBalance(String accountNumber) {
			String sql = "SELECT BALANCE FROM BANK WHERE ACCOUNTNUMBER = ? ";
			
			int balance = 0;
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, accountNumber);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					balance = rs.getInt(1);
					// balance = rs.getInt("BALANCE");
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
			return balance;
		} // end checkBalance()


								// accountNumber = sAccountNumber
								// accountNumber = rAccountNumber
								// 이름은 달라도 상관없는데 데이터타입은 같아야한다.
		// 계좌조회를 하는 메소드 checkAccount()
		// 계좌번호가 있으면 true 없으면 false
		public boolean checkAccount(String accountNumber) {
			String sql = "SELECT ACCOUNTNUMBER FROM BANK WHERE ACCOUNTNUMBER=? ";
			
			boolean cAccount = false;
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, accountNumber);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					cAccount = true;
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			return cAccount;
		} // end checkAccount()


		
		// 송금 메소드 send()
		// 보내는 사람 계좌번호, 받는 사람 계좌번호, 송금액의 정보를 매개변수로 담아서 넘어옴
		public void send(String sAccountNumber, String rAccountNumber, int balance) {
			// 받는 사람은 돈이 + : 입금
			// 보내는 사람은 돈이  - : 출금
			withdraw1(sAccountNumber, balance);
			deposit1(rAccountNumber, balance);
			System.out.println("송금 성공!");
		} // end send()

		
		public void withdraw1(String accountNumber, int balance) {
			String sql = "UPDATE BANK SET BALANCE = BALANCE - ? WHERE ACCOUNTNUMBER = ? ";
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, balance );
				pstmt.setString(2, accountNumber);
				
				pstmt.executeUpdate();
				
			} catch (SQLException se) {
				se.printStackTrace();
			}
		
		} // end withdraw1()

		public void deposit1(String accountNumber, int balance) {
			String sql = "UPDATE BANK SET BALANCE = BALANCE + ? WHERE ACCOUNTNUMBER = ? ";
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, balance );
				pstmt.setString(2, accountNumber);
				
				pstmt.executeUpdate();
				
			} catch (SQLException se) {
				se.printStackTrace();
			}
		
		} // end deposit1()
	
	
	
	
}





