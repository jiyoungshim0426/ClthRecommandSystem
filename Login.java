import java.util.Scanner;
import java.util.regex.Pattern;

public class Login {
	
//	public static void main(String[] args) {
//		Login lg = new Login();
//	}
	
	public Login() {}
	
	public static int isLogin() {
		String id = "";
		String password = "";
		
		System.out.println("\n───────────────────────────────────────");
		System.out.println("		    Type -1 to go back.\n");
		System.out.println("	    ### Log in ###\n");
		Scanner sc = new Scanner(System.in);
		System.out.print("    I      D : ");
		id = sc.next();
		if ( !Register.terminateChecker(id) ) return -1;		
		System.out.print("    PASSWORD : ");
		password = sc.next();
		if ( !Register.terminateChecker(password) ) return -1;		
		
		
		if(MemberTable.loginChecker(id, password) == true) {
			// Login success
			if (Menu.showMenu(id) == -1) return -1;
		}
		else {
			// Login fail
			return -1;
		}
		return 0;
	}
	

}
