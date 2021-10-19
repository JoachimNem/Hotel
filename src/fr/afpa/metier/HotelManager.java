package fr.afpa.metier;
import java.util.Scanner;

import fr.afpa.beans.Chambre;
import fr.afpa.beans.Options;
import fr.afpa.beans.User;

public class HotelManager {

    public HotelManager(){
    	
    	String tokenRole = login();
    	
    	Chambre hotel[] = generateData();
    	
    	
              
        menu(tokenRole, hotel);
    }

    private String login(){

        User users[] = new User[2];

        users[0] = new User("Alexandre","Leblond","Tyrenn","Password","user");
        users[1] = new User("adminNickname","adminName","admin","adminPassword","admin");

        Scanner in = new Scanner(System.in);

        String login = "";

        String password = "";
        
        String tokenRole = "";

        boolean connected = false;

        while(!connected){
            System.out.println("Veuillez entrer votre identifiant de connexion :");
            login = in.next();
            for(int i = 0; i<users.length;i++){
                if(users[i].getLogin().equalsIgnoreCase(login)){
                    System.out.println("Veuillez entrer votre mot de passe :");
                    password = in.next();
                    if(users[i].getPassword().equalsIgnoreCase(password) && users[i].getLogin().equalsIgnoreCase(login)){
                        connected = true;
                        tokenRole = users[i].getRole();
                        break;
                    }
                }
            }
        }
        return tokenRole;
    }

