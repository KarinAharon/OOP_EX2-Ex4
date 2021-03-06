package File_format;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;

	/**
	 * This class read the information from the data base and calculate the average of the points of all students.
	 * @author Mor Danino and Karin Aharon
	 *
	 */
	public class MySql {
		
		public static void main(String[] args)
		{
			String jdbcUrl="jdbc:mysql://ariel-oop.xyz:3306/oop?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";
			String jdbcUser="student";
			String jdbcPassword="student";
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = 
						DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
				
				
				Statement statement = connection.createStatement();
				
				//select data
				String allCustomersQuery = "SELECT * FROM logs;";
				ResultSet resultSet = statement.executeQuery(allCustomersQuery);
				
				double our_sum = 0;
				double sum = 0;
				int i = 0;
				
				// summing up the all points that accumulated
				while(resultSet.next())
				{
					if((resultSet.getInt("FirstID")==205443583) && (resultSet.getInt("SecondID")==312502537)) {
						our_sum = our_sum + resultSet.getDouble("Point");
						i++;
					}
					
					else {
						//if(resultSet.getf)
						sum = sum + resultSet.getDouble("Point");
						i++;
					}
				}
				
				// calculate the average of the points of all students and print it.
				double average = (sum+our_sum)/i;
				System.out.println("The average is: "+average);
				resultSet.close();		
				statement.close();		
				connection.close();		
			}
			
			catch (SQLException sqle) {
				System.out.println("SQLException: " + sqle.getMessage());
				System.out.println("Vendor Error: " + sqle.getErrorCode());
			}
			
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	}
