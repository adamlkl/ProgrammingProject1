package firstProcessing;

import processing.core.PApplet;
import processing.core.PConstants;
/*
 * credits to Leong Kai Ler 
 * creates a sidescreen to be displayed on top of the map 
 * takes in variables from dataEntry classes as parameters to display the information of the selected county
 */
public class sideScreen {

	private int scrollBarX;
	private int scrollBarY;
	private PApplet parent;
	private int [] lineColors;
	private int [] spotlight;
	private String [] label;
	
	sideScreen(PApplet p)
	{
		this.parent = p;
		this.lineColors = new int [] {this.parent.color(63,81,181),this.parent.color(46,125,50)};
		this.spotlight = new int []{this.parent.color(0, 229, 255),this.parent.color(0, 255, 0)};
		this.label = new String [] {"County Name: ", "Average Price: ", "Lowest Price: ", "Highest Price: ", "Quantity: "};
	}
	
	void draw ()
	{
		this.parent.rectMode(PConstants.CORNER);
		this.parent.fill(this.parent.color(0, 66, 105));
		this.parent.noStroke();
		this.parent.rect(0,0,ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.MAP_X_SIZE-18,ProgrammingProject.SCREEN_HEIGHT);
		this.parent.strokeWeight(5);
		this.parent.stroke(0,255,255);
		this.parent.noFill();
		this.parent.rect(20,20,ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.MAP_X_SIZE-60,ProgrammingProject.SCREEN_HEIGHT-220);
		
		for ( int index=0; index<51; index++ )
		{
			this.parent.stroke(lineColors[index%2]);
			this.parent.strokeWeight(1);
			this.parent.line(index*5, (float) (ProgrammingProject.SCREEN_HEIGHT-80+index*1.45), index*5, ProgrammingProject.SCREEN_HEIGHT);
			this.parent.line(ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.MAP_X_SIZE-20-index*5, (float) (ProgrammingProject.SCREEN_HEIGHT-80+index*1.45), ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.MAP_X_SIZE-20-index*5, ProgrammingProject.SCREEN_HEIGHT);
			this.parent.fill(spotlight[index%2]);
			this.parent.ellipse(index*5, (float)(ProgrammingProject.SCREEN_HEIGHT-80+index*1.50), 3, 3);
			this.parent.ellipse(ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.MAP_X_SIZE-20-index*5, (float)(ProgrammingProject.SCREEN_HEIGHT-80+index*1.45), 3, 3);
		}
	}
	
	void showInformation(DataEntry data )
	{
		this.parent.fill(0);
		this.parent.textAlign(PConstants.LEFT,PConstants.CENTER);
		this.parent.textSize(12);
		this.parent.text(data.returnCountyName(), 210, 175);
		this.parent.text("£"+ data.getAveragePrice(), 210, 235);
		this.parent.text("£"+ data.getLowestPrice(), 210, 295);
		this.parent.text("£"+ data.getHighestPrice(), 210, 355);
		this.parent.text(data.getQuantity(), 210, 415);
	}
	
	void showInformationBox()
	{
		this.parent.fill(this.parent.color(176, 190, 197));
		this.parent.textAlign(PConstants.LEFT,PConstants.CENTER);
		this.parent.textSize(16);
		this.parent.text("County Information", 30, 130);
		this.parent.stroke(this.parent.color(176, 190, 197));
		this.parent.strokeWeight(2);
		this.parent.line(30, 143, 180, 143);
		for ( int index = 0; index<this.label.length; index++ )
		{
			this.parent.stroke(this.parent.color(0));
			this.parent.strokeWeight(1);
			this.parent.fill(this.parent.color(0,229,255));
			this.parent.rect(30, 150+index*60, 150, 50);
			this.parent.fill(255);
			this.parent.rect(190, 150+index*60, 350, 50);
			this.parent.fill(0);
			this.parent.textAlign(PConstants.LEFT,PConstants.CENTER);
			this.parent.textSize(12);
			this.parent.text(label[index], 50, 175+index*60);
		}
		this.parent.rectMode(PConstants.CORNER);
		this.parent.fill(this.parent.color(120,144,156));
		this.parent.stroke(this.parent.color(207,2016,220));
		this.parent.strokeWeight(4);
		this.parent.rect(30, 50, 150, 50);
		this.parent.fill(0);
		this.parent.text("Search County: ", 50, 75);
		this.parent.fill(0);
		this.parent.rect(348, 473, 144, 79);
	}
}
