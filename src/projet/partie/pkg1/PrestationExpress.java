/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet.partie.pkg1;

/**
 *
 * @author PC
 */
public class PrestationExpress extends Prestation  {
    boolean nettoyageInterieur;
    
    //constructeur
    public PrestationExpress( char categorie_vehicule,boolean nettoyageInterieur){
        super(categorie_vehicule);
        this.nettoyageInterieur = nettoyageInterieur  ;   
    }
    @Override
    public String toString() {
        String intStr = nettoyageInterieur ?"avec nettoyage intérieur" : "sans nettoyage intérieur";
        return "Prestation Express [" + super.toString() + "] " + intStr;
    }            
 
    
// Methode nettoyage

    @Override
    public double nettoyage() {
        double total = lavage() + sechage();
        if (nettoyageInterieur) {
            total += 30;
        }
        return total;
    }
    
    
    
}