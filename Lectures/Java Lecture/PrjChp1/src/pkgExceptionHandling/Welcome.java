package pkgExceptionHandling;

import java.sql.SQLException;

public class Welcome {
	public void greeting(String msg) throws SQLException{
		if(msg == null) {
			throw new SQLException();
		}
	else
		{
			System.out.println("The message is: "+msg);
		}
	}


	public static void main(String args[]) {
		Welcome welObj = new Welcome();
		try {
			welObj.greeting(null);
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
