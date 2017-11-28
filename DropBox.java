package firstProcessing;
import processing.core.PApplet;
import processing.core.PFont;

public class DropBox extends Widget{
	int[] events;
	PApplet app;
	String currentSelection;
	float x,y;
	int height = 25;
	int width = 100;
	boolean isSelected;
	String[] selections;
	PFont font;
	DropBox(float x, float y,PApplet app,int[] events,String[] selections)
	{
		super(x, y, app, events, selections);
		this.events = events;
		this.app = app;
		this.selections = selections;
		this.x = x;
		this.y = y;
		if(selections != null)
		{
			currentSelection = new String(selections[0]);
		}
		
		font = app.createFont("Arial",16);
	}
	void drawWidget()
	{
		app.stroke(0);
		app.fill(255);
		app.rect(x, y, width, height);
		if(selections != null)
		{
			if(isSelected)
			{
				
					app.rect(x , y + height, width, selections.length * height);
					for(int i = 0;i<selections.length;i++)
					{
						app.fill(0);
						app.textFont(font);
						app.text(selections[i ], x, (y + (2*height)) + (height * i));
					}
				
			}
			app.fill(0);
			app.textFont(font);
			app.text(currentSelection, x, y+height);
		}
		app.fill(0);
		app.textFont(font);
	}
	void selectBox()
	{
		this.isSelected = true;
	}
	void deselectBox()
	{
		this.isSelected = false;
	}
	int getEvent(int mX, int mY)
	{
		if(selections != null)
		{
			for(int i=0;i<=selections.length;i++)
			{
				if(mX>x && mX < x+width && mY >y + (height * (i)) && mY <y + (height * (i))+height)
			     {
					
			        return events[i];
			       
			        
			     }
				
				
				
				
			}
		}
	
	     
	     return 0;   
	}
	boolean isTicked()
	{
		return isSelected;
	}
	void changeSelection(String input)
	{
		currentSelection = input;
	}
	
}
