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
        utenteFileController = new UtenteFileController(Path.of("utenti/"));

        utenti = utenteFileController.leggiPath();

        init();
    }

    private void init() {
        JButton loginButton = new JButton("Login");
        JButton addButton = new JButton("Aggiungi utente");

        add(loginButton);
        add(addButton);

        loginButton.addActionListener( e -> {
            Utente utente = new Utente(parent.getNome(), parent.getPassword());

            if(utenteFileController.trovaUtente(utente)) {
                PersonaFileController personaFileController = new PersonaFileController(Path.of("informazioni/"));

                MainWindow window = new MainWindow(personaFileController);
                parent.dispose();
            }
        });

        addButton.addActionListener(e -> {
            NuovoUtenteDialog window = new NuovoUtenteDialog(parent);
            window.setVisible(true);

            Utente utente = window.getUtente();
            if(utente != null) {
                utenti.add(utente);
                utenteFileController.salvaPath(utenti);
            }
        });
    }
}
