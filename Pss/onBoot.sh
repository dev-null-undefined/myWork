#!/bin/bash
sleep 15;
sudo openvpn --config /home/pi/OpenVpnCert/Home-Server.ovpn |& tee -a  /home/pi/bootOpenVpn.log &
sleep 60;
ip a;
cd /home/pi/pi-home-surveillance/
echo "Running motion detect"
python3 pi_surveillance.py -c conf.json &
sleep 10
cd /home/pi/sensors
echo "Running motion Sensors"
python3 Sensory_databaze_Obrazovka.py &
sleep 10
ssmtp martinkos007@gmail.com < /home/pi/email