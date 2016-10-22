package olap4j;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.olap4j.Cell;
import org.olap4j.CellSet;
import org.olap4j.OlapConnection;
import org.olap4j.OlapStatement;
import org.olap4j.layout.RectangularCellSetFormatter;
import org.saiku.service.util.export.ResultSetHelper;

public class DrillThrough {

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
		final String query = "SELECT\r\n" + 
				"NON EMPTY {Hierarchize({[Measures].[Unit Sales]})} ON COLUMNS,\r\n" + 
				"NON EMPTY {Hierarchize({{[Time.Weekly].[Year].Members}, {[Time.Weekly].[Week].Members}, {[Time.Weekly].[Day].Members}})} ON ROWS\r\n" + 
				"FROM [Sales]"
//				+ "SELECT\r\n"
//				+ "NON EMPTY {Hierarchize({[Measures].[Sales Count]})} ON COLUMNS,\r\n"
//				+ "NON EMPTY {Hierarchize({{[Product].[Product Family].Members},"
//				+ " {[Product].[Product Subcategory].Members}})} ON ROWS\r\n"
//				+ "FROM [Sales 2]"
				+ "";

		Class.forName("mondrian.olap4j.MondrianOlap4jDriver");
		String schema = "/ws/pentaho/biserver-ce/data/foodmart/FoodMart.xml;";
		final Connection connection = DriverManager
				.getConnection("jdbc:mondrian:Jdbc="
						+ "jdbc:mysql://localhost/foodmart?user=root&password=root;"
						+ "Catalog=" + schema);
		
		//mondrian.olap4j.FactoryJdbc41Impl$MondrianOlap4jConnectionJdbc41
		OlapConnection olapConnection = connection
				.unwrap(OlapConnection.class);

		//mondrian.olap4j.FactoryJdbc41Impl$MondrianOlap4jStatementJdbc41
		OlapStatement statement=olapConnection.createStatement();
		final CellSet cellSet = statement.executeOlapQuery(query);

		RectangularCellSetFormatter formatter = new RectangularCellSetFormatter(
				false);

		PrintWriter writer = new PrintWriter(System.out);
		formatter.format(cellSet, writer);
		writer.flush();

		System.out.println();
		System.out.println("--- Drill ---");
		System.out.println();

		List<Integer> coordinates = new ArrayList<Integer>();
		coordinates.add(0);
		coordinates.add(11);
		//coordinates.add(5);
		//mondrian.olap4j.MondrianOlap4jCell
		Cell cell = cellSet.getCell(coordinates);
		
		//MondrianOlap4jCell mCell=(MondrianOlap4jCell)cell;

		ResultSet resultSet = cell.drillThrough();
		//mondrian.rolap.SqlStatement$MyDelegatingInvocationHandler
		//mondrian.rolap.RolapResult
		
		printResultSet(resultSet,0);
	}
}
