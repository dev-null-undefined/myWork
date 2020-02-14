using System;
using System.Collections.Generic;
using System.Text;
using System.Net;
using System.Net.Sockets;

namespace Server
{
    interface ICommand
    {
        public void takeControll(System.Net.Sockets.Socket socket, byte[] data);
    }
}
