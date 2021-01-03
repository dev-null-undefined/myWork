# Mods

> ```
> > enable
> # config terminal
> (config)#
> ```

# Labeling

> ### Banner
>
> ```
> (config)# banner motd <msg>
> ```
>
> ### Host name
>
> ```
> (config)# hostname <hostname>
> ```

# Passwords

> ### Console password
>
> ```
> (config)# line console 0
> (config-line)# password <password>
> (config-line)# login
> (config-line)# end
> ```
>
> ### Exec mode
>
> ```
> (config)# enable secret <password>
> ```
>
> ### Vty (ssh,telnet)
>
> ```
> (config)# line vty 0 15
> (config-line)# password <password>
> (config-line)# login
> (config-line)# end
> ```
>
> ### Encrypt
>
> ```
> (config)# service password-encryption
> ```

# Saving

> ```
> # copy running-config startup-config
> ```

# SSH

> ## Before
>
> > ### Host name
> >
> > ```
> > (config)# hostname <hostname>
> > ```
> >
> > ### Domain Name
> >
> > ```
> > (config)# ip domain-name <domain name>
> > ```
> >
> > ### Generate Key
> >
> > ```
> > (config)# crypto key generate rsa
> > ```
> >
> > 1024
>
> ## Activation
>
> ```
> (config)# line vty 0 10
> (config-line)# transport input ssh
> (config-line)# login local
> (config-line)# password <password>
> (config-line)# exit
> (config)# line console 0
> (config-line)# logging synchronous
> (config-line)# login local
> ```
>
> ## Username and password
>
> ```
> (config)# username <userName> password <password>
> ```
>
> ## Verifying
>
> ```
> # sh ip ssh
> ```
>
> Resoult should be something like
>
> ```
> SSH Enabled - version 1.99
> Authentication timeout: 120 secs; Authentication retries: 3
> ```

# Switch

> ## Default gateway
>
> ```
> (config)# ip default-gateway <address>
> ```
>
> ## Interafaces
>
> no shutdown
>
> > ### Info
> >
> > > #### Status of all interfaces
> > >
> > > ```
> > > #show interfaces status
> > > ```
> > >
> > > #### Shows only trunk interfaces
> > >
> > > ```
> > > #show interfaces trunk
> > > ```
> >
> > ### Chosing
> >
> > - Specificky `(config)# int f0/1`
> > - Range `(config)# int r f0/1-24` (`interaface range f0/1-24`)
> >
> > ### Port mode
> >
> > - host (PC) `(config-if)# switchport mode access`
> > - switch/router `(config-if)# switchport mode trunk`
> >
> > ### Set VLAN
> >
> > pouze pro HOST port mode
> > `(config-if)# switchport access vlan <vlan-ID>`

# Router

> ## Interafaces
>
> > no shutdown
> >
> > ### Info
> >
> > > #### IP address of interfaces
> > >
> > > ```
> > > #show ip interface brief
> > > ```
> >
> > ### Chosing
> >
> > - Specificky `(config)# int g0/0/0`
> > - Range `(config)# int r g0/0/0-1` (`interaface range g0/0/0-1`)
> >
> > ### Create subInteraface
> >
> > `(config)# int g0/0/0.<subInterafaceNumber>`
> >
> > ### Delete subInteraface
> >
> > `(config)# no int g0/0/0.<subInterafaceNumber>`
> >
> > ### Set Vlan
> >
> > `(config-subif)# encapsulation dot1Q <vlan-ID>`
> >
> > ### IP address
> >
> > `(config-if)# ip address <address> <subnet>`
> >
> > ### Static Routing
> >
> > `(config)# ip route <network>/<prefix> <routeTo>`

# VLANs

> ## Create
>
> ```
> (config)# vlan <vlan-id>
> ```
>
> ## Delete
>
> ```
> (config)# no vlan <vlan-id>
> ```
>
> ## Name
>
> ```
> (config)# name <vlan-nam>
> ```
>
> ## IP adresa
>
> ```
> (config)# interface vlan <vlan-ID>
> (config-if)# ip address <address> <mask>
> ```

> # Getting mac address
>
> ```
> arp -a <ip-adressa>
> ```
