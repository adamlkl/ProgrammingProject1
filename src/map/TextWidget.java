import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;

class TextWidget extends Widget
{
	 float x,y;
	  int maxlen;
	  String label;
	  PImage widgetImage;
	  int event;
	  PFont widgetFont;
	  PApplet app;
	  boolean isSelected;
	  
	TextWidget(float x, float y, PImage widgetImage, int event, PApplet app,String label,PFont font) 
	{
		super(x, y, widgetImage, event, app);
		this.x = x;
		this.y = y;
		this.widgetImage = widgetImage;
		this.app = app;
		this.label = label;
		this.widgetFont = font;
		this.maxlen = 20;
		this.event = event;
		
	}
	 void append(char s)
	 {
		    if(s==PConstants.BACKSPACE)
		    {
		      if(!label.equals(""))
		       label=label.substring(0,label.length()-1);
		    }
		    else if (label.length() <maxlen)
		      label=label+app.str(s);
		  }
	 
	 String getText()
	 {
		return label;
	 }
	 void clearString()
	 {
		 label = new String("");
	 }
	 void select()
	 {
		 isSelected = true;
	 }
	 void deselect()
	 {
		 isSelected = false;
	 }
	 int giveEvent()
	 {
		 return event;
	 }
	 void drawWidget()
	 {
		 app.image(widgetImage, x, y);
		 app.fill(0);
		 app.textFont(widgetFont);
		 app.text(label,x + 3,y + widgetImage.height - 4);
		 if(isSelected)
		 {
		//	 app.line(x + (label.length() * 10), y + 3, x + (label.length() * 10), y  +widgetImage.height -6);
		 }
		
	 }
	
	 
	  
	  
	  
	  
	
	}
