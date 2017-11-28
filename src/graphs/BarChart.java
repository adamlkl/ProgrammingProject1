package firstProcessing;

import java.util.ArrayList;

import processing.core.*;

//Written By Daniel Fong
// credits to Leong Kai Ler for supplying the gradient color set and formula to find the height of each bar and Yrange values 

public class BarChart {
	int variation;
	PApplet p;
	ArrayList<String> locations;
	int [] Colors;
	int[] yValues;
	String[] propertyTypes = {"S", "D", "T", "F", "O"};
	String[] propertyTypeStrings = {"Semi-Detached" , "Detached" , "Terraced" , "Flat" , "Other"};
	String[] priceRanges = {"0-10", "10-20", "20-30", "30-40", "40-50", "50-60", "60-70", "70-80", "80-90", "90-100", "100+"}; 
	String county = "";
	
	BarChart( PApplet p,ArrayList<String> locations, int variation ) {
		//location and Avg Price, location and Qty
		this.variation= variation;
		this.p=p;
		this.locations=locations;
		this.Colors = new int [] {this.p.color(73,0,106),this.p.color(122,1,119),this.p.color(174,1,126),this.p.color(221,52,151),this.p.color(247,104,161),
				this.p.color(250,159,181),this.p.color(252,197,192),this.p.color(253,224,221),this.p.color(255,247,243),this.p.color(77,0,75),
				this.p.color(129,15,124),this.p.color(136,65,157),	this.p.color(140,107,177),this.p.color(140,150,198),this.p.color(158,188,218),
				this.p.color(191,211,230),this.p.color(224,236,244),this.p.color(247,252,253),this.p.color(8,48,107),this.p.color(8,81,156),
				this.p.color(33,113,181),this.p.color(66,146,198),this.p.color(107,174,214),this.p.color(158,202,225),this.p.color(198,219,239),
				this.p.color(222,235,247),this.p.color(247,251,255),this.p.color(0,69,41),this.p.color(0,104,55),this.p.color(35,132,67),this.p.color(65,171,93),
				this.p.color(120,198,121),this.p.color(173,221,142),this.p.color(217,240,163),this.p.color(247,252,185),this.p.color(255,255,229),
				this.p.color(128,0,38),this.p.color(189,0,38),this.p.color(227,26,28),this.p.color(252,78,42),this.p.color(253,141,60),this.p.color(254,178,76),
				this.p.color(254,217,118),this.p.color(255,237,160),this.p.color(255,255,204)};
		this.yValues = plotHeights();
		System.out.println(locations.size());
		
	}
	
	public int[] plotHeights() {
		
		
		switch(this.variation) {
		case 1:
			//location and avg price
			
			int[] yValues1 = new int[this.locations.size()];
			
			for (int i=0; i<yValues1.length; i++) {
				
				yValues1[i]=MySQL.averageTown(this.locations.get(i));
			}
			
			return yValues1;
			
			
		case 2:
			//location and Qty
			
			int[] yValues2 = new int[this.locations.size()];
			
			for (int i=0; i<yValues2.length; i++) {
				
				yValues2[i]=MySQL.countTown(this.locations.get(i));
			}
			return yValues2;
		
			
		case 3:
			//Price range and qty
			
			int[] yValues3 = new int[11];
			int min = 0;
			
			for (int i=0; i<yValues3.length; i++) {
				yValues3[i]=MySQL.priceRangeCount(min,min+100000);
				min+=100000;
			}
			return yValues3;
			
			
		case 4:
			//Type and qty
			int[] yValues4 = new int[propertyTypes.length];
			
			for (int i=0; i<yValues4.length; i++) {
				yValues4[i]=MySQL.countType(propertyTypes[i],county);
			}
			
			return yValues4;
			
		case 5:
			//Type and avg price
			
			int[] yValues5 = new int[propertyTypes.length];
			for (int i=0; i<yValues5.length; i++) {
				yValues5[i]=MySQL.averageType(propertyTypes[i],county);
			}
			
			return yValues5;
			
		default:
			
			return null;
			
		}
		
	}
	