    private Chambre[] generateData(){

        String entry = "";

            String listeChambresCsv[] = {"Type chambre;Taille;Vues;Occupation;tarif;Nombre de chambre;Options",
            "Chambre Vue Piscine;44 mètres carrés;Piscine Centrale;2 adultes et 2 enfants de moins de 12 ans;300;7;Fer et planche à repasser sur demande|Téléphone |Télévision par câble|Climatisation|Bouilloire électrique|Concierge 24h/24",
            "Chambre Vue Jardin;44 mètres carrés;Jardin, Forêt ou Golf;2 adultes et 2 enfants de moins de 12 ans;314;12;Fer et planche à repasser sur demande|Téléphone |Télévision par câble|Climatisation|Bouilloire électrique|Concierge 24h/24",
            "Chambre Vue Océan;44 mètres carrés;Partielle Océan et Forêt ou Golf;2 adultes et 2 enfants de moins de 12 ans;350;8;Fer et planche à repasser sur demande|Téléphone |Accès Internet haut débit sans fil|Lecteur DVD sur demande|Télévision par câble|Climatisation|Service aux chambres 24h/24|Concierge 24h/24",
            "Chambre vue imprenable sur l'océan;44 mètres carrés;Océan;2 adultes et 2 enfants de moins de 12 ans;350;10;Fer et planche à repasser sur demande|Téléphone |Accès Internet haut débit sans fil|Lecteur DVD sur demande|Télévision par câble|Climatisation|Service aux chambres 24h/24|Concierge 24h/24",
            "Suite CDA;82 mètres carrés;Océan et Golf;2 adultes et 2 enfants de moins de 12 ans;450;12;Sèche-cheveux|Coffre-fort dans la chambre|Minibar|Téléphone |Accès Internet haut débit sans fil|Lecteur DVD sur demande|Télévision par câble|Climatisation|Service aux chambres 24h/24|Concierge 24h/24",
            "Suite Executive;140 mètres carrés;Océan;2 adultes et 2 enfants de moins de 12 ans;550;5;Sèche-cheveux|Coffre-fort dans la chambre|Minibar|Téléphone |Accès Internet haut débit sans fil|Lecteur DVD sur demande|Télévision par câble|Climatisation|Service aux chambres 24h/24|Concierge 24h/24",
            "Suite Ambassadeur;230 mètres carrés;Océan;2 adultes et 2 enfants de moins de 12 ans;1650;7;Sèche-cheveux|Coffre-fort dans la chambre|Minibar|Téléphone |Accès Internet haut débit sans fil|Lecteur DVD sur demande|Télévision par câble|Climatisation|Service aux chambres 24h/24|Concierge 24h/24",
            "Suite Royale;342 mètres carrés;Océan;2 adultes et 2 enfants de moins de 12 ans;2400;4;Sèche-cheveux|Coffre-fort dans la chambre|Minibar|Téléphone |Accès Internet haut débit sans fil|Lecteur DVD sur demande|Télévision par câble|Climatisation|Service aux chambres 24h/24|Concierge 24h/24"};
            
            String attribute = "";
            int start = 0;
            boolean stopCat=false;

            Chambre[] Hotel = new Chambre[65];

            int nbChambres = 0;

            String type = "";
            String taille = "";
            String vue = "";
            String occupation = "";
            int tarif = 0;
            int total = 0;
            
            boolean fer = false;
            boolean tel = false;
            boolean television = false;
            boolean clim = false;
            boolean bouilloire = false;
            boolean concierge = false;
            boolean internet = false;
            boolean dvd = false;
            boolean services = false;
            boolean sechecheveux = false;
            boolean coffre = false;
            boolean minibar = false;
            String optionSelected = "";
            Options options = new Options();

            // Boucle parcourant chaque itération du tableau CSV

            for(int i = 1; i<listeChambresCsv.length;i++){

                start=0;

                // Boucle parcourant la chambre à l'itération listeChambresCsv[i] j ne peut donc pas dépasser la taille de la chaine de caractère à cet emplacement

                for(int j=0;j<listeChambresCsv[i].length();j++){

                    // stop cat est réinitialisé pour permettre a la boucle récupérant l'attribut en cours de se relancer ( on reset aussi attribute )
                    
                    stopCat=false;

                    attribute="";

                    // Si le caractère est un ; on ne le met pas dans l'entrée

                    if(listeChambresCsv[i].charAt(j)!=';'){

                        entry = entry + listeChambresCsv[i].charAt(j);

                    }

                    // Si le caractère est un ; ou que l'on a atteint la fin de la chaine
                    
                    if(listeChambresCsv[i].charAt(j)==';' || j == listeChambresCsv[i].length()-1){

                        if(j == listeChambresCsv[i].length()-1){
                            entry = entry + listeChambresCsv[i].charAt(j);
                        }

                        // Boucle parcourant la première itération de listeChambresCsv afin de récupérer la propriété actuelle que nous parcourons

                        for(int k=start; !stopCat ;k++){
                            if(listeChambresCsv[0].charAt(k)!=';' && k<listeChambresCsv[0].length()-1){
                                attribute = attribute + listeChambresCsv[0].charAt(k);
                                start++;
                            }
                            else{
                                stopCat=true;
                                start++;
                            }
                        }

                        // Choix de l'attribut en fonction de l'entrée récupérée

                        switch(attribute){
                            case "Type chambre":
                                type = entry;
                                break;
                            case "Taille":
                                taille = entry;
                                break;
                            case "Vues":
                                vue = entry;
                                break;
                            case "Occupation":
                                occupation = entry;
                                break;
                            case "tarif":
                                tarif = Integer.parseInt(entry);
                                break;
                            case "Nombre de chambre":
                                nbChambres = Integer.parseInt(entry);
                                break;
                            default:

                            // Parcours des caracteres de l'option

                            for(int m=0;m<entry.length();m++){
                                if(entry.charAt(m)=='|' || m==entry.length()-1){
                                    switch(optionSelected){
                                        case "Fer et planche à repasser sur demande":{
                                            fer = true;
                                            break;
                                        }
                                        case "Téléphone ":{
                                            tel = true;
                                            break;
                                        }
                                        case "Télévision par câble":{
                                            television = true;
                                            break;
                                        }
                                        case "Climatisation":{
                                            clim = true;
                                            break;
                                        }
                                        case "Bouilloire électrique":{
                                            bouilloire = true;
                                            break;
                                        }
                                        case "Concierge 24h/24":{
                                            concierge = true;
                                            break;
                                        }
                                        case "Accès Internet haut débit sans fil":{
                                            internet = true;
                                            break;
                                        }
                                        case "Lecteur DVD sur demande":{
                                            dvd = true;
                                            break;
                                        }
                                        case "Service aux chambres 24h/24":{
                                            services = true;
                                            break;
                                        }
                                        case "Minibar":{
                                            minibar = true;
                                            break;
                                        }
                                        case "Sèche-cheveux":{
                                            sechecheveux = true;
                                            break;
                                        }
                                        case "Coffre-fort dans la chambre":{
                                            coffre = true;
                                            break;
                                        }
                                    }
                                    optionSelected="";
                                }

                                else{
                                    optionSelected = optionSelected + entry.charAt(m);
                                }

                                options = new Options(fer, tel,television,clim,bouilloire,concierge,internet,dvd,services,sechecheveux,coffre,minibar);

                            }
                        }

                        entry="";

                    }
                    
                } // Fin du parcours du CSV

                fer = false;
                tel = false;
                television = false;
                clim = false;
                bouilloire = false;
                concierge = false;
                internet = false;
                dvd = false;
                services = false;
                sechecheveux = false;
                coffre = false;
                minibar = false;

                // Création de chaque chambre et insertion de celles ci dans le tableau d'objets chambre

                if(!type.equals("") && !taille.equals("") && !vue.equals("") && !occupation.equals("") && tarif !=0 && nbChambres>0){

                    for(; nbChambres > 0 ;){

                        Hotel[total] = new Chambre(0,type,taille,vue,occupation,tarif,options);

                        nbChambres--; 
                        
                        System.out.println(Hotel[i]);

                        total++;
                    
                    }

                }

            }
            return Hotel;
    }
    
