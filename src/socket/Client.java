package socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    Socket socket;
    BufferedReader in;
    BufferedWriter out;
    public Client(String ip, int port) throws Exception {
        try {
            // conecta o socket
            this.socket = new Socket(ip, port);

            // criando um streaming de leitura
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            // criando um streaming de escrita
            this.out = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        } catch(IOException e) {
            throw new Exception("failed to connect in " + ip + ":" + port);
        }
    }
    
    public boolean send() { return false; }
    public boolean getMessage() { return false; }
}
