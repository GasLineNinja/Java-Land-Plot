

import junit.framework.TestCase;

public class TestClass extends TestCase{
	
	
	LandPlot plot1, plot2, plot3, plot4, plot5, plot6, plotEmpty;
	
	public void setUp() {
		plot1 = new LandPlot("Mr. Jenkins", 200, 400);
		plot2 = new LandPlot("Jared", 25000, 48000);
		plot3 = new LandPlot("acreMan", 1);
		plot4 = new LandPlot("Julie", 208.71, 208.71);
		plot5 = new LandPlot("Aditya", 1000, 1000);
		plot6 = new LandPlot("Xiaoli", 800, 400);
		plotEmpty = new LandPlot("empty", 0, 0);
	}
	
	//testing constructor
	public void test00() {
		assertEquals(200.0, plot1.getLength());
		assertEquals(400.0, plot1.getWidth());
		assertEquals("Mr. Jenkins", plot1.getOwner());
		
		assertEquals(0.0, plotEmpty.getLength());
		assertEquals(0.0, plotEmpty.getWidth());
		assertEquals("empty", plotEmpty.getOwner());
		
		//allows an error of 0.005
		//this accounts for precision and rounding errors with doubles
		assertEquals(208.71, plot3.getLength(), 0.005);
		assertEquals(208.71, plot3.getWidth(), 0.005);
		assertEquals("acreMan", plot3.getOwner());
	}
	
	//testing constructor special cases
	public void test01() {
		assertEquals(0.0, new LandPlot("Halap", -1, 10).getLength());
		assertEquals(0.0, new LandPlot("Branyi", 10, -1).getWidth());
		assertEquals(0.0, new LandPlot("Ixi", -1, -1).getWidth());
		assertEquals(0.0, new LandPlot("Guangdong", -1, -1).getLength());
	}
	
	//testing getAcres
	public void test02() {
		assertEquals(1.837, plot1.getAcres(), 0.005);
		assertEquals(27548.209, plot2.getAcres(), 0.005);
		assertEquals(1.0, plot3.getAcres(), 0.005);
		assertEquals(1.0, plot4.getAcres(), 0.005);
		assertEquals(22.957, plot5.getAcres(), 0.005);
		assertEquals(7.346, plot6.getAcres(), 0.005);
		assertEquals(0.0, plotEmpty.getAcres(), 0.005);
	}
	
	//testing getValue and updatePrice
	public void test04() {
		LandPlot.updatePrice(100);
		assertEquals(183.7, plot1.getValue(), 0.5);
		assertEquals(2754820.9, plot2.getValue(), 0.5);
		assertEquals(100.0, plot3.getValue(), 0.5);
		assertEquals(100.0, plot4.getValue(), 0.5);
		assertEquals(2295.7, plot5.getValue(), 0.5);
		assertEquals(734.6, plot6.getValue(), 0.5);
		assertEquals(0.0, plotEmpty.getValue(), 0.5);
		LandPlot.updatePrice(10);
		assertEquals(18.37, plot1.getValue(), .05);
		LandPlot.updatePrice(0);
		assertEquals(0.0, plot1.getValue(), .005);
		LandPlot.updatePrice(-40);
		assertEquals(0.0, plot1.getValue(), .005);
	}
	
	//testing toString
	public void test05() {
		LandPlot.updatePrice(100);
		assertEquals("Mr. Jenkins's plot: 1.84 acres valued at 183.65", plot1.toString());
		assertEquals("Jared's plot: 27548.21 acres valued at 2754820.94", plot2.toString());
		assertEquals("acreMan's plot: 1 acres valued at 100", plot3.toString());
		assertEquals("Julie's plot: 1 acres valued at 100", plot4.toString());
		assertEquals("empty's plot: 0 acres valued at 0", plotEmpty.toString());
		LandPlot.updatePrice(200);
		assertEquals("Julie's plot: 1 acres valued at 200", plot4.toString());
	}
	
	//testing equals
	@SuppressWarnings("unlikely-arg-type")
	public void test06() {
		assertTrue(plot1.equals(plot1));
		assertTrue(plotEmpty.equals(plotEmpty));
		assertFalse(plot1.equals(null));
		assertFalse(plot1.equals(new String("Mr. Jenkins")));
		LandPlot plotSame = new LandPlot("Mr. Jenkins", 200, 400);
		assertTrue(plot1.equals(plotSame));
		assertTrue(plotSame.equals(plot1));
		plotSame = new LandPlot(new String("Mr. Jenkins"), 200, 400);
		assertTrue(plot1.equals(plotSame));
		LandPlot plotDiff = new LandPlot("Mr.Jenkins", 200, 400);
		assertFalse(plot1.equals(plotDiff));
		plotDiff = new LandPlot("Mr. Jenkins", 201, 400);
		assertFalse(plot1.equals(plotDiff));
		plotDiff = new LandPlot("Mr.Jenkins", 200, -400);
		assertFalse(plot1.equals(plotDiff));
		LandPlot plotNull = new LandPlot(null, 200, 400);
		assertFalse(plot1.equals(plotNull));
		assertFalse(plotNull.equals(plot1));
		plotSame = new LandPlot(null, 200, 400);
		assertTrue(plotNull.equals(plotSame));
	}
	
	
	
	//testing sellLand
	public void test07(){
		LandPlot.updatePrice(100);
		assertEquals(100.0, plot3.sellLand("Footstool"));
		assertEquals("Footstool", plot3.getOwner());
		
		LandPlot.updatePrice(150);
		
		assertEquals(150.0, plot3.sellLand("Jane"));
		assertEquals(275.0, plot1.sellLand("Jane"), .5);
		
		LandPlot.updatePrice(20);
		
		LandPlot testPlot = new LandPlot("Raga", 1);
		
		assertEquals(20.0, testPlot.sellLand("Sope"));
	}
	
	
	//testing split
	public void test08(){
		LandPlot plot5_2 = plot5.splitLength();
		assertEquals(500.0,plot5_2.getLength());
		assertEquals(500.0,plot5.getLength());
		
		assertEquals(1000.0,plot5.getWidth());
		
		LandPlot plot5_2_2 = plot5_2.splitWidth();
		
		assertEquals(500.0,plot5_2_2.getLength());
		assertEquals(500.0,plot5_2_2.getWidth());
		
		assertEquals(500.0,plot5_2.getLength());
		assertEquals(500.0,plot5_2.getWidth());
		
		assertNotNull(plot5.splitLength());
		assertNotNull(plot5.splitLength());
		assertNotNull(plot5.splitLength());
		assertNotNull(plot5.splitLength());
		assertNotNull(plot5.splitLength());
		assertNull(plot5.splitLength());
		assertEquals(15.625, plot5.getLength(), 0.0005);
		
	}
	
	//checking equals after splitting
	public void test09(){
		assertFalse(plot6.equals(plot5));
		assertTrue(plot6.equals(plot6));
		LandPlot plot6Twin = plot6.splitLength();
		assertTrue(plot6.equals(plot6Twin));
		
		LandPlot plot5Similar = new LandPlot ("notAditya",1000,1000);
		
		
		assertFalse(plot5.equals(plot5Similar));
		plot5Similar.sellLand("Aditya");
		assertTrue(plot5.equals(plot5Similar));
		
	}
	
	
	
	

}
