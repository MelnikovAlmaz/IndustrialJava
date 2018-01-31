import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private String name;
    private String password;
    private BufferedWriter outputStream;
    private BufferedReader userInput;

    public Client(String host, int port) throws IOException {
        userInput = new BufferedReader(new InputStreamReader(System.in));
        socket = new Socket(host, port);
        outputStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),Server.CHARSET));
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    private boolean sign(ClientReader clientReader){
        try {
            System.out.println("Введите имя: ");
            do {
                name = userInput.readLine();
                // Exit if name is empty
                if (name.equals("")) {
                    close();
                    return false;
                }
                System.out.println("Введите пароль: ");
                password = userInput.readLine();

                writeString(Server.OFFER_CREDENTIALS + ':' + name + ':' + password); // check name
                String answer = clientReader.readString();
                System.out.println(answer);
                if (answer.contains(Server.REGISTER_SUCCESSFUL)) {
                    break;
                }
            } while (true);
            return true;
        }
        catch (IOException e){
            return false;
        }
    }

    public void close() {
        if (socket !=null && !socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        close();
    }

    public void writeString(String s) throws IOException {
        outputStream.write(s);
        outputStream.write('\n');
        outputStream.flush();
    }

    public void run() throws IOException {
        final ClientReader clientReader = new ClientReader(socket);
        boolean isSignSuccess = sign(clientReader);
        if (isSignSuccess) {
            new Thread(clientReader, "ClientReader").start();

            String s;
            while (!(s = userInput.readLine()).equals("")) {
                writeString(s);
            }
        }
        System.out.println("Shutdown!");

    }

    public static void main(String[] args) {
        Client client = null;
        try {
            client = new Client("localhost", 9999);
            client.run();
        }
        catch (IOException e) {
            if (e.getMessage().indexOf("Connection refused") == 0) {
                System.out.println("Server is not running");
            }
            else {
                e.printStackTrace();
            }
        }
        finally {
            if (client != null) {
                client.close();
            }
        }
    }
}