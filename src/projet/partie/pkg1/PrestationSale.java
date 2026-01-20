/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet.partie.pkg1;

/**
 *
 * @author PC
 */
public class PrestationSale extends Prestation {
    
    
    
    // constructeur
    public PrestationSale ( char categorie_vehicule){
        super(categorie_vehicule);
    }
    @Override   
    public String toString() {
    return "Prestation Véhicule Sale [" + super.toString() + "]";
}
    @Override
  public double nettoyage() {
        return prelavage() + lavage() + sechage() + 30;
    }  
    @Override
public String versFichier() {
    // Format : catégorie:prix
    return getCategorieVehicule() + ":" + (int)nettoyage();
}
}
