package firstProcessing;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PConstants;

//Written by Daniel Fong and Leong Kai Ler
/*
 * credits to Leong Kai Ler 
 * supply a set of gradient colors 
 * sets the formula of to draw the arcs in the pie chart
 * display the information box on the screen 
 * credits to Daniel Fong 
 * input formula to calculate the size of each arc
 */

public class PieChart {

	PApplet parent;
	ArrayList <String> priceRange;
	float [] quantities;
	int [] arcColor;
	String xlabel;
	float total;
	ArrayList<String> locations;
	int variation;

	PieChart(PApplet p, ArrayList<String> locations, int variation)
	{
		this.parent = p;
		/*this.arcColor = new int [] {this.parent.color(127,0,0),this.parent.color(204,0,0),this.parent.color(255,68,68),this.parent.color(255,127,127),this.parent.color(255,178,178),this.parent.color(153,81,0),this.parent.color(204,108,0),this.parent.color(255,136,0),this.parent.color(255,187,51)
				,this.parent.color(255,229,100),this.parent.color(44,76,0),this.parent.color(67,101,0),this.parent.color(102,153,0),this.parent.color(153,204,0),this.parent.color(210,254,76),this.parent.color(60,20,81),this.parent.color(107,35,142),this.parent.color(153,51,204)
				,this.parent.color(170,102,204),this.parent.color  (188,147,209),this.parent.color(0,76,102),this.parent.color(0,114,153),this.parent.color(0,153,204),this.parent.color(51,181,229),this.parent.color(142,213,240),this.parent.color(102,0,51)    ,this.parent.color(178,0,88)
				,this.parent.color(229,0,114),this.parent.color(255,50,152),this.parent.color(255,127,191)};
				*/

				this.arcColor = new int [] {this.parent.color(73,0,106),this.parent.color(122,1,119),this.parent.color(174,1,126),this.parent.color(221,52,151),this.parent.color(247,104,161),
				this.parent.color(250,159,181),this.parent.color(252,197,192),this.parent.color(253,224,221),this.parent.color(255,247,243),this.parent.color(77,0,75),
				this.parent.color(129,15,124),this.parent.color(136,65,157),	this.parent.color(140,107,177),this.parent.color(140,150,198),this.parent.color(158,188,218),
				this.parent.color(191,211,230),this.parent.color(224,236,244),this.parent.color(247,252,253),this.parent.color(8,48,107),this.parent.color(8,81,156),
				this.parent.color(33,113,181),this.parent.color(66,146,198),this.parent.color(107,174,214),this.parent.color(158,202,225),this.parent.color(198,219,239),
				this.parent.color(222,235,247),this.parent.color(247,251,255),this.parent.color(0,69,41),this.parent.color(0,104,55),this.parent.color(35,132,67),this.parent.color(65,171,93),
				this.parent.color(120,198,121),this.parent.color(173,221,142),this.parent.color(217,240,163),this.parent.color(247,252,185),this.parent.color(255,255,229),
				this.parent.color(128,0,38),this.parent.color(189,0,38),this.parent.color(227,26,28),this.parent.color(252,78,42),this.parent.color(253,141,60),this.parent.color(254,178,76),
				this.parent.color(254,217,118),this.parent.color(255,237,160),this.parent.color(255,255,204)};

			
		this.locations = locations;
		this.variation=variation;
		System.out.println(locations.size() + "lol");
		
		if (variation==1) {
			quantities = new float[locations.size()];
			for (int i=0; i<quantities.length; i++) {
				quantities[i]=MySQL.countTown(this.locations.get(i));
				total+=quantities[i];
			}
		}
		else if (variation==2) {
			quantities = new float[11];
			int min = 0;
			for (int i=0; i<quantities.length; i++) {
				quantities[i]=MySQL.priceRangeCount(min,min+100000);
				total+=quantities[i];
				min+=100000;
				
			}
		}
	}
	
