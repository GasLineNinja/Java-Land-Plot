import java.text.DecimalFormat;

public class LandPlot {
	
	
	//private object fields
	private	String owner;
	private double length;
	private double width;
	
	//private static mutable variable
	private static double pricePerAcre = 100;
	//public static final constant
	public static final int SQUARE_FEET_PER_ACRE = 43560;
	
	//used for string format
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	
	
	public LandPlot(String name, double lengthInFeet, double widthInFeet) {
		//TODO initialize fields
		//if a negative is given, set to 0.0
		this.owner = name;
		this.length = lengthInFeet;
		this.width = widthInFeet;
		
		if (length < 0)
			length = 0.0;
		if (width < 0)
			width = 0.0;
	}
	
	
	public LandPlot(String name, double acres) {
		//TODO call the other constructor
		//assume the plot is a square
		//compute the length and width from the acres by:
		// 1. convert acreage to square feet by multiplying by the conversion constant
		// 2. take the square root of the square feet
		// 3. do this all on one line, inside the parentheses of the constructor call
		//		eg. this(name, <BUNCH OF MATH>, <BUNCH OF MATH>);
		//What happens if a negative acreage is given? This is not tested, but think about it.
		this(name, Math.sqrt(acres * SQUARE_FEET_PER_ACRE), Math.sqrt(acres * SQUARE_FEET_PER_ACRE));
	}
	
	//basic getters included for you
	
	public String getOwner() {
		return owner;
	}


	public double getLength() {
		return length;
	}


	public double getWidth() {
		return width;
	}
	
	/**
	 * @return the size of this plot in acres.
	 */
	public double getAcres() {
		//TODO multiply length by width to get area in square feet
		//then divide by the number of square feet in an acre
		double sqrFtAcre = (length * width) / SQUARE_FEET_PER_ACRE;
		
		return sqrFtAcre;
	}
	
	/**
	 * @return the value in dollars of this plot of land currently
	 */
	public double getValue() {
		//TODO multiply the acreage by the price per acre
		double value = getAcres() * pricePerAcre;
		return value;
	}
	
	/**
	 * returns a string representation of this object.
	 * syntax: <owner>'s plot: <numberOfAcres> acres valued at <valueInDollars>
	 */
	@Override
	public String toString() {
		//TODO to pass the tests, use df2.format(value) to get the correct format for numbers
		
		return getOwner() + "'s plot: " + df2.format(getAcres()) + " acres valued at " + df2.format(getValue());
	}
	
	
	@Override
	public boolean equals(Object other) {
		//TODO
		//1. if this and other are the same object, return true
		if (this == other) {
			return true;
		}
		
		//2. if other is null, return false
		if (other == null) {
			return false;
		}
		
		//3. if other's class is not == this object's class return false
		//	(use .getClass() for this)
		if (other.getClass() != this.getClass()) {
			return false;
		}
		
		//4. cast other to a LandPlot
		LandPlot p1 = (LandPlot)other;
		
		//5. if either double field is not equals, return false
		if (this.length != p1.length || this.width != p1.width) {
			return false;
		}
		
		//6. if one owner is null and the other is not, return false
		if (owner == null && p1.owner != null || owner != null && p1.owner == null) {
			return false;
		}
			
		//7. if neither owner is null, but the owners are not equal, return false
		if (owner != null && p1.owner != null && (!(owner.equals(p1.owner)))) {
			return false;
		}
		
		//8. return true
		return true;
	}


	/**
	 * @param newOwner: the new owner of this plot
	 * @return the value of this plot at the time of sale.
	 */
	public double sellLand(String newOwner) {
		//TODO change the owner and return the value
		owner = newOwner;
		return getValue();
	}
	
	/**
	 * divides this plot into two equal landPlots width-wise, unless its width is less than 20 feet.
	 * this plot's width should get smaller if a split occurs, and a new plot (the other half) should be returned
	 * @return the new LandPlot if created, null if not.
	 */
	public LandPlot splitWidth() {
		//TODO split the plot
		LandPlot newPlot = new LandPlot(owner, length, width / 2);
		if (width >= 20) {
			width = width / 2;
			return newPlot;
		}
		return null;
	}
	
	
	/**
	 * divides this plot into two equal landPlots length-wise, unless its length is less than 20 feet.
	 * this plot's length should get smaller if a split occurs, and a new plot (the other half) should be returned
	 * @return the new LandPlot if created, null if not.
	 */
	public LandPlot splitLength() {
		//TODO split the plot
		LandPlot newPlot = new LandPlot(owner, length / 2, width);
		if (length >= 20) {
			length = length / 2;
			return newPlot;
		}
		return null;
		
		
	}
	
	

	

	
	//STATIC METHODS
	
	/**
	 * static method to update the current price Per Acre.
	 * if a negative value is given, set to 0.
	 * @param newPrice
	 */
	public static void updatePrice(double newPrice) {
		//TODO change the static variable that determines price per acre
		pricePerAcre = newPrice;
		if (newPrice < 0)
			pricePerAcre = 0;
	}


	

}
