import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Pattern;

public class Register {
//
//	public static void main(String[] args) {
//		Register rg = new Register();
//	}
	
	public Register() {}

	public static int isRegister() {
		System.out.println("───────────────────────────────────────");
		System.out.println("         ### Register Menu ###");
		System.out.println("      ### Fill out followings ###");
		Scanner sc = new Scanner(System.in);

		System.out.println("───────────────────────────────────────");
		System.out.println("		    Type -1 to go back.\n");
		String id = null;
		do {
			
			System.out.print("## ID: ");
			id = sc.next();	
			if (!terminateChecker(id)) return -1;
		} while (MemberTable.registerIDChecker(id) == false); // id 중복 체크 

		System.out.println("───────────────────────────────────────");
		System.out.print("## Password: ");
		String password = sc.next();
		if (!terminateChecker(password)) return -1;

		System.out.println("───────────────────────────────────────");
		System.out.print("## Name: ");
		String name = sc.next();
		if (!terminateChecker(name)) return -1;

		System.out.println("───────────────────────────────────────");
		System.out.println("## Select your favorite style preference");
		System.out.println("1. Sexy Glamorous\n2. Feminine\n"
				+ "3. Lovely\n4. Simple\n5. Modern Chic\n"
				+ "6. Formal\n7. Sporty");
		System.out.print("Select: ");		
		int sp = -99;
		 do {
			 sp = sc.nextInt();
			 if(sp == -1) return -1;
			 if(sp < 1 || sp > 7) sp = -99;
		 } while (MemberTable.stylePreferenceChecker(sp) == false);		
		String stylePreference = null;
		switch (sp) {
		case 1:
			stylePreference = "Sexy Glamourous";
			break;
		case 2:
			stylePreference = "Feminine";
			break;
		case 3:
			stylePreference = "Lovely";
			break;
		case 4:
			stylePreference = "Simple";
			break;
		case 5:
			stylePreference = "Modern chic";
			break;
		case 6:
			stylePreference = "Formal";
			break;
		case 7:
			stylePreference = "Sporty";
			break;
		}

		System.out.println("───────────────────────────────────────");
		System.out.println("## Select your sensitivity to temperature");
		System.out.println("# Sensitivity of Heat");
		System.out.println("1. Very sensitive to heat\n"
				+ "2. Sensitive to heat\n"
				+ "3. Normal");
		System.out.print("Select: ");
		int hs = -99;
		int heatSensitivity = -99;
		do {
			hs = sc.nextInt();
			if(hs == -1) return -1;
			switch (hs) {
			case 1: heatSensitivity = 2;
			break;
			case 2: heatSensitivity = 1;
			break;
			case 3: heatSensitivity = 0;
			break;
			default: heatSensitivity = -99;	
			} 
		} while (MemberTable.sensitivityChecker(heatSensitivity) == false);

		//		System.out.println("───────────────────────────────────────");
		System.out.println("\n# Sensitivity of Cold");
		System.out.println("1. Very sensitive to cold\n"
				+ "2. Sensitive to cold\n"
				+ "3. Normal");
		System.out.print("Select: ");
		int cs = -99;
		int coldSensitivity = -99;

		do {
			cs = sc.nextInt();
			if (cs == -1) return -1;
			switch (cs) {
			case 1: coldSensitivity = -2;
			break;
			case 2: coldSensitivity = -1;
			break;
			case 3: coldSensitivity = 0;
			break;
			default: coldSensitivity = -99;
			}
		} while (MemberTable.sensitivityChecker(coldSensitivity) == false);

		System.out.println("───────────────────────────────────────");
		System.out.println("### Complete Registeration! ###\n");
		System.out.println("Go back to the first page.\n");
		System.out.println("───────────────────────────────────────");
		
		Member m = new Member(id, password, name, stylePreference, heatSensitivity, coldSensitivity);
		Vector regvc = new Vector();
		regvc.add(m);
		MemberTable.registerMember(regvc);
		
		return -1;
	}
	
	public static boolean terminateChecker(String str) {
		if (Pattern.matches("^(-?)[0-9]+$", str) && Integer.parseInt(str) == -1 ) {
			return false;
		}
		return true;
	}
}
