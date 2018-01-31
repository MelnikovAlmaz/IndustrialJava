public class  Message {
    private PeerController controller;
    private String message;

    public Message(PeerController controller, String messageP){
        this.controller = controller;
        message = messageP;
    }


    public PeerController getController() {
        return controller;
    }

    public String getMessage() {
        return message;
    }

}