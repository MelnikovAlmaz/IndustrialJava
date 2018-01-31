import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class ClientReader  implements Runnable{
    private BufferedReader inputReader;
    private Socket serverSocket;
    private boolean isException = false;

    public ClientReader(Socket socket) throws IOException {
        serverSocket = socket;
        inputReader = new BufferedReader(new InputStreamReader(serverSocket.getInputStream(), Server.CHARSET));
    }

    public void run() {
        String line;
        while (!isException && !serverSocket.isClosed() && ((line = readString())!=null)) {
            System.out.println(line);
        }
    }

    public String readString() {
        String line = "";
        try {
            line = inputReader.readLine();
        } catch (IOException e) {
            if(!serverSocket.isClosed()) {
                try {
                    serverSocket.close();
                } catch (IOException ignore) {}
            }
            isException = true;
        }
        return line;
    }

}