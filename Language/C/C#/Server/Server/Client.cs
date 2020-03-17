using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Net.Sockets;
using System.Text;
using System.Threading;

namespace Server
{
    class Client
    {

        public static List<string> endCommands = new List<string>();
        private delegate void command(Client c, string data);
        private static Dictionary<string, command> commands = new Dictionary<string, command>();
        private static Random random = new Random();
        public static void initilizate()
        {
            commands.Add("chat", Chat.takeControll);
            endCommands.Add("end");
            endCommands.Add("disconnect");
        }

        private Socket _socket;
        private int _id;
        private string _name;
        private Thread _t;
        private bool _admin;
        public bool dead = false;
        private MySqlConnection _connection;

        public Thread thread
        {
            get => _t;
        }
        public string name
        {
            get => _name;
        }
        public int id
        {
            get => _id;
        }
        public Client(Socket s)
        {
            string connstring = string.Format("Server={0}; Port={1}; database={2}; UID={3}; password={4}", "84.242.120.206", "5456", "CSharpServer", "CSharpUser", "QETY24yuSDgh$U3FSDB1");
            _connection = new MySqlConnection(connstring);
            _connection.Open();
            _socket = s;
            _admin = false;
            _id = random.Next(100000000, 999999999);
            _t = new Thread(Welcome);
            _t.Start();
        }
        void Welcome()
        {
            write("Welcom client " + _id + "\r\n");
            write("Login (1) or Register (2):");
            MySqlCommand cmd = new MySqlCommand();
            string input;
            do
            {
                input = read();
                switch (input)
                {
                    case "1":
                        do
                        {
                            write("What is your name:");
                            input = read();
                            cmd.Parameters.Clear();
                            cmd.CommandText = "SELECT HashPassword,isAdmin,Name from User where User.Name=@Name;";
                            cmd.Parameters.Add("@Name", MySqlDbType.VarChar);
                            cmd.Parameters["@Name"].Value = input;
                            cmd.Connection = _connection;
                            var l = cmd.ExecuteReader();
                            if (l.HasRows)
                            {
                                write("Password:");
                                input = read();
                                l.Read();
                                if (SecurePasswordHasher.Verify(input, l[0].ToString()))
                                {
                                    if((bool)l[1])
                                    {
                                        write("Connected as " + l[2].ToString()+ " you are Admin.\r\n");
                                        _name = l[2].ToString();
                                        _admin = (bool) l[1];
                                        l.Close();
                                        input = endCommands[0];
                                    }
                                    else
                                    {
                                        write("Connected as " + l[2].ToString()+ ".\r\n");
                                        _name = l[2].ToString();
                                        _admin = (bool)l[1];
                                        l.Close();
                                        input = endCommands[0];
                                    }
                                }
                                else
                                {
                                    write("Wrong password.\r\n");
                                    l.Close();
                                }
                            }
                            else
                            {
                                write("This client doesnt exist.\r\n");
                                l.Close();
                            }
                        } while (!endCommands.Contains(input));
                        break;
                    case "2":
                        do
                        {
                            write("What is your name:");
                            input = read();
                            cmd.Parameters.Clear();
                            cmd.CommandText = "SELECT * from User where User.Name=@Name;";
                            cmd.Parameters.Add("@Name", MySqlDbType.VarChar);
                            cmd.Parameters["@Name"].Value = input;
                            cmd.Connection = _connection;
                            var l = cmd.ExecuteReader();
                            if (!l.HasRows)
                            {
                                l.Close();
                                write("Password:");
                                string hasPasswd = SecurePasswordHasher.Hash(read());

                                cmd.Parameters.Clear();
                                cmd.CommandText = string.Format("INSERT INTO User(Name, HashPassword, isAdmin) VALUES(\"{0}\", \"{1}\", FALSE)", input, hasPasswd);
                                if (cmd.ExecuteNonQuery() == 1)
                                {
                                    write("Connected as " + input + ".\r\n");
                                    _name = input;
                                    input = endCommands[0];
                                }
                                else
                                {
                                    write("Some thing went wrong if this repeat contact Admin.\r\n");
                                }
                            }
                            else
                            {
                                write("This client already exist.\r\n");
                                l.Close();
                            }
                        } while (!endCommands.Contains(input));
                        break;
                }
            } while (!endCommands.Contains(input));
            if (_name == null)
            {
                endConnection();
            }
            else
            {
                commandWait();
            }
        }
        void commandWait()
        {
            string data;
            do
            {
                write(_name + ":");
                data = read();
                    if (commands.ContainsKey(data))
                    {
                        commands[data](this, data);
                    }
                    else
                    {
                        write("Not command: " + data + "\r\n");
                    }
            } while (!endCommands.Contains(data));
            endConnection();
        }

        public string read()
        {
            byte[] data = new byte[_socket.SendBufferSize];
            try
            {
                int j = _socket.Receive(data);
                string dat = Encoding.ASCII.GetString(data, 0, j);
                dat=dat.Replace("\r\n", "");
                Console.WriteLine("Client with ID: " + _id + " and name: " + _name + " ,data: " + dat + " ,size: " + j);
                if (j == 0)
                {
                    dead = true;
                    return endCommands[0];
                }
                if (dat=="")
                {
                    return read();
                }
                return dat;
            }
            catch (System.Net.Sockets.SocketException e)
            {
                dead = true;
                return endCommands[0];
            }
        }
        public void write(string data)
        {
            byte[] dat = Encoding.Default.GetBytes(data);
            //Console.WriteLine(data);
            try
            {
                _socket.Send(dat);
            }
            catch (System.Net.Sockets.SocketException e)
            {
                dead = true;
            }
        }
        public void endConnection()
        {
            write("Closing connection ID: " + _id);
            _socket.Close();
            Server.clientClosed(this);
        }
    }
}
