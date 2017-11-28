package firstProcessing;
import processing.core.*;
import processing.data.*;


public class ArrowWidget extends PApplet
{
	
	float xPos,yPos;
	PImage arrowImage;
	int event;
	ArrowWidget(float xPos,float yPos, PImage arrowImage, int event)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.arrowImage = arrowImage;
		this.event = event;
	}
	void drawArrowWidget()
	{
		image(this.arrowImage,xPos,yPos);
	}
	
	
	

}
