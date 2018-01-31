import java.io.IOException;
import java.net.ServerSocket;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Server {
    public static final String OFFER_CREDENTIALS = "OFFER CREDENTIALS";
    public static final String CHARSET = "UTF-16";
    private ServerSocket ss;

    private ConcurrentMap<String, PeerController> peers;
    private ConcurrentMap<String, String> users;

    private CyclicSender messageSender;
    public static final String REGISTER_SUCCESSFUL = "Register successful!";

    private Server() {
        peers = new ConcurrentHashMap<String, PeerController>(4);
        users = new ConcurrentHashMap<String, String>(4);
        try {
            ss = new ServerSocket(9999);
            messageSender = new CyclicSender(this);
            final Thread cyclicSenderThread = new Thread(messageSender, "CyclicSender");
            cyclicSenderThread.setDaemon(true);
            cyclicSenderThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                PeerController p = new PeerController(this, ss.accept());
                new Thread(p,"PeerController").start();

            } catch (IOException e) {
                e.printStackTrace();
                close();
                Thread.currentThread().interrupt();
            }
        }
    }

    boolean tryRegister(PeerController p) {
        if (users.containsKey(p.getName())) {
            return users.get(p.getName()).equals(p.getPassword()) && peers.putIfAbsent(p.getName(), p) == null;
        }
        else {
            users.put(p.getName(), p.getPassword());
            return peers.putIfAbsent(p.getName(), p) == null;
        }
    }

    boolean unSign(PeerController p) {
        return peers.remove(p.getName()) == p;
    }

    boolean isSigned(PeerController p) {
        return peers.containsValue(p);
    }

    public Collection<PeerController> getPeers() {
        return peers.values();
    }

    public BlockingQueue<Message> getIncomeQueue() {
        return messageSender.getQueue();
    }

    public void close() {
        if (ss != null && !ss.isClosed()) {
            try {
                ss.close();
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

    public static void main(String[] args) {
        // создаем сервер и стартуем его.
        new Server().run();
    }
}