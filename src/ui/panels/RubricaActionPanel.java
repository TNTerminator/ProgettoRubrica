package ui.panels;

import controllers.PersonaFileController;
import model.Persona;
import ui.dialogs.PersonaInfoDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class RubricaActionPanel extends JPanel {

    private JFrame parent;
    private JTable tabella;
    private DefaultTableModel modello;
    private List<Persona> persone;
    private PersonaFileController personaFileController;

    public RubricaActionPanel(JFrame parent, JTable tabella, DefaultTableModel modello, List<Persona> persone, PersonaFileController personaFileController) {
        this.parent = parent;
        this.tabella = tabella;
        this.modello = modello;
        this.persone = persone;
        this.personaFileController = personaFileController;

        JButton pulsanteAggiungi = new JButton("Aggiungi");
        JButton pulsanteModifica = new JButton("Modifica");
        JButton pulsanteAnnulla = new JButton("Rimuovi");

        add(pulsanteAggiungi);
        add(pulsanteModifica);
        add(pulsanteAnnulla);

        pulsanteAggiungi.addActionListener(e -> {
            PersonaInfoDialog finestra = new PersonaInfoDialog(this.parent, null);
            finestra.setVisible(true);
            Persona persona = finestra.getPersona();
            if (persona != null) {
                this.persone.add(persona);
                sync();
            }
        });

        pulsanteModifica.addActionListener(e -> {
            int riga = this.tabella.getSelectedRow();
            if (riga != -1) {
                Persona persona = this.persone.get(riga);
                PersonaInfoDialog finestra = new PersonaInfoDialog(this.parent, persona);
                finestra.setVisible(true);
                Persona newPersona = finestra.getPersona();
                if(persona != null) {
                    this.persone.set(riga, newPersona);
                    sync();
                }
            } else {
                JOptionPane.showMessageDialog(this.parent, "Per modificare una persona è necessario selezionarla", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        pulsanteAnnulla.addActionListener(e -> {
            int riga = this.tabella.getSelectedRow();
            if (riga != -1) {
                int scelta = JOptionPane.showConfirmDialog(this.parent, "Vuoi eliminare la persona: " + this.persone.get(riga).getNome() + " " + this.persone.get(riga).getCognome() + "?", "Elimina persona", JOptionPane.YES_NO_OPTION);

                if(scelta == JOptionPane.YES_NO_OPTION) {
                    this.persone.remove(riga);
                    this.modello.removeRow(riga);
                    sync();
                }
            } else {
                JOptionPane.showMessageDialog(this.parent, "Per eliminare una persona è necessario selezionarla", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void sync() {
        this.modello.setRowCount(0);
        for (Persona p : this.persone) {
            this.modello.addRow(new Object[]{
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