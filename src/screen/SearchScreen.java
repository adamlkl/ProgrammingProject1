package firstProcessing;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class SearchScreen extends Screen {
	PImage backGroundImage;
	ArrayList otherImages;
	ArrayList widgetArray;
	ArrayList textList;
	PApplet app;
	PFont font;
	int currentLanguage = 0;
	ArrayList outputList;
	String displayString;
	String[] outputString;
	YearWidget[] propertyWidgetArray;
	String[][] currentData;
	String[] infoBoxStringArray;
	SearchScreen(PImage backGroundImage, ArrayList otherImages, ArrayList widgetArray, PApplet app) {
		super(backGroundImage, otherImages, widgetArray, app);
		this.backGroundImage = backGroundImage;
		this.otherImages = otherImages;
		this.widgetArray = widgetArray;
		this.app = app;
		font = app.createFont("Arial",16);
		outputList = new ArrayList<String>();
		outputString = new String[]{"","","","","","",""};
		propertyWidgetArray = new YearWidget[11];
		infoBoxStringArray = new String[8];
		
	}
	void draw() {
		app.image(backGroundImage, 0, 0);
		if(displayString != null)
		{
			app.text(displayString, 200, 500);
		}
		drawInfoBox();
		drawReturnBox();
		drawWidgets();
		drawYearWidgets();
	}
	void drawWidgets()
	{
		for(int i =0; i<widgetArray.size();i++)
		{
			((Widget) widgetArray.get(i)).drawWidget();
		}
		
	}

	
	int getEvent() {
		 int event = -1;
		    for(int i = 0; i <widgetArray.size(); i++)
		    {
		    	event = ((Widget) widgetArray.get(i)).getEvent(app.mouseX,app.mouseY);
		    	if(event != 0)
		    	{
		    		return event;
		    	}   
		    }
		    for(int i = 0;i<propertyWidgetArray.length;i++)
		    {
		    	if(propertyWidgetArray[i] != null)
		    	{
		    		event = propertyWidgetArray[i].getEvent(app.mouseX, app.mouseY);
		    		if(event != 0)
			    	{
			    		return event;
			    	}  
		    	}
		    	
		    }
		    return event;
	}
	void drawReturnBox()
	{
		app.fill(255);
		app.stroke(0);
		app.rect(260, 130, 540, 260);
	}
	void addToSearches(String inputString,int r)
	{
		displayString = new String("");
		outputString[r] = inputString;
		for(int i =0;i<outputString.length;i++)
		{
			displayString = displayString + outputString[i] 
			+((i != outputString.length - 1)?((outputString[i].equals(""))?"":", "):"");
			
		}
		outputString[r] = inputString;
	}
	void drawInfoBox()
	{
		
		app.fill(255);
		app.stroke(0);
		app.rect(800, 130, 300, 290);
		app.fill(0);
		app.textAlign(app.LEFT);
		app.textFont(font);
		app.text("Price: " + infoBoxStringArray[0], 810, 155);
		app.text("Date: "+ infoBoxStringArray[1], 810, 180);
		app.text("Type: " + infoBoxStringArray[2], 810, 205);
		app.text("Old/New: " + infoBoxStringArray[3], 810, 230);
		app.text("County: " + infoBoxStringArray[4], 810, 255);
		app.text("District: " + infoBoxStringArray[5], 810, 280);
		app.text("Locality: " + infoBoxStringArray[6], 810, 305);
		app.text("PostCode: " + infoBoxStringArray[7], 810, 330);
	}
	void createReturnedProperties(String[][] inputProperties)
	{
		//creates array of widgets to display on the returned properties
		currentData = inputProperties;
		for(int j = 0;j<inputProperties.length;j++)
		{
			if(inputProperties[j] != null)
			{
				String displayString = new String("");
				int[] numbers = {2,4,5};
				for(int i:numbers )
				{
					displayString = displayString +inputProperties[j][i] + ((i != 5 )?" : ": "");
				}
				propertyWidgetArray[j] = new YearWidget(260,130 + (j * 26),null,60 +j,app,displayString);
				propertyWidgetArray[j].setWidth(540);
				propertyWidgetArray[j].setHeight(26);
			}
				
		}
		
	}
	String[] getSearches()
	{
		return outputString;
	}
	
	
	void setFlagWidget(int i) {
			
	}
	void drawYearWidgets()
	{
		for(int i =0;i<propertyWidgetArray.length;i++)
		{
			if(propertyWidgetArray[i] != null)
			{
				propertyWidgetArray[i].drawWidget();
			}
			
		}
	}
	void updateInfoBox(int index)
	{
		infoBoxStringArray[0] = currentData[index][7];
		infoBoxStringArray[1] = currentData[index][8];
		infoBoxStringArray[2] = currentData[index][9];
		infoBoxStringArray[3] = currentData[index][10];
		infoBoxStringArray[4] = currentData[index][0];
		infoBoxStringArray[5] = currentData[index][1];
		infoBoxStringArray[6] = currentData[index][3];
		infoBoxStringArray[7] = currentData[index][6];
	}
	void changeText(int i) {
		
		
	}

}
