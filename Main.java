import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		while (true) {
			System.out.println("\n■■■■■■■■■■■■■ StyleCast ■■■■■■■■■■■■■");
			System.out.println("			    ver. 1.0 ");
			System.out.println("	     Select Menu\n");
			System.out.println("	     1. Log  in");
			System.out.println("	     2. Sign in");
			System.out.println("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n");
			System.out.print("  Select: ");
			Scanner sc = new Scanner(System.in);
			int select = sc.nextInt();

			if (select == 1){
				if (Login.isLogin() != -1) break;
			}
			else if (select == 2){
					if (Register.isRegister() != -1) break;
			}
		}
	}
}

