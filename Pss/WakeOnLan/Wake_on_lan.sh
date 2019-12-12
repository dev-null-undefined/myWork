#!/bin/bash
mac_address=$0
mac_address=$(echo $mac_address | sed 's/://g')
broadcast="255.255.255.255"
port=9
magic_packet=$(
  printf 'f%.0s' {1..12}
  printf "$mac_address%.0s" {1..16}
)
magic_packet=$(
  echo $magic_packet | sed -e 's/../\\x&/g'
)
echo -e $magic_packet | nc -w1 -b -u $broadcast $port
echo $magic_packet
