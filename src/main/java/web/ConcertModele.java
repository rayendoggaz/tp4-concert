package web;

import java.util.ArrayList;
import java.util.List;
import metier.entities.Concert; // Update the import to use the Concert class

public class ConcertModele { // Rename the class to ConcertModele
    private String motCle;
    List<Concert> concerts = new ArrayList<>(); // Update the list to use Concert instead of Produit

    public String getMotCle() {
        return motCle;
    }

    public void setMotCle(String motCle) {
        this.motCle = motCle;
    }

    public List<Concert> getConcerts() { // Update the method name to getConcerts
        return concerts;
    }

    public void setConcerts(List<Concert> concerts) { // Update the method name to setConcerts
        this.concerts = concerts;
    }
}
