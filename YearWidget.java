//created by isaac walker
package firstProcessing;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class YearWidget extends Widget {
	float width =(float)60/1152 * ProgrammingProject.SCREEN_WIDTH;
	float height = (float)20/648 * ProgrammingProject.SCREEN_HEIGHT;
	float x,y;
	boolean ticked = false;
	final String text;
	PApplet app;
	PFont font;
	final int event;
	
	YearWidget(float x, float y, PImage widgetImage, int event, PApplet app,String text) {
		super(x, y, widgetImage, event, app);
		this.x = x;
		this.y =y;
		this.text = text;
		this.app = app;
		this.event = event;
		font = app.createFont("Arial",16);
		
	}
	int getEvent(int mX, int mY)
	{
	     if(mX>x && mX < x+width && mY >y && mY <y+height)
	     {
	        return event;
	     }
	     return 0;   
	}
	void drawWidget()
	{
		app.textAlign(app.LEFT);
		
		app.textFont(font);
		app.strokeWeight(2);
		app.fill(200,140,35);
		if(ticked)
		{
			app.stroke(255,0,0);
		}
		
		app.rect(((float)x/1152) * ProgrammingProject.SCREEN_WIDTH,((float)y/648) * ProgrammingProject.SCREEN_HEIGHT,width,height ,4);
		app.fill(0,0,0);
		app.noStroke();
		app.text(text,(float)(x +5)/1152 * ProgrammingProject.SCREEN_WIDTH,(float)(y+height -1)/648 * ProgrammingProject.SCREEN_HEIGHT);
		
	}
	void changeSkin()
	{
		if(ticked == true)
		{
			ticked = false;
		}
		else
		{
			ticked = true;
		}
	}
	boolean isTicked()
	{
		return ticked;
	}
	void setWidth(float width)
	{
		this.width = width;
	}
	void setHeight(float height)
	{
		this.height = height;
	}
	void untick()
	{
		this.ticked = false;
	}
			

}
