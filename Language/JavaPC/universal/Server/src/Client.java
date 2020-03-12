import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Client {
	public static ArrayList<String> endCommands = new ArrayList<String>();
    private static HashMap<String, Command> commands = new HashMap<String, Command>();
    private static Random random = new Random();
    public static void fillCommands()
    {
        commands.put("chat", Chat.takeControll);
        endCommands.add("end");
        endCommands.add("disconnect");
    }

    private Socket _socket;
    private int _id;
    private string _name;
    private Thread _t;
    public bool dead = false;

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
        _socket = s;
        _id = random.Next(100000000, 999999999);
        _t = new Thread(velcome);
        _t.Start();
    }
    void velcome()
    {
        write("Welcom client " + _id + "\r\n");
        write("What is your name:");
        _name = read();
        commandWait();
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
