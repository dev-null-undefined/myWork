using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using System.Text;

namespace Server
{
    class Server
    {
        public static List<Socket> inQueue = new List<Client>();
        public static List<Client> clients = new List<Client>();
        public static Socket socket;
        public static int numberOfClientsAtOnce = 5, queueUpdateTime = 10;
        static void Main(string[] args)
        {
            socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            socket.Bind(new IPEndPoint(IPAddress.Parse("127.0.0.1"), 65525));

            socket.Listen(1);
            if (clients.Count >= numberOfClientsAtOnce)
            {
                Socket accept = socket.Accept();
                clients.Add(new Client(accept));
            }
            else
            {
                Socket accept = socket.Accept();
                inQueue.Add(accept);
            }
        }
        static void inQueueChacker()
        {
            for (int i = 0; i < inQueue.Count; i++)
            {
                byte[] data = Encoding.Default.GetBytes("You are in " + (i + 1) + " queue");
                accept.Send(data);
            }
            inQueueChacker();
        }
        public static void clientClosed(Client c)
        {
            Socket t = inQueue[0];
            inQueue.RemoveAt(0);
            
            Client a=new Client(t);
            clients.Remove(c);
            clients.Add(a);
        }
    }
}
