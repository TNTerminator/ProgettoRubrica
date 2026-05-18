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
        nomeField = new JTextField(15);
        jPanel.add(nomeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;

        jPanel.add(new JLabel("Cognome"), gbc);

        gbc.gridx = 1;
        cognomeField = new JTextField(15);
        jPanel.add(cognomeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;

        jPanel.add(new JLabel("Indirizzo"), gbc);

        gbc.gridx = 1;
        indirizzoField = new JTextField(15);
        jPanel.add(indirizzoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;

        jPanel.add(new JLabel("Telefono"), gbc);

        gbc.gridx = 1;
        telefonoField = new JTextField(15);
        jPanel.add(telefonoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;

        jPanel.add(new JLabel("Età"), gbc);

        gbc.gridx = 1;
        etaField = new JTextField(15);
        jPanel.add(etaField, gbc);

        if(persona != null) {
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
            if (persona == null) {
                persona = new Persona(nomeField.getText(), cognomeField.getText(), indirizzoField.getText(), telefonoField.getText(), Integer.parseInt(etaField.getText()));
            } else {
                persona.setNome(nomeField.getText());
                persona.setCognome(cognomeField.getText());
                persona.setIndirizzo(indirizzoField.getText());
                persona.setTelefono(telefonoField.getText());
                persona.setEta(Integer.parseInt(etaField.getText()));
            }

            dispose();
        }
    }

    private void discardPerson() {
        dispose();
    }

    public Persona getPersona() {
        return persona;
    }

    private void initPersona() {
        nomeField.setText(persona.getNome());
        cognomeField.setText(persona.getCognome());
        indirizzoField.setText(persona.getIndirizzo());
        telefonoField.setText(persona.getTelefono());
        etaField.setText(Integer.toString(persona.getEta()));
    }

    private boolean validPerson() {

        if(nomeField.getText().isBlank()
                || cognomeField.getText().isBlank()
                || indirizzoField.getText().isBlank()
                || telefonoField.getText().isBlank()
                || etaField.getText().isBlank()) {
            return false;
        }

        try {
            int eta = Integer.parseInt(etaField.getText());
            return eta != 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