	void displayPieChart ()
	{
		//if ( quantities ! )
		{
			//parent.fill(255);
			//parent.rect(ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.GRAPH_X_SIZE, ProgrammingProject.SCREEN_HEIGHT-ProgrammingProject.GRAPH_Y_SIZE, ProgrammingProject.GRAPH_X_SIZE, ProgrammingProject.GRAPH_Y_SIZE);
			parent.fill(0);
			parent.textSize(14);
			parent.textAlign(PConstants.CENTER);
			parent.fill(255);
			parent.rect(ProgrammingProject.GRAPH_X_STARTS, ProgrammingProject.GRAPH_Y_STARTS, ProgrammingProject.GRAPH_X_SIZE, ProgrammingProject.GRAPH_Y_SIZE);
			switch(this.variation) {
			case 1:
				parent.fill(0);
				parent.textSize(16);
				parent.text("Quantity of Houses Sold at Selected Locations", ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.GRAPH_X_SIZE/2, ProgrammingProject.SCREEN_HEIGHT-ProgrammingProject.GRAPH_Y_SIZE + 30);
				parent.noFill();
				parent.rectMode(PConstants.CENTER);
				parent.rect(ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.GRAPH_X_SIZE*4/5,  ProgrammingProject.SCREEN_HEIGHT-ProgrammingProject.GRAPH_Y_SIZE/2, 200, locations.size()*30);
				parent.textAlign(PConstants.LEFT);
				for (int i=0; i<locations.size(); i++) {
					parent.fill(arcColor[i]);
					parent.rect(ProgrammingProject.SCREEN_WIDTH-30-ProgrammingProject.GRAPH_X_SIZE*5/6, ProgrammingProject.SCREEN_HEIGHT+15-ProgrammingProject.GRAPH_Y_SIZE/2-(15*locations.size())+(i*30), 8, 8);
					parent.fill(0);
					parent.textSize(13);
					parent.text(locations.get(i).toUpperCase(),ProgrammingProject.SCREEN_WIDTH-20-ProgrammingProject.GRAPH_X_SIZE*5/6, ProgrammingProject.SCREEN_HEIGHT+18-ProgrammingProject.GRAPH_Y_SIZE/2-(15*locations.size())+(i*30));
				}
				break;
				
			case 2:
				parent.fill(0);
				parent.textSize(16);
				parent.text("Quantity of Houses by Price Range", ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.GRAPH_X_SIZE/2, ProgrammingProject.SCREEN_HEIGHT-ProgrammingProject.GRAPH_Y_SIZE + 30);
				parent.noFill();
				parent.rectMode(PConstants.CENTER);
				parent.rect(ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.GRAPH_X_SIZE*4/5,  ProgrammingProject.SCREEN_HEIGHT-ProgrammingProject.GRAPH_Y_SIZE/2, 200, quantities.length*30);
				parent.textAlign(PConstants.LEFT);
				int LowRange=0;
				int HighRange=100000;
				for (int i=0; i<quantities.length; i++) {
					parent.fill(arcColor[i]);
					parent.rect(ProgrammingProject.SCREEN_WIDTH-30-ProgrammingProject.GRAPH_X_SIZE*5/6, ProgrammingProject.SCREEN_HEIGHT+15-ProgrammingProject.GRAPH_Y_SIZE/2-(15*quantities.length)+(i*30), 8, 8);
					parent.fill(0);
					parent.textSize(13);
					if (i==10) {
						parent.text(1000000+"+",ProgrammingProject.SCREEN_WIDTH-20-ProgrammingProject.GRAPH_X_SIZE*5/6, ProgrammingProject.SCREEN_HEIGHT+18-ProgrammingProject.GRAPH_Y_SIZE/2-(15*quantities.length)+(i*30));
					}
					else {
						parent.text(LowRange+"-"+HighRange,ProgrammingProject.SCREEN_WIDTH-20-ProgrammingProject.GRAPH_X_SIZE*5/6, ProgrammingProject.SCREEN_HEIGHT+18-ProgrammingProject.GRAPH_Y_SIZE/2-(15*quantities.length)+(i*30));
					}
					LowRange+=100000;
					HighRange+=100000;
				}
				break;
			default:	
			}
			
			float lastAngle = 0;
			for ( int index = 0; index<quantities.length; index++ )
			{
				parent.fill(this.arcColor[index]);
				parent.arc(ProgrammingProject.SCREEN_WIDTH-200, ProgrammingProject.SCREEN_HEIGHT-300, 300, 300, lastAngle, lastAngle+(quantities[index]/total)*PConstants.TWO_PI);
				lastAngle+=(quantities[index]/total)*PConstants.TWO_PI;
			}
			lastAngle = 0;
			parent.textAlign(parent.LEFT);
			parent.rectMode(PConstants.CORNER);
			parent.strokeWeight(1);
		}
	}
	
}