    private void showRooms(Chambre[] hotel){
    	
    	for(int i = 0; i < hotel.length;i++) {
    		if( hotel[i].isFree()  ){
    			System.out.println("+-----------------------------------------------+");
    			System.out.println("|	   La chambre " + hotel[i].getId() + " est libre.	        |");
    			System.out.println("+-----------------------------------------------+");
    		}
    		else {
    			System.out.println("+-----------------------------------------------+");
    			System.out.println("|	   La chambre " + hotel[i].getId() + " est reservee.	        |");
    			System.out.println("+-----------------------------------------------+");
    		}
    	}
    }
    
    private void isFull(Chambre[] hotel){
    	
    	int fullRooms = 0;
    	
        for(int i = 0; i< hotel.length; i++){
            if(!hotel[i].isFree()){
                fullRooms++;
            }
        }
        
        System.out.println("Il y a " + fullRooms + " chambres qui sont pleines" );
    }
    
    private void isNotFull(Chambre[] hotel){
    	
    	int notFullRooms = 0;
    	
        for(int i = 0; i< hotel.length; i++){
            if(hotel[i].isFree()){
                notFullRooms++;
            }
        }
        System.out.println("Il y a " + notFullRooms + " chambres qui ont encore des disponibilit�s" );
    }
    
    private void firstNotFull(Chambre[] hotel) {
        for(int i = 0; i< hotel.length; i++){
            if(hotel[i].isFree()){
                System.out.println("La premiere chambre libre est la chambre numero " + hotel[i].getId());
                i=hotel.length-1;
                break;
            }
        }
    }
    
    private void lastNotFull(Chambre[] hotel) {
        for(int i = hotel.length-1 ; i > 0; i--){
            if(hotel[i].isFree()){
                System.out.println("La premiere chambre libre depuis la fin est la chambre numero " + hotel[i].getId());
                i=0;
                break;
            }
        }
    }
    
    private void contact(Chambre[] hotel){
    	
    	Scanner in = new Scanner(System.in);
    	
    	String choix = "";
    	
    	System.out.println("\n\n+---------------------- -! VOUS ETES ACTUELLEMENT EN CONTACT AVEC L'UN DE NOS HOTELIERS !- ----------------------+\n\n");
    	
    	System.out.println("- Bonjour ! Que pouvons nous faire pour vous aujourd'hui ?");
    	
        System.out.println("  [A] - Je souhaiterais reserver une chambre.");
    	
        System.out.println("  [B] - Je souhaiterais annuler une reservation passee chez vous.");

        System.out.println("  [C] - Je souhaiterais modifier l'une de mes reservations passees chez vous.");
        
        choix = in.next();
        
        System.out.println(choix);
        
    }
    
