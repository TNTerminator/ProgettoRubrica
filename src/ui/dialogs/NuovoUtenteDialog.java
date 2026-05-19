package ui.dialogs;

import model.Utente;

import javax.swing.*;
import java.awt.*;

public class NuovoUtenteDialog extends JDialog {

    private JFrame parent;

    private JTextField campoNome;
    private JPasswordField campoPassword;

    private Utente utente;

    public NuovoUtenteDialog(JFrame parent) {
        super(parent, true);

        this.parent = parent;

        init();
    }

    private void init() {
        JPanel jPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;

        jPanel.add(new JLabel("Nome"), gbc);

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

        JPanel pannelloBottoni = new JPanel();

        JButton bottoneAggiungi = new JButton("Aggiungi utente");
        JButton bottoneAnnulla = new JButton("Annulla");

        bottoneAggiungi.addActionListener(e -> saveUser());
        bottoneAnnulla.addActionListener(e -> discardUser());

        pannelloBottoni.add(bottoneAggiungi);
        pannelloBottoni.add(bottoneAnnulla);

        add(jPanel, BorderLayout.CENTER);
        add(pannelloBottoni, BorderLayout.SOUTH);

        pack();

        setLocationRelativeTo(this.parent);
    }

    private void saveUser() {
        if(this.validUser()) {
            this.utente = new Utente(this.campoNome.getText(), new String(this.campoPassword.getPassword()));

            dispose();
        }
    }

    private void discardUser() {
        dispose();
    }

    private boolean validUser() {
        return !campoNome.getText().isBlank() && !(new String(campoPassword.getPassword()).isBlank());
    }

    public Utente getUtente() {
        return this.utente;
    }

}
