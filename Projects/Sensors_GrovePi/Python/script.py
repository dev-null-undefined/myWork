import time
import pymysql
from grovepi import *
from grove_rgb_lcd import *


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
    temp, hum = dht(7, 0)
    lux = analogRead(0)
    noise = analogRead(1)
    avgT += temp
    avgH += hum
    avgLux += lux
    avgNoise += noise
    loop += 1
    if loop == 60:
        insertVariblesIntoTable(connection, str("{0:.2f}".format(avgH/60.0)), str("{0:.2f}".format(
            avgT/60.0)), str("{0:.2f}".format(avgLux/60.0)), str("{0:.2f}".format(avgNoise/60.0)))
        loop = 0
        avgT = 0
        avgH = 0
        avgLux = 0
        avgNoise = 0
    if digitalRead(3) == 1:
        show = not show
    if show:
        setRGB(10, 40, 5)
        setText("T="+str(temp)+"C, H="+str(hum)+"%Lux="+str(lux) +
                ", S="+str(noise)+"db"+str(loop))  # 15 char na radek
    else:
        setRGB(0, 0, 0)
        setText("")  # 15 char na radek
    time.sleep(1)
