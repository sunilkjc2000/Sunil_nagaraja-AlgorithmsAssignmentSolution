package com.greatlearn.stocks;

import java.util.Arrays;

public class Stockers {
	
	private int numberOfComps;
	private double stocks[];
	private boolean stocksTrend[];
	private long sortedData[];
	private int maxDecplaces;
	
    public Stockers(int numberOfComps)
    {
    	this.numberOfComps = numberOfComps;
    	stocks = new double[numberOfComps];
    	stocksTrend = new boolean[numberOfComps];
    	sortedData = new long[numberOfComps];
    	maxDecplaces = 0;
    }
    
    public int getMaxDecplaces() {
		return maxDecplaces;
	}

	public void setMaxDecplaces(int maxDecplaces) {
		this.maxDecplaces = maxDecplaces;
	}

	public void setStocks(int index,double value)
    {
    	stocks[index] = value;
    	
    	
    	// Compute decimal places. This is used to convert double to int, which helps in 
    	// handling precision issues while double data comparision
    	String double2Str = String.valueOf(value);
    	String subString[] = double2Str.split("\\.");
    	if(maxDecplaces < subString.length) {
    		maxDecplaces = subString.length;
    	}
    }
    
    public double getStock(int index)
    {
    	//return(sortedData[index]);
    	return(sortedData[index])/((double)Math.pow(10, maxDecplaces));
    }
    
    public void setStocksTrend(int index,boolean value)
    {
    	stocksTrend[index] = value;
    }
    
    public boolean getStockTrend(int index)
    {
    	return(stocksTrend[index]);
    }
    
    // Sort stock data in ascending order
    public void sortData() {
    	
    	MergeSort mergeSort = new MergeSort();
    	
    	// Construct a temporary array for search. As we are searching for double value
    	// multiply by a factor to avoid precision errors
    	for(int i = 0; i < numberOfComps; i++)
    	{
    		sortedData[i] = (long) (stocks[i] * (double)Math.pow(10, maxDecplaces));
    	}
    	mergeSort.sort(sortedData, 0, numberOfComps-1);
    }
    
    // Search for stock
    public boolean searchStock(double value)
    {	
    	long scaledData = (long) (value*(double)Math.pow(10, maxDecplaces));
    	long tempArray[] = new long[numberOfComps];
    	
    	// Reverse array for binary search of requested data
    	for(int i = 0;i < numberOfComps; i++)
    	{
    		tempArray[i] = sortedData[numberOfComps - i - 1];
    	}
    	
    	int res = Arrays.binarySearch(tempArray, scaledData);
    	
    	boolean test = res >= 0 ? true: false;   
    	
    	return test;
    }
    

}

