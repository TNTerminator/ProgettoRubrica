package ui.panels;

import controllers.PersonaFileController;
import controllers.UtenteFileController;
import model.Utente;
import ui.dialogs.NuovoUtenteDialog;
import ui.windows.LoginWindow;
import ui.windows.MainWindow;

import javax.swing.*;
import java.nio.file.Path;
import java.util.List;

public class LoginActionPanel extends JPanel {

    private LoginWindow parent;
    private UtenteFileController utenteFileController;
    private List<Utente> utenti;

    public LoginActionPanel(LoginWindow parent) {
        this.parent = parent;
        this.utenteFileController = new UtenteFileController(Path.of("utenti/"));

        this.utenti = this.utenteFileController.leggiPath();

        init();
    }

    private void init() {
        JButton pulsanteLogin = new JButton("Login");
        JButton pulsanteAggiungi = new JButton("Aggiungi utente");

        add(pulsanteLogin);
        add(pulsanteAggiungi);

        pulsanteLogin.addActionListener( e -> {
            Utente utente = new Utente(this.parent.getNome(), this.parent.getPassword());

            if(this.utenteFileController.trovaUtente(utente)) {
                PersonaFileController personaFileController = new PersonaFileController(Path.of("informazioni/"));

                MainWindow finestra = new MainWindow(personaFileController);
                this.parent.dispose();
            } else {
                JOptionPane.showMessageDialog(this.parent, "Nome utente e/o password errati", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        pulsanteAggiungi.addActionListener(e -> {
            NuovoUtenteDialog finestra = new NuovoUtenteDialog(this.parent);
            finestra.setVisible(true);

            Utente utente = finestra.getUtente();
            if(utente != null) {
                this.utenti.add(utente);
                this.utenteFileController.salvaPath(this.utenti);
            }
        });
    }
}
