import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.util.concurrent.BlockingQueue;


public class PeerReader implements Runnable{
    private BufferedReader inputStream;
    private PeerController peerController;
    private BlockingQueue<Message> messages;

    PeerReader(PeerController pcP, BlockingQueue<Message> messagesP) {
        peerController = pcP;
        messages = messagesP;
        try {
            inputStream = new BufferedReader(new InputStreamReader(peerController.getInputStream(), Server.CHARSET));
        } catch (IOException e) {
            if (!peerController.isClosed()) {
                peerController.close();
            }
            e.printStackTrace();
        }
    }

    public void run() {
        String line = "";
        try {
            do {
                line = inputStream.readLine();
                if (line != null && line.length() != 0){
                    Message m = new Message(peerController, line);
                    messages.offer(m);
                }
            } while (line!= null && line.length() != 0 && !Thread.currentThread().isInterrupted());

        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (!peerController.isClosed()) {
                peerController.close();
            }
        }

    }

    public synchronized void setNewQueue(BlockingQueue<Message> newQueue) {
        messages.drainTo(newQueue);
        messages = newQueue;
    }
}