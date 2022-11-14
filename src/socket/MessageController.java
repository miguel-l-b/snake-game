package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Console.ConsoleManager;
import controller.Communicate;

public class MessageController {
    public final Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public MessageController(Socket socket) throws Exception {
        if(socket == null)
            throw new Exception("socket cannot be null");
        this.socket = socket;
        this.out = new ObjectOutputStream(this.socket.getOutputStream());
        this.in = new ObjectInputStream(this.socket.getInputStream());
    }

     public Object getObject() {
        try {
            return in.readObject();
        } catch(Exception err) { return null; }
    }

    public boolean sendObject(Communicate message) {
        try {
            out.writeObject(message);
            out.flush();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void close() throws Exception {
        try {
            in.close();
            out.close();
            socket.close();
        } catch(IOException err) { 
            throw new Exception("Error when closing: \n ->"+
                err.getMessage());
        }
    }
}