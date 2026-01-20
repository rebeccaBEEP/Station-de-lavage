/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet.partie.pkg1;

/**
 *
 * @author PC
 */
public class Prestation {
    private char categorie_vehicule;
    
    
    
    // constructeur
    public Prestation(char categorie_vehicule){
        this.categorie_vehicule= categorie_vehicule;
    }
  
@Override
public String toString() {
    return "Catégorie du véhicule : " + categorie_vehicule;
}




//  Methode lavage

    
public double lavage(){
    double prix =20;
    if (this.categorie_vehicule=='A') {
            
        return prix;
        }
    else if (this.categorie_vehicule== 'B'){
        prix *= 1.5;
        return prix;
            }
   else if (this.categorie_vehicule=='C') {
       prix *=1.75;
       return prix;
        }
            
    return 0.0;    
        }
    


//Methode sechage

    public double sechage() {
        double prix_sechage = 10;
        if (this.categorie_vehicule == 'A') {
            return prix_sechage ;
        }
        else if (this.categorie_vehicule == 'B') {
            prix_sechage *= 1.05;
        return prix_sechage;
        }
        else if (this.categorie_vehicule == 'C') 
            prix_sechage *= 1.10;
        return prix_sechage;
    }
    
    
    
// Methode prelavage    
 public double prelavage() {
        double prix_prelavage = 5;
        if (this.categorie_vehicule == 'A'){ 
            
        return prix_prelavage;
        }
        else if (this.categorie_vehicule == 'B'){ 
            prix_prelavage *= 1.5;
        return prix_prelavage;
        }
        else if(this.categorie_vehicule=='C') {
            prix_prelavage *= 1.75;
        return  prix_prelavage;   
    }
        
        return 0.0;
        
 }
 // methode nettoyage et nettoyage interieur
 
 public double nettoyage() {
        return lavage() + sechage();
    }   
    
public double nettoyageInterieur() {
    
    if (this.categorie_vehicule == 'C'){
        return 40;
    }
    else if (this.categorie_vehicule=='A'||this.categorie_vehicule=='B'){
        return 30;    
    }
    
    return 0;
    

}
    
   public char getCategorieVehicule() {
    return categorie_vehicule;
} 
    public String versFichier() {
        return "TypeInconnu:0"; 
    }
    
    
    
    }

   