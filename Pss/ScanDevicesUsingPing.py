import platform    # For getting the operating system name
import subprocess  # For executing a shell command
import ipaddress


def ping(host):
    command = ['ping', '-c', '1', host]
    return subprocess.call(command,stdout=subprocess.PIPE) == 0

print("Give me Network in format X.X.X.X/X:")
hosts=[]

for ip in ipaddress.IPv4Network(input()):
    if ping(str(ip)):
        print("YES:"+str(ip))
        hosts.append(ip)
    else:
        print("NO :"+str(ip))

print(hosts)
