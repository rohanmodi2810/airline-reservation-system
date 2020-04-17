package airlineReservationSystem;
import java.util.*;
import java.io.*;
public class UserDetail 
{
	static Scanner in = new Scanner(System.in);
	private int pnr;
	private String flightID;
	private String source;
	private String destination;
	private int d, m ,y;
	private String Atime;
	private String Dtime;
	private int nofcs;
	private int noscs;
	private double amount;
	static long num = 111111;
	public void getUserDetails()
	{
		System.out.print("Enter the source:\t");
		source = in.next();
		System.out.print("Enter the destination:\t");
		destination = in.next();
		System.out.println("Enter date (dd/mm/yyyy):");
		System.out.print("Enter day:\t");
		d = in.nextInt();
		System.out.print("Enter month:\t");
		m = in.nextInt();
		System.out.print("Enter year:\t");
		y = in.nextInt();
	}
	public void reserve() throws IOException
	{
		boolean flag;
		Random num = new Random();
		getUserDetails();
		FileReader fr = new FileReader("D:\\java programs\\airlineReservationSystem\\src\\airlineReservationSystem\\flight.txt");
		BufferedReader bfr = new BufferedReader(fr);
		String i = new String("");
		int k = 0;
		String arr[] = new String[10];
		flag = false;
		while ((i = bfr.readLine()) != null)
		{
			String[] j = i.split(" ");
//			System.out.println(i);
//			System.out.println(j[0] + "  " + j[1] + "  " + j[2]);
			String temp = new String("");
			temp += d+"/"+m+"/"+y;
			if (j[1].equalsIgnoreCase(source) && j[2].equalsIgnoreCase(destination) && j[3].equalsIgnoreCase(temp))
			{
				flag = true;
				arr[k] = new String();
				arr[k] = i;
				k++;
			}
		}
		bfr.close();
		fr.close();
		if (flag == false)
		{
			System.out.println("No such flights available!");
			return;
		}
		for (int l=0;l<k;l++)
			System.out.println((l+1) + "   " + arr[l]);
		System.out.println("Enter the required flight");
		int non = in.nextInt();
		String[] s = arr[non-1].split("\\s");
		this.flightID = s[0];
		this.Atime = s[4];
		this.Dtime = s[5];
		int nf = Integer.parseInt(s[6]);
		double fcf = Double.parseDouble(s[7]);
		flag = true;
		do
		{
			System.out.print("Enter the no. of first class seats required:\t");
			this.nofcs = in.nextInt();
			if (nofcs <= nf)
			{
				flag = true;
				this.amount += nofcs*fcf; 
			}
			else
			{
				flag = false;
				System.out.println("Sorry, requested number of seats are not available!");
				return;
			}
		} while (flag == false);
		int ns = Integer.parseInt(s[8]);
		double scf = Double.parseDouble(s[9]);
		flag = true;
		do
		{
			System.out.print("Enter the no. of second class seats required:\t");
			this.noscs = in.nextInt();
			if (noscs <= ns)
			{
				flag = true;
				this.amount += noscs*scf; 
			}
			else
			{
				flag = false;
				System.out.println("Sorry, requested number of seats are not available!");
				return;
			}
		} while (flag == false);
		this.pnr = num.nextInt(9999) + 1111;
		
		String output = new String("");
		FileReader fr1 = new FileReader("D:\\java programs\\airlineReservationSystem\\src\\airlineReservationSystem\\flight.txt");
		BufferedReader bfr1 = new BufferedReader(fr1);
		String l;
		while ((l = bfr1.readLine()) != null)
		{
			flag = false;
			String[] s1 = l.split("\\s");
			if (s1[0].equals(this.flightID))
			{
				flag = true;
				String sf = String.valueOf(nf-this.nofcs);
				//System.out.println("sf = " + sf);
				s1[6] = sf;
				String ss = String.valueOf(ns-this.noscs);
				//System.out.println("ss = " + ss);
				s1[8] = ss;
				l = "";
				l += s1[0]+" "+s1[1]+" "+s1[2]+" "+s1[3]+" "+s1[4]+" "+s1[5]+" "+s1[6]+" "+s1[7]+" "+s1[8]+" "+s1[9]+"\n";
			}
			if (flag == false)
				l = l+"\n";
			output = output + l;
		}
		//System.out.println("output = " + output);
		FileWriter fw1 = new FileWriter("D:\\java programs\\airlineReservationSystem\\src\\airlineReservationSystem\\flight.txt");
		BufferedWriter bfw1 = new BufferedWriter(fw1);
		bfw1.write(output);
		bfw1.close();
		fw1.close();
		bfr1.close();
		fr1.close();
		System.out.println("The details of your flight:");
		System.out.println("PNR number:\t"+pnr);
		System.out.println("Flight ID:\t"+flightID);
		System.out.println("Source:\t"+source);
		System.out.println("Destination:\t"+destination);
		System.out.println("date:\t"+d+"/"+m+"/"+y);
		System.out.println("Arrival time:\t"+Atime);
		System.out.println("Departure time:\t"+Dtime);
		System.out.println("No. of first class Seats:\t"+nofcs);
		System.out.println("No. of second class Seats:\t"+noscs);
		System.out.println("Total amount:\t"+amount);
		System.out.println("Enter \'y\' to confirm else \'n\':");
		char ans = in.next().charAt(0);
		if (ans == 'y' || ans == 'Y')
		{
			FileWriter fw = new FileWriter("D:\\java programs\\airlineReservationSystem\\src\\airlineReservationSystem\\passenger.txt",true);
			BufferedWriter bfw = new BufferedWriter(fw);
			bfw.write(this.pnr+" "+this.flightID+" "+this.source+" "+this.destination+" "+this.d+"/"+this.m+"/"+this.y+" "+this.Atime+" "+this.Dtime+" "+this.nofcs+" "+this.noscs+" "+this.amount+"\n");
			bfw.close();
			fw.close();
			System.out.println("Flight reserved successfully!");
		}
		else
		{
			return;
		}
	}
	public void cancel() throws NumberFormatException, IOException
	{
		boolean flag = false;
		System.out.print("Enter the pnr you want to cancel:\t");
		int p = in.nextInt();
		FileReader fr = new FileReader("D:\\java programs\\airlineReservationSystem\\src\\airlineReservationSystem\\passenger.txt");
		BufferedReader bfr = new BufferedReader(fr);
		String i;
		int nf=0, ns=0;
		String output = new String("");
		while ((i = bfr.readLine()) != null)
		{
			String[] s = i.split(" ");
			int q = Integer.parseInt(s[0]);
			if (p == q)
			{
				flag = true;
				nf = Integer.parseInt(s[7]);
				ns = Integer.parseInt(s[8]);
			}
			else
			{
				output += i;
				output += "\n";
			}
		}
		bfr.close();
		fr.close();
		if (flag == false)
		{
			System.out.println("PNR does not exist!");
			return;
		}
		else
		{
			FileWriter fw = new FileWriter("D:\\java programs\\airlineReservationSystem\\src\\airlineReservationSystem\\passenger.txt");
			BufferedWriter bfw = new BufferedWriter(fw);
			bfw.write(output);
			bfw.close();
			fw.close();
			String output1 = new String("");
			FileReader fr1 = new FileReader("D:\\java programs\\airlineReservationSystem\\src\\airlineReservationSystem\\flight.txt");
			BufferedReader bfr1 = new BufferedReader(fr1);
			String l;
			while ((l = bfr1.readLine()) != null)
			{
				flag = false;
				String[] s1 = l.split("\\s");
				if (s1[0].equals(this.flightID))
				{
					flag = true;
					int nf1 = Integer.parseInt(s1[6]);
					String sf = String.valueOf(nf+nf1);
					s1[6] = sf;
					int ns1 = Integer.parseInt(s1[8]);
					String ss = String.valueOf(ns+ns1);
					s1[8] = ss;
					l = "";
					l += s1[0]+" "+s1[1]+" "+s1[2]+" "+s1[3]+" "+s1[4]+" "+s1[5]+" "+s1[6]+" "+s1[7]+" "+s1[8]+" "+s1[9]+"\n";
				}
				if (flag == false)
					l = l+"\n";
				output1 = output1 + l;
			}
			bfr1.close();
			fr1.close();
			FileWriter fw1 = new FileWriter("D:\\java programs\\airlineReservationSystem\\src\\airlineReservationSystem\\flight.txt");
			BufferedWriter bfw1 = new BufferedWriter(fw1);
			bfw1.write(output1);
			bfw1.close();
			fw1.close();
			System.out.println("Cancelled succesfully!");
		}
	}
	public void enquiry() throws NumberFormatException, IOException
	{
		boolean flag = false;
		System.out.print("Enter the pnr you want to cancel:\t");
		int p = in.nextInt();
		FileReader fr = new FileReader("D:\\java programs\\airlineReservationSystem\\src\\airlineReservationSystem\\passenger.txt");
		BufferedReader bfr = new BufferedReader(fr);
		String i;
		while ((i = bfr.readLine()) != null)
		{
			String[] s = i.split(" ");
			int q = Integer.parseInt(s[0]);
			if (p == q)
			{
				flag = true;
				System.out.println("The details of your flight:");
				System.out.println("PNR number:\t"+s[0]);
				System.out.println("Flight ID:\t"+s[1]);
				System.out.println("Source:\t"+s[2]);
				System.out.println("Destination:\t"+s[3]);
				System.out.println("date:\t"+s[4]);
				System.out.println("Arrival time:\t"+s[5]);
				System.out.println("Departure time:\t"+s[6]);
				System.out.println("No. of first class Seats:\t"+s[7]);
				System.out.println("No. of second class Seats:\t"+s[8]);
				System.out.println("Total amount:\t"+s[9]);
				break;
			}
		}
		bfr.close();
		fr.close();
		if (flag == false)
		{
			System.out.println("PNR does not exist!");
			return;
		}
	}
}
