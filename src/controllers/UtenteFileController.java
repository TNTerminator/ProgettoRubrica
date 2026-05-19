package controllers;

import model.Utente;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class UtenteFileController {
    private Path percorso;

    public UtenteFileController(Path percorso) {
        this.percorso = percorso;

        if(!Files.exists(this.percorso)) {
            try {
                Files.createDirectories(this.percorso);
            } catch (IOException e) {
                System.out.println("Errore creazione directory");
            }
        }
    }

    public void salvaPath(List<Utente> utenti) {
        try (Stream<Path> stream = Files.list(this.percorso)) {
            stream.forEach(p -> {
                try {
                    Files.delete(p);
                } catch (IOException e) {
                    System.out.println("Errore nella cancellazione del file: " + p);
                }
            });
        } catch (Exception e) {
            System.out.println("Errore nella pulizia della cartella: " + this.percorso);
        }

        try {
            int contatoreUtenti = 1;

            for (Utente u : utenti) {
                Path userFile = this.percorso.resolve("Utente_" + Integer.toString(contatoreUtenti) + ".txt");
                Files.writeString(userFile, u.fileFormat());
                contatoreUtenti++;
            }
        } catch (IOException e) {
            System.out.println("Errore scrittura file");
        }
    }

    public List<Utente> leggiPath() {
        List<Utente> utenti = new ArrayList<>();
        try (Stream<Path> stream = Files.list(this.percorso)) {
            stream.forEach(p -> {
                try (Scanner scanner = new Scanner(p)) {
                    String riga = scanner.nextLine();
                    String[] parti = riga.split(";");
                    Utente utente = new Utente(
                            parti[0],
                            parti[1]
                    );
                    utenti.add(utente);
                } catch (Exception e) {
                    System.out.println("Errore lettura file");
                }
            });
        } catch (Exception e) {
            System.out.println("Errore lettura cartella");
        }

        return utenti;
    }

    public boolean trovaUtente(Utente utente) {
        List<Utente> utenti = this.leggiPath();

        return utenti.contains(utente);
    }
}
