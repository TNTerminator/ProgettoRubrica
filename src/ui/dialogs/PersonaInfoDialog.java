package ui.dialogs;
import model.Persona;

import javax.swing.*;
import java.awt.*;

public class PersonaInfoDialog extends JDialog {

    private Persona persona;

    private JTextField campoNome;
    private JTextField campoCognome;
    private JTextField campoIndirizzo;
    private JTextField campoTelefono;
    private JTextField campoEta;

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
        this.campoNome = new JTextField(15);
        jPanel.add(this.campoNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;

        jPanel.add(new JLabel("Cognome"), gbc);

        gbc.gridx = 1;
        this.campoCognome = new JTextField(15);
        jPanel.add(this.campoCognome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;

        jPanel.add(new JLabel("Indirizzo"), gbc);

        gbc.gridx = 1;
        this.campoIndirizzo = new JTextField(15);
        jPanel.add(this.campoIndirizzo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;

        jPanel.add(new JLabel("Telefono"), gbc);

        gbc.gridx = 1;
        this.campoTelefono = new JTextField(15);
        jPanel.add(this.campoTelefono, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;

        jPanel.add(new JLabel("Età"), gbc);

        gbc.gridx = 1;
        this.campoEta = new JTextField(15);
        jPanel.add(this.campoEta, gbc);

        if(this.persona != null) {
            initPersona();
        }

        JPanel pannelloBottoni = new JPanel();

        JButton pulsanteSalva = new JButton("Salva");
        JButton pulsanteAnnulla = new JButton("Annulla");

        pulsanteSalva.addActionListener(e -> savePerson());
        pulsanteAnnulla.addActionListener(e -> discardPerson());

        pannelloBottoni.add(pulsanteSalva);
        pannelloBottoni.add(pulsanteAnnulla);

        add(jPanel, BorderLayout.CENTER);
        add(pannelloBottoni, BorderLayout.SOUTH);

        pack();

        setLocationRelativeTo(parent);
    }

    private void savePerson() {
        if(this.validPerson()) {
            if (this.persona == null) {
                this.persona = new Persona(this.campoNome.getText(), this.campoCognome.getText(), this.campoIndirizzo.getText(), this.campoTelefono.getText(), Integer.parseInt(this.campoEta.getText()));
            } else {
                this.persona.setNome(this.campoNome.getText());
                this.persona.setCognome(this.campoCognome.getText());
                this.persona.setIndirizzo(this.campoIndirizzo.getText());
                this.persona.setTelefono(this.campoTelefono.getText());
                this.persona.setEta(Integer.parseInt(this.campoEta.getText()));
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
        this.campoNome.setText(this.persona.getNome());
        this.campoCognome.setText(this.persona.getCognome());
        this.campoIndirizzo.setText(this.persona.getIndirizzo());
        this.campoTelefono.setText(this.persona.getTelefono());
        this.campoEta.setText(Integer.toString(this.persona.getEta()));
    }

    private boolean validPerson() {

        if(this.campoNome.getText().isBlank()
                || this.campoCognome.getText().isBlank()
                || this.campoIndirizzo.getText().isBlank()
                || this.campoTelefono.getText().isBlank()
                || this.campoEta.getText().isBlank()) {
            return false;
        }

        try {
            int eta = Integer.parseInt(this.campoEta.getText());
            return eta != 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
