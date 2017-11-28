package firstProcessing;

import java.sql.*;
import java.util.ArrayList;

//written by Tin Oreskovic

abstract class MySQL {
	
	public static Statement main(){
		Statement stmt = null;
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/project","root","cs1031");  
			//here sonoo is database name, root is username and password  
			stmt=con.createStatement();  
		//ResultSet rs=stmt.executeQuery("select * from data");  
		/**while(rs.next())  
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)
		+ " " + rs.getString(4));  
		con.close(); **/ 
		}
		catch(Exception e){ System.out.println(e);}
		
		return stmt;
	}
	
	public static String[][] searchMethod ( String county, String district,
			String town, String locality, String street, String numName,
			String postCode )
	{
		String[][] list = new String[10][11];
		
		try
		{
			Statement stmt = main();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM data"
					+ " WHERE price > 0 "
					+ (county == ""?"":" AND county LIKE '%" + county.toUpperCase() + "%'")
					+ (district == ""?"":" AND district LIKE '%" + district.toUpperCase() + "%'")
					+ (town == ""?"":" AND town LIKE '%" + town.toUpperCase() + "%'")
					+ (locality == ""?"":" AND locality LIKE '%" + locality.toUpperCase() + "%'")
					+ (street == ""?"":" AND street LIKE '%" + street.toUpperCase() + "%'")
					+ (numName == ""?"":" AND numName LIKE '%" + numName.toUpperCase() + "%'")
					+ (postCode == ""?"":" AND postCode LIKE '%" + postCode.toUpperCase() + "%'")
					+ "GROUP BY price DESC "
					+ "LIMIT 10;");
			int i = 0;
			
			while (rs.next())
			{
				 list[i][0] = rs.getString(11);
				 list[i][1] = rs.getString(10);
				 list[i][2] = rs.getString(9);
				 list[i][3] = rs.getString(8);
				 list[i][4] = rs.getString(7);
				 list[i][5] = rs.getString(6);
				 list[i][6] = rs.getString(3);
				 list[i][7] = Integer.toString(rs.getInt(1));
				 list[i][8] = rs.getString(2);
				 list[i][9] = rs.getString(4);
				 list[i][10] = rs.getString(5);
				 
				 i++;
			}
		}
		catch(Exception e){ System.out.println(e);}
		
		return list;
	}
	
	public static int countOfPropertiesPerYear( String year, String county )
	{
		//Co-written with Daniel Fong
		int count = 0;
		
		if ( county == null )
		{
			county = "";
		}
		
		if ( Integer.parseInt(year) >= 2000 )
		{
			year = Integer.toString(Integer.parseInt(year) - 2000);
		}
		
		else
		{
			year = Integer.toString(Integer.parseInt(year) - 1900);
		}
		
		try
		{
			Statement stmt = main();
			
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM data "
					+ "WHERE SUBSTRING_INDEX(SUBSTRING_INDEX(dateOfSale, ' ', 1)"
					+ ", '/', -1) = " + year 
					+ (county == ""?"":" AND county = '" + county.toUpperCase() + "'") + ";");
			while (rs.next())
			{
				count = rs.getInt(1);
			}
		}
		catch(Exception e){ System.out.println(e);}
		
		return count;
	}
	
	public static int averagePriceOfPropertiesPerYear( String year, String county )
	{
		//Co-written with Daniel Fong
		int sum = 0;
		
		if ( county == null )
		{
			county = "";
		}
		
		if ( Integer.parseInt(year) >= 2000 )
		{
			year = Integer.toString(Integer.parseInt(year) - 2000);
		}
		
		else
		{
			year = Integer.toString(Integer.parseInt(year) - 1900);
		}
		
		try
		{
			Statement stmt = main();
			
			ResultSet rs = stmt.executeQuery("SELECT AVG(price) FROM data "
					+ "WHERE SUBSTRING_INDEX(SUBSTRING_INDEX(dateOfSale, ' ', 1)"
					+ ", '/', -1) = " + year 
					+ (county == ""?"":" AND county = '" + county.toUpperCase() + "'") + ";");
			while (rs.next())
			{
				sum = rs.getInt(1);
			}
		}
		catch(Exception e){ System.out.println(e);}
		
		return sum;
	}
	
	public static ArrayList<String> forMap ( String county )
	{
		ArrayList<String> list = new ArrayList<String>();
		
		try
		{
			int count;
			int maxCount = 0;
			String piece = "";
			Statement stmt = main();
			
			ResultSet rs = stmt.executeQuery("SELECT type,count(*) from data"
					+ " where county = '" + county.toUpperCase() + "' group by type"
							+ " order by count(*) desc"
							+ " limit 1;");
			while (rs.next())
			{
				piece = rs.getString(1);
				maxCount = rs.getInt(2);
				
			}
			
			rs = stmt.executeQuery("SELECT AVG(price), count(*),"
					+ " max(price), min(price) from data"
					+ " where county = '" + county.toUpperCase() + "';");
			while (rs.next())
			{
				list.add(county);
				list.add(Integer.toString(rs.getInt(1)));
				list.add(piece);
				list.add(Integer.toString(rs.getInt(3)));
				list.add(Integer.toString(rs.getInt(4)));
				list.add(Integer.toString(rs.getInt(2)));
			}
		}
		catch(Exception e){ System.out.println(e);}
		
		return list;
	}
	
	public static long averageLocality ( String locality )
	{
		long sum = 0;
		
		try
		{
			Statement stmt = main();
			
			ResultSet rs = stmt.executeQuery("SELECT AVG(price) FROM data "
					+ "WHERE locality = '" + locality.toUpperCase() + "';");
			while (rs.next())
			{
				sum = rs.getInt(1);
			}
		}
		catch(Exception e){ System.out.println(e);}
		
		return sum;
	}
	
	public static long averageDistrict ( String district )
	{
		long sum = 0;
		
		try
		{
			Statement stmt = main();
			
			ResultSet rs = stmt.executeQuery("SELECT AVG(price) FROM data "
					+ "WHERE district = '" + district.toUpperCase() + "';");
			while (rs.next())
			{
				sum = rs.getInt(1);
			}
		}
		catch(Exception e){ System.out.println(e);}
		
		return sum;
	}
	
	public static int averageTown ( String town )
	{
		int sum = 0;
		
		try
		{
			Statement stmt = main();
			
			ResultSet rs = stmt.executeQuery("SELECT AVG(price) FROM data "
					+ "WHERE town = '" + town.toUpperCase() + "';");
			while (rs.next())
			{
				sum = rs.getInt(1);
			}
		}
		catch(Exception e){ System.out.println(e);}
		
		return sum;
	}
	
	public static int priceRangeCount ( int min, int max )
	{
		//Co-written with Daniel Fong
		int count = 0;
		
		try
		{
			Statement stmt = main();
			ResultSet rs;
			
			if (min==1000000) {
				rs = stmt.executeQuery("SELECT COUNT(*) FROM data "
						+ "WHERE price > " + min + ";");
				
			}
			else {
				rs = stmt.executeQuery("SELECT COUNT(*) FROM data "
						+ "WHERE price < " + max + " AND price > " + min + ";");
			}
			
			while (rs.next())
			{
				count = rs.getInt(1);
			}
		}
		catch(Exception e){ System.out.println(e);}
		
		return count;
	}
	

	public static int countType ( String type, String county)
	{
		//Co-written with Daniel Fong
		int count = 0;
		
		try
		{
			Statement stmt = main();
			
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM data "
					+ "WHERE type = '" + type.toUpperCase() + "'"
							+ " " + (county==""?"":"AND county = '" + 
					county.toUpperCase() + "'") + ";");
			while (rs.next())
			{
				count = rs.getInt(1);
			}
		}
		catch(Exception e){ System.out.println(e);}
		
		return count;
	} 
	
	public static int countTown ( String town )
	{
		//Co-written with Daniel Fong
		int count = 0;
		
		try
		{
			Statement stmt = main();
			
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM data "
					+ "WHERE town = '" + town.toUpperCase() + "';");
			while (rs.next())
			{
				count = rs.getInt(1);
			}
		}
		catch(Exception e){ System.out.println(e);}
		
		return count;
	}
	
	public static int averageType ( String type, String county )
	{
		//Co-written with Daniel Fong
		int sum = 0;
		
		try
		{
			Statement stmt = main();
			
			ResultSet rs = stmt.executeQuery("SELECT AVG(price) FROM data "
					+ "WHERE type = '" + type.toUpperCase() + "'"
							+ " " + (county==""?"":"AND county = '" + 
					county.toUpperCase() + "'") + ";");
			while (rs.next())
			{
				sum = rs.getInt(1);
			}
		}
		catch(Exception e){ System.out.println(e);}
		
		return sum;
	}
	
	public static long averageCounty ( String county )
	{
		//Co-written with Daniel Fong
		long sum = 0;
		
		try
		{
			Statement stmt = main();
			
			ResultSet rs = stmt.executeQuery("SELECT AVG(price) FROM data "
					+ "WHERE county = '" + county.toUpperCase() + "';");
			while (rs.next())
			{
				sum = rs.getInt(1);
			}
		}
		catch(Exception e){ System.out.println(e);}
		
		return sum;
	}
	
	

}
