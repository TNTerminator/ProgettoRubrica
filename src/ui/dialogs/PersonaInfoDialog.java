package ui.dialogs;
import model.Persona;

import javax.swing.*;
import java.awt.*;

public class PersonaInfoDialog extends JDialog {

    private Persona persona;

    private JTextField nomeField;
    private JTextField cognomeField;
    private JTextField indirizzoField;
    private JTextField telefonoField;
    private JTextField etaField;

    JFrame parent;

    public PersonaInfoDialog(JFrame parent, Persona persona) {
        super(parent, true);

        this.persona = persona;
        this.parent = parent;

        if(persona == null) {
            setTitle("Aggiungi persona");
        }
        else {
            setTitle("Modifica persona");
        }

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
        this.nomeField = new JTextField(15);
        jPanel.add(this.nomeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;

        jPanel.add(new JLabel("Cognome"), gbc);

        gbc.gridx = 1;
        this.cognomeField = new JTextField(15);
        jPanel.add(this.cognomeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;

        jPanel.add(new JLabel("Indirizzo"), gbc);

        gbc.gridx = 1;
        this.indirizzoField = new JTextField(15);
        jPanel.add(this.indirizzoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;

        jPanel.add(new JLabel("Telefono"), gbc);

        gbc.gridx = 1;
        this.telefonoField = new JTextField(15);
        jPanel.add(this.telefonoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;

        jPanel.add(new JLabel("Età"), gbc);

        gbc.gridx = 1;
        this.etaField = new JTextField(15);
        jPanel.add(this.etaField, gbc);

        if(this.persona != null) {
            initPersona();
        }

        JPanel buttonPanel = new JPanel();

        JButton saveButton = new JButton("Salva");
        JButton discardButton = new JButton("Annulla");

        saveButton.addActionListener(e -> savePerson());
        discardButton.addActionListener(e -> discardPerson());

        buttonPanel.add(saveButton);
        buttonPanel.add(discardButton);

        add(jPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();

        setLocationRelativeTo(parent);
    }

    private void savePerson() {
        if(this.validPerson()) {
            if (this.persona == null) {
                this.persona = new Persona(this.nomeField.getText(), this.cognomeField.getText(), this.indirizzoField.getText(), this.telefonoField.getText(), Integer.parseInt(this.etaField.getText()));
            } else {
                this.persona.setNome(this.nomeField.getText());
                this.persona.setCognome(this.cognomeField.getText());
                this.persona.setIndirizzo(this.indirizzoField.getText());
                this.persona.setTelefono(this.telefonoField.getText());
                this.persona.setEta(Integer.parseInt(this.etaField.getText()));
            }

            dispose();
        }
    }

    private void discardPerson() {
        dispose();
    }

    public Persona getPersona() {
        return this.persona;
    }

    private void initPersona() {
        this.nomeField.setText(this.persona.getNome());
        this.cognomeField.setText(this.persona.getCognome());
        this.indirizzoField.setText(this.persona.getIndirizzo());
        this.telefonoField.setText(this.persona.getTelefono());
        this.etaField.setText(Integer.toString(this.persona.getEta()));
    }

    private boolean validPerson() {

        if(this.nomeField.getText().isBlank()
                || this.cognomeField.getText().isBlank()
                || this.indirizzoField.getText().isBlank()
                || this.telefonoField.getText().isBlank()
                || this.etaField.getText().isBlank()) {
            return false;
        }

        try {
            int eta = Integer.parseInt(this.etaField.getText());
            return eta != 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
