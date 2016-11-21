


import java.sql.*;


import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Driver {
   public static void showDriver() {
	   int number =0;
	   int tf=0;
	   int co=0;
	   int temp =0;
	   //Connection conn =null;
	         //createDatabase();
    //  useDatabase();
      //createCloset();      
      //createPurchase();
      //createDailyReport();
     // createStocktrigger();
     // createdefaulttrigger();
     String query = "insert into weather values ('20160606', '☔', 20, 17, 3, 60), "
      		+ "('20160607', '☔', 27, 18, 3, 60), ('20160608', '☀', 9, 4, 1, 10), ('20160609', '☁', 3, -5, 1, 5), ('20160610', '☀', 17, 11, 2, 30), ('20160611', '☔', 15, 8, 4, 70), "
      		+ "('20160612', '☁', 31, 28, 1, 20), ('20160613', '❄', 2, -7, 2, 10)";
      //insertw(query);
     Enterwant();
      
     //today_picks();
   
   }

   static String url = "jdbc:mysql://localhost:3306/dbr?autoReconnect=true&useSSL=false";
   static String id = "root";
   static String pass = "1234";
   public Connection dc;
   
   
   public static void insertw(String query){
	   Connection conn = null;
	   PreparedStatement stmt = null;
	  // ResultSet rs = null;
	   try {
	    	  
	         conn = DriverManager.getConnection(url, id, pass);
	         stmt = conn.prepareStatement(query);
	         
	         
	       stmt.executeUpdate();       
	     System.out.println("INsert done!");
    stmt.close();
    conn.close();
 } catch (SQLException e) {System.err.println("insert failure : " + e.toString()); }

	   
   
   }
  public static int GetbodyTemperature(String userid, String tem){
	  String query1 = "SELECT "+tem+" FROM memberTable WHERE ID ='"+userid+"'";
	  Connection conn1 = null;
	  PreparedStatement pstmt1  = null;
	  ResultSet rs1 = null;
	  int temper = 0;
	  try {
    	  
	         conn1 = DriverManager.getConnection(url, id, pass);
	         pstmt1 =conn1.prepareStatement(query1);
	        
	         
	         rs1 = pstmt1.executeQuery();
	         
	         if( rs1 == null ) System.out.println("We can't find that tem.");
	         
	         while (rs1.next()) {
	        	 temper =rs1.getInt(tem);
	         }
  }catch (SQLException e) { }
	  return temper;
  }
  public static void Enterwant(){
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
      int number =clothpick(style);
      

  }
   public static int clothpick(String style){
	   
   	   int bodyt =0;
	  int avertemperature = 0;
	 
	  int season = 0;
	  
	
	  String [] cname = new String[3];
	  String [] category = new String[3];
	 
	  int [] dateMaxt = new int[10];
	  int [] dateMint = new int[10];
	  
	  //season =2 ;
	  int j= 0;
	  int k =1;
	  int f = 0;
	  
	  int count =0;
	  int []flag = new int[3];
	  Connection conn1 = null;
	   PreparedStatement pstmt1 = null;
  	   ResultSet rs1=null;
  	 Connection conn2 = null;
	   PreparedStatement pstmt2 = null;
	   ResultSet rs2=null;
	   int max_temp = 0;
       int min_temp = 0;
	   String query1 = "SELECT * FROM weather WHERE Date >= DATE_SUB(CURRENT_DATE(), INTERVAL 1 DAY)";
	  
	  try {
    	  
	         conn1 = DriverManager.getConnection(url, id, pass);
	         pstmt1 =conn1.prepareStatement(query1);
	         conn2 = DriverManager.getConnection(url, id, pass);
	         
	         rs1 = pstmt1.executeQuery();
	         
	         if( rs1 == null ) System.out.println("We can't find that weather.");
	         
	         while (rs1.next()) {
	        	 max_temp = rs1.getInt("maxTemperature");
	        	 min_temp = rs1.getInt("maxTemperature");
	        	// dateMaxt[j++] = rs1.getInt("maxTemperature");
	         //  dateMint[f++] = rs1.getInt("minTemperature");//여기서 인트형 어레이를 할경우 일주일 치를 볼수 있을 듯?
	         }
	        System.out.println(dateMaxt[0] +"max_temp");
	         avertemperature = (dateMaxt[0] + dateMint[0])/2;
	         if(avertemperature<10) {
	        	 season = 3;
	        	// avertemperature= avertemperature + GetbodyTemperature( userid, tem);	 
	        	 }
	    	  else if(avertemperature>20){ 
	    		  season = 2;
	    		// avertemperature= avertemperature + GetbodyTemperature( userid, tem);	 ;	  
	    		  }
	       	  else season = 1;
	         
	         if(avertemperature<10) {
	        	 season = 3;
	        	// avertemperature= avertemperature + GetbodyTemperature( userid, tem);	 
	        	 }
	    	  else if(avertemperature>20){ 
	    		  season = 2;
	    		// avertemperature= avertemperature + GetbodyTemperature( userid, tem);	 ;	  
	    		  }
	       	  else season = 1;
	         System.out.println(season +"season");
	         String query = "select cname,category from Closet where season =" + season+" AND (category = 'top' OR category = 'onepiece') AND (style ='"+style+"')"
	        		  +" ORDER BY RAND() LIMIT 3" ;
	         pstmt2 = conn2.prepareStatement(query);
	         rs2 = pstmt2.executeQuery();
	         if( rs2 == null ) System.out.println("We can't find that season clothes.");
          j=0;
          f=0;
          while( rs2.next() )
          {
             cname[j++]= rs2.getString(1);
             category[f++]=rs2.getString(2);
  
          }
      rs1.close();
	  rs2.close();
	  pstmt1.close();
      pstmt2.close();
      conn1.close();
      conn2.close();
   } catch (SQLException e) { }
  
           for(int i =0;i <3; i ++){
        	   System.out.println(cname[i]);
           }
          System.out.println("");
	        
if(season !=1) showouter(style,season);
	 
	  for(int i =0;i <3; i ++){
		  if (category[i].equals("onepiece")){ 
			  flag[i] = 1;
			  count ++;}
		  else flag[i] =0 ;
		  
	  }
	 count = 3 - count;
	 System.out.println(count);
	 Clothpic2(count,season,style);
      return count;
 
}
   public static void showouter(String style,int season){
		  
		  String query = null;
		  Connection conn =null;
		  PreparedStatement pstmt =null;
		  ResultSet rs = null;
		  String [] cname3 = new String[3];
		  String [] category3 = new String[3];
		  int j =0;
		  int f =0;
			  System.out.println("dnd");
			   query = "select cname,category from Closet where season =" + season+" AND (category = 'Outer') AND (style ='"+style+"')"
					  +" ORDER BY RAND() LIMIT 3" ;
			   try{
			    conn = DriverManager.getConnection(url, id, pass);
		          pstmt = conn.prepareStatement(query);
		         
					         
					         rs = pstmt.executeQuery();
					        
					         if( rs == null ) System.out.println("We can't find that season clothes.");
				          
				          while( rs.next() )
				          {
				             cname3[j++]= rs.getString(1);
				             category3[f++]=rs.getString(2);
				  
				          }
				           for(int i =0;i <3; i ++){
				        	   System.out.println(cname3[i]);
				           }
		  rs.close();
	      pstmt.close();
	      conn.close();
		  }catch (SQLException e) { }
		  
				           
   }
 public static void Clothpic2(int count, int season,String style){
	 String query =null;
	 Connection conn = null;
	   PreparedStatement pstmt = null;
 	   ResultSet rs=null;
 	 
 	  int j =0;
 	  String [] cname2 = new String[3];
	  String [] category2 = new String[3];
	    query = "select cname from Closet where season = " + season + " AND category = 'Bottom'"+"AND style = '" + style+"'"+
	    		 "ORDER BY RAND() LIMIT "+count ;
	    	    try {
	    	  	  
	    	        conn = DriverManager.getConnection(url, id, pass);
	    	        pstmt = conn.prepareStatement(query);
	    	        
	    	      
	    	        
	    	      rs = pstmt.executeQuery();
	    	       
	    	        if( rs == null ) System.out.println("We can't find that season clothes.");
	    	     
	    	     while( rs.next() )
	    	     {
	    	        cname2[j++]= rs.getString(1);
	    	        //category2[f++]=rs.getString(2);

	    	     }
	    	      for(int i =0;i <3; i ++){
	    	   	   System.out.println(cname2[i]);
	    	      }
	    	     System.out.println("");
	    	        rs.close();
	    	        pstmt.close();
	    	        conn.close();
	    	     } catch (SQLException e) { }
 } 

   public static void Search(String keyword){
	   Connection conn = null;
	  PreparedStatement pstmt = null;
	   String query = "select * from Stock where  title like '%" + keyword + "%' "+
	  "or author like '%"+ keyword +"%'";
	   ResultSet rs = null;
	      try {
	    	  
	         conn = DriverManager.getConnection(url, id, pass);
	         pstmt = conn.prepareStatement(query);
	         
	      
	        
	         rs = pstmt.executeQuery();
	        
	         if( rs == null ) System.out.println("We can't find the book.");
             
             while( rs.next() )
             {
                String title = rs.getString("title");
                 String author = rs.getString("author");
                 String category = rs.getString("category");
                 int ISBN = rs.getInt("ISBN");
                 int copyN = rs.getInt("copyN");
                 System.out.printf("title \tauthor \t category ISBN copyN\n");
                 System.out.printf("%s %s \t%s %d %d",title, author, category, ISBN, copyN);
                 System.out.println("");
             }
              
             System.out.println("");
	         rs.close();
	         pstmt.close();
	         conn.close();
	      } catch (SQLException e) { }
//	      return loadvc;
   }
   
   public static int isNumeric(String s) {
	   try {
	       Double.parseDouble(s);
	       return 1;
	   } catch(NumberFormatException e) {
	       return 0;
	   }
	 }
   
   
   
   public static void useDatabase () {
      try {
         Class.forName("com.mysql.jdbc.Driver");
      } catch (ClassNotFoundException e) { return; }
      Connection conn = null;
      Statement stmt = null;
      
      try {
         conn = DriverManager.getConnection(url, id, pass);
         stmt = conn.createStatement();
         stmt.executeUpdate("use kiwon;");
         System.out.println("use db!");
         stmt.close();
         conn.close();
      } catch(SQLException e) {
         System.err.println("use db failure  : " + e.toString());
         return;
      }
   }
   
   
   
   public static void createCloset () {
     try {
         Class.forName("com.mysql.jdbc.Driver");
      } catch (ClassNotFoundException e) { return; }
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
      }
 
   
         }   
  
   
 
}
    
  


