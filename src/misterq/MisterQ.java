package misterq;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MisterQ {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BufferedReader br = null;
		List<String> myMeatList = null;
		List<String> myChoiceList = null;
		List<String> myWeightList = null;
		
		try
		{
			br = new BufferedReader(new InputStreamReader(System.in));
			myMeatList = new ArrayList<String>();
			myChoiceList = new ArrayList<String>();
			myWeightList = new ArrayList<String>();
			
			String compareString = null;
			int input = 0;
			
			do
			{
				
				System.out.println("\nWelcome to MisterQ\n");
				System.out.println("1.Select meat type");
				System.out.println("2.Select weight of the meat");
				System.out.println("3. Short Summary of the order");
				
				input = Integer.valueOf(br.readLine());
				
				switch(input)
				{
				case 1:
					System.out.println("Meat types:\n");
					System.out.println("Steak 'S'");
					System.out.println("Burger 'B'");
					System.out.println("Chicken Steak 'CS'");
					System.out.println("Sausage 'SG'");
					
					System.out.println("\nPlease type in your choice:");
					compareString = br.readLine();
					
					if((compareString.compareTo("S"))==0)
					{
						myMeatList.add("Steak");
					}
					else if((compareString.compareTo("B"))==0)
					{
						myMeatList.add("Burger");
					}
					else if((compareString.compareTo("CS"))==0)
					{
						myMeatList.add("ChickenSteak");
					}
					else if((compareString.compareTo("SG"))==0)
					{
						myMeatList.add("Sausage");
					}
					
					System.out.println("\nThanks for your choice!\n");
					
					System.out.println("\nHow do you want your meat!");
					
					System.out.println("Rare -R");
					System.out.println("Medium -M");
					System.out.println("Well Done -WD");
					
					System.out.println("Please type in");
					compareString = br.readLine();
					
					if((compareString.compareTo("R"))==0)
					{
						myChoiceList.add("Rare");
					}
					else if((compareString.compareTo("M"))==0)
					{
						myChoiceList.add("Medium");
					}
					else if((compareString.compareTo("WD"))==0)
					{
						myChoiceList.add("WellDone");
					}
				
					break;
				case 2:
					System.out.println("Weight Types\n");
					System.out.println("a. 50g");
					System.out.println("b. 100g");
					System.out.println("c. 150g");
					System.out.println("d. 200g");
					System.out.println("e. 250g");
					System.out.println("f. 300g");
					System.out.println("g. 350g");
					System.out.println("h. 400g");
					System.out.println("i. 450g");
					System.out.println("j. 500g");
					
					System.out.println("\nPlease type in your choice:\n");
					compareString = br.readLine();
					
					if((compareString.compareTo("a"))==0)
					{
						myWeightList.add("50g");
					}
					else if((compareString.compareTo("b"))==0)
					{
						myWeightList.add("100g");
					}
					else if((compareString.compareTo("c"))==0)
					{
						myWeightList.add("150g");
					}
					else if((compareString.compareTo("d"))==0)
					{
						myWeightList.add("200g");
					}
					else if((compareString.compareTo("e"))==0)
					{
						myWeightList.add("250g");
					}
					else if((compareString.compareTo("f"))==0)
					{
						myWeightList.add("300g");
					}
					else if((compareString.compareTo("g"))==0)
					{
						myWeightList.add("350g");
					}
					else if((compareString.compareTo("h"))==0)
					{
						myWeightList.add("400g");
					}
					else if((compareString.compareTo("i"))==0)
					{
						myWeightList.add("450g");
					}
					else if((compareString.compareTo("j"))==0)
					{
						myWeightList.add("500g");
					}
					
					System.out.println("Thanks for your choice!");
					break;
				case 3:
					System.out.println("Chosen order:");
					System.out.println(myMeatList.toString());
					System.out.println(myChoiceList.toString());
					System.out.println(myWeightList.toString()+"\n");
					System.out.println("Thanks for your order .. it will be ready in XY-Min!");
					break;
				}
				
			}while(input != 0);
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
			
	}
	
	public static void initMeat()
	{
		
		
	}
	
	public static void initDegrees()
	{
		List<Integer> degreesList = new ArrayList<Integer>();
		
		degreesList.add(60);
		degreesList.add(71);
		degreesList.add(70);
		degreesList.add(74);
		degreesList.add(77);
		
	}
	
}
