package airlineReservationSystem;
import java.util.*;
import java.io.*;
class Login
{
	static Scanner in = new Scanner(System.in);
	private String id;
	private String pwd;
	public boolean getidSignIn()
	{
		System.out.println("Enter your id:\t");
		id = in.next();
		System.out.println("Enter your password:\t");
		pwd = in.next();
		return checkid();
	}
	private boolean checkid()
	{
		return true;
	}
	public void getidSignUp()
	{
		System.out.println("Enter your new id:\t");
		id = in.next();
		System.out.println("Enter your new password:\t");
		pwd = in.next();
		if (checkid())
		{
			System.out.println("Id already exists!\nPlease enter new id");
			this.getidSignUp();
		}
		else
		{
			System.out.println("Id created successfully!");
		}
	}
}

