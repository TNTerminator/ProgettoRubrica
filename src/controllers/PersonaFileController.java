package controllers;

import model.Persona;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class PersonaFileController {
    private Path percorso;

    public PersonaFileController(Path percorso) {
        this.percorso = percorso;

        if(!Files.exists(this.percorso)) {
            try {
                Files.createDirectories(this.percorso);
            } catch (IOException e) {
                System.out.println("Errore creazione directory");
            }
        }
    }

    public void salvaPath(List<Persona> persone) {
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
            int contatorePersona = 1;

            for (Persona p : persone) {
                Path personFile = this.percorso.resolve("Persona_" + contatorePersona + ".txt");
                Files.writeString(personFile, p.fileFormat());
                contatorePersona++;
            }
        } catch (IOException e) {
            System.out.println("Errore scrittura file");
        }
    }

    public List<Persona> leggiPath() {
        List<Persona> persone = new ArrayList<>();
        try (Stream<Path> stream = Files.list(this.percorso)) {
            stream.forEach(p -> {
                try (Scanner scanner = new Scanner(p)) {
                    String riga = scanner.nextLine();
                    String[] parti = riga.split(";");
                    Persona persona = new Persona(
                            parti[0],
                            parti[1],
                            parti[2],
                            parti[3],
                            Integer.parseInt(parti[4])
                    );
                    persone.add(persona);
                } catch (Exception e) {
                    System.out.println("Errore lettura file");
                }
            });
        } catch (Exception e) {
            System.out.println("Errore lettura cartella");
        }

        return persone;
    }
}
