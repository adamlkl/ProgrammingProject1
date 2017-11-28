package firstProcessing;
/* credits to Leong Kai Ler 
 * utilizes external library Unfolding 
 * get gradient colors from palette
 * creates the map screen only takes in the PApplet as the parameter. 
 * read in datas from GeoJson file that stores the shape of each county and csv files that stores the names of each county and their respective values
 * stores the data values of each county in the dataEntry class 
 * color all the counties in the map created using markers from unfolding maps and read from GeoJson file
 * checks if any marker is selected or if the mouse if on the marker and change their stroke color and marker color
 * creates the side screen and textbox for inputs
 * displays data values from dataEntry class when marker is selected 
 * display name of county when the mouse is within the area of marker
 * display where the county is when searched by user 
 */
import java.util.List;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.MapBox;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;
import processing.core.PConstants;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;

public class map1 {
	private UnfoldingMap map;
	private PApplet parent;
	private Location locationOfUK;
	private float maxPanningDistance = 380; // in km
	private List<Marker> ukMarkers;
	private String [] counties;
	private String [] countyList;
	public String [] countyCode;
	private DataEntry [] countyDetails;
	private DataEntry dataToBeDisplayed;
	private int [] colorArray;
	private int [] threshHold;
	private sideScreen myScreen;
	private String name;
	public textBox mytextBox;

	map1(PApplet p)
	{
		this.parent = p;
		this.myScreen = new sideScreen (this.parent);
		this.mytextBox = new textBox (this.parent,200,50,300,50);
		this.colorArray = new int []  {this.parent.color(255,255,204),this.parent.color(255,237,160),this.parent.color(254,217,118),this.parent.color(254,178,76),this.parent.color(253,141,60),this.parent.color(252,78,42),this.parent.color(227,26,28),this.parent.color(73,0,106)};
		this.threshHold = new int [] {100000,200000,300000,400000,500000,600000,700000,1000000};
		this.map = new UnfoldingMap(this.parent,new MapBox.BlankProvider());
		this.map.setBackgroundColor(this.parent.color(187,222,251));
		MapUtils.createDefaultEventDispatcher(parent, map);
		this.locationOfUK = new Location(52.3781f, -7.936f);
		List<Feature> UKMap = GeoJSONReader.loadData(this.parent, "Counties_and_Unitary_Authorities_December_2015_Full_Extent_Boundaries_in_England_and_Wales.json");
		ukMarkers = MapUtils.createSimpleMarkers(UKMap);
		map.addMarkers(ukMarkers);
		this.counties = parent.loadStrings("CountiesName.csv");
		this.countyList = parent.loadStrings("allCounties.csv");
		this.countyDetails = new DataEntry [countyList.length-1];
		getCountyDetails();
		this.countyCode = getAllCounties();
	}

	public void draw()
	{
		createMap();
		this.parent.textAlign(PConstants.LEFT,PConstants.BOTTOM);
		this.parent.textSize(30);
		this.parent.fill(0);
		this.parent.text("Map of England & Wales", ProgrammingProject.SCREEN_WIDTH-370,ProgrammingProject.SCREEN_HEIGHT);
		this.myScreen.draw();
		for ( int index = 0; index<this.colorArray.length; index++ )
		{
			this.parent.rectMode(PConstants.CORNER);
			this.parent.stroke(0);
			this.parent.strokeWeight(2);
			this.parent.fill(this.colorArray[index]);
			this.parent.rect(ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.MAP_X_SIZE+22+63*index,ProgrammingProject.SCREEN_HEIGHT-100,15,15);
			this.parent.fill(0);
			this.parent.textAlign(PConstants.CENTER,PConstants.CENTER);
			this.parent.textSize(10);
			this.parent.text("<£"+this.threshHold[index],ProgrammingProject.SCREEN_WIDTH-ProgrammingProject.MAP_X_SIZE+27+63*index,ProgrammingProject.SCREEN_HEIGHT-75);
		}
		this.myScreen.showInformationBox();
		if(dataToBeDisplayed!=null)
		{
			this.myScreen.showInformation(dataToBeDisplayed);
		}
		this.mytextBox.draw();
		if (!mytextBox.getText().equals("")&&mytextBox.search)
		{
			if ( !mytextBox.doesExist(countyCode))
			{
				mytextBox.displayError();
			}
			else if (mytextBox.doesExist(countyCode))
			{
				showCounty(mytextBox.getText(),mytextBox.search);
			}
		}
	}
	public void createMap()
	{	
		this.map.setZoomRange(6,6);
		this.map.zoomAndPanTo(this.locationOfUK, 6);
		this.map.setPanningRestriction(this.locationOfUK, this.maxPanningDistance); 
		this.map.draw();	
		shadeMap();
		mouseMoved();
		mousePressed();
	}

