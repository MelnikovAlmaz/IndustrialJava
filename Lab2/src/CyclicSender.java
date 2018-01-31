import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class CyclicSender implements Runnable{
    private BlockingQueue<Message> generalQueue = new LinkedBlockingQueue<Message>(256);
    private Server server;

    public CyclicSender (Server s) {
        server = s;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Message m = null;
            try {
                m = generalQueue.take();
                if (m != null) {
                    Collection<PeerController> peers = server.getPeers();
                    for (PeerController pc : peers) {
                        pc.send(m);
                    }
                }
            } catch (InterruptedException ignored) {
            }
        }
    }
    public BlockingQueue<Message> getQueue() {
        return generalQueue;
    }
}