package creditCardStatementReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CCStatement {

	public static void main(String[] args) {
		
		List<String[]> transactions = new ArrayList<String[]>();
		String filename = "F:\\Java files\\CCStatement.csv";
		String dataRow;
		double balance = 0;
		
		
		try {
			//open the file
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			//read the data as long as it's not empty
			while ((dataRow = br.readLine()) != null) {
				String[] line = dataRow.split(",");
				transactions.add(line);				
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("DATE | TRANSACTION | VENDOR | AMOUNT | BALANCE");
		for(String[] transaction : transactions) {
			String date = transaction[0];
			String type = transaction[1];
			String vender = transaction[2];
			double amount = Double.parseDouble(transaction[3]);
			System.out.print(date + " | " + type + " | " + vender + " | " + amount);
			
			if (type.equals("CREDIT")) {
				balance = balance + amount;
			}
			else if(type.equals("DEBIT")) {
				balance = balance - amount;
			}
			System.out.println(" | " + balance + "\n");
		}
		if (balance > 0) {
			System.out.println("You have a balance of " + balance);
			System.out.println("You are charged a 10% fee of " + balance * .10);
			System.out.println("Your new balance is: " + balance*1.1);
		}
		else if (balance < 0) {
			System.out.println("Thanks for your payment");
			System.out.println("You have a overpayment of - " + balance);
		}
		else {
			System.out.println("Thanks for your payment \n Have a nice day !");
		}
	}
		
}


