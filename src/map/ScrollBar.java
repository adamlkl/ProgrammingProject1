import processing.core.PApplet;
import processing.core.PConstants;

public class ScrollBar {

	private PApplet parent;
	private float x;
	private float y;
	private int sbc;
	private int sbsc;
	private int sbsw;
	private float sx;
	private float sy;
	private float xVector;

	ScrollBar( PApplet p, float xCoordinates, float yCoordinates, int scrollBarColor, int scrollBarStrokeColor,int scrollBarStrokeWeight )
	{
		this.parent=p;
		this.x=xCoordinates;
		this.xVector=25;
		this.y=yCoordinates;
		this.sx=50;
		this.sy=this.y;
		this.sbc=scrollBarColor;
		this.sbsc=scrollBarStrokeColor;
		this.sbsw=scrollBarStrokeWeight;
	}

	void draw()
	{
		this.parent.rectMode(PConstants.CORNER);
		this.parent.stroke(this.sbsc);
		this.parent.strokeWeight(this.sbsw);
		this.parent.fill(this.sbc);
		this.parent.rect(this.x, this.y, 450, 16);
		this.scrollMove();
	}

	int getQuantityRange ()
	{
		return (int) (10000*((this.sx-50)/450));
	}

	void scrollMove()
	{
		this.parent.fill(this.parent.color(96,125,139));
		this.parent.rect(this.sx,this.sy,40,16); 
		if ( this.parent.mousePressed)
		{
			if ( this.parent.mouseY>=this.y && this.parent.mouseY<=this.y+50 && this.parent.mouseX>=this.x && this.parent.mouseX+40<=this.x+450 )
			{
				if (this.sx<this.parent.mouseX)
				{
					if ((this.sx+=this.xVector)>this.x+410)
					{
						this.sx=this.x+410;
					}
				}

				else if (this.sx>this.parent.mouseX)
				{
					this.sx-=this.xVector;
				}
			}
		}
	}
}
