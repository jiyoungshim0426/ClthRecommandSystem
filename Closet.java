import java.sql.*;
import java.util.Scanner;

//jdbc:mysql://127.0.0.1:3306/teamm?autoReconnect=true&useSSL=false


public class Closet {
	static String driverName = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/dbr";
	static String id = "root";
	static String pass = "1234";
	public Connection dc;
	//ublic static String userID = "teamM";

	public static void closet(String userID){
		//connectDatabase();
		System.out.println("Select menu");
		System.out.println("1. register clothes");
		System.out.println("2. show clothes");
		System.out.println("3. delete clothes");
		System.out.println("4. back");
		Scanner sc = new Scanner(System.in);
		int menu = sc.nextInt();
		if(menu==1){registerClothes(userID);}
		else if(menu==2){showClothes(userID);}
		else if(menu==3){deleteClothes(userID);}
		else {return;}
		//System.out.println("aaaaaaaaa!");
	}


	public static void deleteClothes(String userID) {
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		String query = "select * from Closet";
		try{
			conn = DriverManager.getConnection(url, id, pass);
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery(query);
			while(rs.next()){
				System.out.print("clothID : "+rs.getInt(2));
				System.out.println("\t Cloth explanation : "+rs.getString("cname"));   
			}
		}catch(SQLException e) {
			System.err.println("here : " + e.toString());
			return ;
		}

		System.out.println("cloth ID you want to delete : ");
		int delID = sc.nextInt();
		try{
			query = "delete from closet where clothid ="+delID;
			stmt.executeUpdate(query);
			System.out.println("delete done");
			stmt.close();
			conn.close();
		}catch(SQLException e){
			System.err.println("delete error" + e.toString());
			return;
		}

	}

	private static void showClothes(String userID) {
		Scanner sc = new Scanner(System.in);
		System.out.println("==Show Closet==");
		System.out.println("season : ");
		System.out.println("1. Spring&Fall");
		System.out.println("2. Summer");
		System.out.println("3. Winter");
		System.out.println("4. N/A");
		int season = sc.nextInt();

		System.out.println("style : ");
		System.out.println("1. Sexy Glamorous");
		System.out.println("2. Feminine");
		System.out.println("3. Lovely");
		System.out.println("4. Simple");
		System.out.println("5. Modern Chic");
		System.out.println("6. Formal");
		System.out.println("7. Sporty");
		System.out.println("8. N/A");
		int styleint = sc.nextInt();
		String style="";
		switch(styleint){
		case 1 : style = "Sexy Glamorous";break;
		case 2 : style = "Feminine";break;
		case 3 : style = "Lovely";break;
		case 4 : style = "Simple";break;
		case 5 : style = "Modern Chic";break;
		case 6 : style = "Formal";break;
		case 7 : style = "Sporty";break;
		case 8 : style = "*";break;
		default : System.out.println("wrong input");sc.close();return;
		}

		String category;
		System.out.println("Category : ");
		System.out.println("1. Top");
		System.out.println("2. Bottom");
		System.out.println("3. One piece");
		System.out.println("4. Outer");
		System.out.println("5 N/A");
		int categoryint = sc.nextInt();
		switch(categoryint){
		case 1 : category = "Top";break;
		case 2 : category = "Bottom";break;
		case 3 : category = "One piece"; break;
		case 4 : category = "Outer";break;
		case 5 : category = "*";break;
		default : System.out.println("wrong input");sc.close();return;
		}

		searchcloset(userID, season, style, category);


	}


	public static void searchcloset(String userID, int season, String style, String category) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		String query = "select * from Closet";
		String season_string = "";
		switch (season) {
		case 1:
			season_string = "Sprint/Fall";
			break;
		case 2:
			season_string = "Summer";
			break;
		case 3:
			season_string = "Winter";
			break;
		}

