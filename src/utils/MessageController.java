package utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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

    public String getID() { return socket.getRemoteSocketAddress().toString(); }

    public Communicate getObject() {
        try { return (Communicate)in.readObject(); } 
        catch(Exception err) { err.printStackTrace(); return null; }
    }

    public boolean sendObject(Communicate object) {
        try {
            out.writeObject(object);
            out.flush();
            return true;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public void close() throws Exception {
        try {
            in.close();
            out.close();
            socket.close();
        } catch(IOException err) { throw new Exception("Error when closing: \n ->"+err.getMessage()); }
    }
}