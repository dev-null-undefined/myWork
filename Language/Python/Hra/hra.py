from grovepi import *
from grove_rgb_lcd import *
import _thread
import random

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
doneBlue = False

class bomba(object):
    started = False
    current = 300

class beepSpeed(object):
    speed = 100
    current = 0
    done = False
    
def bombaMinus(bomba):
    while True:
        bomba.current=bomba.current-1
        time.sleep(1)

def beeping(beepSpeed):
    while True:
        if(abs(beepSpeed.speed-410) < 40):
            beepSpeed.done = True
        else:
            beepSpeed.done = False
        if (beepSpeed.speed < 1000) and (beepSpeed.speed > 10) and not beepSpeed.done:
            beepSpeed.current = 1
        time.sleep(abs((beepSpeed.speed-410)/100.0))
        beepSpeed.current = 0
        time.sleep(abs((beepSpeed.speed-410)/100.0))

_thread.start_new_thread(beeping, (beepSpeed,))

_thread.start_new_thread(bombaMinus, (bomba,))

gameOverLoop=0
gameOverText=False
while not done:
    if bomba.current<=0:
        if not gameOverText:
          setText("GAME OVER")
          gameOverText=True
        gameOverLoop+=1
        if gameOverLoop == 5:
            setRGB(random.randint(0,255),random.randint(0,255),random.randint(0,255))
            gameOverLoop=0
        digitalWrite(buzzer, 1)
        digitalWrite(blueDiod, 0)
        time.sleep(0.01)
        digitalWrite(buzzer, 0)
        digitalWrite(blueDiod, 1)

    else:
        if analogRead(lux) < 30:
            showText = True
            setRGB(0,255,0)
            if bomba.current<150:
                setRGB(0, 102, 255)
            if bomba.current<60:
                setRGB(255, 153, 0)
            if bomba.current<30:
                setRGB(255, 0, 0)
        else:
            showText = False
            setRGB(0, 0, 0)

        if beepSpeed.current == 1 and showText:
            digitalWrite(buzzer, 1)
        else:
            digitalWrite(buzzer, 0)

        if doneBlue and showText:
            digitalWrite(blueDiod, 1)
        else:
            digitalWrite(blueDiod, 0)

        # print(str(beepSpeed.speed)+","+str(abs((beepSpeed.speed-410)/200.0))+","+str(abs(beepSpeed.speed-410)<20)+","+str(beepSpeed.done)+","+str(beepSpeed.current))
        temp, hum = dht(humTemp, 0)
        if temp>25.0:
            doneBlue=True
        

        beepSpeed.speed = analogRead(rotary)

        if analogRead(noise) > 1000:
            doneNoiseTest = True

        if showText:
            if bomba.current>0:
                setText("Bomba vybuchne za "+str(bomba.current)+" sekund")
        else:
            setText("")
        
        if doneBlue and beepSpeed.done and showText:
            digitalWrite(greenDiod, 1)
            digitalWrite(blueDiod, 1)
            digitalWrite(buzzer, 0)
            if digitalRead(button):
                done=True
                setText("!YOU WIN! :)    Heslo:hesloje123")
                setRGB(255, 255, 255)