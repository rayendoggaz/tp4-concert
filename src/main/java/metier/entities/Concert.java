package metier.entities;

import java.io.Serializable;

public class Concert implements Serializable {
    private int idConcert;  // Match the data type with the database (int instead of Long)
    private String nomConcert;
    private double prix;

    public Concert() {
        super();
    }

    public Concert(String nomConcert, double prix) {
        super();
        this.nomConcert = nomConcert;
        this.prix = prix;
    }

    public int getIdConcert() {
        return idConcert;
    }

    public void setIdConcert(int idConcert) {
        this.idConcert = idConcert;
    }

    public String getNomConcert() {
        return nomConcert;
    }

    public void setNomConcert(String nomConcert) {
        this.nomConcert = nomConcert;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Concert [idConcert=" + idConcert + ", nomConcert=" +
               nomConcert + ", prix=" + prix + "]";
    }
}
