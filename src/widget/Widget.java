package firstProcessing;
import processing.core.PApplet;
import processing.core.PImage;;
public class Widget {
	float xPos,yPos;
	PImage widgetImage;
	int event;
	PApplet app;
	Widget(float x, float y, PImage widgetImage,int event,PApplet app)
	{
		this.xPos = x;
		this.yPos = y;
		this.widgetImage =widgetImage;
		this.event = event;
		this.app = app;
	}
	
	public Widget(float x, float y,PApplet app,int[] events,String[] selections) {
		// TODO Auto-generated constructor stub
	}

	void drawWidget()
	{
		app.image(widgetImage,xPos,yPos);
	}
	void drawWidget(int input)
	{
		
	}
	int getEvent(int mX, int mY)
	{
	     if(mX>xPos && mX < xPos+widgetImage.width && mY >yPos && mY <yPos+widgetImage.height)
	     {
	        return event;
	     }
	     return 0;   
	}
	
	

}
