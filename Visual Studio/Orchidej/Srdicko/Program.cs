using System;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;

namespace server
{
    public class TcpServer
    {
        private TcpListener _server;
        private Boolean _isRunning;
        private Dictionary<string, string> _users;
        //pole prikazu, ktere server umi
        private static string[] commands = { "citat", "help", "stop", "konec" , "kvadraticka" };
        private static Random rnd = new Random();

        public TcpServer(int port)
        {
            _server = new TcpListener(IPAddress.Any, port);
            _server.Start();
            _isRunning = true;
            _users = new Dictionary<string, string>();
            using (StreamReader sr = new StreamReader("users.txt"))
            {
                string s;
                while ((s = sr.ReadLine()) != null)
                {
                    string jmeno = s.Split(";")[0];
                    string password = s.Split(";")[1];
                    _users.Add(jmeno,password);
                }
            }
            LoopClients();
        }

        public void LoopClients()
        {
            Console.WriteLine("Server se spustil");
            while (_isRunning)
            {
                // čeká na spojení klienta
                TcpClient newClient = _server.AcceptTcpClient();
                string clientIPAddress = "IP adresa klienta: " + IPAddress.Parse(((IPEndPoint)newClient.Client.RemoteEndPoint).Address.ToString());
                Console.WriteLine(clientIPAddress);
                // klient se spojil
                // vytvoření vlákna pro komunikaci s klientem
                Thread t = new Thread(new ParameterizedThreadStart(HandleClient));
                t.Start(newClient);
            }
        }

