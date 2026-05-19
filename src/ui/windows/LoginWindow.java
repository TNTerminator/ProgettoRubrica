package ui.windows;

import model.Utente;
import ui.panels.LoginActionPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LoginWindow extends JFrame {
    private List<Utente> utenti = new ArrayList<>();
    private DefaultTableModel model;

    private JTextField nomeField;
    private JPasswordField passwordField;

    public LoginWindow() {
        init();
    }

    private void init() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel titolo = new JLabel("Login Rubrica", SwingConstants.CENTER);

        JPanel jPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        setPreferredSize(new Dimension(400,400));

        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;

        jPanel.add(new JLabel("Nome utente"), gbc);

        gbc.gridx = 1;
        this.nomeField = new JTextField(15);
        jPanel.add(this.nomeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;

        jPanel.add(new JLabel("Password"), gbc);

        gbc.gridx = 1;
        this.passwordField = new JPasswordField(15);
        this.passwordField.setEchoChar('*');
        jPanel.add(this.passwordField, gbc);

        mainPanel.add(titolo, BorderLayout.NORTH);
        mainPanel.add(jPanel, BorderLayout.CENTER);
        mainPanel.add(new LoginActionPanel(this), BorderLayout.SOUTH);

        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public String getNome() {
        return this.nomeField.getText();
    }

    public String getPassword() {
        return new String(this.passwordField.getPassword());
    }
}