	public void shadeMap()
	{	
		for ( int index = 0; index<this.ukMarkers.size(); index++)
		{
			Marker marker = ukMarkers.get(index); 
			for (int index2 = 0; index2<countyDetails.length; index2++ )
			{
				if (countyCode[index].equalsIgnoreCase(countyDetails[index2].returnCountyName()))
				{
					int regionColor = 0;
					for ( int count = 0; count<this.threshHold.length; count++ ){
						if (!marker.isSelected())
						{
							if (!countyCode[index].equalsIgnoreCase(this.name))
							{
								if ( count!=0 )
								{
									if (countyDetails[index2].getAveragePrice()>threshHold[count-1]&&countyDetails[index2].getAveragePrice()<=threshHold[count])
									{
										regionColor = count;
									}
								}

								else if ( count==0 )
								{
									if (countyDetails[index2].getAveragePrice()<=threshHold[count])
									{
										regionColor = count;
									}
								}
								marker.setColor(this.colorArray[regionColor]);
							}

							else if ( countyCode[index].equalsIgnoreCase(this.name) )
							{
								marker.setColor(this.parent.color(128, 203, 196));
							}
						}

						else if (marker.isSelected())
						{
							marker.setColor(this.parent.color(239, 83, 80));
							this.dataToBeDisplayed=new DataEntry (countyDetails[index2].returnCountyName(),""+countyDetails[index2].getAveragePrice(),
									""+countyDetails[index2].getLowestPrice(),""+countyDetails[index2].getHighestPrice(),""+countyDetails[index2].getQuantity());
							for( int index3 = 0; index3 < countyCode.length; index3++ )
							{
								if ( countyCode[index3].equalsIgnoreCase(countyCode[index]))
								{
									Marker marker2 = ukMarkers.get(index3);
									marker2.setColor(this.parent.color(183, 28, 28));
								}
							}
						}
					}
				}
			}
		}

	}

	public void getCountyDetails ()
	{
		for ( int index = 1; index<this.countyList.length; index++)
		{
			String split [] = this.countyList[index].split(",");
			this.countyDetails[index-1]=new DataEntry (split[0],split[1],split[2],split[3],split[4]);
		}
	}

	public String [] getAllCounties ()
	{
		String [] county = new String [this.counties.length-1];
		for (int index = 1; index<this.counties.length; index++)
		{
			String split [] = this.counties[index].split(",");
			county[index-1]=split[1];
		}
		return county;
	}

	public void mousePressed ()
	{
		for (int index = 0; index<this.ukMarkers.size();index++)
		{
			Marker marker = this.ukMarkers.get(index);
			if (marker.isInside(this.map, this.parent.mouseX, this.parent.mouseY))
			{
				if (this.parent.mousePressed)
				{
					if (marker.isSelected())
					{
						marker.setSelected(false);
					}

					else if (!marker.isSelected())
					{
						for ( Marker marker2 : this.ukMarkers )
						{
							marker2.setSelected(false);
						}
						marker.setSelected(true);
					}
				}
			}
		}
	}

	public void mouseMoved()
	{
		for (Marker marker : this.map.getMarkers())
		{
			if (marker.isInside(this.map, this.parent.mouseX, this.parent.mouseY))
			{
				if (!marker.isSelected())
				{
					marker.setStrokeColor(this.parent.color(215, 204, 200));
					marker.setStrokeWeight(3);
				}
			}

			else 
			{
				marker.setStrokeColor(this.parent.color(139,69,19));
				marker.setStrokeWeight(0);;
			}
		}
		
		for ( int index=0; index<this.ukMarkers.size();index++)
		{
			Marker marker = this.ukMarkers.get(index);
			if (marker.isInside(this.map, this.parent.mouseX, this.parent.mouseY))
			{
				if (!marker.isSelected())
				{
					marker.setStrokeColor(this.parent.color(215, 204, 200));
					marker.setStrokeWeight(3);
				}
				showStat(index);
			}

			else 
			{
				marker.setStrokeColor(this.parent.color(139,69,19));
				marker.setStrokeWeight(0);
			}	
		}
	}

	public void showCounty(String input, boolean status)
	{
		if ( input!= null && status )
		{
			this.name = input;
		}
	}
	
	public void showStat (int index)
	{
		this.parent.strokeWeight(3);
		this.parent.stroke(this.parent.color(100,255,218));
		this.parent.fill(this.parent.color(0,150,136));
		this.parent.rect(900, 80, 200,40);
		this.parent.textAlign(PConstants.LEFT,PConstants.CENTER);
		this.parent.textSize(12);
		this.parent.fill(0);
		this.parent.text(""+this.countyCode[index], 920, 100);
		
	}
}

class DataEntry {
	private String countyName;
	private int averagePrice;
	private int highestPrice;
	private int lowestPrice;
	private int quantity;
	private float q;
	private float p;
	private float r;

	DataEntry(String name, String price, String minPrice, String maxPrice, String quantity)
	{
		this.countyName = name;
		this.q = Float.parseFloat(price);
		this.averagePrice = (int)this.q;
		this.p=Float.parseFloat(minPrice);
		this.lowestPrice=(int)this.p;
		this.r=Float.parseFloat(maxPrice);
		this.highestPrice=(int)this.r;
		this.quantity=Integer.parseInt(quantity);
	}

	public String returnCountyName()
	{
		return this.countyName;
	}

	public int getAveragePrice()
	{
		return this.averagePrice;
	}

	public int getLowestPrice()
	{
		return this.lowestPrice;
	}

	public int getHighestPrice()
	{
		return this.highestPrice;
	}

	public int getQuantity ()
	{
		return this.quantity;
	}
}