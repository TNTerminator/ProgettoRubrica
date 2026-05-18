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
    private DefaultTableModel model;
    private JTable jTable;
    private PersonaFileController personaFileController;

    public MainWindow(PersonaFileController personaFileController) {
        this.personaFileController = personaFileController;
        persone = personaFileController.leggiPath();

        setTitle("Rubrica");
        String[] columns = {"Nome", "Cognome", "Indirizzo"};

        model = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        sync();

        init();
    }

    private void init() {
        jTable = new JTable(model);
        setLayout(new BorderLayout());
        add(new JScrollPane(jTable), BorderLayout.CENTER);
        add(new RubricaActionPanel(this, jTable, model, persone, personaFileController), BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void sync() {
        model.setRowCount(0);
        for (Persona p : persone) {
            model.addRow(new Object[]{
                    p.getNome(),
                    p.getCognome(),
                    p.getIndirizzo(),
                    p.getTelefono(),
                    p.getEta()
            });
        }
    }

}