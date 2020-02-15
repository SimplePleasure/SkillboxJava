import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingChat {

    public static void main(String[] args) throws Exception {
        Server.StartServer();
    }

    public static class Server {
        static ExecutorService ex;
        public static HashSet<Clients> list = new HashSet<>();

        public static void StartServer() throws Exception {
            ex = Executors.newCachedThreadPool();
            ServerSocket serverSocket = new ServerSocket(0);
            System.out.println("Server port: " + serverSocket.getLocalPort());
            while (true) {

                Socket clientSocket = serverSocket.accept();
                Clients client = new Clients(clientSocket);
                list.add(client);
                ex.execute(client::start);
            }
        }
    }

    static class Clients {
        Socket client;
        String userName;
        BufferedReader in;
        BufferedWriter out;

        public Clients(Socket socket) throws IOException {
            client = socket;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        }

        public void start() {
            try {
                while (true) {
                    if (userName == null) {
                        out.write("Enter your name\n");
                        out.flush();
                        userName = in.readLine();
                        System.err.println(userName + " connected");
                    }

                    String str = in.readLine();
                    if (str == null) break;
                    str = userName + ": " + str;
                    System.out.println(str);
                    for (Clients c : Server.list) {
                        c.sent(str);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            Server.list.remove(this);
            System.err.println(userName + " disconnected");
            try {
                client.close();
                in.close();
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        public void sent(String text) throws IOException {
            out.write(text + "\n");
            out.flush();
        }
    }

    //==================================================================================================================



    public static class Cl1ForChat {
        static ExecutorService ex;
        static Socket socket;

        public static void main(String[] args) {

            try {
                ex = Executors.newFixedThreadPool(2);
                Scanner portScanner = new Scanner(System.in);

                System.out.println("Type port: ");
                socket = new Socket("localhost", portScanner.nextInt());
                ex.execute(Cl1ForChat::read);
                ex.execute(Cl1ForChat::writer);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void read() {
            try (BufferedReader r = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                while (true) {
                    String text = r.readLine();
                    if (text == null) break;
                    System.out.println(text);
                }
            } catch (IOException ignore) {}

            System.out.println("Server shutdown");
            System.exit(0);
        }


        public static void writer() {
            Scanner scanner = new Scanner(System.in);
            try (BufferedWriter w = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
                while (true) {
                    String str = scanner.nextLine();
                    if (str == null) w.close();
                    w.write(str + "\n");
                    w.flush();
                }
            } catch (IOException ignore) {}
        }
    }



}
