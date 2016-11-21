import java.sql.*;
import java.util.Scanner;

public class todayPicks {
  
   
   public static void ShowPicks() {
    //  createCloset();
      today_picks("aa");
   }
   
   static String url = "jdbc:mysql://localhost:3306/dbr";
   static String id = "root";
   static String pw = "1234";
   
   public static void today_picks(String userid) {
      try {
         Class.forName("com.mysql.jdbc.Driver");
      } catch (ClassNotFoundException e) { }
      
      Scanner keyboard = new Scanner(System.in);
      System.out.println("Select your style preference");
      System.out.print("1. Sexy Glamourous\n2. Feminine\n3. Lovely\n4. Simple\n5. Modern chic\n6. Formal\n7. Sporty\n");
      int user_pref = keyboard.nextInt();
      String style = "Simple";
      int season = 1;
      switch (user_pref) {
      case 1:
         style = "Sexy Glamourous";
         break;
      case 2:
         style = "Feminine";
         break;
      case 3:
         style = "Lovely";
         break;
      case 4:
         style = "Simple";
         break;
      case 5:
         style = "Modern chic";
         break;
      case 6:
         style = "Formal";
         break;
      case 7:
         style = "Sporty";
         break;
      }
      
      Connection conn = null;
      PreparedStatement ps = null;
      PreparedStatement ps2 = null;
      PreparedStatement ps3 = null;
      PreparedStatement ps4 = null;
      ResultSet rs = null;
      ResultSet rs2 = null;
      ResultSet rs3 = null;
      ResultSet rs4 = null;
      String query1 = "SELECT * FROM weather WHERE Date = DATE_SUB(CURRENT_DATE(), INTERVAL 0 DAY)";
      
      try {
         conn = DriverManager.getConnection(url, id, pw);
         ps = conn.prepareStatement(query1);
         rs = ps.executeQuery();
         
         if (rs == null) {
            System.out.println("Error! No database exist for that date");
         }
         
         int max_temp = 0;
         int min_temp = 0;
         while (rs.next()) {
            max_temp = rs.getInt(3);
            min_temp = rs.getInt(4);
         }
         int avrg_temp = (max_temp + min_temp)/2;
         if(avrg_temp>15){
      
         avrg_temp = avrg_temp + GetbodyTemperature(userid, "heatSensitivity");         
         }
         else if(avrg_temp<15){
        	 avrg_temp = avrg_temp + GetbodyTemperature(userid, "coldSensitivity");
         }
         
         if (avrg_temp>=10 && avrg_temp<=20)
            season = 1; 
         else if (avrg_temp>=20){
            season = 2;
         }
         else {
            season = 3;
         }
         
         String query2 = "SELECT * FROM closet WHERE style= '" + style + "' AND season= '" + season + "' AND (category='Top' OR category='One piece') ORDER BY RAND() LIMIT 3";
         String query3 = "SELECT * FROM closet WHERE style= '" + style + "' AND season= '" + season + "' AND category='Bottom' ORDER BY RAND() LIMIT 1";
         String query4 = "SELECT * FROM closet WHERE style= '" + style + "' AND season= '" + season + "' AND category='Outer' ORDER BY RAND() LIMIT 1";
         ps2 = conn.prepareStatement(query2);
         rs2 = ps2.executeQuery();
         if (rs2 == null) {
            System.out.println("Error! No database exist for that style preference");
         }
         String[] category = new String[10];
         String[] todays_picks = new String[10];
         int i = 0;
         while (rs2.next()) {
            todays_picks[i] = rs2.getString(3);
            category[i] = rs2.getString(6);
            if (category[i].equalsIgnoreCase("top")) {
               ps3 = conn.prepareStatement(query3);
               rs3 = ps3.executeQuery();
               if (rs3 == null) {
                  System.out.println("Error! No bottom exist for that season and preference");
               }
               else {
                  while (rs3.next()) {
                     i++;
                     todays_picks[i] = rs3.getString(3);
                     category[i] = rs3.getString(6);
                  }
               }
            }
            i++;
         }
         	
         int print_counter = 1; int j = 0;
         System.out.println("Today's Picks");
         System.out.println(style);
         while (print_counter <= 3) {
            if (category[j].equalsIgnoreCase("top")) {
               System.out.println("Choice " + print_counter);
               System.out.println(todays_picks[j] + "\t" + category[j]);
               j++;
               System.out.println(todays_picks[j] + "\t" + category[j]);
               System.out.println();
               j++;
               print_counter++;
            }
            
            else {
               System.out.println("Choice " + print_counter);
               System.out.println(todays_picks[j] + "\t" + category[j]);
               System.out.println();
               j++;
               print_counter++;
            }
         }
         
         if (season == 1 || season == 3) {
            ps4 = conn.prepareStatement(query4);
            rs4 = ps4.executeQuery();
            if (rs4 == null) {
               System.out.println("Error! No database exist for that style preference");
            }
            
            while (rs4.next()) {
               System.out.println("Outer");
               System.out.println(rs4.getString(3));
            }
            rs4.close();
            ps4.close();
         }
         rs3.close();
         rs2.close();
         rs.close();
         ps3.close();
         ps2.close();
         ps.close();
         conn.close();
      }
      
      catch (SQLException e){
         System.err.println("Failed to execute! : " + e.toString());
         return;
      }
   }
   public static int GetbodyTemperature(String userid, String tem){
		  String query1 = "SELECT "+tem+" FROM memberTable WHERE ID ='"+userid+"'";
		  Connection conn1 = null;
		  PreparedStatement pstmt1  = null;
		  ResultSet rs1 = null;
		  int temper = 0;
		  try {
	    	  
		         conn1 = DriverManager.getConnection(url, id, pw);
		         pstmt1 =conn1.prepareStatement(query1);
		        
		         
		         rs1 = pstmt1.executeQuery();
		         
		         if( rs1 == null ) System.out.println("We can't find that tem.");
		         
		         while (rs1.next()) {
		        	 temper =rs1.getInt(tem);
		         }
	  }catch (SQLException e) { }
		  return temper;
	  }
   public static void createCloset () {
         /*   try {
               Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) { return; }*/
      Connection conn = null;
      Statement stmt = null;
            
      try {
         conn = DriverManager.getConnection(url, id, pw);
          stmt = conn.createStatement();
          String query = "CREATE TABLE Closet " +
                      "(USERID CHAR(20) , " +
                         "CLOTHED INT(10) PRIMARY KEY,"+
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