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
        JButton loginButton = new JButton("Login");
        JButton addButton = new JButton("Aggiungi utente");

        add(loginButton);
        add(addButton);

        loginButton.addActionListener( e -> {
            Utente utente = new Utente(this.parent.getNome(), this.parent.getPassword());

            if(this.utenteFileController.trovaUtente(utente)) {
                PersonaFileController personaFileController = new PersonaFileController(Path.of("informazioni/"));

                MainWindow window = new MainWindow(personaFileController);
                this.parent.dispose();
            }
        });

        addButton.addActionListener(e -> {
            NuovoUtenteDialog window = new NuovoUtenteDialog(this.parent);
            window.setVisible(true);

            Utente utente = window.getUtente();
            if(utente != null) {
                this.utenti.add(utente);
                this.utenteFileController.salvaPath(this.utenti);
            }
        });
    }
}
