import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static Statement state;
	public static boolean debug = false;

	public static boolean saveToMySQL(String args, boolean init) {
		try {
			String input;
			if (init) {
				input = "use martin_skola;";
			} else {
				input = args;// "INSERT INTO Report(Senzor_id,Date_time,JSON)VALUES('1',NOW(),'"+args+"');";
			}
			if (debug) {
				System.out.println("Sending: " + input);
			}
			state.execute(input);
			ResultSet rs = state.getResultSet();
			if (rs != null) {
				ResultSetMetaData rsmd = rs.getMetaData();
				List allRows = new ArrayList();
				while (rs.next()) {
					int numberColumns = rsmd.getColumnCount();
					System.out.print("Resoult:");
					for (int i = 1; i <= numberColumns; i++) {
						System.out.print(rs.getString(i).toString() + ",");
					}
					System.out.println("");
				}
			}
		} catch (Throwable e) {
			System.out.println("Error: " + e.getMessage());
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		if (args.length > 2) {
			if (args[2].equalsIgnoreCase("-debug")) {
				debug = true;
			}
		}
		String username = "martin";
		String password = "Kos";
		String serverIp = "jdbc:mysql://jestrab.kolej.mff.cuni.cz:3306/test?useSSL=false";// 192.168.0.170
		try {
			Connection conector = DriverManager.getConnection(serverIp, username, password);
			Scanner sc = new Scanner(System.in);
			state = (Statement) conector.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		saveToMySQL(null, true);
		SerialPortReader reader = new SerialPortReader();
		if (args.length > 1) {
			reader.initialize(args[0]);// args[0]);
		} else {
			reader.initialize();
		}
		Thread t = new Thread() {
			public void run() {
				try {
					Thread.sleep(1000000);
				} catch (InterruptedException ie) {
				}
			}
		};
		t.start();
		if (debug) {
			System.out.println("Started");
		}
	}
}
