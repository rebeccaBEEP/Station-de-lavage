
package projet.partie.pkg1;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        // 1. INITIALISATION
        Client[] tabClients = new Client[100]; 
        Rdv[][] tabPlanning = new Rdv[16][7];  
        etablissement station = new etablissement("Super Lavage", 0, tabClients, tabPlanning, 0);
        
        // 2. CHARGEMENT AUTOMATIQUE (Question 4)
        System.out.println("--- Chargement des données ---");
        station.depuisFichierClients("clients.txt");
        station.depuisFichierRDV("rdv.txt");

        Scanner sc = new Scanner(System.in);
        boolean continuer = true;

        // 3. MENU
        while (continuer) {
            System.out.println("\n=== STATION DE LAVAGE ===");
            System.out.println("1. Planifier un RDV");
            System.out.println("2. Afficher planning d'un jour");
            System.out.println("3. Historique client");
            System.out.println("4. Quitter et Sauvegarder");
            System.out.print("Votre choix : ");
            
            int choix = 0;
            try { choix = sc.nextInt(); } catch(Exception e) { sc.next(); } // Sécurité saisie

            switch (choix) {
                case 1:
                    station.planifier(); // Ta méthode Question 1
                    break;
                case 2:
                    System.out.print("Date à afficher (AAAA-MM-JJ) : ");
                    try {
                        String s = sc.next();
                        station.afficherPlanning(LocalDate.parse(s));
                    } catch(Exception e) { System.out.println("Date invalide."); }
                    break;
                case 3:
                    System.out.print("ID du client : ");
                    station.afficherRdvClient(sc.nextInt());
                    break;
                case 4:
                    System.out.println("--- Sauvegarde et fermeture ---");
                    station.versFichierClients("clients.txt");
                    station.versFichierRDV("rdv.txt");
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix incorrect.");
            }
        }
        sc.close();
    }
}