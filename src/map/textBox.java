import processing.core.*;
public class textBox {
	private PApplet parent;
	private String textField = "";
	private String c = "";
	private int secs = 0;
	boolean search = true;
	boolean exist = false;
	private int x;
	private int y;
	private int xsize;
	private int ysize;


	public textBox(PApplet p,  int textBoxX, int textBoxY, int textBoxSizeX, int textBoxSizeY){
		this.parent = p;
		this.x=textBoxX;
		this.y=textBoxY;
		this.xsize=textBoxSizeX;
		this.ysize=textBoxSizeY;
	}

	public void draw ()
	{
		this.parent.rect(this.x,this.y,this.xsize,this.ysize);
		displayText();
	}

	public void displayText(){
		this.parent.fill(255);
		this.parent.text(textField + c, 210, 75);
		if ( !this.search )
		{
			this.parent.text(textField, 210, 75);
			if (this.parent.millis() - secs >= 500){
				c = c.equals("") ? "|" : "";
				secs = this.parent.millis();
			}	
		}
	}

	public void addText(char character){
		if (character >= 'A' && character <= 'z')
			this.textField += character;
		else if (character == ' ')
		{
			this.textField += character;
		}
		else if (character == '\b' && this.textField.length() != 0)
			this.textField = this.textField.substring(0, textField.length() - 1);
		else if (character == PConstants.ENTER)
		{
			this.search=true;
		}
	}

	public boolean doesExist ( String [] list )
	{
		
		for ( int index = 0; index<list.length; index++ )
		{
			if (this.textField.equalsIgnoreCase(list[index]))
			{
				this.exist = true;
			}
		}
		return this.exist;
	}

	public boolean notCalled(float x, float y)
	{
		return (x>=this.x && x<=this.x+this.xsize&&y>=this.y&&y<=this.y+this.ysize);
	}

	public String getText ()
	{
		return this.textField;
	}

	public String getSymbol()
	{
		return this.c;
	}
	
	public void displayError ()
	{
			this.parent.fill(this.parent.color(213,0,0));
			this.parent.text("Invalid input!", 420,75);
	}
}
