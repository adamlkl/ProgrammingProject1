package firstProcessing;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class MapScreen extends Screen{
	PImage backGroundImage;
	ArrayList otherImages;
	ArrayList widgetArray;
	ArrayList textList;
	String[][] textBox;
	PApplet app;
	PFont font;
	int currentLanguage = 0;
	MapScreen(PImage backGroundImage,ArrayList otherImages,ArrayList widgetArray,ArrayList textList,PApplet app)
	{
		super(backGroundImage, textList, textList, app);
		this.backGroundImage = backGroundImage;
		this.otherImages = otherImages;
		this.widgetArray = widgetArray;
		this.app = app;
		textBox = getBoxStringArray();
		font = app.createFont("Arial",16);
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
	
	void draw() {
		app.image(backGroundImage,0,0);
		
		ProgrammingProject.mapSideScreen.draw();
		ProgrammingProject.map.draw();
		drawWidgets();
	}
	
	void setFlagWidget(int i) {
		
		
	}
	
	void changeText(int i) {
		
		
	}
	void drawInfoBox()
	{
		app.fill(255);
		app.rect(200,200,240,200);
		app.fill(0);
		app.textFont(font);
		for(int i =0;i<textBox[0].length;i++)
		{
			app.text(textBox[currentLanguage][i], 210, 230+ (i*20));
		}
	}
	String[][] getBoxStringArray()
	{
		String[][] boxArray = new String[4][6];
		boxArray[0][0] = "County Name:";
		boxArray[0][1] = "Average Price:";
		boxArray[0][2] = "Popular House Type:";
		boxArray[0][3] = "Quantity of properties:";
		boxArray[0][4] = "Highest Price:";
		boxArray[0][5] = "Lowest Price:";
		
		//croatian
		boxArray[1][0] = "Ime pokrajine:";
		boxArray[1][1] = "Prosjecna cijena:";
		boxArray[1][2] = "Popularni vrsta kuce:";
		boxArray[1][3] = "Broj posjeda:";
		boxArray[1][4] = "Najvisa cijena:";
		boxArray[1][5] = "Najniza cijena:";
		
		//spanish
		boxArray[2][0] = "Condado:";
		boxArray[2][1] = "Precio promedio:";
		boxArray[2][2] = "Typo de vivienda mas popolar:";
		boxArray[2][3] = "Cantidad de viviendas:";
		boxArray[2][4] = "Precio Maximo:";
		boxArray[2][5] = "Precio Minimo:";
		
		//italian
		boxArray[3][0] = "Contea:";
		boxArray[3][1] = "Prezzo medio:";
		boxArray[3][2] = "Tipo di Casa piu popolare:";
		boxArray[3][3] = "Quantita di Case:";
		boxArray[3][4] = "Prezzo Massimo:";
		boxArray[3][5] = "Prezzo Minimo:";
		return boxArray;
	}
	
	 
	

}
