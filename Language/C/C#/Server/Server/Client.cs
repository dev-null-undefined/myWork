using System;
using System.Collections.Generic;
using System.Text;

namespace Server
{
    class Client
    {
        public static Dictionary<string, ICommand> commands = new Dictionary<string, ICommand>();
        private Socket socket;
        private int _id;
        public static Random random=new Random(1011023242);
        Client(Socket s)
        {
            socket = s;
            _id=random.Next(100000,999999);
        }
        void reader()
        {
            byte[] data = new byte[socket.SendBufferSize];
            int j = accept.Receive(data);
            byte[] adata = new byte[j];
            for (int i = 0; i < j; i++){
                adata[i]=data[j];
            }
            string dat = Encoding.Default.GetString(adata);
            Console.WriteLine("Client with ID: "+_id+",data: "+dat);
        }
        void write(string data)
        {
            string dat = Encoding.Default.GetString(data);
            Console.WriteLine(data);
            socket.Send(dat);
        }
        void endConnection(){
            write("Closing connection.");
            Server.clientClosed(this);
        }
    }
}
