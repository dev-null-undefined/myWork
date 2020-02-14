using System;
using System.Collections.Generic;
using System.Net.Sockets;
using System.Text;

namespace Server
{
    class Help : ICommand
    {
        public void takeControll(Socket socket, byte[] data)
        {
            throw new NotImplementedException();
        }
    }
}
