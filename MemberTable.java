import java.sql.*;
import java.util.Vector;

public class MemberTable {
//	public static void main(String[] args) {
//		String name="";
//		name = searchNameUsingId("aa");
//		System.out.println(name);
//	}


	public MemberTable() {
	}

	static String url = "jdbc:mysql://localhost:3306/dbr";
	static String id = "root";
	static String password = "1234";
	public Connection dc;

	public static void createTable () {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "create table memberTable (ID CHAR(20), password CHAR(20), "
				+ "name CHAR(20), stylePreference CHAR(30), "
				+ "heatSensitivity INT, coldSensitivity INT)"; 
		try {
			conn = DriverManager.getConnection(url, id, password);
			pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();		
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Vector loadMember() {
		Vector loadvc = new Vector();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "select * from memberTable";
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(url, id, password);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member m = new Member(rs.getString(1), rs.getString(2), 
						rs.getString(3), rs.getString(4), 
						rs.getInt(5), rs.getInt(6));
				loadvc.add(m);
			}
			// close 부분 나중에 처리
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loadvc;
	}


	public static String searchNameUsingId (String ID) {		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) { }
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = null;
		query = "select * from memberTable where ID = '" + ID + "'";
		ResultSet rs = null;
		String name="";
		try {
			conn = DriverManager.getConnection(url, id, password);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			// ID가 있으면 PW 검사
			if (rs.next() == true) {
				name = rs.getString(3);	
			}			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return name;
	}	


	//	public static Vector searchMember (String str, int field) {
	//		Vector searchvc = new Vector();
	//		try {
	//			Class.forName("com.mysql.jdbc.Driver");
	//		} catch (ClassNotFoundException e) { }
	//		Connection conn = null;
	//		PreparedStatement pstmt = null;
	//		String query = null;
	//		if(field==1) {			// ID
	//			query = "select * from memberTable where ID = '" + str + "'";
	//		} else if(field==2) {	// password
	//			query = "select * from memberTable where password = '" + str + "'";
	//		} else if(field==3) {	// name
	//			query = "select * from memberTable where name = '" + str + "'";
	//		} else if(field==4) {	// style pref
	//			query = "select * from memberTable where stylePreference = '" + str + "'";
	//		}
	////		else if(field==5) {	// heat sens
	////			query = "select * from memberTable where heatSensitivity is " + str;
	////		} else if(field==6) {	// cold sens
	////			query = "select * from memberTable where coldSensitivitiy is " + str;
	////		}
	//		ResultSet rs = null;		
	//		try {
	//			conn = DriverManager.getConnection(url, id, password);
	//			pstmt = conn.prepareStatement(query);
	//			rs = pstmt.executeQuery();
	//			while(rs.next()) {				
	//				Member m = new Member(rs.getString(1), rs.getString(2), 
	//						rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
	//				searchvc.add(m);				
	//			}			
	//			rs.close();
	//			pstmt.close();
	//			conn.close();
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//		}	
	//		return searchvc;
	//	}
	//	

	public static void registerMember (Vector regvc) {
		Vector vc = new Vector();
		vc = loadMember();
		vc.add(regvc);
		Member m = (Member)regvc.elementAt(0);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "insert into memberTable values(?, ?, ?, ?, ?, ?)";
		try {
			conn = DriverManager.getConnection(url, id, password);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,  m.getID().trim());
			pstmt.setString(2, m.getPassword().trim());
			pstmt.setString(3, m.getName().trim());
			pstmt.setString(4, m.getStylePreference().trim());
			pstmt.setInt(5, m.getHeatSensitivity());
			pstmt.setInt(6, m.getColdSensitivity());
			pstmt.executeUpdate();		
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public static boolean loginChecker(String ID, String pw) {
		//		Vector searchvc = new Vector();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) { }
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = null;
		//		query = "select ID, count(ID) as cnt from memberTable group by ID having cnt==1";
		query = "select * from memberTable where ID = '" + ID + "'";
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(url, id, password);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			// ID가 있으면 PW 검사
			if (rs.next() == true) {
				PreparedStatement pstmt1 = null;
				String query1 = null;
				ResultSet rs1 = null;
				query1 = "select password from memberTable where ID = '" + ID + "'";
				pstmt1 = conn.prepareStatement(query1);
				rs1 = pstmt1.executeQuery();
				if (rs1.next() == true) {
					if (pw.equals(rs1.getString(1))) {
						System.out.println("───────────────────────────────────────");
						System.out.println("    Welcome!");
						System.out.println("    '" + ID + "' successfully logged in.");
						System.out.println("\n───────────────────────────────────────\n");
						rs1.close();
						rs.close();
						pstmt1.close();
						pstmt.close();
						conn.close();
						return true;
					}
					// password 틀린 경우
					else {
						System.out.println("───────────────────────────────────────");
						System.out.println("    Wrong password!");
					}
				}
			}
			// id 없는 경우
			else {
				System.out.println("───────────────────────────────────────");
				System.out.println("    ID doesn't exist");
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}




	public static boolean registerIDChecker(String ID) {
		//		Vector searchvc = new Vector();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) { }
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = null;


		//		query = "select ID, count(ID) as cnt from memberTable where ID = " + ID + " group by ID having cnt=0";
		// 중복 체크
		query = "select * from memberTable where ID = '" + ID + "'";
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(url, id, password);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			// 중복된 ID 있으면 insert 불가
			if (rs.next() == true) {
				System.out.println("'" + ID + "' is already exists. Choose another ID.");
				rs.close();
				pstmt.close();
				conn.close();
				return false;
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}


	// 1-7 사이 선택하도록
	public static boolean stylePreferenceChecker (int stylePreference) {
		if (stylePreference == -99) {
			System.out.print("Select correctly. Range: 1-7.\nSelect: ");
			return false;
		}
		return true;
	}


	// heat의 경우 -2~0, cold의 경우 0~2 범위인지 체크
	public static boolean sensitivityChecker (int temperature) {
		if (temperature == -99) {
			System.out.print("Select correctly. Range: 1-3.\nSelect: ");
			return false;
		}
		return true;
	}
}