		try {
			if(season!=4 || style != "*" || category!="*")
				query = query+" where ";

			conn = DriverManager.getConnection(url, id, pass);

			if(season!=4)
				query = query+"season like '%"+season+"%'";

			if(style!="*")
				query = query+"and style like '%" + style + "%'";

			if(category != "*")
				query = query + "and category like '%"+category+"%'";

			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery(query);
			System.out.println(style + "\t" + category + "\t" + season_string);
			while(rs.next()){
				System.out.print("clothID : "+rs.getInt(2));
				System.out.println("\t" + rs.getString("cname"));   

			}
		}catch(SQLException e) {
			System.err.println("here : " + e.toString());
			return ;
		}
	}

	public static void registerClothes(String userID){
		Scanner sc = new Scanner(System.in);

		System.out.println("==Register Clothes==");
		System.out.println("name of clothes : ");
		String cname = sc.next();

		System.out.println("season : ");
		System.out.println("1. Spring&Fall");
		System.out.println("2. Summer");
		System.out.println("3. Winter");
		int season = sc.nextInt();

		System.out.println("style : ");
		System.out.println("1. Sexy Glamorous");
		System.out.println("2. Feminine");
		System.out.println("3. Lovely");
		System.out.println("4. Simple");
		System.out.println("5. Modern Chic");
		System.out.println("6. Formal");
		System.out.println("7. Sporty");
		int styleint = sc.nextInt();
		String style="";
		switch(styleint){
		case 1 : style = "Sexy Glamorous";break;
		case 2 : style = "Feminine";break;
		case 3 : style = "Lovely";break;
		case 4 : style = "Simple";break;
		case 5 : style = "Modern Chic";break;
		case 6 : style = "Formal";break;
		case 7 : style = "Sporty";break;
		default : System.out.println("wrong input");
		}

		String category = "";
		System.out.println("Category : ");
		System.out.println("1. Top");
		System.out.println("2. Bottom");
		System.out.println("3. One piece");
		System.out.println("4. Outer");
		int categoryint = sc.nextInt();
		switch(categoryint){
		case 1 : category = "Top";break;
		case 2 : category = "Bottom";break;
		case 3 : category = "One piece"; break;
		case 4 : category = "Outer";break;
		default : System.out.println("wrong input");
		}

		inputclothes(userID, cname,season,style,category);
	}

	public static void inputclothes(String userID, String cname, int season, String style, String category){
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(url, id, pass);
			String query = "insert into Closet " +
					"values(?,?,?,?,?,?)";
			int clothesID = getclothesID(userID);
			if(clothesID ==0){
				System.out.println("error in getting clothes ID");
			}
			stmt = conn.prepareStatement(query);

			stmt.setString(1,userID);
			stmt.setInt(2,clothesID);
			stmt.setString(3,cname);
			stmt.setInt(4,season);
			stmt.setString(5,style);
			stmt.setString(6,category);
			stmt.executeUpdate();
			System.out.println("input clothes done!");
			stmt.close();
			conn.close();
		} catch(SQLException e) {
			System.err.println("input item failure  : " + e.toString());
			return;
		}   
	}

	public static int getclothesID(String userID) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		int clothesID = 700;
		String query = "select * from Closet where CLOTHID=";
		try {
			conn = DriverManager.getConnection(url, id, pass);
			do{
				clothesID++;
				query = query+clothesID;
				stmt = conn.prepareStatement(query);
				rs = stmt.executeQuery(query);
				System.out.println(clothesID);
			}while(rs.next());
		}catch(SQLException e) {
			System.err.println("here : " + e.toString());
			return 0;
		}
		return clothesID;
	}


	public static void connectDatabase () {
		try {
			Class.forName(driverName);  //load driver
		} catch (ClassNotFoundException e) { 
			System.err.println("connect db 실패! : "+e.toString());
		}
		Statement stmt = null;
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, "root", "1234");
			stmt = conn.createStatement();
			stmt.executeUpdate("use teamM;");
			System.out.println("use db!");
			stmt.close();
			conn.close();
		} catch(SQLException e) {
			System.err.println("use db 실패! : " + e.toString());
			return;
		}
	}               


	public static void createCloset () {

		/*   try {
               Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) { return; }*/
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DriverManager.getConnection(url, id, pass);
			stmt = conn.createStatement();
			String query = "CREATE TABLE Closet " +
					"(USERID CHAR(20) , " +
					"CLOTHID INT(10) PRIMARY KEY,"+
					"cname CHAR(20) ,"+
					"season int(2)," +
					"style CHAR(30),"+
					"category CHAR(30))";
			stmt.executeUpdate(query);
			System.out.println("Create table done!");
			stmt.close();
			conn.close();
		} catch(SQLException e) {
			System.err.println("create table failure  : " + e.toString());
			return;
		}
	}
}