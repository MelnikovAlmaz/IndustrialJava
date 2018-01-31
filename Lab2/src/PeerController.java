import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


public class PeerController implements Runnable {
    private Socket s;
    private String name = null;
    private String password = null;

    private BlockingQueue<Message> incomeMessages = new LinkedBlockingQueue<Message>(256);
    private BlockingQueue<Message> outgoingMessages = new LinkedBlockingQueue<Message>(256);

    private Server server;
    private Thread inputStream;
    private Thread outputStream;

    public PeerController(Server serverParam, Socket socket) {
        s = socket;
        server = serverParam;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void run() {
        final PeerReader target = new PeerReader(this, incomeMessages);
        (inputStream = new Thread(target, "PeerReader")).start();

        (outputStream = new Thread(new PeerWriter(this,  outgoingMessages), "PeerWriter")).start();

        try {
            while(!isClosed() && !Thread.currentThread().isInterrupted()) {
                Message m = incomeMessages.poll(1, TimeUnit.SECONDS);
                if (m != null) {
                    String messageStr = m.getMessage();
                    if (messageStr.startsWith(Server.OFFER_CREDENTIALS)) {
                        name = messageStr.split(":")[1];
                        password = messageStr.split(":")[2];

                        if (!server.tryRegister(this)) {
                            outgoingMessages.put(new Message(this, "Неверный пароль или пользователь уже в системе"));
                        } else {
                            outgoingMessages.put(new Message(this, Server.REGISTER_SUCCESSFUL));
                            final BlockingQueue<Message> serverQueue = server.getIncomeQueue();
                            target.setNewQueue(serverQueue);
                            break;
                        }

                    } else {
                        System.out.println(m.getMessage());
                        System.out.println("AAAAA!");
                        this.close();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public InputStream getInputStream()  {
        try {
            return s.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            close();
        }
        return null;
    }


    public OutputStream getOutputStream() {
        try {
            return s.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
            close();
        }
        return null;

    }

    public synchronized void close() {
        inputStream.interrupt();
        outputStream.interrupt();
        if (server.isSigned(this)) {
            server.unSign(this);
        }
        if (s != null && !s.isClosed()) {
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(Message m) {
        outgoingMessages.offer(m);
    }

    public boolean isClosed()  {
        return s.isClosed();
    }

}