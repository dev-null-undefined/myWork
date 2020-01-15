from grovepi import *
from grove_rgb_lcd import *
import _thread

ranger = 2  # digital ultrasonicRead(ranger)
button = 3  # digital digitalRead(button)
greenDiod = 4  # digital digitalWrite(greenDiod,1/0)
blueDiod = 5  # digital digitalWrite(blueDiod,1/0)
relay = 6  # digital digitalWrite(relay,1/0)
humTemp = 7  # digital dht(humTemp, 0)
buzzer = 8  # digitalWrite(buzzer,1/0)

lux = 0  # analog analogRead(lux)
noise = 1  # analog analogRead(noise)
rotary = 2  # analog analogRead(rotary)
# pinMode(5,"OUTPUT")
# analogWrite(5,0-255)
showText = True

done = False
doneNoiseTest = False


class beepSpeed(object):
    speed = 100
    current = 0
    done = False
    

def beeping(beepSpeed):
    while True:
        if(abs(beepSpeed.speed-410) < 40):
            beepSpeed.done = True
        else:
            beepSpeed.done = False
        if (beepSpeed.speed < 1000) and (beepSpeed.speed > 10) and not beepSpeed.done:
            beepSpeed.current = 1
        time.sleep(abs((beepSpeed.speed-410)/400.0))
        beepSpeed.current = 0
        time.sleep(abs((beepSpeed.speed-410)/400.0))

_thread.start_new_thread(beeping, (beepSpeed,))

while not done:

    if beepSpeed.current == 1:
        digitalWrite(buzzer, 1)
    else:
        digitalWrite(buzzer, 0)

    # print(str(beepSpeed.speed)+","+str(abs((beepSpeed.speed-410)/200.0))+","+str(abs(beepSpeed.speed-410)<20)+","+str(beepSpeed.done)+","+str(beepSpeed.current))

    if analogRead(lux) < 30:
        showText = True
        setRGB(340, 150, 12)
    else:
        showText = False
        setRGB(0, 0, 0)

    beepSpeed.speed = analogRead(rotary)

    if analogRead(noise) > 1000:
        doneNoiseTest = True

    if showText:
        setText(str(doneNoiseTest)+",state="+str(analogRead(noise)/4.0))
    else:
        setText("")
