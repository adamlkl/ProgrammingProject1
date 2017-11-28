package firstProcessing;
import processing.core.*;
import java.util.ArrayList;
public class Menu extends Screen
{
	FlagWidget[] flagWidgetArray;
	ArrayList widgetArray,otherImages;
	PImage backGroundImage,englishFlag
	,irishFlag,spanishFlag,croatianFlag,italianFlag;

	String[] englishText,spanishText,irishText,croatianText,italianText,currentText;
	PFont font;
	PApplet app;
	Menu( PImage backGroundImage,ArrayList otherImages,ArrayList widgetArray,ArrayList textList,PApplet app)
	{
		
		super(backGroundImage,otherImages,widgetArray,app);
		this.app = app;
		this.backGroundImage = backGroundImage;
		this.otherImages = otherImages;
		this.widgetArray = widgetArray;
		createFlagWidgetArray(widgetArray);
		englishText = (String[])textList.get(0);
		croatianText = (String[])textList.get(1);
		italianText = (String[])textList.get(2);
		spanishText = (String[])textList.get(3);
		
		currentText = englishText;
		app.fill(0);
		font = app.createFont("Arial",20);
		setFlagWidget(0);
		
		
	}
	public void draw()
	{
	
		app.image(backGroundImage,0,0);
		
		for(int i = 0; i<otherImages.size();i++)
		{
			app.image((PImage)otherImages.get(i),700 + (96 * i),100);
		}
		app.textFont(font);
		drawWidgets();
		displayText();	
	}
	void createFlagWidgetArray(ArrayList widgetArrayList)
	{
		flagWidgetArray = new FlagWidget[4];
		for(int i = 0; i<4;i++)
		{
			flagWidgetArray[i] = (FlagWidget)widgetArrayList.get(i + 3);
		}
	}
	
	void setFlagWidget(int clickedButtonIndex)
	{
		
		for(int i = 0; i<flagWidgetArray.length;i++)
		{
			flagWidgetArray[i].untickSkin();
		}
		flagWidgetArray[clickedButtonIndex].tickSkin();
	}
	void drawWidgets()
	{
		for(int i =0; i<widgetArray.size();i++)
		{
			((Widget) widgetArray.get(i)).drawWidget();
		}
		
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
	    return event;
	  }
	 void displayText()
	 {
		
		 app.textAlign(app.LEFT);
		 for(int i = 0; i<currentText.length;i++)
		 {
			 app.fill(0);
			 app.text(currentText[i], 175, 200 + (36 * i));
			 app.noFill();
			 app.textAlign(app.LEFT);
		 }
	 }
	 void changeText(int index)
	 {
		 switch(index)
		 {
		 case(3):
			 currentText = englishText;
		 break;
		 case(4):
			 currentText = croatianText;
		 break;
		 case(5):
			 currentText = irishText;
		 break;
		 case(6):
			 currentText = italianText;
		 break;
		 case(7):
			 currentText = spanishText;
		 break;
		 
		 }
		 
	 }
	


}
