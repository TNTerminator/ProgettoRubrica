package model;

import java.util.Objects;

public class Utente {
    private String nome;
    private String password;

    public Utente(String nome, String password) {
        this.nome = nome;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return Objects.equals(nome, utente.nome) && Objects.equals(password, utente.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, password);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String fileFormat() {
        return this.nome + ";" + this.password;
    }
}
