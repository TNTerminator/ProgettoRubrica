package ui.windows;

import ui.panels.LoginActionPanel;

import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame {
    private JTextField campoNome;
    private JPasswordField campoPassword;

    public LoginWindow() {
        init();
    }

    private void init() {
        JPanel pannelloPrincipale = new JPanel(new BorderLayout());

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
        this.campoNome = new JTextField(15);
        jPanel.add(this.campoNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;

        jPanel.add(new JLabel("Password"), gbc);

        gbc.gridx = 1;
        this.campoPassword = new JPasswordField(15);
        this.campoPassword.setEchoChar('*');
        jPanel.add(this.campoPassword, gbc);

        pannelloPrincipale.add(titolo, BorderLayout.NORTH);
        pannelloPrincipale.add(jPanel, BorderLayout.CENTER);
        pannelloPrincipale.add(new LoginActionPanel(this), BorderLayout.SOUTH);

        add(pannelloPrincipale);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public String getNome() {
        return this.campoNome.getText();
    }

    public String getPassword() {
        return new String(this.campoPassword.getPassword());
    }
}