	public void displayGraph( ) {
		if ( yValues != null && yValues.length != 0 )
		{
			int barWidth = ((ProgrammingProject.GRAPH_X_SIZE-100)/yValues.length);
			int YScale=getYScale(yValues);
			p.fill(255);
			p.rect(ProgrammingProject.GRAPH_X_STARTS-50, ProgrammingProject.GRAPH_Y_STARTS-30, ProgrammingProject.GRAPH_X_SIZE+50, ProgrammingProject.GRAPH_Y_SIZE+30);
			p.noFill();
			
			for (int i=0; i<yValues.length; i++) {
				
				double height = ((double)yValues[i]/(double)YScale)*50;
				p.fill(Colors[i]);
				p.rect(ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.GRAPH_X_SIZE+80+(i*(barWidth)),ProgrammingProject.SCREEN_HEIGHT-65-(int)height,barWidth-(barWidth/5),(int)height);
				p.textSize(9);
				p.fill(0);

				p.text(yValues[i],ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.GRAPH_X_SIZE+80+(i*barWidth) , ProgrammingProject.SCREEN_HEIGHT-70-(int)height);
				
			}
			
			for (int i=0; i<=10; i++) {
				p.textAlign(p.RIGHT);
				p.text(i*YScale, ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.GRAPH_X_SIZE+67 , ProgrammingProject.SCREEN_HEIGHT-63-(i*50));
				
			}
			
			p.textAlign(p.CENTER);

			p.noFill();
			p.stroke(0);
			p.strokeWeight(4);
			p.strokeJoin(p.MITER);
			p.beginShape();
			p.vertex(ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.GRAPH_X_SIZE+70,ProgrammingProject.SCREEN_HEIGHT-ProgrammingProject.GRAPH_Y_SIZE+35);
			p.vertex(ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.GRAPH_X_SIZE+70,ProgrammingProject.SCREEN_HEIGHT-65);
			p.vertex(ProgrammingProject.SCREEN_WIDTH-30,ProgrammingProject.SCREEN_HEIGHT-65);
			p.endShape();
			
			switch(this.variation) {
			case 1:
				p.textSize(16);
				p.text("Avg. Prices in £ of Houses Sold at Selected Locations", ProgrammingProject.SCREEN_WIDTH+50-ProgrammingProject.GRAPH_X_SIZE/2, ProgrammingProject.SCREEN_HEIGHT-ProgrammingProject.GRAPH_Y_SIZE);
				p.textSize(13);
				p.text("Avg. Price in £", ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.GRAPH_X_SIZE, ProgrammingProject.SCREEN_HEIGHT-300);
				p.text("Locations", 870, ProgrammingProject.SCREEN_HEIGHT-20);
				for (int i=0; i<yValues.length; i++) {
					double height = ((double)yValues[i]/(double)YScale)*50;
					p.textSize(11);
					p.fill(0);
					p.textAlign(p.LEFT);
					p.text(locations.get(i).toUpperCase(),ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.GRAPH_X_SIZE+80+(i*barWidth) , ProgrammingProject.SCREEN_HEIGHT-50);
					
				}
				break;
				
			case 2:
				p.textSize(16);
				p.text("Quantity of Houses Sold at Selected Locations", ProgrammingProject.SCREEN_WIDTH+50-ProgrammingProject.GRAPH_X_SIZE/2, ProgrammingProject.SCREEN_HEIGHT-ProgrammingProject.GRAPH_Y_SIZE );
				p.textSize(13);
				p.text("Quantity", ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.GRAPH_X_SIZE, ProgrammingProject.SCREEN_HEIGHT-300);
				p.text("Locations", 870, ProgrammingProject.SCREEN_HEIGHT-20);
				for (int i=0; i<yValues.length; i++) {
					double height = ((double)yValues[i]/(double)YScale)*50;
					p.textSize(11);
					p.fill(0);
					p.textAlign(p.LEFT);
					p.text(locations.get(i).toUpperCase(),ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.GRAPH_X_SIZE+80+(i*barWidth) , ProgrammingProject.SCREEN_HEIGHT-50);
					
				}
				break;
			
			case 3:
				p.textSize(16);
				p.text("Quantity of Houses Sold by Price Range", ProgrammingProject.SCREEN_WIDTH+50-ProgrammingProject.GRAPH_X_SIZE/2, ProgrammingProject.SCREEN_HEIGHT-ProgrammingProject.GRAPH_Y_SIZE );
				p.textSize(13);
				p.text("Quantity", ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.GRAPH_X_SIZE, ProgrammingProject.SCREEN_HEIGHT-300);
				p.text("Price Range in Hundreds of Thousands of £", 870, ProgrammingProject.SCREEN_HEIGHT-20);
				int priceRange=0;
				for (int i=0; i<yValues.length; i++) {
					double height = ((double)yValues[i]/(double)YScale)*50;
					p.textSize(11);
					p.fill(0);
					p.textAlign(p.LEFT);
					if (priceRange==10) {
						p.text(priceRange + "+",ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.GRAPH_X_SIZE+80+(i*barWidth) , ProgrammingProject.SCREEN_HEIGHT-50);
					}
					else {
						p.text(priceRange + " - " + (priceRange+1),ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.GRAPH_X_SIZE+80+(i*barWidth) , ProgrammingProject.SCREEN_HEIGHT-50);
					}
					priceRange+=1;
				}
				break;
				
			case 4:
				p.textSize(16);
				p.text("Quantity of sold House Property Types", ProgrammingProject.SCREEN_WIDTH+50-ProgrammingProject.GRAPH_X_SIZE/2, ProgrammingProject.SCREEN_HEIGHT-ProgrammingProject.GRAPH_Y_SIZE );
				p.textSize(13);
				p.text("Quantity", ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.GRAPH_X_SIZE, ProgrammingProject.SCREEN_HEIGHT-300);
				p.text("Property Types", 870, ProgrammingProject.SCREEN_HEIGHT-20);
				for (int i=0; i<yValues.length; i++) {
					double height = ((double)yValues[i]/(double)YScale)*50;
					p.textSize(11);
					p.fill(0);
					p.textAlign(p.LEFT);
					p.text(propertyTypeStrings[i],ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.GRAPH_X_SIZE+80+(i*barWidth) , ProgrammingProject.SCREEN_HEIGHT-50);
					
				}
				break;
				
			case 5:
				p.textSize(16);
				p.text("Avg Prices in £ of House Property Types", ProgrammingProject.SCREEN_WIDTH+50-ProgrammingProject.GRAPH_X_SIZE/2, ProgrammingProject.SCREEN_HEIGHT-ProgrammingProject.GRAPH_Y_SIZE );
				p.textSize(13);
				p.text("Avg. Price in £", ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.GRAPH_X_SIZE, ProgrammingProject.SCREEN_HEIGHT-300);
				p.text("Property Types", 870, ProgrammingProject.SCREEN_HEIGHT-20);
				for (int i=0; i<yValues.length; i++) {
					double height = ((double)yValues[i]/(double)YScale)*50;
					p.textSize(11);
					p.fill(0);
					p.textAlign(p.LEFT);
					p.text(propertyTypeStrings[i],ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.GRAPH_X_SIZE+80+(i*barWidth) , ProgrammingProject.SCREEN_HEIGHT-50);
					
				}
				break;
				
			default:	
			}
			p.textAlign(p.LEFT);
			p.rectMode(PConstants.CORNER);
			p.strokeWeight(1);
		}
	}
	
	public static int getYScale (int[] array)
	{	
		int currentMax = 0;
		int nextNumber = 0;
		for ( int index = 0;  index<array.length; index++ )
		{
			nextNumber = array[index];
			if ( nextNumber>currentMax)
			{
				currentMax = nextNumber;
			}
		}

		if (currentMax < 9) return 1;
		else if ( currentMax > 9 && currentMax%10 == 0 ) return currentMax/10;
		else return (currentMax/10) + 1; 
	}
	
}
