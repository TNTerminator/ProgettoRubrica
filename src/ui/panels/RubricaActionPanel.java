package ui.panels;

import controllers.PersonaFileController;
import model.Persona;
import ui.dialogs.PersonaInfoDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class RubricaActionPanel extends JPanel {

    private JFrame parent;
    private JTable table;
    private DefaultTableModel model;
    private List<Persona> persone;
    private PersonaFileController personaFileController;

    public RubricaActionPanel(JFrame parent, JTable table, DefaultTableModel model, List<Persona> persone, PersonaFileController personaFileController) {
        this.parent = parent;
        this.table = table;
        this.model = model;
        this.persone = persone;
        this.personaFileController = personaFileController;

        JButton addButton = new JButton("Aggiungi");
        JButton editButton = new JButton("Modifica");
        JButton removeButton = new JButton("Rimuovi");

        add(addButton);
        add(editButton);
        add(removeButton);

        addButton.addActionListener(e -> {
            PersonaInfoDialog window = new PersonaInfoDialog(this.parent, null);
            window.setVisible(true);
            Persona persona = window.getPersona();
            if (persona != null) {
                this.persone.add(persona);
                sync();
            }
        });

        editButton.addActionListener(e -> {
            int row = this.table.getSelectedRow();
            if (row != -1) {
                Persona persona = this.persone.get(row);
                PersonaInfoDialog window = new PersonaInfoDialog(this.parent, persona);
                window.setVisible(true);
                Persona newPersona = window.getPersona();
                if(persona != null) {
                    this.persone.set(row, newPersona);
                    sync();
                }
            } else {
                JOptionPane.showMessageDialog(this.parent, "Per modificare una persona è necessario selezionarla", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        removeButton.addActionListener(e -> {
            int row = this.table.getSelectedRow();
            if (row != -1) {
                int choice = JOptionPane.showConfirmDialog(this.parent, "Vuoi eliminare la persona: " + this.persone.get(row).getNome() + " " + this.persone.get(row).getCognome() + "?", "Elimina persona", JOptionPane.YES_NO_OPTION);

                if(choice == JOptionPane.YES_NO_OPTION) {
                    this.persone.remove(row);
                    this.model.removeRow(row);
                    sync();
                }
            } else {
                JOptionPane.showMessageDialog(this.parent, "Per eliminare una persona è necessario selezionarla", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void sync() {
        this.model.setRowCount(0);
        for (Persona p : this.persone) {
            this.model.addRow(new Object[]{
                    p.getNome(),
                    p.getCognome(),
                    p.getTelefono(),
                    p.getIndirizzo(),
                    p.getEta()
            });
        }

        personaFileController.salvaPath(this.persone);
    }
}