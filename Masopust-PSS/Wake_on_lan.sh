#!/usr/bin/env bash
mac_address="20:CF:30:5C:4C:15"
mac_address=$(echo $mac_address | sed 's/://g')
broadcast="255.255.255.255"
port=9

# Magic packets consist of 12*`f` followed by 16 repetitions of the MAC address
magic_packet=$(
  printf 'f%.0s' {1..12}
  printf "$mac_address%.0s" {1..16}
)
# ... and they need to be hex-escaped
magic_packet=$(
  echo $magic_packet | sed -e 's/../\\x&/g'
)

echo -e $magic_packet | sudo nc -w1 -b -u $broadcast $port
