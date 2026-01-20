/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet.partie.pkg1;
import java.time.LocalDateTime;

/**
 *
 * @author PC
 */
public class Rdv {
    Client client;
    Prestation prestation;
    int prix;
    
    
    // Constructeurs 
    
    public Rdv(Client client,Prestation prestation ,int prix){
        this.client=client;
        this.prestation= prestation;
        this.prix=prix;
    }
    
    @Override
public String toString() {
    return """
           Rendez-vous :
            - """ + client.toString() + "\n" +
           " - " + prestation.toString() + "\n" +
           " - Prix total : " + prix + " €";
}
            

public String versFichier(LocalDateTime date) {
    return date.toString() + "\n" +
           client.getNumero_client() + "\n" +
           prestation.versFichier();
}
    public Client getClient() {
        return client;
    }

    // Je te conseille d'ajouter aussi celui-ci pour la suite (affichage/fichier) :
    public Prestation getPrestation() {
        return prestation;
    }
}
