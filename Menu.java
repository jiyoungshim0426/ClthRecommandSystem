import java.util.Scanner;

public class Menu {
	//	public static void main(String[] args) {
	//		showMenu();
	//	}
	public static int showMenu(String id) {
		char loop = 'Y';
		do {
			System.out.println("\n■□■□■□ StyleCast ■□■□■□■□■□■□■□■□■□■□■□");
			System.out.print("			    ★  User: ");
			System.out.println(MemberTable.searchNameUsingId(id));
			System.out.println("		     Type -1 to logout.");
			System.out.println("────────────────────────────────────────\n");
			System.out.println("	     Select Menu\n");
			System.out.println("	    1. Weather");
			System.out.println("	    2. Closet");
			System.out.println("	    3. Today's Pick");
			System.out.println("\n■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□■□\n");
			System.out.print("  Select: ");
			Scanner sc = new Scanner(System.in);
			int select = sc.nextInt();
			if (select == -1) 
				return -1;

			if (select == 1) {
				weather.showWeather();
				System.out.println("Would you like to go back to main menu? Y or N");
				loop = sc.next().charAt(0);
			}
			else if (select == 2) {
				Closet.closet(id);
				System.out.println("Would you like to go back to main menu? Y or N");
				loop = sc.next().charAt(0);
			}
			else if (select == 3) {
				//Driver.showDriver();
				todayPicks.ShowPicks();
				System.out.println("Would you like to go back to main menu? Y or N");
				loop = sc.next().charAt(0);
			}	
			
		} while (loop == 'Y' || loop == 'y');
		System.out.println("Good-bye!");
		return 0;
	}

}
