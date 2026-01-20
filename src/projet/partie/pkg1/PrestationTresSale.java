/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet.partie.pkg1;

/**
 *
 * @author PC
 */
public class PrestationTresSale extends Prestation{
    
    private int typeSalissure;
    
    
    //Constructeur
    
    public PrestationTresSale(char categorie_vehicule, int typeSalissure){
        
        super(categorie_vehicule);
        this.typeSalissure=typeSalissure;
    }
    
    @Override
    public String toString() {
    return "Prestation Véhicule Très Sale [" + super.toString() + "]  Type de salissure : " + typeSalissure;
}
  
    
    // definir la methode surcout qui depend du type de salissure
    
    private double surcout(){
        
        return 5*typeSalissure;
    }
    
    @Override
  public double nettoyage(){
      return prelavage()+lavage()+ sechage() +nettoyageInterieur()+surcout();
  } 
    @Override
public String versFichier() {
    // Format : catégorie:typeSalissure:prix
    return getCategorieVehicule() + ":" + typeSalissure + ":" + (int)nettoyage();
}
    
    
    
    
    
    
    
    
}
