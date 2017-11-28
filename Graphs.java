package firstProcessing;

import java.util.ArrayList;
import processing.core.*;
import java.util.Scanner;

//Written by Daniel Fong

public class Graphs{
	
	int variable;
	int graphVariable;
	String county="";
	PApplet ab;
	BarChart barChart;
	PieChart pieChart;
	LineGraph lineGraph;
	ArrayList<String> locationList;
	ArrayList<String> years;
	
	Graphs(PApplet p) {
		ab = p;
	}
	
	public void initiateGraph() {
		
		switch(variable) {
		case 1:
			pieChart=new PieChart(ab,locationList,graphVariable);
			break;
			
		case 2:
			barChart=new BarChart(ab,locationList,graphVariable);
			break;
		case 3:	
			lineGraph=new LineGraph(ab, years, county, graphVariable);
			break;
		}
		

	
	}
	
	public void draw() {
		
		switch(variable)
		{
		case 1:
		
			pieChart.displayPieChart();
			break;
		case 2:
		
			barChart.displayGraph();
			break;
		case 3:	
			
			lineGraph.displayPoints();
			break;
		}
		

	
	}
	

	
	
	
}
	
	
	