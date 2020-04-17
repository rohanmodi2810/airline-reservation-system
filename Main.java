package airlineReservationSystem;
import java.util.*;
import java.io.*;
public class Main
{
	static Scanner in = new Scanner(System.in);
	static Login acc = new Login();
	static UserDetail a = new UserDetail();
	static Admin b = new Admin();
	public static void main(String args[]) throws Exception
	{
		System.out.println("WELCOME TO AIRLINE RESERVATION SYSTEM");
		System.out.println("\n\t1. ADMIN MODE\n\t2. USER MODE\n\t3. EXIT\n\tEnter your choice...\t");
		int choice;
		int flag = 1;
		do
		{
			choice = in.nextInt();
			switch(choice)
			{
			case 1:
				adminLogin();
				break;
			case 2:
				userLogin();
				break;
			case 3:
				return;
			default:
					System.out.println("Invalid input\n");
					flag = 0;
			}
		} while (flag == 0);
		
	}
	static void userLogin() throws Exception
	{
		
		System.out.println("\n\t1. SIGN IN\n\t2. SIGN UP\n\t3. RETURN TO MAIN MENU\n\tEnter your choice...");
		int choice;
		int flag = 1;
		do
		{
			choice = in.nextInt();
			switch(choice)
			{
			case 1:
			{
				boolean b = acc.getidSignIn();
				if (b == true)
				{
					user();
				}
				else
				{
					System.out.println("\nInvalid username or password!");
					System.out.println("\nDo you want to sign in again?\n\'y\' or \'n\'...");
					char c = in.next().charAt(0);
					switch(c)
					{
					case 'y':
					case 'Y':
						flag = 0;
						break;
					case 'n':
					case 'N':
						main(null);
						break;
					default:
						userLogin();
					}
				}
				break;
			}
			case 2:
			{
				acc.getidSignUp();
				user();
				break;
			}
			case 3:
				return;
			default:
					System.out.println("Invalid input\n");
					flag = 0;
			}
		} while (flag == 0);
	}
	static void adminLogin()
	{
		
	}
	static void user() throws IOException
	{
		do
		{
			System.out.println("USER MENU");
			System.out.println("\n\t1. RESERVE\n\t2. CANCEL\n\t3. ENQUIRY\n\t4. RETURN TO MAIN MENU\n\tEnter your choice...");
			int choice;
			choice = in.nextInt();
			switch(choice)
			{
			case 1:
				a.reserve();
				break;
			case 2:
				a.cancel();
				break;
			case 3:
				a.enquiry();
				break;
			case 4:
				return;
			default:
					System.out.println("Invalid input\n");
			}
		} while (true);
	}
	static void admin() throws Exception
	{
		
	}
}
