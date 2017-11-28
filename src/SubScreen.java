package firstProcessing;
//created by isaacWalker
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class SubScreen extends Screen {
	ArrayList widgetList;
	ArrayList currentSearches;
	ArrayList screens;
	SubScreen currentSubScreen;
	ArrayList outputSearchesList;
	ArrayList outputYearList;
	
	SubScreen(PImage backGroundImage, ArrayList otherImages, ArrayList widgetArray, PApplet app,ArrayList screens) {
		super(backGroundImage, otherImages, widgetArray, app);
		this.widgetList = widgetArray;
		currentSearches = new ArrayList<YearWidget>();
		changeSkin(widgetList.size()+ 5);
		this.screens = screens;
		if(screens!=null)
		{
			currentSubScreen = (SubScreen)screens.get(0);
		}
		outputSearchesList = new ArrayList<String>();
		outputYearList = new ArrayList<String>();
	}


	void draw() 
	{
		for(int i=0;i<widgetList.size();i++)
		{
			
			((Widget)widgetList.get(i)).drawWidget();
		}
		if(currentSearches != null)
		{
			for(int i = 0;i<currentSearches.size();i++)
			{
				((YearWidget)currentSearches.get(i)).drawWidget();
			}
		}
		if(screens!=null)
		{
			currentSubScreen.draw();
		}
		
	}
	int getEvent() {
		int event = -1;
	    for(int i = 0; i <widgetList.size(); i++){

	    	event = ((Widget) widgetList.get(i)).getEvent(app.mouseX,app.mouseY);
	    	if(event != 0)
	    	{
	    		return event;
	    	}	
	       
	    }
	    if(currentSubScreen != null)
	    {
	    	
	    	event = currentSubScreen.getEvent();
	    }
	    return event;
	}
	void createSearchedBox(String inputString)
	{
		currentSearches.add(new YearWidget
		(((float)90/1152 * ProgrammingProject.SCREEN_WIDTH) + ((currentSearches.size() %3)* (105/(float)1152* ProgrammingProject.SCREEN_WIDTH)),((float)255/648 * ProgrammingProject.SCREEN_HEIGHT) + (((float)30/648 * ProgrammingProject.SCREEN_HEIGHT) *(currentSearches.size() /3)),null,0,app,inputString));
		((YearWidget)(currentSearches.get(currentSearches.size() -1))).width = 90;
		this.outputSearchesList.add(inputString);
		System.out.println(outputSearchesList.size());	
	}

	
	void setFlagWidget(int i) {
		
		
	}

	
	void changeText(int i) {	
	}
	void changeSkin(int i)
	{
		
		if(widgetList.get(i-6) instanceof YearWidget)
		{
			if(((YearWidget)widgetList.get(i-6)).isTicked())
			{
				
				int removed = outputYearList.indexOf(Integer.toString(i + 1993));
				System.out.println(removed);
				outputYearList.remove(removed);
				((YearWidget) widgetList.get(i-6)).changeSkin();
			}
			else
			{
				((YearWidget) widgetList.get(i-6)).changeSkin();
				outputYearList.add(Integer.toString(i + 1993));
				System.out.println("out");
			}
			
		}
		
		if(widgetList.get(i-6) instanceof FlagWidget)
		{
			for(int j = 0; j<widgetArray.size();j++)
			{
				if(widgetArray.get(j) instanceof FlagWidget)
				{
					((FlagWidget) widgetArray.get(j)).untickSkin();
				}
				
			}
			((FlagWidget)widgetArray.get(i -6)).tickSkin();
			
		}
	}
	void changeSubScreen(int i)
	{
		currentSubScreen = (SubScreen)screens.get(i);
	}
	ArrayList getYears()
	{
		return outputYearList;
	}
	ArrayList getSearches()
	{
		
		return outputSearchesList;
	}
	

}