    private void menu(String tokenRole, Chambre[] hotel){
        
        boolean stop = false;

        Scanner in = new Scanner(System.in);
            
            if(tokenRole.equals("admin")){
            	
            	while(stop==false){
            		
    	            System.out.println("\n\n+---------------------- -! WELCOME TO STEPHANE PLAZA HOTEL !- ----------------------+\n\n  [A]  Etat de L'Hotel");
    	
    	            System.out.println("  [B]  Afficher le nombre de chambres chambres reservees");
    	
    	            System.out.println("  [C]  Afficher le nombre de chambres chambres libres");
    	
    	            System.out.println("  [D]  Afficher le numero de la premiere chambre libre");
    	
    	            System.out.println("  [E]  Afficher le numero de la derniere chambre libre");
    	
    	            System.out.println("  [F]  Reserver une chambre");
    	            
    	            System.out.println("  [G]  Liberer une chambre");
    	
    	            System.out.println("  [H]  Modifier une reservation");
    	
    	            System.out.println("  [I]  Annuler une reservation");
    	
    	            System.out.println("  [Q]  Quitter l'interface\n\n+---------------------- -! WELCOME TO STEPHANE PLAZA HOTEL !- ----------------------+\n\n");
    	
    	            String choix = in.next();
    	
    	            switch(choix.toUpperCase()){
    	                case "A":
    	                	showRooms(hotel);
    	                    break;
    	                case "B":
    	                	isFull(hotel);
    	                    break;
    	                case "C":
    	                	isNotFull(hotel);
    	                    break;
    	                case "D":
    	                	firstNotFull(hotel);
    	                    break;
    	                case "E":
    	                	lastNotFull(hotel);
    	                    break;
    	                case "F":
    	                	firstFreeCritere(hotel);
    	                	break;
    	                case "Q":
    	                    System.out.println("\nMerci d'avoir utilis\u00e9 l'application Stephane Plaza Hotel !");
    	                    System.out.println("*Fin de transmission*\n");
    	                    System.exit(0);
    	                    break;
    	                    
    	            }
    	            
    	        }
            	
            }
            
            else{
            	
            	while(stop==false){
            		
    	            System.out.println("\n\n+---------------------- -! WELCOME TO STEPHANE PLAZA HOTEL !- ----------------------+\n\n  [A]  Etat de L'Hotel");
    	
    	            System.out.println("  [B]  Afficher le nombre de chambres chambres reservees");
    	
    	            System.out.println("  [C]  Afficher le nombre de chambres chambres libres");
    	
    	            System.out.println("  [D]  Afficher le numero de la premiere chambre libre");
    	
    	            System.out.println("  [E]  Afficher le numero de la derniere chambre libre");
    	
    	            System.out.println("  [F]  Contacter l'hotel");
    	            
    	            System.out.println("  [Q]  Quitter l'interface\n\n+---------------------- -! WELCOME TO STEPHANE PLAZA HOTEL !- ----------------------+\n\n");
    	
    	            String choix = in.next();
    	
    	            switch(choix.toUpperCase()){
    	                case "A":
    	                	showRooms(hotel);
    	                    break;
    	                case "B":
    	                	isFull(hotel);
    	                    break;
    	                case "C":
    	                	isNotFull(hotel);
    	                    break;
    	                case "D":
    	                	firstNotFull(hotel);
    	                    break;
    	                case "E":
    	                	lastNotFull(hotel);
    	                    break;
    	                case "F":{
    	                	contact(hotel);
    	                	break;
    	                }
    	                case "Q":
    	                    System.out.println("\nMerci d'avoir utilis\u00e9 l'application Stephane Plaza Hotel !");
    	                    System.out.println("*Fin de transmission*\n");
    	                    System.exit(0);
    	                    break;
    	                    
    	            }
    	            
    	        }
            }
	        
        in.close();
        
    }    

    private void firstFreeCritere(String tokenRole, Chambre[] hotel) {
    	
    	String choice = "";
    	boolean stop = false;
    	
    	Scanner in = new Scanner(System.in);
    	choice = in.next();
    	
    	while(stop== true) {
    		
    		System.out.println("Quel type de logement souhaiteriez-vous? ");  
    		
            System.out.println("  [A]  Chambre vue piscine (44 mètres carrés");
            
            System.out.println("  [B]  Chambre vue jardin (44 mètres carrés");
            
            System.out.println("  [C]  Chambre vue ocean (44 mètres carrés");
            
            System.out.println("  [D]  Chambre vue imprenable sur l'ocean (44 mètres carrés");
            
            System.out.println("  [E]  Suite CDA (82 mètres carrés");
            
            System.out.println("  [F]  Suite Executive (140 mètres carrés");
            
            System.out.println("  [G]  Suite Ambassadeur (230 mètres carrés");
            
            System.out.println("  [H]  Suite Royale (342 mètres carrés");
                    
            System.out.println("  [Q]  Retour");
            
            switch(choice.toUpperCase()) {
            case "A":
            	
            	for (int i = 0; i<hotel.length; i++)
            	{
            		if (hotel[i].getType().contains("Chambre Vue Piscine") && (hotel[i].getReservations().length < 3)) 
            		{
            			
            		}
            	}
            	break;
            case "B":
            	break;
            case "C":
            	break;
            case "D":
            	break;
            case "E":
            	break;
            case "F":
            	break;
            case "G":
            	break;
            case "H":
            	break;
            case "Q":
            	menu();
            	break;
            	
            	
            }
    	}
        
    	System.out.println("Quel type de chambre souhaiteriez-vous? ");

    	hotel[0].getOptions().toString();
    }
}
