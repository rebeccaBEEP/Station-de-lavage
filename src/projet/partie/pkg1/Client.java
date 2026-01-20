/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet.partie.pkg1;

/**
 *
 * @author PC
 */
public class Client {
  int numero_client;
  String nom;
  int numero_telephone;
  String mail;
  
  // constructeur CAS 1
  
  public Client ( int numero_client,String nom,int numero_telephone){
      this.numero_client = numero_client ;
      this.nom= nom;
      this.numero_telephone = numero_telephone;
  }

  
  // constructeur CAS 2
  
 public Client (String mail,int numero_client,String nom,int numero_telephone){

      this.mail= mail;
      this.numero_client = numero_client ;
      this.nom= nom;
      this.numero_telephone = numero_telephone;

  }
 
 // getters 
 
 public int getNumero_client() {
     return numero_client; 
 }


public String getNom() {
    return nom; 
}

public int getNumero_telephone() { 
    return numero_telephone; 
}

public String getMail() { 
    return mail; 
}

 
 // setters


public void setNom(String nom) { 
    this.nom = nom; 
}

public void setNumero_telephone(int numero_telephone) { 
    this.numero_telephone = numero_telephone; 
}

public void setMail(String mail) { 
    this.mail = mail;
}
 
 
@Override
public String toString() {
    String info = "Client n°" + numero_client + " : " + nom + " (Tél : " + numero_telephone + ")";
    if (mail != null && !mail.isEmpty()) {
        info += " Email : " + mail;
    }
    return info;
}
 

// methode placerApres
 public boolean placerApres ( Client autre_client){
   if( this.nom.compareTo(autre_client.getNom()) > 0) {
       return true;
   } 
   else{
       return false;
   }
     
     
 }
 
 
 // PARTIE II
 // Q3.a : Format fichier
    public String versFichier() {
        String s = numero_client + ": " + nom + ": " + numero_telephone;
        if (mail != null && !mail.isEmpty()) s += ": " + mail;
        return s;
    }
 
 
 
 
 
 
 
 
  
  
}
