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

while True:
    if digitalRead(button) == 1:
        digitalWrite(relay, 1)
    else:
        digitalWrite(relay, 0)
    analogWrite(blueDiod, int(analogRead(rotary)/4.0))
