import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.concurrent.BlockingQueue;


public class PeerWriter implements Runnable {
    private PeerController controller;
    private Charset ch = Charset.forName(Server.CHARSET);
    private BlockingQueue<Message> queueToSend;


    public PeerWriter(PeerController peerController, BlockingQueue<Message> queueParam) {
        controller = peerController;
        queueToSend = queueParam;
    }

    public void run() {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(controller.getOutputStream(), ch);
            while (!Thread.currentThread().isInterrupted() && !controller.isClosed()) {
                Message message = queueToSend.take();
                write(osw, message);
            }
        } catch (InterruptedException ignored) {
        }
    }

    private void write(OutputStreamWriter writer, Message message){
        try {
            writer.write("[" + message.getController().getName() + "] " + message.getMessage() + '\n');
            writer.flush();
        } catch (IOException e) {
        }
    }
}