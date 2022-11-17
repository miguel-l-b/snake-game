package views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.Socket;

import controller.LogIn;
import utils.MessageController;
import utils.Window;

public class Launcher extends Window {
    private JLabel lbl_server = new JLabel("Servidor e porta:");
    private JTextField txt_server = new JTextField();
    private JLabel lbl_username = new JLabel("Escolha um nome de usuário:");
    private JTextField txt_username = new JTextField();
    private JButton btn_connection = new JButton("Conectar");

    public Launcher() {  
        super("Jogo da Cobra", "main");
        super.setSize(270, 235);
        super.centralize();
        super.start();
        super.closeProgramOnClose();
        super.setResizable(false);

        super.setLayout(null);
        super.add(lbl_server);
        super.add(txt_server);
        super.add(lbl_username);
        super.add(txt_username);
        super.add(btn_connection);

        lbl_server.setBounds(10, 15, 250, 20);
        txt_server.setBounds(10, 45, 250, 25);
        lbl_username.setBounds(10, 80, 250, 20);
        txt_username.setBounds(10, 110, 250, 25);
        btn_connection.setBounds(160, 150, 100, 30);

        btn_connection.addActionListener((e) -> { btnConnectionHandler(e); });
    }

    public void btnConnectionHandler(ActionEvent e) {
        String[] ipAndPort = txt_server.getText().split(":");
        int port = Integer.valueOf(ipAndPort[1]);
        try {
            MessageController server = new MessageController(new Socket(ipAndPort[0], port));
            server.sendObject(new LogIn(txt_username.getText()));

            WindowsManager.handleGame(server);

            this.setVisible(false);
        } catch (Exception e1) { WindowsManager.handleMessage("Erro ao conectar ao servidor"); }
    }
}
