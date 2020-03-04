using System;
using System.Collections.Generic;
using System.Text;

namespace Server
{
    class Chat
    {
        private static bool debug=true;
        private static Random random = new Random();
        private static List<Chat> sessions = new List<Chat>();

        private Client _owner;
        private List<Client> _clients = new List<Client>();
        private int _id;
        private string _passwd;

        public string passwd
        {
            get => _passwd;
        }
        public int id {
            get => _id;
        }
        public static void takeControll(Client c, string data)
        {
            string input;
            do
            {
                do
                {
                    c.write("(1) Connect to session, (2) create session:");
                    input = c.read();
                    Console.WriteLine(input);
                } while (input != "1" && input != "2" && !Client.endCommands.Contains(input));
                Chat t = null;
                switch (input)
                {
                    case "1":
                        do
                        {
                            c.write("What session Id do you want to join (1000-9999):");
                            input = c.read();
                            sessions.ForEach(x => t = x.id.ToString() == input ? x : t);
                            if (t != null&& t.passwd != null)
                            {
                                    c.write("Session locked give me password:");
                                    if (t.passwd != c.read())
                                    {
                                        t = null;
                                        c.write("! Wrong password !\r\n");
                                    }
                            }
                        } while (t == null && !Client.endCommands.Contains(input));
                        if (t != null)
                        {
                            c.write("Connected to chat room with ID: " + t.id + "\r\n");
                            t.connect(c);
                        }
                        else
                        {
                            return;
                        }
                        break;
                    case "2":
                        do
                        {
                            c.write("(1) Unlock session, (2) Locked session:");
                            input = c.read();
                        } while (input != "1" && input != "2" && !Client.endCommands.Contains(input));
                        switch (input)
                        {
                            case "1":
                                t = new Chat(c);
                                c.write("Connected to chat room with ID: " + t.id + "\r\n");
                                sessions.Add(t);
                                t.connect(c);
                                break;
                            case "2":
                                c.write("Zadej heslo zmde:");
                                string passwd = c.read();
                                t = new Chat(c, passwd);
                                c.write("Connected to chat room with ID: " + t.id + "\r\n");
                                sessions.Add(t);
                                t.connect(c);
                                break;
                        }
                        break;
                }
            } while (!Client.endCommands.Contains(input));
        }
        public void connect(Client c)
        {
            _clients.ForEach(x => {
                if (x != c)
                {
                    x.write("\r\n" + c.name + " connected.\r\n" + x.name + ":");
                }
            });
            _clients.Add(c);
            string input;
            do
            {
                c.write(c.name + ":");
                input = c.read();
                if (!Client.endCommands.Contains(input) && !input.StartsWith("/"))
                {
                    _clients.ForEach(x => {
                        if (x != c)
                        {
                            x.write("\r\n"+c.name + ":" + input + "\r\n"+x.name+":");
                        }
                    });
                }
            } while (!Client.endCommands.Contains(input));
            _clients.ForEach(x => {
                if (x != c)
                {
                    x.write("\r\n" + c.name + " disconected.\r\n" + x.name + ":");
                }
            });
            _clients.Remove(c);
            if (_clients.Count == 0)
            {
                sessions.Remove(this);
                if (debug)
                {
                    Console.WriteLine("Session ID: " + _id + " closed.");
                }
            }
        }
        Chat(Client owner)
        {
            _owner = owner;
            bool idSetted = true;
            int id;
            do
            {
                id = random.Next(1000, 9999);
                _clients.ForEach(x =>idSetted = x.id == id ? false : idSetted);
            } while(!idSetted);
            _id = id;
            if (debug)
            {
                Console.WriteLine("Created new chat room ID: " + _id + ", without password");
            }
        }

        Chat(Client owner,string passwd)
        {
            _owner = owner;
            bool idSetted = true;
            int id;
            do
            {
                id = random.Next(1000, 9999);
                _clients.ForEach(x => idSetted = x.id == id ? false : idSetted);
            } while (!idSetted);
            _id = id;
            _passwd = passwd;
            if (debug)
            {
                Console.WriteLine("Created new chat room ID: " + _id + ", password: " + _passwd);
            }
        }
    }
}
