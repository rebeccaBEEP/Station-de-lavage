/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * 
 * PROJET : Gestion de Station de Lavage
 * AUTEURS : Rebecca KOUADIO et Fatou KA
 * DATE : Janvier 2026
 * CONTEXTE : TP Conception et Programmation Objet
 * 
 */
package projet.partie.pkg1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class etablissement {
   private String nom_etablissement;
   private int nombre_client;
   private Client[] listeClient;
   private Rdv[][] planning = new Rdv[16][7];;
   private int id = 0;
   private Scanner sc = new Scanner(System.in);
   
   
   
   // constructeurs 
   
   public etablissement ( String nom_etablissement,int nombre_client,Client[] listeClient,Rdv[][] planning,int id){
       this.listeClient= listeClient ;
       this.nom_etablissement=nom_etablissement ;
       this.nombre_client= nombre_client;
       this.planning=planning;
       
   }
   
   // methode rechercher²
   // Dans etablissement.java

    public String rechercher (String nom, int numero_telephone){
        for(int i = 0; i < listeClient.length; i++){
            if (listeClient[i] != null) {

                if(numero_telephone == listeClient[i].getNumero_telephone() && nom.equalsIgnoreCase(listeClient[i].getNom())){
                    return listeClient[i].toString();
                }
            }
        }
        return null; // Pas trouvé
    }
   
  
    // Methode1: ajouter sans adresse
   
   public Client ajouter1(String nomclient, int numero_tel ){
       Client ajout1 =new Client(id+1, nomclient, numero_tel);
      
       int i = nombre_client-1;
       while(i>=0&& listeClient[i].placerApres(ajout1)){
           listeClient[i + 1] = listeClient[i];
        i--;
         }
   
    listeClient[i + 1] = ajout1;
    nombre_client++; // On incrémente le compteur global de l'établissement

    return ajout1;    
   }
   
   // Methode2 : ajouter avec adresse
   
   public Client ajouter2(String nomclient, int numero_tel,String mail ){
       Client ajout1 =new Client( mail,id+1, nomclient,numero_tel);
      
       int i = nombre_client-1;
       while(i>=0&& listeClient[i].placerApres(ajout1)){
           listeClient[i + 1] = listeClient[i];
        i--;
         }
   
    listeClient[i + 1] = ajout1;
    nombre_client++; // On incrémente le compteur global de l'établissement

    return ajout1;    
   }
 
   
   //  Methode rechercher
   
   public LocalDateTime rechercher(LocalDate jour){
   
       
       
        LocalDate aujourdhui = LocalDate.now();
        long diffJours = aujourdhui.until(jour, java.time.temporal.ChronoUnit.DAYS);


        if (diffJours < 0 || diffJours >= 7) {
            System.out.println("Erreur : La date doit être dans les 7 prochains jours.");
            return null;
        }
        int colonne = (int) diffJours;  // l'index de la colonne
        
        //affichage des heures disponibles pour ce jour
        
        System.out.println("Heures disponibles pour le " + jour + " :");
        ArrayList<LocalTime> disponibilite = new ArrayList<>();
        
        // Faire un parcours des lignes (les crenaus horaires)
        LocalTime heureStart = LocalTime.of(10, 0); // Ouverture à 10h00
        
        
        boolean found = false;
        for (int i = 0; i < 16; i++) {
            // Chaque index vaut 30 min. i=0 -> 10h, i=1 -> 10h30
            // Si la case est null, le créneau est libre


            if (planning[i][colonne] == null) {
                // Calcul de l'heure correspondant à l'index i
                LocalTime temps = heureStart.plusMinutes(i * 30);
                System.out.println("- " + temps);
                disponibilite.add(temps);
                found = true;
            }
        }
        
        
        if (!found) {
            System.out.println("Aucun créneau disponible ce jour-là.");
            return null;
        }
        
        //  Faire choisir une heure au client 
        System.out.println("Veuillez saisir l'heure souhaitée (format HH:mm) :");
        String saisie = sc.next();
        LocalTime heure = LocalTime.parse(saisie); 
        
        
        // Vérification sommaire que l'heure est bien dans la liste affichée
        if (disponibilite.contains(heure)) {
            // Retourner le créneau (Date et Heure) 
            return LocalDateTime.of(jour, heure);
        } else {
            System.out.println("Cette heure n'est pas disponible ");
            return null;
        }
        
   }    
        
    // Rechercher un créneau pour une heure donnée
    
    public LocalDateTime rechercher(LocalTime heureChoisie) {
        // 1. Calculer l'index de l'heure (ligne)
        // L'ouverture est à 10h. 
        if (heureChoisie.getHour() < 10 || heureChoisie.getHour() >= 18) {
            System.out.println("L'établissement est fermé à cette heure.");
            return null;
        }

        int minutes = heureChoisie.getMinute();
        if (minutes != 0 && minutes != 30) {
            System.out.println("Les créneaux sont toutes les 30 minutes ( 10:00, 10:30 ....).");
            return null;
        }

        // Calcul de l'index ligne (ex: 10h -> 0, 10h30 -> 1)
        int ligne = (heureChoisie.getHour() - 10) * 2 + (minutes == 30 ? 1 : 0);

        // 2. Afficher les jours disponibles pour cette heure 
        System.out.println("Jours disponibles à " + heureChoisie + " :");
        ArrayList<LocalDate> joursDispos = new ArrayList<>();
        LocalDate aujourdhui = LocalDate.now(); 

        boolean found = false;
        // On parcourt les colonnes (les 7 jours)
        for (int j = 0; j < 7; j++) {
            LocalDate dateDuJour = aujourdhui.plusDays(j); // 
            
            // Vérifier si c'est lundi (fermé)  et si le créneau est libre
            if (dateDuJour.getDayOfWeek() != java.time.DayOfWeek.MONDAY && planning[ligne][j] == null) {
                System.out.println("- " + dateDuJour);
                joursDispos.add(dateDuJour);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Aucun jour disponible pour cette heure.");
            return null;
        }

        // Faire choisir un jour au client 
        System.out.println("Veuillez saisir le jour souhaité (format AAAA-MM-JJ) :");
        String saisie = sc.next();
        LocalDate jourChoisi = LocalDate.parse(saisie); // 

        if (joursDispos.contains(jourChoisi)) {
            // Retourner le créneau (Date et Heure)
            return LocalDateTime.of(jourChoisi, heureChoisie);
        } else {
            System.out.println("Ce jour n'est pas disponible.");
            return null;
        }
    }
       
    // Methode Ajouter1 un rendezvous pour prestation express
    
    public Rdv ajouter1 (Client client ,LocalDateTime creneau,char categorie_vehicule,boolean nettoyageInterieur){
      
        PrestationExpress p = new PrestationExpress(categorie_vehicule, nettoyageInterieur);
        
        
        
        //  Calcul du prix via la méthode de la prestation
        int prix = (int) p.nettoyage(); 
        
        //  Création du Rendez-vous
        Rdv nouveauRdv = new Rdv(client, p, prix);
        
        //  Enregistrement dans le planning (Calcul des indices)
        // Colonne (Jour) :
        long diffJours = LocalDate.now().until(creneau.toLocalDate(), java.time.temporal.ChronoUnit.DAYS);
        int col = (int) diffJours;
        
        // Ligne (Heure) :
        int ligne = (creneau.getHour() - 10) * 2 + (creneau.getMinute() == 30 ? 1 : 0);
        
        // Vérification de sécurité (pour ne pas faire planter le programme)
        if (col >= 0 && col < 7 && ligne >= 0 && ligne < 16) {
            this.planning[ligne][col] = nouveauRdv; // On stocke dans le tableau
            return nouveauRdv; 
        } else {
            System.out.println("Erreur : Créneau hors planning.");
            return null;
        }
    }
    
     // ajout de rendezvous pour prestation sale
    
    public Rdv ajouter2 (Client client, LocalDateTime creneau, char categorie_vehicule) {
        
        PrestationSale p = new PrestationSale(categorie_vehicule);
        int prix = (int) p.nettoyage();
        
        Rdv nouveauRdv = new Rdv(client, p, prix);
        
        if (enregistrerDansPlanning(nouveauRdv, creneau)) {
            return nouveauRdv;
        }
        return null;
    }

    
    
    public Rdv ajouter3(Client client, LocalDateTime creneau, char categorie_vehicule, int typeSalissure) {
        
        PrestationTresSale p = new PrestationTresSale(categorie_vehicule, typeSalissure);
        int prix = (int) p.nettoyage();
        
        Rdv nouveauRdv = new Rdv(client, p, prix);
        
        if (enregistrerDansPlanning(nouveauRdv, creneau)) {
            return nouveauRdv;
        }
        return null;
    }

    
   
    // Cette méthode calcule la case [i][j] et stocke le rdv
   
    private boolean enregistrerDansPlanning(Rdv rdv, LocalDateTime creneau) {
        LocalDate aujourdhui = LocalDate.now();
        LocalDate jourRdv = creneau.toLocalDate();
        LocalTime heureRdv = creneau.toLocalTime();

        // 1. Calcul de la colonne (Jour)
        long diffJours = java.time.temporal.ChronoUnit.DAYS.between(aujourdhui, jourRdv);
        int col = (int) diffJours;

        // 2. Calcul de la ligne (Heure)
        // Formule : (Heure - 10h) * 2 + (1 si 30min)
        int ligne = (heureRdv.getHour() - 10) * 2 + (heureRdv.getMinute() == 30 ? 1 : 0);

        // 3. Vérifications de sécurité
        if (col >= 0 && col < 7 && ligne >= 0 && ligne < 16) {
            // On vérifie aussi si la place est libre
            if (this.planning[ligne][col] == null) {
                this.planning[ligne][col] = rdv; // Sauvegarde
                return true;
            } else {
                System.out.println("Erreur : Créneau déjà occupé.");
                return false;
            }
        } else {
            System.out.println("Erreur : Date ou heure hors planning.");
            return false;
        }
    }
    
    
    





// PARTIE 2 - QUESTION 1 : Méthode planifier
    
    
    public void planifier() {
        System.out.println("\n PLANIFICATION ");

        //  Gestion Client (Recherche + Création en 5 lignes)
        System.out.print("Nom et Tél : ");
        String nom = sc.next(); int tel = sc.nextInt();
        Client cl = null;
        for (int i = 0; i < nombre_client; i++) 
            if (listeClient[i] != null && listeClient[i].getNumero_telephone() == tel) cl = listeClient[i];
        
        if (cl == null) cl = this.ajouter1(nom, tel); // On crée si pas trouvé

        //  Choix Date (Direct avec try/catch compact)
        System.out.print("Date (AAAA-MM-JJ) : ");
        LocalDateTime date = null;
        try { date = this.rechercher(LocalDate.parse(sc.next())); } catch (Exception e) { return; }
        if (date == null) return;

        //  Choix Prestation & Validation
        System.out.print("Catégorie (A/B/C) : ");
        char cat = sc.next().toUpperCase().charAt(0);
        System.out.print("Type (1:Express, 2:Sale, 3:Très Sale) : ");
        int type = sc.nextInt();
        
        Rdv res = null;
        if (type == 1) {
            System.out.print("Intérieur (true/false) ? ");
            res = this.ajouter1(cl, date, cat, sc.nextBoolean());
        } else if (type == 2) {
            res = this.ajouter2(cl, date, cat);
        } else if (type == 3) {
            System.out.print("Niveau Salissure (int) ? ");
            res = this.ajouter3(cl, date, cat, sc.nextInt());
        }

        if (res != null) System.out.println(" OK ! Prix : " + res.prix + " €");
    }
    
    
    
    //  MÉTHODES AFFICHER 

    // 1. Afficher planning d'un jour
    public void afficherPlanning(LocalDate jour) {
        LocalDate now = LocalDate.now();
        int col = (int) java.time.temporal.ChronoUnit.DAYS.between(now, jour);
        
        if (col < 0 || col >= 7) {
            System.out.println("Date hors du planning (7 jours max).");
            return;
        }

        System.out.println("--- Planning du " + jour + " ---");
        boolean vide = true;
        for (int lig = 0; lig < 16; lig++) {
            if (planning[lig][col] != null) {
                LocalTime h = LocalTime.of(10, 0).plusMinutes(lig * 30);
                System.out.println(h + " : " + planning[lig][col]); // Affiche le RDV
                vide = false;
            }
        }
        if (vide) System.out.println("Aucun RDV ce jour-là.");
    }

    // 2. Afficher client(s) par nom ou téléphone
    public void afficherClient(String nom, int tel) {
        boolean trouve = false;
        for (int i = 0; i < nombre_client; i++) {
            if (listeClient[i] != null) {
                // On vérifie si le nom OU le téléphone correspond
                boolean matchNom = (nom != null && listeClient[i].getNom().equalsIgnoreCase(nom));
                boolean matchTel = (tel != 0 && listeClient[i].getNumero_telephone() == tel);
                
                if (matchNom || matchTel) {
                    System.out.println(listeClient[i]);
                    trouve = true;
                }
            }
        }
        if (!trouve) System.out.println("Aucun client trouvé.");
    }

    // 3. Afficher les RDV d'un client (par ID)
    public void afficherRdvClient(int idClient) {
        System.out.println("Historique RDV du client n°" + idClient + "");
        boolean trouve = false;
        for (int col = 0; col < 7; col++) {
            for (int lig = 0; lig < 16; lig++) {
                if (planning[lig][col] == null || idClient != planning[lig][col].getClient().getNumero_client()) {
                } else {
                    LocalDate d = LocalDate.now().plusDays(col);
                    LocalTime h = LocalTime.of(10, 0).plusMinutes(lig * 30);
                    System.out.println("Le " + d + " à " + h + " : " + planning[lig][col]);
                    trouve = true;
                }
            }
        }
        if (!trouve) System.out.println("Aucun RDV trouvé pour ce client.");
    }
    
        
    // Methode verFichiersClients
    public void versFichierClients(String nomF) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomF))) {
            for (int i=0; i<nombre_client; i++) {
                if (listeClient[i] != null) {
                    bw.write(listeClient[i].versFichier());
                    bw.newLine();
                }
            }
            System.out.println("Clients sauvegardés.");
        } catch (IOException e) { System.out.println("Erreur sauvegarde clients."); }
    }

    // Methode depuisfichiers
    public void depuisFichierClients(String nomF) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomF))) {
            String lig;
            while ((lig = br.readLine()) != null) {
                String[] t = lig.split(":");
                int id = Integer.parseInt(t[0].trim());
                String nom = t[1].trim();
                int tel = Integer.parseInt(t[2].trim());
                
                Client c;
                if (t.length > 3) c = new Client(t[3].trim(), id, nom, tel);
                else c = new Client(id, nom, tel);
                
                // Ajout manuel au tableau
                if (nombre_client < listeClient.length) {
                    listeClient[nombre_client++] = c;
                    if (id > this.id) this.id = id; // Met à jour le compteur d'ID
                }
            }
            System.out.println("Clients chargés.");
        } catch (Exception e) { System.out.println("Fichier clients vide ou absent."); }
    }

    // 3. Sauvegarder RDV
    public void versFichierRDV(String nomF) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomF))) {
            for (int c=0; c<7; c++) {
                for (int l=0; l<16; l++) {
                    if (planning[l][c] != null) {
                        LocalDateTime date = LocalDateTime.of(LocalDate.now().plusDays(c), LocalTime.of(10,0).plusMinutes(l*30));
                        bw.write(planning[l][c].versFichier(date));
                        bw.newLine();
                    }
                }
            }
            System.out.println("RDV sauvegardés.");
        } catch (IOException e) { System.out.println("Erreur sauvegarde RDV."); }
    }

    // 4. Charger RDV
    public void depuisFichierRDV(String nomF) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomF))) {
            String lig;
            while ((lig = br.readLine()) != null) { // Lit la date (Ligne 1)
                LocalDateTime date = LocalDateTime.parse(lig);
                
                int idCli = Integer.parseInt(br.readLine().trim()); // Lit l'ID (Ligne 2)
                
                // Recherche du client pour recréer le lien
                Client cl = null;
                for(int i=0; i<nombre_client; i++) if(listeClient[i].getNumero_client() == idCli) cl = listeClient[i];
                
                String pStr = br.readLine(); // Lit la prestation (Ligne 3)
                String[] t = pStr.split(":");
                char cat = t[0].trim().charAt(0);
                
                // Recréation du RDV selon le type
                if (t.length == 3) { // Express ou Très Sale
                    String milieu = t[1].trim();
                    if (milieu.equals("true") || milieu.equals("false")) 
                        ajouter1(cl, date, cat, Boolean.parseBoolean(milieu));
                    else 
                        ajouter3(cl, date, cat, Integer.parseInt(milieu));
                } else { // Sale
                    ajouter2(cl, date, cat);
                }
            }
            System.out.println("RDV chargés.");
        } catch (Exception e) { System.out.println("Fichier RDV vide ou absent."); }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    }
        
        
        
        
        
        
        
        
        
        
        
    
    
        
       
       












   
   


   



