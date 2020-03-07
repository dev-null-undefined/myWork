# 1. redo last command but as root
sudo !!
# 2. open an editor to run a command
ctrl+x+e
# 3. create a super fast ram disk
mkdir -p /mnt/ram
mount -t tmpfs tmpfs /mnt/ram -o size=8192M
# 4. don't add command to history (note the leading space)
 ls -l
# 5. fix a really long command that you messed up
fc
# 6. tunnel with ssh (local port 3337 -> remote host's 127.0.0.1 on port 6379)
ssh -L 3337:127.0.0.1:6379 root@emkc.org -N
# 7. quickly create folders
mkdir -p folder/{sub1,sub2}/{sub1,sub2,sub3}
# 8. intercept stdout and log to file
cat file | tee -a log | cat > /dev/null
# bonus: exit terminal but leave all processes running
disown -a && exit
# Print file appending in real time 
tail -f {FileName}
# Raspberry Pi camera stream VLC and save to /home/pi/Videos/video.h264
raspivid -o - -t 0 -hf -w 800 -h 400 -fps 24 | tee /home/pi/Videos/video.h264 | cvlc -vvv stream:///dev/stdin --sout '#standard{access=http,mux=ts,dst=:8160}' :demux=h264
# screen with name
screen -S session_name
# Connect to screen with more users
screen -x youruser/multisession
# h264 to mp4 
ffmpeg -framerate 24 -i Video1.h264 -c copy Video1.mp4
# spotify from terminal
librespot
# Ip tables port forward
iptables -A PREROUTING -t nat -i eth0 -p tcp --dport 80 -j REDIRECT --to-port 8080