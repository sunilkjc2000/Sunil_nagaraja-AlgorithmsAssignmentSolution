package com.greatlearn.stockdriver;
import java.util.Scanner;

import com.greatlearn.stocks.*;

public class driver {

	public static void main(String args[])
	{
		int caseId = 0;
		Scanner scan = new Scanner(System.in);
		System.out.println("enter the no of companies");

		int numComps = scan.nextInt();

		Stockers stockers = new Stockers(numComps);

		for(int i = 0; i < numComps; i++) {
			System.out.println("Enter current stock price of the company " + (i+1));
			stockers.setStocks(i, scan.nextDouble());

			System.out.println("Whether company's stock price rose today compare to yesterday?");
			stockers.setStocksTrend(i, scan.nextBoolean());

		}

		// Sort data for future use
		stockers.sortData();

		//Till user wants to exit
		try {
			do {
				System.out.println("-----------------------------------------------");

				System.out.println("Enter the operation that you want to perform\r\n"
						+ "1. Display the companies stock prices in ascending order\r\n" +
						"2. Display the companies stock prices in descending order\r\n" +
						"3. Display the total no of companies for which stock prices rose today\r\n" +
						"4. Display the total no of companies for which stock prices declined today\r\n" +
						"5. Search a specific stock price\r\n" +
						"6. press 0 to exit\r\n" +
						"----------------------------------------------");

				caseId = scan.nextInt();
				switch(caseId) {
				case 1:
					//Print stocks in ascending order
					System.out.println("Stock prices in ascending order are :");
					for(int i = numComps-1; i >= 0 ; i--)
					{
						System.out.print(stockers.getStock(i) + " ");
					}
					break;
				case 2:
					System.out.println("Stock prices in descending order are :");
					//Print stocks in descending order
					
					for(int i = 0;i < numComps;i++)
					{
						System.out.print(stockers.getStock(i)+ " ");
					}
					break;
				case 3:
					//Print stock count for which value raised today
					int raiseCount = 0;
					for(int i = 0;i < numComps;i++)
					{
						if(stockers.getStockTrend(i)) {
							raiseCount = raiseCount + 1;
						}
					}
					System.out.println("Total no of companies whose stock price rose today :" + raiseCount);
					break;
				case 4:
					//Print stock count for which value decreased today
					int decCount = 0;
					for(int i = 0;i < numComps;i++)
					{
						if(!stockers.getStockTrend(i)) {
							decCount = decCount + 1;
						}
					}
					System.out.println("Total no of companies whose stock price declined today :" + decCount);
					break;
				case 5:
					//Search for stock
					System.out.println("enter the key value");
					double value = scan.nextDouble();
					if(stockers.searchStock(value)) {
						System.out.println("Stock of value " + value + " is present");
					}
					else
					{
						System.out.println("Stock of value " + value + " not present");
					}
					break;
				case 0:
					System.out.println("Successfully exited");   	   
					break;
				}
			}while(caseId != 0);
		}
		catch(Exception e)
		{
			System.out.println("Enter valid inputs");   	
		}
		scan.close();
	}
}
