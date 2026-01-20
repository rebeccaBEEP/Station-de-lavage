package projet.partie.pkg1;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        
        // 1. INITIALISATION
        
        // On crée les tableaux vides nécessaires au constructeur de etablissement
        Client[] tabClients = new Client[100]; 
        Rdv[][] tabPlanning = new Rdv[16][7];  
        
        // Création de la station (Respect du constructeur de etablissement)
        etablissement station = new etablissement("Super Lavage", 0, tabClients, tabPlanning, 0);
        
        Scanner sc = new Scanner(System.in);
        boolean continuer = true;

        System.out.println("=== GESTION STATION DE LAVAGE ===");

        
        //  BOUCLE PRINCIPALE
      
        while (continuer) {
            System.out.println("\n- MENU -");
            System.out.println("1. Nouveau Rendez-vous");
            System.out.println("2. Rechercher disponibilités (par Heure)");
            System.out.println("3. Quitter");
            System.out.print("Choix : ");
            
            int choix = 0;
            if (sc.hasNextInt()) {
                choix = sc.nextInt();
                sc.nextLine(); 
            } else {
                sc.next();
                continue;
            }

            switch (choix) {
                case 1:
                    gererNouveauRdv(station, sc);
                    break;
                    
                case 2:
                    System.out.print("Heure souhaitée (HH:mm) : ");
                    String hStr = sc.next();
                    try {
                        LocalTime h = LocalTime.parse(hStr);
                        
                        // Appel de la méthode rechercher(LocalTime)
                        station.rechercher(h); 
                    } catch (DateTimeParseException e) {
                        System.out.println("Format d'heure invalide.");
                    }
                    break;
                    
                case 3:
                    System.out.println("Au revoir !");
                    continuer = false;
                    break;
                    
                default:
                    System.out.println("Choix invalide.");
            }
        }
        sc.close();
    }

    
    // MÉTHODE POUR GÉRER LA PRISE DE RDV (CAS 1)
    
    public static void gererNouveauRdv(etablissement station, Scanner sc) {
        try {
            //  ÉTAPE A : IDENTIFICATION CLIENT 
            System.out.print("Nom du client : ");
            String nom = sc.nextLine();
            
            System.out.print("Téléphone (entier) : ");
            int tel = sc.nextInt();
            sc.nextLine(); // Vider le buffer
            
            // On vérifie si le client existe
            String infoClient = station.rechercher(nom, tel);
            Client leClient;

            if (infoClient == null) {
                System.out.println("Nouveau client détecté.");
                System.out.print("Voulez-vous ajouter un email ? (o/n) : ");
                String rep = sc.nextLine();
                
                if (rep.equalsIgnoreCase("o")) {
                    System.out.print("Email : ");
                    String mail = sc.nextLine();
                    // Appel de ajouter2 (avec mail)
                    leClient = station.ajouter2(nom, tel, mail);
                } else {
                    // Appel de ajoute1r (sans mail, attention à l'orthographe de votre méthode)
                    leClient = station.ajouter1(nom, tel);
                }
                System.out.println("Client créé : " + leClient.getNom());
            } else {
                System.out.println("Client retrouvé : " + infoClient);
                
                leClient = new Client(0, nom, tel); 
            }

            // ÉTAPE B : CHOIX DU CRÉNEAU 
            System.out.print("Date du RDV (AAAA-MM-JJ) : ");
            String dateStr = sc.next();
            LocalDate date = LocalDate.parse(dateStr);
            
            // Recherche des heures pour cette date
            LocalDateTime creneau = station.rechercher(date);
            
            if (creneau == null) {
                return; // Annulation si pas de créneau choisi
            }

            // ÉTAPE C : CHOIX PRESTATION
            System.out.print("Catégorie véhicule (A, B, C) : ");
            String catStr = sc.next().toUpperCase();
            char categorie = catStr.charAt(0);

            System.out.println("Type de prestation :");
            System.out.println("1. Express");
            System.out.println("2. Sale");
            System.out.println("3. Très Sale");
            System.out.print("Votre choix : ");
            int typePresta = sc.nextInt();

            Rdv rdvConfirme = null;

            switch (typePresta) {
                case 1: // Express
                    System.out.print("Nettoyage intérieur ? (true/false) : ");
                    boolean interieur = sc.nextBoolean();
                    // Appel de ajouter1
                    rdvConfirme = station.ajouter1(leClient, creneau, categorie, interieur);
                    break;
                    
                case 2: // Sale
                    // Appel de ajouter2
                    rdvConfirme = station.ajouter2(leClient, creneau, categorie);
                    break;
                    
                case 3: // Très Sale
                    System.out.print("Niveau de salissure (1 à 5) : ");
                    int salissure = sc.nextInt();
                    // Appel de ajouter3 (nommé ainsi dans votre fichier)
                    rdvConfirme = station.ajouter3(leClient, creneau, categorie, salissure);
                    break;
                    
                default:
                    System.out.println("Type de prestation inconnu.");
            }

            //  ÉTAPE D : CONFIRMATION 
            if (rdvConfirme != null) {
                System.out.println("\n SUCCÈS ! Rendez-vous enregistré.");
                System.out.println(rdvConfirme.toString());
            } else {
                System.out.println("\n ECHEC. Créneau indisponible ou erreur.");
            }

        } catch (Exception e) {
            System.out.println("Erreur de saisie : " + e.getMessage());
            sc.nextLine(); // Nettoyage préventif du scanner
        }
    }
}