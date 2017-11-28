package firstProcessing;
import processing.core.PApplet;
import processing.core.PImage;
public class FlagWidget extends Widget
{
	PImage currentImage;
	PImage unticked;
	PImage ticked;
	float xPos,yPos;
	int event;
	PApplet app;
	
	
	FlagWidget(float xPos, float yPos, PImage tickedImage,PImage untickedImage,int event,PApplet app)
	{
		
		 super(xPos,yPos,tickedImage,event,app);
		 this.app = app;
	     this.unticked = untickedImage;
	     this.ticked = tickedImage;
	     this.currentImage = unticked;
	     this.xPos = xPos;
	     this.yPos = yPos;
	     this.event = event;
	     
	}
	
	void tickSkin()
	  {
		currentImage = ticked;
	   
	  }
	int getEvent(int mX, int mY)
	{
		
	     if(mX>xPos && mX < xPos+currentImage.width && mY >yPos && mY <yPos+currentImage.height)
	     {
	        return event;
	     }
	     return 0;
	}
    void untickSkin()
	  {
	    currentImage = unticked;
	  }
    void drawWidget()
    {
    	app.image(currentImage,xPos,yPos);
    }
    

}

