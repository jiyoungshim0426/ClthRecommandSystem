

import java.sql.*;
import java.util.Scanner;



public class weather {
   public static void showWeather() {
      
	  System.out.println();
      System.out.println(" ㆍ  Show weather ㆍ");
      System.out.println();
   
         weather();
      

   }
   static String url = "jdbc:mysql://localhost:3306/dbr";
   static String id = "root";
   static String pass = "1234";
   public Connection dc;

   
   public static void weather() {
	   
      try {
         Class.forName("com.mysql.jdbc.Driver");
      } catch (ClassNotFoundException e) { return; }
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      java.sql.Date date=null;
      
      try {
         conn = DriverManager.getConnection(url, id, pass);
         
         pstmt = conn.prepareStatement("select Curdate() from dual");
         rs = pstmt.executeQuery();

         rs.next();
         date = rs.getDate(1);
      
         pstmt = conn.prepareStatement("select * from weather where date = ?" );
         pstmt.setDate(1, date);
         rs = pstmt.executeQuery();
   
         while(rs.next()) {
        	 
             
             System.out.println("──────────────────────────────────────────────────────────────────────");
             System.out.println("     date   \t\t Max/Min \t wind \t \train");
             System.out.println("──────────────────────────────────────────────────────────────────────");
             System.out.println();
             System.out.println("     【  today 】");
             
             System.out.println("  "+rs.getDate(1)+"\t" + rs.getString(2)
             + " \t " + rs.getInt(3) + "  / " + rs.getInt(4)
             + " ℃ \t " + rs.getInt(5) + " m/s \t \t " + rs.getInt(6) + "%");
          }
         // 오늘 날씨
         
         System.out.println();
         System.out.println();

         System.out.println("──────────────────────────────────────────────────────────────────────");
         for (int i=1 ; i<=7; i++){
         
         pstmt = conn.prepareStatement("select adddate(?,?)" );
         pstmt.setDate(1, date);
         pstmt.setInt(2, 1);
         rs = pstmt.executeQuery();
         
         rs.next();
         date = rs.getDate(1);
         
         pstmt = conn.prepareStatement("select * from weather where date = ?" );
         pstmt.setDate(1, date);
         rs = pstmt.executeQuery();
   
         
         while(rs.next()) {
             System.out.println("  " + rs.getDate(1) + "\t" + rs.getString(2)
             + "\t " + rs.getInt(3) + " / " + rs.getInt(4)
             + " ℃  \t " + rs.getInt(5) + " m/s \t \t " + rs.getInt(6) + "%");
          }
         }
         
         System.out.println("──────────────────────────────────────────────────────────────────────");
         //일주일 치 날씨
         System.out.println("  >> Back: ");
         pstmt.close();
         conn.close();
         
      } catch(SQLException e) {
         System.err.println("show weather fail! : " + e.toString());
         return;
      }
      
      
   }
   
}
  