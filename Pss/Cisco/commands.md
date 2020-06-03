# Mods
>```
>>enable
>#config terminal
>(config)#
>```
# Oznaceni
>### Banner
>```
>(config)#banner motd <msg> 
>```
>### Host name 
>```
>(config)#hostname <hostname>
>```
# Passwords
>### Console password
>```
>(config)# line console 0
>(config-line)# password <password>
>(config-line)# login
>(config-line)# end
>```
>### Exec mode 
>```
>(config)# enable secret <password>
>```
>### Vty (ssh,telnet)
>```
>(config)# line vty 0 15
>(config-line)# password <password> 
>(config-line)# login 
>(config-line)# end
>```
>### Encrypt
>```
>(config)# service password-encryption
>```
# Ulozeni
>```
>#copy running-config startup-config
>```
# SSH
>## Pred
>>### Host name 
>>```
>>(config)#hostname <hostname>
>>```
>>### Domain Name
>>```
>>(config)#ip <domain-name>
>>```
>>### Generate Key
>>```
>>(config)#crypto key generate rsa
>>```
>## Aktivace
>```
># line vty 0 10
>(config-line)# transport input ssh
>(config-line)# login local
>(config-line)# password <password>
>(config-line)# exit
># line console 0
>(config-line)# logging synchronous
>(config-line)# login local
>```
>## Username and password
>```
>(config)# username <userName> password <password>
>```
>## Overeni
>```
>sh ip ssh
>```
>Vysledek by mel byt neco jako
>```
>SSH Enabled - version 1.99
>Authentication timeout: 120 secs; Authentication retries: 3
>```
# Switch
>## Ip adresa
>```
>(config)#interface vlan <vlan-ID>
>(config-if)#ip address <address> <mask>
>```
>## Default gateway
>```
>(config)#ip default-gateway <address>
>```
# CMD 
>## Zjisteni mac addressy
>```
>arp -a <ip-adressa>
>```