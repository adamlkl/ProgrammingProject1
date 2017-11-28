package firstProcessing;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class ProgrammingProject extends PApplet{

	public static final int SCREEN_WIDTH = 1152; // 1920
	public static final int SCREEN_HEIGHT = 648; // 1080
	public static final int GRAPH_X_SIZE = 600;
	public static final int GRAPH_Y_SIZE = 600;
	public static final int GRAPH_X_STARTS = SCREEN_WIDTH-GRAPH_X_SIZE;
	public static final int GRAPH_Y_STARTS = SCREEN_HEIGHT-GRAPH_Y_SIZE;
	public static final int EVENT_BUTTON_LEFT_ARROW = 1;
	public static final int EVENT_BUTTON_RIGHT_ARROW = 2;
	public static final int EVENT_BUTTON_ENG = 3;
	 public static final int EVENT_BUTTON_CRO = 4;
	
	public static final int EVENT_BUTTON_ITA= 5;
	public static final int EVENT_BUTTON_SPA = 6;
	//statScreen
	public static final int EVENT_BUTTON_PIE = 3;
	public static final int EVENT_BUTTON_BAR = 4;
	public static final int EVENT_BUTTON_LIN = 5;
	public static final int EVENT_BUTTON_L2000 = 6;
	public static final int EVENT_BUTTON_2000 = 7;
	public static final int EVENT_BUTTON_2001 = 8;
	public static final int EVENT_BUTTON_2002 = 9;
	public static final int EVENT_BUTTON_2003 = 10;
	public static final int EVENT_BUTTON_2004 = 11;
	public static final int EVENT_BUTTON_2005 = 12;
	public static final int EVENT_BUTTON_2006 = 13;
	public static final int EVENT_BUTTON_2007 = 14;
	public static final int EVENT_BUTTON_2008 = 15;
	public static final int EVENT_BUTTON_2009 = 16;
	public static final int EVENT_BUTTON_2010 = 17;
	public static final int EVENT_BUTTON_2011 = 18;
	public static final int EVENT_BUTTON_2012 = 19;
	public static final int EVENT_BUTTON_2013 = 20;
	public static final int EVENT_BUTTON_2014 = 21;
	public static final int EVENT_BUTTON_2015 = 22;
	public static final int EVENT_BUTTON_2016 = 23;
	public static final int EVENT_BUTTON_LIN_PRICE = 24;
	public static final int EVENT_BUTTON_LIN_QTY = 25;
	public static final int EVENT_BUTTON_GENGRAPH = 26;
	public static final int EVENT_BUTTON_PI_LOCATION = 27;
	public static final int EVENT_BUTTON_PI_PRICE = 28;
	public static final int EVENT_BUTTON_PI_LOCATION_TEXT = 29;
	public static final int EVENT_BUTTON_PI_LOWPRICE_TEXT = 30;
	public static final int EVENT_BUTTON_PI_HIGHPRICE_TEXT = 31;
	public static final int EVENT_BUTTON_BAR_QTY = 32;
	public static final int EVENT_BUTTON_BAR_AVPRC = 33;
	public static final int[] EVENT_BUTTONS_BC_MAIN ={34,35,36,37};
	public static final int[] EVENT_BUTTONS_BC_LOCATION ={38,39,40};
	public static final int[] EVENT_BUTTONS_BC_TYPE ={41,42,43};
	public static final int EVENT_BUTTON_BC_LOCATION_TEXT = 44;
	public static final int EVENT_BUTTON_SEARCH_COUNTY = 45;
	public static final int EVENT_BUTTON_SEARCH_DISTRICT = 46;
	public static final int EVENT_BUTTON_SEARCH_TOWN = 47;
	public static final int EVENT_BUTTON_SEARCH_LOCALITY = 48;
	public static final int EVENT_BUTTON_SEARCH_STREET = 49;
	public static final int EVENT_BUTTON_SEARCH_HOUSE_NUM = 50;
	public static final int EVENT_BUTTON_SEARCH_POSTCODE= 51;
	public static final int EVENT_BUTTON_LEFT_ARROW_SEARCH = 52;
	public static final int EVENT_BUTTON_RIGHT_ARROW_SEARCH = 53;
	public static final int EVENT_BUTTON_GENERATE_SEARCH = 54;
	public static final int[] EVENT_BUTTONS_PROPERTY_SEARCH = {60,61,62,63,64,65,66,67,68,69};
	private static final int EVENT_BUTTON_SEARCH_LOCATION_TEXT = 55;
	private static final int EVENT_BUTTON_TO_SEARCH = 56;
	private static final int EVENT_BUTTON_TO_MAP = 57;
	private static final int EVENT_BUTTON_TO_STAT = 58;
	public static final int MAP_X_SIZE = 550;
	

	PImage backGroundImage,statBackGroundImage,leftArrowImage,rightArrowImage,
	tickedImage,untickedImage,croatianFlag,mapBackGroundImage,textButtonImage,searchBackGroundImage;
	PImage QTYticked,QTYunticked,PRCticked,PRCunticked,AVPRCunticked,AVPRCticked,generateGraph,LOCticked,LOCunticked;
	ArrayList menuImages,statImages,mapImages;
	ArrayList statScreenList,pieChartLocationScreenWidgets,pieChartPriceScreenWidgets,pieChartScreenList,barChartScreenList;
	Screen currentScreen;
	Menu menu;
	boolean graphIsDisplayed = false;
	StatScreen statScreen;
	SearchScreen searchScreen;
	SubScreen pieChartScreen,barChartScreen,lineGraphScreen,
	pieChartLocationScreen,pieChartPriceScreen,barChartLocationScreen,barChartTypeScreen,barChartRangeScreen;
	MapScreen mapScreen;
	int graphVariable;
	Widget leftArrowWidget,rightArrowWidget;
	ArrayList menuWidgets,statWidgets,mapWidgets,descrTextList,pieWidgets
	,lineGraphWidgets,barWidgets,barLocationWidgets,barTypeWidgets,barRangeWidgets,searchWidgets;
	PFont textBoxFont;
	TextWidget currentInputBox;
	String[] firstBoxSelections = {"Location","Prop Type","Price Range"};
	String[] secondBoxSelections = {"Average Price","Quantity"};
	String[][] statDescriptions,mapDescriptions;
	String currentGraphLocation;
	static map1 map;
	static sideScreen mapSideScreen;
	PImage toSearchImage,toMapImage,toStatImage;
	
	public static void main(String[] args) {
		PApplet.main("firstProcessing.ProgrammingProject");
	}
	String[] lines ;
	String [] years;
	ArrayList <String> yearQuery;
	//DisplayingGraphs new1;
	Graphs graph;
	
	public void settings()
	{
		
	}
	public void setup()
	{
		size ( SCREEN_WIDTH, SCREEN_HEIGHT );
		loadFiles();
    	createWidgets();
    	createScreens();
    	currentScreen = menu;
    	System.out.println();
    	map = new map1(this);
    	mapSideScreen = new sideScreen(this);
		
		
	}
	public void draw(){
		currentScreen.draw();
		if(graphIsDisplayed)
		{
			graph.draw();
		}
		
	}
	public void loadFiles()
	    {
	    	menuImages = new ArrayList<PImage>();
	    	descrTextList = new ArrayList<String[]>();
	    	textBoxFont = createFont("Arial",18);
	    	this.leftArrowImage = loadImage("backArrow.jpg");
			this.rightArrowImage = loadImage("rightMenuArrow.jpg");
			this.backGroundImage = loadImage("backgroundImage.jpg");
			this.statBackGroundImage = loadImage("statBackGroundImage.jpg");
			this.mapBackGroundImage = loadImage("mapBackGroundImage.jpg");
			this.searchBackGroundImage = loadImage("searchBackGroundImage.jpg");
			this.textButtonImage = loadImage("textBoxImage.jpg");
			menuImages.add((PImage)loadImage("english.jpg"));
			menuImages.add((PImage)loadImage("croatian.jpg"));
			menuImages.add((PImage)loadImage("italian.jpg"));
			menuImages.add((PImage)loadImage("spanish.jpg"));
			this.untickedImage = loadImage("unticked.jpg");
		    this.tickedImage =loadImage("ticked.jpg");
		    descrTextList.add((String[])loadStrings("english.txt"));
		    descrTextList.add((String[])loadStrings("croatian.txt"));
		    descrTextList.add((String[])loadStrings("italian.txt"));
		    descrTextList.add((String[])loadStrings("spanish.txt"));
		    this.toSearchImage =loadImage("toSearchScreenImage.jpg");
		    this.toMapImage = loadImage("toMapScreenImage.jpg");
		    this.toStatImage = loadImage("toStatScreenImage.jpg");
		    
		    this.QTYticked = loadImage("QTYticked.jpg");
		    this.QTYunticked = loadImage("QTYunticked.jpg");
		    this.PRCticked = loadImage("PRCticked.jpg");
		    this.PRCunticked = loadImage("PRCunticked.jpg");
		    this.generateGraph = loadImage("generateGraph.jpg");
		    this.LOCticked = loadImage("LOCticked.jpg");
		    this.LOCunticked = loadImage("LOCunticked.jpg");
		    this.AVPRCunticked = loadImage("AVPRCunticked.jpg");
		    this.AVPRCticked = loadImage("AVPRCticked.jpg");
	    }
    public void statMousePressed()
    {
    	int event = statScreen.getEvent();
    	switch(event)
    	{case(EVENT_BUTTON_LEFT_ARROW):
			currentScreen = menu;
    	graphIsDisplayed = false;
			break;
	
			case(EVENT_BUTTON_PIE):
				currentScreen.setFlagWidget(EVENT_BUTTON_PIE -2 );
				((StatScreen) currentScreen).changeScreen(0);
			break;
			case(EVENT_BUTTON_LIN):
				currentScreen.setFlagWidget(EVENT_BUTTON_LIN-2);
				((StatScreen) currentScreen).changeScreen(1);
				break;
			case(EVENT_BUTTON_BAR):
				currentScreen.setFlagWidget(EVENT_BUTTON_BAR-2);
			
			((StatScreen) currentScreen).changeScreen(2);
				break;
			case(EVENT_BUTTON_GENGRAPH):
				generateGraph();
				break;
			
    	}
    	 
    	lineStatMousePressed(event);   //contains all outputs for lineGraph
    	pieStatMousePressed(event);	//outputs for pie chart
    	barStatMousePressed(event); //outputs for bar chart
    	
    	
    	
    }
    public void createText()
    {
    	statDescriptions = new String[5][];
    	statDescriptions[0] = loadStrings("english.txt");
    	statDescriptions[1] = loadStrings("english.txt");
    	statDescriptions[2] = loadStrings("english.txt");
    	statDescriptions[3] = loadStrings("english.txt");
    	statDescriptions[4] = loadStrings("english.txt");
    	
    	mapDescriptions = new String[5][];
    	mapDescriptions[0] = loadStrings("english.txt");
    }
    public void mapMousePressed()
    {
    	if ( map.mytextBox.notCalled(mouseX, mouseY))
    	{
    		map.mytextBox.search = false;
    	}
    	
    	int event = currentScreen.getEvent();
    	switch(event)
		{
		case(EVENT_BUTTON_LEFT_ARROW):
			currentScreen = menu;
		graphIsDisplayed = false;
			break;
		
		}
    	
    }
    public void barStatMousePressed(int event)
    {
    	//Written by Isaac Walker with help by Daniel Fong 
    	if(event == EVENT_BUTTONS_BC_MAIN[0]) //opens drop box
    	{
    		if(!((DropBox)((SubScreen)((StatScreen)currentScreen).currentScreen).widgetList.get(0)).isTicked())
    		{
    			((DropBox)((SubScreen)((StatScreen)currentScreen).currentScreen).widgetList.get(0)).selectBox();;
    		}
    		else
    		{
    			((DropBox)((SubScreen)((StatScreen)currentScreen).currentScreen).widgetList.get(0)).deselectBox();
    		}
    		
    	}
    	
    	if(event == EVENT_BUTTONS_BC_MAIN[1] && ((DropBox)((SubScreen)((StatScreen)currentScreen).currentScreen).widgetList.get(0)).isTicked())
        {
    		//selects location
        		String newSelection = ((DropBox)((SubScreen)((StatScreen)currentScreen).currentScreen).widgetList.get(0)).selections[0];
        		((DropBox)((SubScreen)((StatScreen)currentScreen).currentScreen).widgetList.get(0)).changeSelection(newSelection);
        		((SubScreen)((StatScreen)currentScreen).currentScreen).changeSubScreen(0);
        		((DropBox)((SubScreen)((StatScreen)currentScreen).currentScreen).widgetList.get(0)).deselectBox();
        		graphVariable = 1;
        }
        else if(event == EVENT_BUTTONS_BC_MAIN[2] && ((DropBox)((SubScreen)((StatScreen)currentScreen).currentScreen).widgetList.get(0)).isTicked() )
        {
        	//selects property type
        		String newSelection = ((DropBox)((SubScreen)((StatScreen)currentScreen).currentScreen).widgetList.get(0)).selections[1];
        		((DropBox)((SubScreen)((StatScreen)currentScreen).currentScreen).widgetList.get(0)).changeSelection(newSelection);
        		((SubScreen)((StatScreen)currentScreen).currentScreen).changeSubScreen(1);
        		((DropBox)((SubScreen)((StatScreen)currentScreen).currentScreen).widgetList.get(0)).deselectBox();
        		graphVariable = 5;
        }
        else if(event == EVENT_BUTTONS_BC_MAIN[3] && ((DropBox)((SubScreen)((StatScreen)currentScreen).currentScreen).widgetList.get(0)).isTicked() )
        {
        	//selects price range
        		String newSelection = ((DropBox)((SubScreen)((StatScreen)currentScreen).currentScreen).widgetList.get(0)).selections[2];
        		((DropBox)((SubScreen)((StatScreen)currentScreen).currentScreen).widgetList.get(0)).changeSelection(newSelection);
        		((SubScreen)((StatScreen)currentScreen).currentScreen).changeSubScreen(2); //no screen
        		((DropBox)((SubScreen)((StatScreen)currentScreen).currentScreen).widgetList.get(0)).deselectBox();
        		graphVariable = 3;
        }
    	
    	

    	if(event == EVENT_BUTTONS_BC_LOCATION[0])//opens location dropBox
    	{
    		if(!((DropBox)((SubScreen)((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).widgetList.get(0)).isTicked())
    		{
    			((DropBox)((SubScreen)((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).widgetList.get(0)).selectBox();;
    		}
    		else
    		{
    			((DropBox)((SubScreen)((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).widgetList.get(0)).deselectBox();
    		}
    	}
    	
    		
    	if(event == EVENT_BUTTONS_BC_LOCATION[1] && ((DropBox)((SubScreen)((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).widgetList.get(0)).isTicked())
        {
        		String newSelection = ((DropBox)((SubScreen)((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).widgetList.get(0)).selections[0];
        		((DropBox)((SubScreen)((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).widgetList.get(0)).changeSelection(newSelection);
        		((DropBox)((SubScreen)((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).widgetList.get(0)).deselectBox();
        		graphVariable = 1;
        }
        else if(event == EVENT_BUTTONS_BC_LOCATION[2] && ((DropBox)((SubScreen)((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).widgetList.get(0)).isTicked())
        {
        		String newSelection = ((DropBox)((SubScreen)((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).widgetList.get(0)).selections[1];
        		((DropBox)((SubScreen)((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).widgetList.get(0)).changeSelection(newSelection);
        		((DropBox)((SubScreen)((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).widgetList.get(0)).deselectBox();
        		graphVariable = 2;
        }
       
    	if(event == EVENT_BUTTONS_BC_TYPE[0])//opens PropType dropBox
    	{
    		if(!((DropBox)((SubScreen)((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).widgetList.get(0)).isTicked())
    		{
    			((DropBox)((SubScreen)((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).widgetList.get(0)).selectBox();;
    		}
    		else
    		{
    			((DropBox)((SubScreen)((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).widgetList.get(0)).deselectBox();
    		}
    	}
        else if(event == EVENT_BUTTONS_BC_TYPE[1] && ((DropBox)((SubScreen)((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).widgetList.get(0)).isTicked())
        {
        		String newSelection = ((DropBox)((SubScreen)((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).widgetList.get(0)).selections[0];
        		((DropBox)((SubScreen)((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).widgetList.get(0)).changeSelection(newSelection);
        		((DropBox)((SubScreen)((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).widgetList.get(0)).deselectBox();
        		graphVariable = 5;
        }
        else if(event == EVENT_BUTTONS_BC_TYPE[2] && ((DropBox)((SubScreen)((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).widgetList.get(0)).isTicked())
        {
        		String newSelection = ((DropBox)((SubScreen)((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).widgetList.get(0)).selections[1];
        		((DropBox)((SubScreen)((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).widgetList.get(0)).changeSelection(newSelection);
        		((DropBox)((SubScreen)((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).widgetList.get(0)).deselectBox();
        		graphVariable = 4;
        }
    	if(event == EVENT_BUTTON_BC_LOCATION_TEXT)
    	{
    		currentInputBox = ((TextWidget)((SubScreen)((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).widgetList.get(1));
    		
    	}
    	
    }
    public void pieStatMousePressed(int event)
    {
    	//Written by Isaac Walker with help by Daniel Fong 
    	switch(event)
    	{
    	case(EVENT_BUTTON_PI_PRICE): //create new location screen
    		((StatScreen)currentScreen).setSubWidget(6);
    		graphVariable = 2;
    		break;
    	case(EVENT_BUTTON_PI_LOCATION):
    		((StatScreen)currentScreen).setSubWidget(7);
    	((SubScreen)((StatScreen)currentScreen).currentScreen).changeSubScreen(0);
    	graphVariable =1;
    		break;
    	case(EVENT_BUTTON_PI_LOCATION_TEXT):
    		currentInputBox = (TextWidget)((SubScreen)((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).widgetList.get(0);
    	   ((TextWidget)((SubScreen)((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).widgetList.get(0)).select();
    	   currentInputBox.clearString();
    		break;
    	

    	}
    }
    public void lineStatMousePressed(int event)
	{
    	//Written by Isaac Walker with help by Daniel Fong 
    	for(int i = EVENT_BUTTON_L2000;i<=EVENT_BUTTON_2016;i++)
    	{
    		if(event == i)
    		{
    			((StatScreen) currentScreen).selectYear(i);
    		}
    		
    	}
    	switch(event)
    	{
    	case(EVENT_BUTTON_LIN_PRICE):
    		((StatScreen)currentScreen).setSubWidget(EVENT_BUTTON_LIN_PRICE);
    		graphVariable =1;
    	    break;
    	case(EVENT_BUTTON_LIN_QTY):
    		((StatScreen)currentScreen).setSubWidget(EVENT_BUTTON_LIN_QTY);
    		graphVariable =2;
    	case(EVENT_BUTTON_SEARCH_LOCATION_TEXT):
    		currentInputBox = (TextWidget)(((SubScreen)((StatScreen)currentScreen).currentScreen).widgetList.get(20));
    		
    		break;
    	
    	}
	}
	public void  mousePressed()
    {
		currentInputBox = null;
    	if(currentScreen == menu)
    	{
    		menuMousePressed();
    	}
    	if(currentScreen == statScreen)
    	{
    		statMousePressed();
    	}
    	if(currentScreen == mapScreen)
    	{
    		mapMousePressed();
    	}
    	if(currentScreen == searchScreen)
    	{
    		searchMousePressed();
    	}
    	
    }
    public void createWidgets()
    {
    	menuWidgets = new ArrayList<Widget>();
    	statWidgets = new ArrayList<Widget>();
    	pieWidgets = new ArrayList<Widget>();
    	barWidgets = new ArrayList<Widget>();
    	mapWidgets = new ArrayList<Widget>();
    	searchWidgets = new ArrayList<Widget>();
    	barLocationWidgets = new ArrayList<Widget>();
    	barTypeWidgets = new ArrayList<Widget>();
    	barRangeWidgets = new ArrayList<Widget>();
    	pieChartLocationScreenWidgets = new ArrayList<Widget>();
    	//pieChartPriceScreenWidgets = new ArrayList<Widget>();
    	lineGraphWidgets = new ArrayList<TextWidget>();
    	
    	leftArrowWidget = new Widget((float)200/1152 * SCREEN_WIDTH,(float)480/648* SCREEN_HEIGHT,leftArrowImage,EVENT_BUTTON_LEFT_ARROW,this);
    	statWidgets.add(leftArrowWidget);
    	
    	
    	menuWidgets.add(new Widget((float)550/1152 * SCREEN_WIDTH,(float)480/648 *  SCREEN_HEIGHT,toSearchImage,EVENT_BUTTON_TO_SEARCH,this));
    	menuWidgets.add(new Widget((float)950/1152 * SCREEN_WIDTH,(float)480/648 *  SCREEN_HEIGHT,toMapImage,EVENT_BUTTON_TO_MAP,this));
    	menuWidgets.add(new Widget((float)750/1152 * SCREEN_WIDTH,(float)480/648 *  SCREEN_HEIGHT,toStatImage,EVENT_BUTTON_TO_STAT,this));
    	mapWidgets.add(new Widget((float)350/1152 * SCREEN_WIDTH,(float)475/648 *  SCREEN_HEIGHT,leftArrowImage,EVENT_BUTTON_LEFT_ARROW,this));
    	
    	for(int i =0; i<4;i++)
    	{
    		menuWidgets.add((FlagWidget)new FlagWidget(((float)720/1152 *SCREEN_WIDTH )+ (96 * i),(float)150/648* SCREEN_HEIGHT,tickedImage,untickedImage,i + 3,this));
    		
    	}
    	for(int i =0; i<3;i++)
    	{
    		statWidgets.add((FlagWidget)new FlagWidget(((float)120/1152 * SCREEN_WIDTH) + (96 * i),(float)80/648 * SCREEN_HEIGHT,tickedImage,untickedImage,i + 3,this));
    		
    	}
    	statWidgets.add(new Widget((float)235 /1152 * SCREEN_WIDTH,435,generateGraph,EVENT_BUTTON_GENGRAPH,this));
    	
    	//pieChartPriceScreenWidgets.add(new TextWidget(130,240,null,EVENT_BUTTON_PI_LOWPRICE_TEXT,this,"Lower",textBoxFont));
    	//pieChartPriceScreenWidgets.add(new TextWidget(250,240,null,EVENT_BUTTON_PI_HIGHPRICE_TEXT,this,"Higher",textBoxFont));
    	pieChartLocationScreenWidgets.add(new TextWidget(150,220,textButtonImage,EVENT_BUTTON_PI_LOCATION_TEXT,this,"location",textBoxFont));
    	lineGraphWidgets.add(new YearWidget(100 + (((6-2)%4)*65),(((6-2)/4) * 35 + 130),null,6,this,"<2000"));
    	for(int i=7;i<=23;i++)
    	{
    		lineGraphWidgets.add(new YearWidget(100 + (((i-2)%4)*65),(((i-2)/4) * 35 +130),null,i,this,Integer.toString(i + 1993)));
    		
    	}
    	lineGraphWidgets.add(new FlagWidget(115,380,QTYunticked,QTYticked,EVENT_BUTTON_LIN_QTY,this));
    	lineGraphWidgets.add(new FlagWidget(250,380,PRCunticked,PRCticked,EVENT_BUTTON_LIN_PRICE,this));
    	pieWidgets.add(new FlagWidget(120,130,LOCunticked,LOCticked, EVENT_BUTTON_PI_LOCATION,this));
        pieWidgets.add(new FlagWidget(250,130,PRCunticked,PRCticked, EVENT_BUTTON_PI_PRICE,this));
    	barWidgets.add(new DropBox(120,130,this,EVENT_BUTTONS_BC_MAIN, firstBoxSelections));
    	barLocationWidgets.add(new DropBox(245,130,this,EVENT_BUTTONS_BC_LOCATION, secondBoxSelections));
    	barLocationWidgets.add(new TextWidget(245,215,textButtonImage,EVENT_BUTTON_BC_LOCATION_TEXT,this,"town",textBoxFont));
    	barTypeWidgets.add(new DropBox(250,130,this,EVENT_BUTTONS_BC_TYPE, secondBoxSelections));
    	barRangeWidgets.add(new DropBox(250,130,this,null, null));
    	
    	searchWidgets.add(new Widget((float)450/1152 * SCREEN_WIDTH,(float)470/648* SCREEN_HEIGHT,leftArrowImage,EVENT_BUTTON_LEFT_ARROW_SEARCH,this));
    	searchWidgets.add(new TextWidget(110,130,textButtonImage,EVENT_BUTTON_SEARCH_COUNTY,this,"County",textBoxFont));
    	searchWidgets.add(new TextWidget(110,180,textButtonImage,EVENT_BUTTON_SEARCH_DISTRICT,this,"District",textBoxFont));
    	searchWidgets.add(new TextWidget(110,230,textButtonImage,EVENT_BUTTON_SEARCH_TOWN,this,"Town",textBoxFont));
    	searchWidgets.add(new TextWidget(110,280,textButtonImage,EVENT_BUTTON_SEARCH_LOCALITY,this,"Locality",textBoxFont));
    	searchWidgets.add(new TextWidget(110,330,textButtonImage,EVENT_BUTTON_SEARCH_STREET,this,"Street",textBoxFont));
    	searchWidgets.add(new TextWidget(110,380,textButtonImage,EVENT_BUTTON_SEARCH_HOUSE_NUM,this,"House number",textBoxFont));
    	searchWidgets.add(new TextWidget(110,430,textButtonImage,EVENT_BUTTON_SEARCH_POSTCODE,this,"Post Code",textBoxFont));
    	searchWidgets.add(new Widget((float)805 /1152 * SCREEN_WIDTH,380,generateGraph,EVENT_BUTTON_GENERATE_SEARCH,this));
    	lineGraphWidgets.add(new TextWidget(100,120,textButtonImage,EVENT_BUTTON_SEARCH_LOCATION_TEXT,this,"location",textBoxFont));
    }
    public void generateGraph() {
    
    //Written by Isaac Walker with help by Daniel Fong 
    	this.graph = new Graphs(this);
    	
    	if(((FlagWidget)((StatScreen)currentScreen).widgetArray.get(1)).currentImage.equals(tickedImage))
    	{
    		//generates pie chart
    		graph.variable=1;
    		graph.graphVariable=graphVariable;
    		ArrayList pieChartSearches =(((SubScreen)((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen)).getSearches();
    		graph.locationList=pieChartSearches;
    		(((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).currentSearches = new ArrayList<YearWidget>();
    		(((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).outputSearchesList = new ArrayList<String>();
    		
    	}
    	else if(((FlagWidget)((StatScreen)currentScreen).widgetArray.get(2)).currentImage.equals(tickedImage))
        {
    		graph.variable=2;
    		ArrayList barChartSearches =(((SubScreen)((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen)).getSearches();
    		graph.graphVariable=graphVariable;
    		System.out.println(barChartSearches);
    		graph.locationList=barChartSearches;
    		(((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).currentSearches = new ArrayList<YearWidget>();
    		(((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).outputSearchesList = new ArrayList<String>();
    		//generates bar chart
    		//graph.locationList=pieChartSearches;
        }
    	else if(((FlagWidget)((StatScreen)currentScreen).widgetArray.get(3)).currentImage.equals(tickedImage))
        {
    		graph.variable=3;
    		graph.graphVariable=graphVariable;
    		graph.county=currentGraphLocation;
    		ArrayList selectedYears = ((((SubScreen)((StatScreen)currentScreen).currentScreen))).getYears();
    		selectedYears.sort(null);
    		graph.years=selectedYears;
        }	//generates line graph
    	graph.initiateGraph();
    	System.out.println(graphVariable + "sgrgdrg");
    	graphIsDisplayed=true;
    }
    public void createScreens()
    {
    	statScreenList = new ArrayList<Screen>();
    	menu = new Menu(this.backGroundImage,this.menuImages,this.menuWidgets,this.descrTextList,this);
    	pieChartScreenList = new ArrayList<SubScreen>();
    	barChartScreenList = new ArrayList<SubScreen>();
    	
    	pieChartLocationScreen = new SubScreen(null,null,pieChartLocationScreenWidgets,this,null);
    	pieChartScreenList.add(pieChartLocationScreen);
    	
    	pieChartScreen = new SubScreen(null,null,pieWidgets,this,pieChartScreenList);
    	
		barChartLocationScreen = new SubScreen(null,null,barLocationWidgets,this,null);
    	barChartScreenList.add(barChartLocationScreen);
    	barChartTypeScreen = new SubScreen(null,null,barTypeWidgets,this,null);
    	barChartScreenList.add(barChartTypeScreen);
		barChartRangeScreen = new SubScreen(null,null,barRangeWidgets,this,null);
		barChartScreenList.add(barChartRangeScreen);
		
		searchScreen = new SearchScreen(searchBackGroundImage,null,this.searchWidgets,this);
    	
    	statScreenList.add(pieChartScreen);
    	lineGraphScreen = new SubScreen(null,null,lineGraphWidgets,this,null);
    	statScreenList.add(lineGraphScreen);
    	barChartScreen = new SubScreen(null,null,barWidgets,this,barChartScreenList);
    	statScreenList.add(barChartScreen);
    	statScreen = new StatScreen(this.statBackGroundImage,statImages,statWidgets,this,statScreenList,null);
    	mapScreen = new MapScreen(this.mapBackGroundImage,null,mapWidgets,null,this);
    	
    	
    }
    public void menuMousePressed()
    {
    	int event = menu.getEvent();
    	
    		switch(event)
    		{
    		case(EVENT_BUTTON_TO_SEARCH):
    			currentScreen = searchScreen;
    		graphIsDisplayed = false;
    			break;
    		case(EVENT_BUTTON_TO_MAP):
    			currentScreen = mapScreen;
    		graphIsDisplayed = false;
    			break;
    		case(EVENT_BUTTON_TO_STAT):
    			currentScreen = statScreen;
    		graphIsDisplayed = false;
    			break;
    		case(EVENT_BUTTON_ENG):
    			currentScreen.setFlagWidget(EVENT_BUTTON_ENG -3);
    			currentScreen.changeText(3);
    			break;
    		case(EVENT_BUTTON_CRO):
    			currentScreen.setFlagWidget(EVENT_BUTTON_CRO -3);
    			currentScreen.changeText(4);
    			break;
    		case(EVENT_BUTTON_ITA):
    			currentScreen.setFlagWidget(EVENT_BUTTON_ITA -3);
    			currentScreen.changeText(6);
    			break;
    		case(EVENT_BUTTON_SPA):
    			currentScreen.setFlagWidget(EVENT_BUTTON_SPA -3);
    			currentScreen.changeText(7);
    			break;
    		
    			
    		}
    }
    public void searchMousePressed()
    {
	   int event = searchScreen.getEvent();
	   switch(event)
	   {
	   case(EVENT_BUTTON_LEFT_ARROW_SEARCH):
		   currentScreen = menu;
		   break;
	   case(EVENT_BUTTON_SEARCH_COUNTY):
		   currentInputBox = (TextWidget) currentScreen.widgetArray.get(1);
	       currentInputBox.clearString();
		   break;
	   case(EVENT_BUTTON_SEARCH_DISTRICT):
		   currentInputBox = (TextWidget) currentScreen.widgetArray.get(2);
	       currentInputBox.clearString();
		   break;
	   case(EVENT_BUTTON_SEARCH_TOWN):
		   currentInputBox = (TextWidget) currentScreen.widgetArray.get(3);
	       currentInputBox.clearString();
		   break;
	   case(EVENT_BUTTON_SEARCH_LOCALITY):
		   currentInputBox = (TextWidget) currentScreen.widgetArray.get(4);
	       currentInputBox.clearString();
		   break;
	   case(EVENT_BUTTON_SEARCH_STREET):
		   currentInputBox = (TextWidget) currentScreen.widgetArray.get(5);
           currentInputBox.clearString();
           break;
	   case(EVENT_BUTTON_SEARCH_HOUSE_NUM):
		   currentInputBox = (TextWidget) currentScreen.widgetArray.get(6);
	       currentInputBox.clearString();
		   break;
	   case(EVENT_BUTTON_SEARCH_POSTCODE):
		   currentInputBox = (TextWidget) currentScreen.widgetArray.get(7);
	   	   currentInputBox.clearString();
		   break;
	   case(EVENT_BUTTON_GENERATE_SEARCH):
		   // creates searches;
		   String[] searches = searchScreen.getSearches();
	   		String[][] list = MySQL.searchMethod(searches[0], searches[1], searches[2],
	   				searches[3], searches[4], searches[5], searches[6]);
	   		searchScreen.createReturnedProperties(list);
	   	
	   		
		   break;
	   
	   }
	   System.out.print(event);
	   if ( event >= 60 && event <= EVENT_BUTTONS_PROPERTY_SEARCH[EVENT_BUTTONS_PROPERTY_SEARCH.length-1])
	   {
		   
		   for(int i = 0;i<EVENT_BUTTONS_PROPERTY_SEARCH.length;i++)
		   {
			   //if a property is selected to be viewed
			   
			   if(event == EVENT_BUTTONS_PROPERTY_SEARCH[i])
			   {
				   searchScreen.propertyWidgetArray[i].changeSkin();
				  searchScreen.updateInfoBox(i) ;
			   }
			   else
			   {
				  searchScreen.propertyWidgetArray[i].untick();  
			   }
		   }  
	   }
    }
    public void keyPressed()
    {
    	if ( currentScreen == mapScreen )
    	{
    		if ( !map.mytextBox.search)
    		{
    			map.mytextBox.addText(key);
    		}
    	}
    	else if(currentInputBox != null)
    	{
    		if(keyCode == ENTER)
        	{
    			if((statScreen.currentScreen ==(statScreen.screenList.get(0)) || statScreen.currentScreen ==(statScreen.screenList.get(2)))
    					&& currentScreen != searchScreen )
    			{
    				(((SubScreen)((StatScreen)currentScreen).currentScreen).currentSubScreen).createSearchedBox(currentInputBox.getText());
    			}
    			else if(currentScreen == searchScreen)
    			{
    				System.out.println(currentInputBox.getText());
    				searchScreen.addToSearches(currentInputBox.getText(),(currentInputBox).giveEvent() -EVENT_BUTTON_SEARCH_COUNTY);
    			}
    			else if(statScreen.currentScreen != statScreen.screenList.get(2))
    			{
    				 currentGraphLocation = currentInputBox.getText();
    			}
        		currentInputBox.clearString();
        	}
        	else
        	{
        		currentInputBox.append(key);
        	}
    	}
    	
    	
    }
    
}






