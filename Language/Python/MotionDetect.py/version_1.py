import io
import socket
import struct
import time
import picamera
from PIL import Image

client_socket = socket.socket()
client_socket.bind(('192.168.1.7', 8000))
connection = client_socket.makefile('wb')

try:
    # init camera
    camera = picamera.PiCamera()
    camera.resolution = (640, 480)

    # warm up for 2 secound
    camera.start_preview()
    time.sleep(2)
    
    # init values
    start = time.time()
    stream = io.BytesIO()

    # loop throught frames
    for foo in camera.capture_continuous(stream, 'jpeg'):
        print(type(foo))
        # writing frame size to the socket
        connection.write(struct.pack('<L', stream.tell()))
        connection.flush()

        # writing frame data
        stream.seek(0)
        connection.write(stream.read())


        #if time.time() - start > 30:
        #    break

        #stream.seek(0)
        #image = Image.open(stream).convert("RGBA")
        #width, height = image.size 
        #pritn(width)


        # reseting for next loop
        stream.seek(0)
        stream.truncate()


    connection.write(struct.pack('<L', 0))
finally:
    connection.close()
    client_socket.close()