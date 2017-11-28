package firstProcessing;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;;

abstract class Screen 
{
	
	ArrayList otherImages;
	PImage backGroundImage;
	ArrayList widgetArray;
	PApplet app;
	Screen(PImage backGroundImage,ArrayList otherImages,ArrayList widgetArray,PApplet app)
	{
		
		this.backGroundImage = backGroundImage;
		this.widgetArray = widgetArray;
		this.otherImages = otherImages;
		this.app = app;
	}
	abstract void draw();
	
	 abstract int getEvent();
	 
	 abstract void setFlagWidget(int i);
	 
	 abstract void changeText(int i);
	
	 
}
