package ui.dialogs;

import model.Utente;

import javax.swing.*;
import java.awt.*;

public class NuovoUtenteDialog extends JDialog {

    private JFrame parent;

    private JTextField nomeField;
    private JTextField passwordField;

    private Utente utente;

    public NuovoUtenteDialog(JFrame parent) {
        super(parent, true);

        this.parent = parent;

        init();
    }

    private void init() {
        JPanel jPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        setPreferredSize(new Dimension(400,400));

        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;

        jPanel.add(new JLabel("Nome"), gbc);

        gbc.gridx = 1;
        nomeField = new JTextField(15);
        jPanel.add(nomeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;

        jPanel.add(new JLabel("Password"), gbc);

        gbc.gridx = 1;
        passwordField = new JTextField(15);
        jPanel.add(passwordField, gbc);

        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("Aggiungi utente");
        JButton discardButton = new JButton("Annulla");

        addButton.addActionListener(e -> saveUser());
        discardButton.addActionListener(e -> discardUser());

        buttonPanel.add(addButton);
        buttonPanel.add(discardButton);

        add(jPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();

        setLocationRelativeTo(parent);
    }

    private void saveUser() {
        if(this.validUser()) {
            utente = new Utente(nomeField.getText(), passwordField.getText());

            dispose();
        }
    }

    private void discardUser() {
        dispose();
    }

    private boolean validUser() {
        return !nomeField.getText().isBlank() && !passwordField.getText().isBlank();
    }

    public Utente getUtente() {
        return utente;
    }

}
