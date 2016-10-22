package olap4j;


import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import org.olap4j.CellSet;
import org.olap4j.OlapConnection;
import org.olap4j.layout.RectangularCellSetFormatter;

public class QuerySqlserver {

	public static void main(String[] args) throws Exception {
//		SELECT
//		NON EMPTY {Hierarchize({[deliveryOrder].[DeliveryNumber].Members})} PROPERTIES [deliveryOrder].[DeliveryNumber].[Carrier Name] ON ROWS,
//		NON EMPTY {Hierarchize({[Measures].[Total DDL]})} ON COLUMNS
//		FROM [CDD]
		final String query = ""
//				+ "WITH"
//				+ " MEMBER [Measures].[Status %] as"
//				+ "  ([Measures].[Count])/([Measures].[Count],[All Status]),"
//				+ " FORMAT_STRING = \"#.00%\""
				//+ "SELECT"
				//+ " {[Measures].[Count]} ON COLUMNS,"
//				+ " {[ReasonCategory].[L1].[Gross Make],[ReasonCategory].[L1].[Early],"
//				+ "[ReasonCategory].[L1].[Detractor].children,"
//				+ "[ReasonCategory].[Net Make D],[ReasonCategory].[Net Failed D]} ON COLUMNS,"
				//+ " {[Measures].[Make],[Measures].[Make %],[Measures].[Fail],[Measures].[Fail %],[Measures].[DO Total]} ON COLUMNS,"
				//+ " {[ReasonCategory].Members} ON COLUMNS,"
				//+ " {[ReasonCategory].[Detractor].Members} ON COLUMNS,"
				//+ " {[ReasonCategory].[Category].Members,[ReasonCategory].[Net Make],[ReasonCategory].[Net Failed]} ON COLUMNS,"
				//+ " {,[ReasonCategory].[Detractor].Members,} ON COLUMNS,"
				//+ " {[Measures].[Count],[Measures].[Status %]} ON COLUMNS,"
				//+ " {[Measures].[Make],[Measures].[Fail],[Measures].[Make %],[Measures].[Fail %]} ON COLUMNS,"
				//+ " NON EMPTY"
				//+ " {[Carrier].Members} ON ROWS"
				//+ " {[ShipToCountry].Members} ON ROWS"
				//+ " {[CDD].Members} ON ROWS"
				//+ " {[CDD].[Year].Members} ON ROWS"
				//+ " {[CDD.Financial].Members} ON ROWS"
				//+ " {[POD].Members} ON ROWS"
				//+ " {[POD.Financial].Members} ON ROWS"
				//+ " {[MOT].Members} ON ROWS"
				//+ " [deliveryOrder].[DeliveryNumber].Members DIMENSION PROPERTIES [deliveryOrder].[DeliveryNumber].[Carrier Name] ON ROWS"
				//+ " {[ShippingPoint].Members} ON ROWS"
				//+ " {[KPI-CDD].Members} ON ROWS"
				//+ " {[IOD_T].[IOD_T].Members} ON ROWS"
				//+ " {[Status].[Status].Members} ON ROWS"
				//+ " {[X1Reason].Members} ON ROWS"
				//+ " {CrossJoin([CDD].[Year].Members,[MOT].Members)} ON ROWS"
				//+ " {[POD].[Year].Members} ON PAGES"
				//+ " {[PKI-IOD].Members} ON ROWS"
				//+ " {[ServiceLevel].Members} ON ROWS"
				//+ " FROM [CDD]"
				+ "SELECT\r\n" + 
				"NON EMPTY {Hierarchize({{[Measures].[Make],"
				+ " [Measures].[Make %],"
				+ " [Measures].[Fail],"
				+ " [Measures].[Fail %],"
				+ " [Measures].[Total DO]}})} ON COLUMNS,\r\n" + 
				"NON EMPTY {Hierarchize({[Status].[Status].Members})} ON ROWS\r\n" + 
				"FROM [IOD-C]\r\n" + 
				"WHERE {[CDD].[2014]}";
		//+ " FROM [KPI]";

		Class.forName("mondrian.olap4j.MondrianOlap4jDriver");
		// String schema="/ws/workspace0/pr/src/Schema.xml;";
		String schema = "/ws/proj/Pentaho-BI/scctbi-saiku/src/main/webapp/WEB-INF/classes/scctbi/Schema-All.xml;";

		final Connection connection = DriverManager
				.getConnection("jdbc:mondrian:Jdbc="
						+ "jdbc:sqlserver://10.99.200.122:1433;"
						+ "jdbc.databaseName=scctbi_tst;"
						+ "JdbcUser=s_SCCT;JdbcPassword=Initial1;Catalog="
						+ schema);

		final OlapConnection olapConnection = connection
				.unwrap(OlapConnection.class);

		final CellSet cellSet = olapConnection.createStatement()
				.executeOlapQuery(query);

		RectangularCellSetFormatter formatter = new RectangularCellSetFormatter(
				false);

		PrintWriter writer = new PrintWriter(System.out);
		formatter.format(cellSet, writer);
		writer.flush();
	}
}
