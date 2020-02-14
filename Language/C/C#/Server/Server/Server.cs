using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using System.Text;

namespace Server
{
    class Server
    {
        public static Dictionary<string, ICommand> commands = new Dictionary<string, ICommand>();
        public static List<Client> clients = new List<Client>();
        public static Socket socket;
        static void fillCommands()
        {
            commands.Add("help", new Help());
        }
        static void Main(string[] args)
        {
            socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            socket.Bind(new IPEndPoint(IPAddress.Parse("127.0.0.1"), 65525));
            
            socket.Listen(1);
            Socket accept = socket.Accept();
            byte[] data = new byte[accept.SendBufferSize];
            int j = accept.Receive(data);
            byte[] adata = new byte[j];
            for (int i = 0; i < j; i++)
            {
                adata[i] = data[i];
            }
            string dat = Encoding.Default.GetString(adata);
            Console.WriteLine(dat);
            byte[] data2 = Encoding.Default.GetBytes("Ahoj");
            accept.Send(data2);
        }
        static void Main()
    }
}
