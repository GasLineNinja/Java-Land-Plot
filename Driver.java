
public class Driver {

	public static void main(String[] args) {
		//TODO:
		// 1. Create 4 Strings representing different owners
		//		Make up names
		//		Store them in an array
		String[] name = {"Hayden", "Mike", "Melanie", "Killan"};
		
		// 2. Create an initial 4000ft by 4000ft plot of land
		//		Owned by one of the owners
		LandPlot plot1 = new LandPlot ("Hayden", 4000, 4000);
		
		// 3. Split that plot into 4 equal-sized plots
		//		each one 2000ft by 2000ft
		//		storing them all in an array
		//		(make sure you understand the splitWidth and splitLength methods)
		LandPlot plot2 = plot1.splitLength();
		LandPlot plot3 = plot1.splitWidth();
		LandPlot plot4 = plot2.splitWidth();
		
		LandPlot[] plotLand = new LandPlot[] {plot1, plot2, plot3, plot4};
		
		// 4. Call printEqual on your array of LandPlots
		//		you should see lots of prints, since all plots have the same value
		printEqual(plotLand);
		
		// 5. Sell the plots so that each owner owns one plot
		plot2.sellLand("Mike");
		plot3.sellLand("Melanie");
		plot4.sellLand("Killian");
		
		// 6. Increase the price per acre to 200
		double newPrice = 200;
		LandPlot.updatePrice(newPrice);

		// 7. Print out the toString for each plot
		//		You should see the value of the plots has gone up!
		System.out.println(plot1.toString());
		System.out.println(plot2.toString());
		System.out.println(plot3.toString());
		System.out.println(plot4.toString());
		
		// 8. Call printEqual on your array again
		//		this time nothing should print, since all plots have a different owner
		printEqual(plotLand);
	}
	
	private static void printEqual(LandPlot[] plots) {
		for(int i=0; i<plots.length; i++)
			for(int j=i+1; j<plots.length; j++)
				//TODO change this to only print if the two plots are equal
				if(plots[i].equals(plots[j]))
					System.out.println("Two equal plots: " + plots[i].toString() + " at " + i + " and " + plots[j].toString() + " at " + j);
	}
}
