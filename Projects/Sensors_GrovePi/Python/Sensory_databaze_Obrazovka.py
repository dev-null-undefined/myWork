import time
import pymysql
from grovepi import *
from grove_rgb_lcd import *
ranger = 2  # digital ultrasonicRead(ranger)
button = 3  # digital digitalRead(button)
greenDiod = 4  # digital digitalWrite(greenDiod,1/0)
blueDiod = 5  # digital digitalWrite(blueDiod,1/0)
relay = 6  # digital digitalWrite(relay,1/0)
humTemp = 7  # digital dht(humTemp, 0)
buzzer = 8  # digital dht(humTemp, 0)

lux = 0  # analog analogRead(lux)
noise = 1  # analog analogRead(noise)
rotary = 2  # analog analogRead(rotary)
# pinMode(5,"OUTPUT")
# analogWrite(5,0-255)


def insertVariblesIntoTable(connection, hum, temp, lux, noise):
    cursor = connection.cursor()
    mySql_insert_query = """INSERT INTO Zaznam (Hum, Temp, Lux, Noise, Time)
                                VALUES (%s, %s, %s, %s, %s) """
    now = time.strftime('%Y-%m-%d %H:%M:%S')
    recordTuple = (hum, temp, lux, noise, now)
    cursor.execute(mySql_insert_query, recordTuple)
    connection.commit()


connection = pymysql.connect(
    host='192.168.0.170', user='Pi', passwd='hesloProPiDoDatabaze', db='Pi', port=5456)
show = True
loop = 0
avgT = 0
avgH = 0
avgLux = 0
avgNoise = 0
while True:
    temp, hum = dht(humTemp, 0)
    lux = analogRead(lux)
    noise = analogRead(noise)
    avgT += temp
    avgH += hum
    avgLux += lux
    avgNoise += noise
    loop += 1
    if loop == 6:
        insertVariblesIntoTable(connection, str("{0:.2f}".format(avgH/6.0)), str("{0:.2f}".format(
            avgT/6.0)), str("{0:.2f}".format(avgLux/6.0)), str("{0:.2f}".format(avgNoise/6.0)))
        loop = 0
        avgT = 0
        avgH = 0
        avgLux = 0
        avgNoise = 0
    if digitalRead(button) == 1:
        show = not show
    if show:
        setRGB(10, 40, 5)
        setText("T="+str(temp)+"C, H="+str(hum)+"%Lux="+str(lux) +
                ", S="+str(noise)+"db"+str(loop))  # 15 char na radek
    else:
        setRGB(0, 0, 0)
        setText("")  # 15 char na radek
    time.sleep(10)
