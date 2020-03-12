import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class Server {

	public static ArrayList<Socket> inQueue = new ArrayList<Socket>();

	public static ArrayList<Client> clients = new ArrayList<Client>();

	public static ServerSocket socket;

	public static int numberOfClientsAtOnce = 3;

	public static int queueUpdateTime = 5000;

	public static void main(String[] args) {
		Thread inQueueChackerThread = new Thread(inQueueChacker);
		socket = new ServerSocket(5555);
		inQueueChackerThread.Start();
		Client.fillCommands();
		while (true) {
			Console.WriteLine("Waiting for client");
			socket.accept();
			if ((clients.Count < numberOfClientsAtOnce)) {
				Socket accept = socket.Accept();
				Console.WriteLine("Connected new Client");
				clients.Add(new Client(accept));
			} else {
				Socket accept = socket.Accept();
				Console.WriteLine("New connection send to Queue");
				inQueue.Add(accept);
			}

		}

	}

	static void inQueueChacker() {
		for (int i = 0; (i < inQueue.Count); i++) {
			byte[] data = Encoding.Default.GetBytes(("You are in " + ((i + 1) + " queue\r\n")));
			try {
				inQueue[i].Send(data);
			} catch (System.Net.Sockets.SocketException e) {
				inQueue.RemoveAt(i);
			}

		}

		Console.WriteLine(
				("There are " + (inQueue.Count + (" clients in queue and " + (clients.Count + " active clients")))));
		Thread.Sleep(queueUpdateTime);
		Server.inQueueChacker();
	}

	public static void clientClosed(Client c) {
		Console.WriteLine(("!Client " + (c.name + " ended connection!")));
		if ((inQueue.Count > 0)) {
			Socket t = inQueue[0];
			inQueue.RemoveAt(0);
			Client a = new Client(t);
			clients.Add(a);
		}

		clients.Remove(c);
	}
}