package olap4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.olap4j.OlapConnection;
import org.olap4j.OlapStatement;
import org.saiku.service.util.export.ResultSetHelper;

public class DrillThrough2 {

	public static void printResultSet(ResultSet rs, int limit) throws Exception {

		Integer width = 0;
		Integer height = 0;
		ResultSetHelper rsch = new ResultSetHelper();

		try {
			while (rs.next() && (limit == 0 || height < limit)) {
				if (height == 0) {
					System.out.print(' ');
					ResultSetMetaData rsmd=rs.getMetaData();
					width = rsmd.getColumnCount();
					for (int s = 0; s < width; s++) {
						System.out.print(" |");
						System.out.print(rsmd.getColumnName(s + 1));
					}
					System.out.println();
				}
				System.out.print(height+1);
				for (int i = 0; i < width; i++) {
					int colType = rs.getMetaData().getColumnType(i + 1);
					String content = rsch.getValue(rs, colType, i + 1);
					if (content == null)
						content = "";
					System.out.print(" |");
					System.out.print(content);
				}
				System.out.println();
				height++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) throws Exception {
		final String query = ""
//				+ "DRILLTHROUGH"
//				+ " MAXROWS 200"
//				+ " SELECT ("
//				+ " [Measures].[Sales Count],"
//				+ " [Time.Weekly].[1997].[2].[4]"
//				+ ") ON COLUMNS \r\n" + 
//				"FROM [Sales]\r\n"
//				+ "WHERE [Promotions].[Shelf Emptiers] \r\n" + 
//				" RETURN "
//				+ " [Time.Weekly].[Year],"
//				+ " [Time.Weekly].[Week],"
//				+ " [Time.Weekly].[Day],"
//				+ " [Measures].[Sales Count],"
//				+ " [Measures].[Unit Sales],"
//				+ " [Promotions]"
				+ "DRILLTHROUGH"
				+ " MAXROWS 300"
//				+ " SELECT ([IOD_T].[make], [Status].[BF]) ON COLUMNS \r\n" + 
//				"FROM [IOD-T]\r\n" + 
//				"WHERE {[POD].[2014]}"
				+ " SELECT ([Measures].[Make], [Status].[K]) ON COLUMNS \r\n" + 
				"FROM [IOD-C]\r\n" + 
				"WHERE {[CDD].[2014]}"
				+ " RETURN [EventDeliveryOrder]";

		Class.forName("mondrian.olap4j.MondrianOlap4jDriver");
		//String schema = "/ws/pentaho/biserver-ce/data/foodmart/FoodMart.xml;";
		String schema = "/ws/proj/Pentaho-BI/scctbi-saiku/src/main/webapp/WEB-INF/classes/scctbi/Schema-ALL.xml;";
		final Connection connection = DriverManager
				.getConnection("jdbc:mondrian:Jdbc="
						+ "jdbc:mysql://localhost/"
						//+ "foodmart"
						+ "scctbi_tst"
						+ "?user=root&password=root;"
						+ "Catalog=" + schema);
		
		//mondrian.olap4j.FactoryJdbc41Impl$MondrianOlap4jConnectionJdbc41
		OlapConnection olapConnection = connection
				.unwrap(OlapConnection.class);

		//mondrian.olap4j.FactoryJdbc41Impl$MondrianOlap4jStatementJdbc41
		OlapStatement statement=olapConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);

		printResultSet(resultSet,0);
	}
}
