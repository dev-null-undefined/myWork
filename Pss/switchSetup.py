import serial

serialPort = serial.Serial(port = "/dev/ttyUSB0", baudrate=115200, bytesize=8, timeout=2, stopbits=serial.STOPBITS_ONE)
serialPort.write('{!r}'.format("help"))
print(serialPort.readline()) 
