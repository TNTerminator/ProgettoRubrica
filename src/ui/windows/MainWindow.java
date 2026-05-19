package ui.windows;
import controllers.PersonaFileController;
import model.Persona;
import ui.panels.RubricaActionPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainWindow extends JFrame {

    private List<Persona> persone;
    private DefaultTableModel modello;
    private JTable jTable;
    private PersonaFileController personaFileController;

    public MainWindow(PersonaFileController personaFileController) {
        this.personaFileController = personaFileController;
        this.persone = personaFileController.leggiPath();

        setTitle("Rubrica");
        String[] colonne = {"Nome", "Cognome", "Telefono"};

        this.modello = new DefaultTableModel(colonne, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        sync();

        init();
    }

    private void init() {
        this.jTable = new JTable(this.modello);
        setLayout(new BorderLayout());
        add(new JScrollPane(jTable), BorderLayout.CENTER);
        add(new RubricaActionPanel(this, jTable, modello, persone, personaFileController), BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
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
    }

}