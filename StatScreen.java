package firstProcessing;
//created by isaac walker
import java.util.ArrayList;
import processing.core.*;
import processing.core.PApplet;
import processing.core.PImage;

public class StatScreen extends Screen
{
	PImage backGroundImage;
	ArrayList otherImages;
	ArrayList widgetArray,screenList;
	PApplet app;
	int currentLanguage = 0;
	Screen currentScreen;
	String[][] description;
	PFont font;
	StatScreen(PImage backGroundImage, ArrayList otherImages, ArrayList widgetArray,PApplet app,ArrayList screenList,String[][] description) 
	{
		super(backGroundImage, otherImages, widgetArray,app);
		this.backGroundImage = backGroundImage;
		this.widgetArray = widgetArray;	
		this.app = app;
		this.screenList = screenList;
		currentScreen = (Screen) screenList.get(2);
		setFlagWidget(2);
		this.description = description;
		font = app.createFont("Arial",16);
		
	}
	public void draw()
	{	
		app.image(backGroundImage,0,0);
		drawWidgets();
		currentScreen.draw();
		app.textAlign(app.LEFT);
		app.textFont(font);
		app.text("Bar Chart", 200, 70);
		app.text("Pie Chart", 110, 70);
		app.text("Line Graph", 290, 70);
		
	}
	
	 int getEvent()
	  {
	    int event = -1;
	    for(int i = 0; i <widgetArray.size(); i++){

	    	event = ((Widget) widgetArray.get(i)).getEvent(app.mouseX,app.mouseY);
	    	if(event != 0)
	    	{
	    		return event;
	    	}	
	       
	    }
	    event = currentScreen.getEvent();
	    return event;
	  }
	void drawWidgets()
	{
		for(int i =0; i<widgetArray.size();i++)
		{
			
		
		((Widget) widgetArray.get(i)).drawWidget();
			
		}	
	}
	void setFlagWidget(int clickedButtonIndex)
	{
		
		for(int i = 0; i<widgetArray.size();i++)
		{
			if(widgetArray.get(i) instanceof FlagWidget)
			{
				((FlagWidget) widgetArray.get(i)).untickSkin();
			}
			
		}
		((FlagWidget)widgetArray.get(clickedButtonIndex)).tickSkin();
	}
	
	void printImages()
	{
		
	}
	
	void changeText(int i) {
		currentLanguage = i;
		
	}
	void changeScreen(int inputScreen)
	{
		currentScreen = (Screen) screenList.get(inputScreen);
	}
	public void selectYear(int i)
	{
		((SubScreen) currentScreen).changeSkin(i);
		
	}
	void setSubWidget(int i)
	{
		((SubScreen)currentScreen).changeSkin(i);
	}
	void printText()
	{
		for(int i = 0; i<description[currentLanguage].length;i++)
		{
			 app.text(description[currentLanguage][i], 240, 200 + (36 * i));
		}
	}
	

}
