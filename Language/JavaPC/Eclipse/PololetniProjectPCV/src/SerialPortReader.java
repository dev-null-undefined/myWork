import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.*;
import java.util.Enumeration;

public class SerialPortReader implements SerialPortEventListener {
	SerialPort serialPort;
	/** The port we're normally going to use. */
	private static final String PORT_NAMES[] = {
			"/dev/ttyUSB0", // Linux
			"COM1", 
			"COM2", 
			"COM3", 
			"COM4",
			"COM5",
			"COM6",
			"COM7",
			"COM8",
			"COM9",
			"COM10",
			"COM11",
			"COM12",
			"COM13",
			"COM14",
			"COM15",
			"COM16",
			"COM17",
			"COM18",
			"COM19",
			"COM20",
			"COM21",
			"COM22",
			"COM23",
			"inputfromuser",
	};
	private BufferedReader input;
	private OutputStream output;
	private static final int TIME_OUT = 2000;
	private static final int DATA_RATE = 9600;

	public void initialize(String port) throws Exception {
		PORT_NAMES[PORT_NAMES.length-1]=port;
		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			System.out.println(currPortId.getName());
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}
			serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);

			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
	}
	public void initialize() throws Exception {
		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			System.out.println(currPortId.getName());
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}
			serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);

			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
	}

	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine = null;
				if (input.ready()) {
					inputLine = input.readLine();
					if(!inputLine.isEmpty()&&inputLine.contains("INSERT INTO Report(Senzor_id,Date_time,HTS221_temp,HTS221_hum,LPS22HB_temp,LPS22HB_press,LSM303AGR_mag_x,LSM303AGR_mag_y,LSM303AGR_mag_z,LSM303AGR_acc_x,LSM303AGR_acc_y,LSM303AGR_acc_z,LS")) {
						Main.saveToMySQL(inputLine, false);
					}
				}

			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
	}
}