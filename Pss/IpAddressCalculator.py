import ipaddress

print("Give me Network:")
inputA=input()
network = ipaddress.ip_network(inputA)

print("Give me IP address:")
inputB=input()
address = ipaddress.ip_address(inputB)

if address in list(network.hosts()):
    print("Ip address is in network")