        public void HandleClient(object obj)
        {
            // získání klienta z parametru předaného vláknu
            TcpClient client = (TcpClient) obj;
            string clientName=null;
            string clientIPAddress = IPAddress.Parse(((IPEndPoint)client.Client.RemoteEndPoint).Address.ToString()).ToString();
            // vytvoření streamů pro komunikaci
            StreamWriter sWriter = new StreamWriter(client.GetStream(), Encoding.UTF8);
            StreamReader sReader = new StreamReader(client.GetStream(), Encoding.UTF8);

            Boolean bClientConnected = true;
            String sData = null;

            sWriter.Write("(1)-Prihlasit, (2)-Registrace:");
            sWriter.Flush();
            sData = sReader.ReadLine();
            sData = sData.Trim();
            switch (sData)
            {
                case "1":
                    {
                        sWriter.Write("Zadejte Jmeno:");
                        sWriter.Flush();
                        string jmeno = sReader.ReadLine();
                        jmeno = jmeno.Trim();
                        if (_users.ContainsKey(jmeno))
                        { 
                            sWriter.Write("Zadejte heslo:");
                            sWriter.Flush();
                            string heslo = sReader.ReadLine();
                            heslo = heslo.Trim();
                            if (_users[jmeno] == heslo)
                            {
                                sWriter.WriteLine("Connected!");
                                sWriter.Flush();
                                clientName = jmeno;
                            }
                            else
                            {
                                sWriter.WriteLine("Wrong heslo!");
                                sWriter.Flush();
                                bClientConnected = false;
                            }
                        }
                        else
                        {
                            sWriter.WriteLine("Neexistuje Uzivatel!");
                            sWriter.Flush();
                            bClientConnected = false;
                        }
                        break;
                    }
                case "2":
                    {
                        sWriter.Write("Zadejte Jmeno:");
                        sWriter.Flush();
                        string jmeno = sReader.ReadLine();
                        jmeno = jmeno.Trim();
                        if (_users.ContainsKey(jmeno))
                        {
                            sWriter.WriteLine("Uzivatel jix existuje!");
                            sWriter.Flush();
                            bClientConnected = false;
                        }
                        else
                        {
                            sWriter.Write("Zadejte heslo:");
                            sWriter.Flush();
                            string heslo = sReader.ReadLine();
                            heslo = heslo.Trim();
                            _users.Add(jmeno, heslo);
                            sWriter.WriteLine("Connected!");
                            sWriter.Flush();
                            clientName = jmeno;
                            using (StreamWriter sw = new StreamWriter("users.txt", true))
                            {
                                sw.WriteLine(jmeno + ";" + heslo);
                                sw.Flush();
                            }
                        }
                        break;
                    }
            }

            if (bClientConnected)
            {
                using (StreamWriter sw = new StreamWriter("log.txt", true))
                {
                    sw.WriteLine("PRIPOJEN - Uzivatel:" + clientName + ", Time:" + (DateTime.Now).ToString() + ", IP:" + clientIPAddress);
                    sw.Flush();
                }
            }
            while (bClientConnected)
            {
                // reads from stream
                sData = sReader.ReadLine();
                //odseknuti bilych znaku ze zacatku a konce textoveho retezce
                sData = sData.Trim();
                //prevedeni velkych pismen na mala
                sData = sData.ToLower();
                string answer = "";
                switch (sData)
                {
                    case "date":
                        {
                            answer = (DateTime.Now).ToString();
                            break;
                        }
                    case "help":
                        {
                            foreach (var command in commands)
                            {
                                answer += command + ",";
                            }
                            break;
                        }
                    case "citat":
                        {
                            List<string> citaty = new List<string>();
                            try
                            {
                                using (StreamReader str = new StreamReader("citaty.txt"))
                                {
                                    string cit = "";
                                    while ((cit = str.ReadLine()) != null)
                                    {
                                        citaty.Add(cit);
                                    }
                                }
                            }
                            catch (Exception)
                            {
                                Console.WriteLine("Bohuzel nemame soubor citatu");
                            }
                            //a jeste jeden citat, kdyby nahodou nebyl soubor s citaty
                            citaty.Add("Chaos vždy vítězí nad řádem, protože je lépe organizován.");
                            //nahodne vylosovani citatu
                            answer = citaty[rnd.Next(citaty.Count)];
                            break;
                        }
                    case "stop":
                        {
                            bClientConnected = false;
                            answer = "Nashledanou";
                            break;
                        }
                    case "kvadraticka":
                        {
                            float x1, x2;
                            sWriter.WriteLine("Kvadratická rovnice a * x^2 + b * x + c = 0");
                            sWriter.Write("Zadej a: ");
                            sWriter.Flush();
                            float a = float.Parse(sReader.ReadLine());
                            sWriter.Write("Zadej b: ");
                            sWriter.Flush();
                            float b = float.Parse(sReader.ReadLine());
                            sWriter.Write("Zadej c: ");
                            sWriter.Flush();
                            float c = float.Parse(sReader.ReadLine());
                            float d = b * b - 4 * a * c;
                            if (d > 0)
                            {
                                x1 = (-b + (float)Math.Sqrt(d)) / (2 * a);
                                x2 = (-b - (float)Math.Sqrt(d)) / (2 * a);
                                answer = string.Format("Kvadratická rovnice {0} * x^2 + ({1}) * x + ({2}) = 0 má dvě řešení: x1 = {3} a x2 = {4}", a, b, c, x1, x2);
                            }
                            else if (d == 0)
                            {
                                x1 = -b / (2 * a);
                                answer = string.Format("Kvadratická rovnice {0} * x^2 + ({1}) * x + ({2}) = 0 má jedno řešení: x1 = {3}", a, b, c, x1);
                            }
                            else // d < 0
                            {
                                answer = string.Format("Kvadratická rovnice {0} * x^2 + ({1}) * x + ({2}) = 0 nemá řešení v oboru R", a, b, c);
                            }
                            break;
                        }
                    default:
                        {
                            answer = "Neznamy prikaz";
                            break;
                        }
                }
                sWriter.WriteLine(answer);
                sWriter.Flush();
            }
            Console.WriteLine(clientIPAddress);
            if (clientName != null)
            {
                using (StreamWriter sw = new StreamWriter("log.txt", true))
                {
                    sw.WriteLine("ODPOJEN - Uzivatel:" + clientName + ", Time:" + (DateTime.Now).ToString() + ", IP:" + clientIPAddress);
                    sw.Flush();
                }
            }
            else
            {
                using (StreamWriter sw = new StreamWriter("log.txt", true))
                {
                    sw.WriteLine("HACKER POZOR - Time:" + (DateTime.Now).ToString() + ", IP:" + clientIPAddress);
                    sw.Flush();
                }
            }
            client.Close();
        }
    }
}
