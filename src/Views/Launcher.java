package Views;

import javax.swing.*;

import Console.ConsoleManager;
import game.Player;

import java.awt.event.ActionEvent;

import utils.Window;

public class Launcher extends Window {
    private JLabel lbl_server = new JLabel("Servidor e porta:");
    private JTextField txt_server = new JTextField();
    private JLabel lbl_username = new JLabel("Escolha um nome de usuário:");
    private JTextField txt_username = new JTextField();
    private JButton btn_connection = new JButton("Conectar");

    public Launcher() {  
        super("Jogo da Cobra", "main");
        super.setSize(270, 260);
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
        ConsoleManager.println(txt_username.getText());
    }
}
