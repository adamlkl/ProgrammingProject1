package firstProcessing;

import java.util.ArrayList;
import firstProcessing.MySQL;
import processing.core.PApplet;
import processing.core.PConstants;

/*Written by Daniel Fong and Leong Kai Ler 
 * credits to Leong Kai Ler 
 * Supply colors
 * formula to get Y range
 * created the graph
 * credits to Daniel Fong 
 * adjust the graphs to fit in the screen 
*/

public class LineGraph {

	PApplet parent;
	String xlabel;
	String ylabel;
	int lineColor;
	int pointColor;
	int fontColor;
	int scaleColor;
	String [] price;
	int [] yPoints;
	ArrayList <String> years;
	String county;
	int yScale;
	int xAxis;
	float xpos;
	float ypos;
	float lastxpos;
	float lastypos;
	int variation;

	LineGraph (PApplet p, ArrayList <String> allYears, String county, int variation )
	{
		this.parent = p;
		this.xlabel = "year";
		this.years = allYears;
		this.yPoints=new int[years.size()];
		this.variation=variation;
		this.county=county;
		
		for (int i=0; i<yPoints.length;i++) {
			if (variation==1) {
				this.ylabel = "Avg Prices in £";
				if ( years.get(i).equals("1999"))
				{
					int totalPrice = 0;
					for ( int j = 1995 ; j < 2000 ; j++ )
					{
						totalPrice += MySQL.averagePriceOfPropertiesPerYear( Integer.toString(j), county );
					}
					
					yPoints[i] = totalPrice/5;
				}
				
				else
				{
					yPoints[i]=MySQL.averagePriceOfPropertiesPerYear( years.get(i), county );
				}
			}
			else if (variation==2) {
				this.ylabel = "Quantity";
				if ( years.get(i).equals("1999"))
				{
					int totalQt = 0;
					for ( int j = 1995 ; j < 2000 ; j++ )
					{
						
						totalQt += MySQL.countOfPropertiesPerYear( Integer.toString(j), county );
					}
					
					yPoints[i] = totalQt;
				}
				
				else
				{
					
					yPoints[i]=MySQL.countOfPropertiesPerYear( years.get(i), county );
				}
			}
			
			
		}
		
		this.xAxis = getXAxis();
		this.yScale = getYScale();
		
		
		this.lineColor = this.parent.color(0,191,255);
		this.pointColor = this.parent.color(70,130,180);
		this.fontColor = this.parent.color(255,255,224);
		this.scaleColor = this.parent.color(127,255,212);
		
	}

