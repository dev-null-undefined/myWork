using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;

namespace Server
{
    class Server
    {
        public static List<Socket> inQueue = new List<Socket>();
        public static List<Client> clients = new List<Client>();

        public static Socket socket;
        public static int numberOfClientsAtOnce = 3, queueUpdateTime = 5000;

        static void Main(string[] args)
        {
            Thread inQueueChackerThread = new Thread(inQueueChacker);
            socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            socket.Bind(new IPEndPoint(IPAddress.Parse("127.0.0.1"), 5555));
            inQueueChackerThread.Start();
            Client.fillCommands();
            while (true)
            {
                Console.WriteLine("Waiting for client");
                socket.Listen(1);
                if (clients.Count < numberOfClientsAtOnce)
                {
                    Socket accept = socket.Accept();
                    Console.WriteLine("Connected new Client");
                    clients.Add(new Client(accept));
                }
                else
                {
                    Socket accept = socket.Accept();
                    Console.WriteLine("New connection send to Queue");
                    inQueue.Add(accept);
                }
            }
        }
        static void inQueueChacker()
        {
            for (int i = 0; i < inQueue.Count; i++)
            {
                byte[] data = Encoding.Default.GetBytes("You are in " + (i + 1) + " queue\r\n");
                try 
                {
                    inQueue[i].Send(data);
                }
                catch (System.Net.Sockets.SocketException e)
                {
                    inQueue.RemoveAt(i);
                }
            }
            Console.WriteLine("There are " + inQueue.Count + " clients in queue and "+clients.Count+" active clients");
            Thread.Sleep(queueUpdateTime);
            inQueueChacker();
        }
        public static void clientClosed(Client c)
        {
            Console.WriteLine("!Client " + c.name + " ended connection!");
            if (inQueue.Count > 0)
            {
                Socket t = inQueue[0];
                inQueue.RemoveAt(0);
                Client a = new Client(t);
                clients.Add(a);
            }
            clients.Remove(c);
        }
    }
}