	void displayPoints ()
	{
		
		parent.stroke(143,188,139);
		parent.strokeWeight(3);
		parent.fill(0,125,127);
		parent.rect(ProgrammingProject.SCREEN_WIDTH - ProgrammingProject.GRAPH_X_SIZE, ProgrammingProject.SCREEN_HEIGHT - ProgrammingProject.GRAPH_Y_SIZE, ProgrammingProject.GRAPH_X_SIZE , ProgrammingProject.GRAPH_Y_SIZE);
		parent.noFill();
		parent.stroke(127,255,0);
		parent.strokeWeight(10);
		parent.strokeJoin(PConstants.MITER);
		parent.beginShape();
		parent.vertex(ProgrammingProject.GRAPH_X_STARTS+70, ProgrammingProject.GRAPH_Y_STARTS+35);
		parent.vertex(ProgrammingProject.GRAPH_X_STARTS+70, ProgrammingProject.SCREEN_HEIGHT-65);
		parent.vertex(ProgrammingProject.SCREEN_WIDTH-30,ProgrammingProject.SCREEN_HEIGHT-65);
		parent.endShape();
		parent.stroke(143,188,139);
		parent.strokeWeight(2);

		parent.textSize(14);
		parent.fill(this.fontColor);
		parent.textAlign(PConstants.CENTER);
		parent.text(this.ylabel, ProgrammingProject.GRAPH_X_STARTS+70, ProgrammingProject.GRAPH_Y_STARTS+20);
		parent.text(this.xlabel, ProgrammingProject.GRAPH_X_STARTS+ProgrammingProject.GRAPH_X_STARTS/2, ProgrammingProject.SCREEN_HEIGHT-20);
		parent.text( this.ylabel + " of properties sold per " + this.xlabel + " in " + ((county==null)?"Database":this.county) , ProgrammingProject.GRAPH_X_STARTS + ProgrammingProject.GRAPH_X_STARTS/2 + 50, ProgrammingProject.GRAPH_Y_STARTS+30);


		for ( int index2 = 0; index2<11; index2++ )
		{
			parent.strokeWeight(2);
			parent.stroke(this.scaleColor);
			parent.line(ProgrammingProject.GRAPH_X_STARTS+60,(ProgrammingProject.SCREEN_HEIGHT-65)-(index2*50),ProgrammingProject.GRAPH_X_STARTS+80,(ProgrammingProject.SCREEN_HEIGHT-65)-(index2*50));
			parent.textSize(12);
			parent.textAlign(PConstants.RIGHT);
			parent.text(""+ this.yScale*index2, ProgrammingProject.GRAPH_X_STARTS+55, (ProgrammingProject.SCREEN_HEIGHT-60)-(index2*50));
		}

		for ( int index = 0; index<this.yPoints.length; index++ )
		{
			this.xpos = (index*(ProgrammingProject.GRAPH_X_SIZE-100)/xAxis)+ProgrammingProject.GRAPH_X_STARTS+70;
			this.ypos = (ProgrammingProject.SCREEN_HEIGHT-65)-(float)(((float)yPoints[index]/(float)yScale)*50);
			parent.strokeWeight(2);
			parent.stroke(this.scaleColor);
			parent.line(this.xpos, ProgrammingProject.SCREEN_HEIGHT-55, this.xpos,ProgrammingProject.SCREEN_HEIGHT-75);
			parent.textSize(11);
			parent.textAlign(PConstants.CENTER);
			parent.text(""+ this.years.get(index), this.xpos, ProgrammingProject.SCREEN_HEIGHT-40);

			if (index>=1)
			{
				parent.stroke(this.lineColor);
				parent.strokeWeight(2);
				parent.line(this.lastxpos, this.lastypos, this.xpos, this.ypos);
			}
			this.lastxpos = xpos;
			this.lastypos = ypos;
		}

		for ( int index2 = 0; index2<this.yPoints.length; index2++ )
		{
			this.xpos = (index2*(ProgrammingProject.GRAPH_X_SIZE-100)/xAxis)+ProgrammingProject.GRAPH_X_STARTS+70;
			this.ypos = (ProgrammingProject.SCREEN_HEIGHT-65)-(float)(((float)yPoints[index2]/(float)yScale)*50);
			parent.stroke(this.pointColor);
			parent.strokeWeight(6);
			parent.point(this.xpos,this.ypos);
			parent.textSize(10);
			parent.textAlign(PConstants.CENTER);
			parent.text(yPoints[index2], this.xpos, this.ypos-15);
		}
		
		parent.textAlign(parent.LEFT);
		parent.rectMode(PConstants.CORNER);
		parent.noStroke();
	}

	int getYScale()
	{
		int currentMax = 0;
		int nextNumber = 0;
		int multiplier = 1;
		for ( int index = 0; index<this.yPoints.length; index++ )
		{
			nextNumber = this.yPoints[index];
			if ( nextNumber > currentMax )
			{
				currentMax = nextNumber;
			}
		}

		int l = String.valueOf(currentMax).length();
		for(int index2 = 0; index2<l; index2++ )
		{
			multiplier *= 10;
		}
		return multiplier/10;	
	}
	// get the length of xaxis
	int getXAxis()
	{
		return this.years.size();
	}
}
