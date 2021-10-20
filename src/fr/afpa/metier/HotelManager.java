package fr.afpa.metier;
import java.time.LocalDate;
import java.util.Scanner;

import fr.afpa.beans.Chambre;
import fr.afpa.beans.Options;
import fr.afpa.beans.Reservation;
import fr.afpa.beans.User;

public class HotelManager {

    public HotelManager() throws InterruptedException{
    	
    	User user = login();
    	
    	Chambre hotel[] = generateData();
    	
    	
              
        menu(user, hotel);
    }

    private User login(){

        User users[] = new User[2];
        
        // TABLEAU D'UTILISATEURS BRUT

        users[0] = new User("Alexandre","Leblond","Tyrenn","Password","user");
        users[1] = new User("adminNickname","adminName","admin","adminPassword","admin");

        Scanner in = new Scanner(System.in);

        String login = "";

        String password = "";
        
        User user = new User();

        boolean connected = false;
        
        // CONNEXION

        while(!connected){
            System.out.println("Veuillez entrer votre identifiant de connexion :");
            login = in.next();
            for(int i = 0; i<users.length;i++){
                if(users[i].getLogin().equalsIgnoreCase(login)){
                    System.out.println("Veuillez entrer votre mot de passe :");
                    password = in.next();
                    if(users[i].getPassword().equalsIgnoreCase(password) && users[i].getLogin().equalsIgnoreCase(login)){
                        connected = true;
                        user = users[i];
                        break;
                    }
                }
            }
        }
        return user;
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
    	
    	// AFFICHAGE DES CHAMBRES
    	
    	for(int i = 0; i < hotel.length;i++) {
    		if( hotel[i].isFree()  ){
    			System.out.println("               +-----------------------------------------------+");
    			System.out.println("               |	   La chambre " + hotel[i].getId() + " est libre.	       |");
    			System.out.println("               +-----------------------------------------------+");
    		}
    		else {
    			System.out.println("               +-----------------------------------------------+");
    			System.out.println("               |	   La chambre " + hotel[i].getId() + " est reservee.	       |");
    			System.out.println("               +-----------------------------------------------+");
    		}
    	}
    }
    
    private void isFull(Chambre[] hotel){
    	
    	int fullRooms = 0;
    	
    	// AFFICHAGE DES CHAMBRES QUI N'ONT PLUS DE DISPONIBILITES
    	
        for(int i = 0; i< hotel.length; i++){
            if(!hotel[i].isFree()){
                fullRooms++;
            }
        }
        
        System.out.println("Il y a " + fullRooms + " chambres qui sont pleines" );
    }
    
    private void isNotFull(Chambre[] hotel){
    	
    	int notFullRooms = 0;
    	
    	// AFFICHAGE DES CHAMBRES QUI ONT TOUJOURS AU MOINS UNE DISPONIBILITE
    	
        for(int i = 0; i< hotel.length; i++){
            if(hotel[i].isFree()){
                notFullRooms++;
            }
        }
        System.out.println("Il y a " + notFullRooms + " chambres qui ont encore des disponibilit�s" );
    }
    
    private void firstNotFull(Chambre[] hotel) {
    	
    	// AFFICHAGE DE LA PREMIERE CHAMBRE AVEC DES DISPONIBILITES AUJOURD'HUI
    	
        for(int i = 0; i< hotel.length; i++){
            if(hotel[i].isFree()){
                System.out.println("La premiere chambre libre est la chambre numero " + hotel[i].getId());
                i=hotel.length-1;
                break;
            }
        }
    }
    
    private void lastNotFull(Chambre[] hotel) {
    	
    	// AFFICHAGE DE LA DERNIERE CHAMBRE AVEC DES DISPONIBILITES AUJOURD'HUI
    	
        for(int i = hotel.length-1 ; i > 0; i--){
            if(hotel[i].isFree()){
                System.out.println("La premiere chambre libre depuis la fin est la chambre numero " + hotel[i].getId());
                i=0;
                break;
            }
        }
    }
    
    private void contact(User user,Chambre[] hotel) throws InterruptedException{
    	
    	Scanner in = new Scanner(System.in);
    	
    	String choix = "";
    	
    	System.out.println("\n\n+---------------------- -! VOUS ETES ACTUELLEMENT EN CONTACT AVEC L'UN DE NOS HOTELIERS !- ----------------------+\n\n");
    	
    	String sentence = "Employ� :  Bonjour ! Que pouvons nous faire pour vous aujourd'hui ?";
    	
    	String dialogue = sentence;
    	
    	for (int i=0;i<sentence.length();i++) {
		  System.out.print(sentence.charAt(i));
		  Thread.sleep(25);
		}
    	
    	System.out.println("\n");
    	
        System.out.println("  [A] - Je souhaiterais reserver une chambre.");
    	
        System.out.println("  [B] - Je souhaiterais annuler une reservation passee chez vous.");

        System.out.println("  [C] - Je souhaiterais modifier l'une de mes reservations passees chez vous.");
        
        choix = in.next();
        
        switch(choix) {
        	case "A":
        		
        		// PATH A - RESERVER UNE CHAMBRE
        		
        		// INITIALISATION DE LA PHRASE ET ENREGISTREMENT DU DIALOGUE
        		
        		sentence = "Vous : Je souhaiterais reserver une chambre.";
        		
        		dialogue = dialogue + ";" + sentence;
		
        		System.out.println(" ");
        		
        		// AFFICHAGE DU TEXTE CARACTERE PAR CARACTERE ET REAFFICHAGE DE L'HISTORIQUE DE DISCUSSION, CLEAR DE CONSOLE VIA 50 PRINTLN()
        		
            	for (int i=0;i<sentence.length();i++) {
          		  System.out.print(sentence.charAt(i));
          		  Thread.sleep(25);
          		}
            	
            	for(int space = 0;space<50;space++) {
            		System.out.println("");
            	}
            	
            	for(int j = 0;j<dialogue.length();j++) {
            		if(dialogue.charAt(j)!=';') {
            			System.out.print(dialogue.charAt(j));
            		}
            		else {
            			System.out.println(" ");
            			System.out.println(" ");
            		}
            	}
            	
            	boolean reservationTerminee = false;
            	
            	while(!reservationTerminee) {
            		
                	sentence = "Employ� :  Quelle chambre vous ferait plaisir ?";
                	
                	dialogue = dialogue + ";" + sentence;
                	
                	System.out.println(" ");
                	
                	for (int i=0;i<sentence.length();i++) {
                		  System.out.print(sentence.charAt(i));
                		  Thread.sleep(25);
                		}
                	
                	for(int space = 0;space<50;space++) {
                		System.out.println("");
                	}
                	
                	for(int j = 0;j<dialogue.length();j++) {
                		if(dialogue.charAt(j)!=';') {
                			System.out.print(dialogue.charAt(j));
                		}
                		else {
                			System.out.println(" ");
                			System.out.println(" ");
                		}
                	}
                	
                	System.out.println(" ");
                	
                    System.out.println("\n  [A] - Je pense que la Chambre Vue Piscine serait un choix eau poil !");
                    
                    System.out.println("\n  [B] - La Chambre Vue Jardin me remplit de pens�es positives.");
                    
                    System.out.println("\n  [C] - Jetons nous � l'eau ! Je choisis la Chambre Vue Oc�an.");
                    
                    System.out.println("\n  [D] - La vue est peut-�tre imprenable mais pas la chambre, je choisis la Chambre vue imprenable sur l'oc�an.");
                          	
                    System.out.println("\n  [E] - J'aimerais une suite � mon niveau, la suite CDA est-elle disponible ?");
                    
                    System.out.println("\n  [F] - J'�tais bourreau dans mes jeunes ann�es, partons pour la Suite Executive ?");
                    
                    System.out.println("\n  [G] - Je suis un grand fan du che guevara, je choisis la Suite Ambassadeur");
                    
                    System.out.println("\n  [H] - La vue est peut-�tre imprenable mais pas la chambre, je choisis la Chambre vue imprenable sur l'oc�an.");      

                    System.out.println("\n  [I] - Aucune id�e, pourriez vous me conseiller ?");
                	
                    choix = in.next();
                    
                    switch(choix) {
                    	case "A":
                    		
                    		// PATH A-A CHOIX DE CHAMBRE VUE PISCINE
                    		
                    		sentence = "Vous :  Je pense que la Chambre Vue Piscine serait un choix eau poil !";
                    		
                        	dialogue = dialogue + ";" + sentence;
                        	
                        	System.out.println(" ");
                        	
                        	for (int i=0;i<sentence.length();i++) {
                        		  System.out.print(sentence.charAt(i));
                        		  Thread.sleep(25);
                        		}
                        	
                        	for(int space = 0;space<50;space++) {
                        		System.out.println("");
                        	}
                        	
                        	for(int j = 0;j<dialogue.length();j++) {
                        		if(dialogue.charAt(j)!=';') {
                        			System.out.print(dialogue.charAt(j));
                        		}
                        		else {
                        			System.out.println(" ");
                        			System.out.println(" ");
                        		}
                        	}
                        	
                        	for(int k=0;k<hotel.length;k++) {
                        		
                        		if(hotel[k].getType().equals("Chambre Vue Piscine")) {
                        			
                            		if(hotel[k].AreReservationsFull()){
                            			
                                    	sentence = "Employ� : Tres bien ! Quand souhaitez vous reserver cette chambre ?";
                                    	
                                    	dialogue = dialogue + ";" + sentence;
                                    	
                                    	System.out.println(" ");
                                    	
                                    	for (int i=0;i<sentence.length();i++) {
                                    		  System.out.print(sentence.charAt(i));
                                    		  Thread.sleep(25);
                                    		}
                                    	
                                    	for(int space = 0;space<50;space++) {
                                    		System.out.println("");
                                    	}
                                    	
                                    	for(int j = 0;j<dialogue.length();j++) {
                                    		if(dialogue.charAt(j)!=';') {
                                    			System.out.print(dialogue.charAt(j));
                                    		}
                                    		else {
                                    			System.out.println(" ");
                                    			System.out.println(" ");
                                    		}
                                    	}
                                    	
                                    	System.out.println(" ");
                                    	
                                    	// PATH A
                                    	
                                        System.out.println("\nQuand souhaitez vous reserver votre chambre ? ( Entrez la date d'arrivee et de depart sous ce format : yyyy-mm-dd )");
                                    	
                                        System.out.println("Date d'arriv�e :");
                                        
                                        choix = in.next();
                                    	
                                        LocalDate dateA = LocalDate.parse(choix);
                                        
                                        System.out.println("Date de d�part :");
                                        
                                        choix = in.next();
                                    	
                                        LocalDate dateD = LocalDate.parse(choix);
                                        
                                        System.out.println("\nTout est bon pour moi ! Puis-je vous demander votre numero de carte de credit ?");
                                        
                                        choix = in.next();
                                        
                                        System.out.println("\nMerci Beaucoup ! Votre r�servation a bien ete prise en compte, un mail contant votre reservation vous sera adress� dans les plus brefs delais. A bientot chez Plaza Hotel ! ( Vous allez etre rediriges vers le menu dans quelques instants. )");
                                        
                                        int reservationVide = hotel[k].getAnEmptyReservation(hotel[k].getReservations());
                                        
                                        hotel[k].setReservations(dateA, dateD, reservationVide, user.getLogin());
                                        
                                        System.out.println(hotel[k].getReservations()[reservationVide].toString());
                                        
                                        Thread.sleep(5000);
                                        
                                        k=hotel.length;
                                        
                                        reservationTerminee = true;
                            		}
                            		else {
                            			
                            		}
                        			
                        		}
                    			
                        	}
                        	
                    		break;
                    }
            		
            	}
                
        		break;
        }
        
    	
        
        System.out.println(choix);
        
    }
    
    private void menu(User user, Chambre[] hotel) throws InterruptedException{
    	
    	boolean stop = false;

        Scanner in = new Scanner(System.in);
            
            if(user.getLogin().equals("admin")){
            	
            	while(!stop){
            		
    	            System.out.println("+---------------------- -! WELCOME TO STEPHANE PLAZA HOTEL !- ----------------------+\n\n  [A]  Etat de L'Hotel");
    	
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
    	                	menuReservation(hotel, user);
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
            	
            	String asciiHotel = "\n                                      /\\\r\n"
            			+ "                                      /\\\r\n"
            			+ "                                      /\\\r\n"
            			+ "                                      /\\\r\n"
            			+ "                                    _`=='_\r\n"
            			+ "                                 _-~......~-_\r\n"
            			+ "                             _--~............~--_\r\n"
            			+ "                       __--~~....................~~--__\r\n"
            			+ "           .___..---~~~................................~~~---..___,\r\n"
            			+ "            `=.________________________________________________,='\r\n"
            			+ "               @^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^@\r\n"
            			+ "                        |  |  I   I   II   I   I  |  |\r\n"
            			+ "                        |  |__I___I___II___I___I__|  |\r\n"
            			+ "                        | /___I_  I   II   I  _I___\\ |\r\n"
            			+ "                        |'_     ~~~~~~~~~~~~~~     _`|\r\n"
            			+ "                    __-~...~~~~~--------------~~~~~...~-__\r\n"
            			+ "            ___---~~......................................~~---___\r\n"
            			+ ".___..---~~~......................................................~~~---..___,\r\n"
            			+ " `=.______________________________________________________________________,='\r\n"
            			+ "    @^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^@\r\n"
            			+ "              |   |    | |    |  |    ||    |  |    | |    |   |\r\n"
            			+ "              |   |____| |____|  |    ||    |  |____| |____|   |\r\n"
            			+ "              |__________________|____||____|__________________|\r\n"
            			+ "            _-|_____|_____|_____|__|------|__|_____|_____|_____|-_ ";
                
                String asciiName = "                  ___ _                              _       _ \r\n"
                		+ "                 / _ \\ | __ _ ______ _    /\\  /\\___ | |_ ___| |\r\n"
                		+ "                / /_)/ |/ _` |_  / _` |  / /_/ / _ \\| __/ _ \\ |\r\n"
                		+ "               / ___/| | (_| |/ / (_| | / __  / (_) | ||  __/ |\r\n"
                		+ "               \\/    |_|\\__,_/___\\__,_| \\/ /_/ \\___/ \\__\\___|_|\r\n"
                		+ "                                                               \r\n"
                		+ "\r";
            	
            	while(stop==false){
            		
            		System.out.println(asciiHotel);
            		System.out.println(asciiName);
            		
    	            System.out.println("+---------------------- -! WELCOME TO STEPHANE PLAZA HOTEL !- ----------------------+\n\n  [A]  Etat de L'Hotel");
    	
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
    	                	contact(user, hotel);
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

    private void menuReservation(Chambre[] hotel, User user) {
    	
    	String choice = "";
    	boolean stop = true;
    	String typeChambre = "";
    	
    	Scanner in = new Scanner(System.in);
    	
    	
    	while(stop == true) {
    		
    		System.out.println("Quel type de logement souhaiteriez-vous? \n ");  
    		
            System.out.println("  [A]  Chambre vue piscine (44 mètres carrés)");
            
            System.out.println("  [B]  Chambre vue jardin (44 mètres carrés)");
            
            System.out.println("  [C]  Chambre vue ocean (44 mètres carrés)");
            
            System.out.println("  [D]  Chambre vue imprenable sur l'ocean (44 mètres carrés)");
            
            System.out.println("  [E]  Suite CDA (82 mètres carrés)");
            
            System.out.println("  [F]  Suite Executive (140 mètres carrés)");
            
            System.out.println("  [G]  Suite Ambassadeur (230 mètres carrés)");
            
            System.out.println("  [H]  Suite Royale (342 mètres carrés)");
                    
            System.out.println("  [Q]  Retour");
            
            choice = in.next();
            
            switch(choice.toUpperCase()) {
            case "A":
            	typeChambre = "Chambre Vue Piscine";
            	reserverChambre(hotel,user,typeChambre);
            	stop=false;
            	break;
            case "B":
            	typeChambre = "Chambre Vue Jardin";
            	reserverChambre(hotel,user,typeChambre);
            	stop=false;
            	break;
            case "C":
            	typeChambre = "Chambre Vue Océan";
            	reserverChambre(hotel,user,typeChambre);
            	stop=false;
            	break;
            case "D":
            	typeChambre = "Chambre vue imprenable sur l'océan";
            	reserverChambre(hotel,user,typeChambre);
            	stop=false;
            	break;
            case "E":
            	typeChambre = "Suite CDA";
            	reserverChambre(hotel,user,typeChambre);
            	stop=false;
            	break;
            case "F":
            	typeChambre = "Suite Executive";
            	reserverChambre(hotel,user,typeChambre);
            	stop=false;
            	break;
            case "G":
            	typeChambre = "Suite Ambassadeur";
            	reserverChambre(hotel,user,typeChambre);
            	stop=false;
            	break;
            case "H":
            	typeChambre = "Suite Royale";
            	reserverChambre(hotel,user,typeChambre);
            	stop=false;
            	break;
            case "Q":
            	stop = false;
            	break;
            	
            	
            }
    	}
    }
    private void reserverChambre(Chambre[] hotel, User user, String typeChambre) {
    	
    	Scanner in = new Scanner(System.in);
    	
    	
    	
    	for (int i = 0; i<hotel.length; i++)
    	{
    		int numeroChambre  = hotel[i].getId()+1;
    		
    		if (hotel[i].getType().contains(typeChambre) && hotel[i].isFree()) 
    		{
    			System.out.println(" La chambre numéro " + numeroChambre + " est disponible. \n");

    			System.out.println(" Voici les détails de celle-ci :\n");
    			
    			System.out.println(hotel[i].toString());
    			
    			System.out.println(" Souhaitez vous la réserver ?");
    			
    			System.out.println("  [A] Oui");
    			
    			System.out.println("  [B] Non");
    			
    	    	String choiceReserv = "";
    			
    			choiceReserv = in.next();
    			
    			
    			if (choiceReserv.toUpperCase().equals("A")) {
    				
    		    	LocalDate localDateA;
    		    	LocalDate localDateD;
    		    	String dateA;
    		    	String dateD;
    				
    				System.out.println(" Merci d'indiquer votre date d'arrivée (AAAA-MM-DD) : ");
    				dateA = in.next();
    				localDateA = LocalDate.parse(dateA);
    				
    				System.out.println(" Merci d'indiquer votre date de départ (AAAA-MM-DD) : ");
    				dateD = in.next();
    				localDateD = LocalDate.parse(dateD);
    				
    				if (hotel[i].isFree(localDateA,localDateD)) {
    					
    			    	int codeCB = 0;
    					
    					System.out.println(" Veuillez entrer votre code de carte bancaire :");
    					codeCB = in.nextInt();
    					
    			    	int indiceReserv = -1;
    					
    					indiceReserv = hotel[i].getAnEmptyReservation(hotel[i].getReservations());
    					
    					Reservation[] tabReserv = new Reservation[3]; 
    					
    					tabReserv = hotel[i].getReservations();
    					
    					tabReserv[indiceReserv] = new Reservation(localDateA, localDateD, user.getLogin());
    					
    					System.out.println("\n Votre réservation a bien été prise en compte! \n\n"); 
    					    					
    					i = hotel.length;
    							
    				}
    				
    				else {
    					
    					System.out.println(" Une réservation est déja enregistrée pendant cette période ou vos dates ne sont pas correctes \n");
    					// relance la fonction de réservation
    					
    					i = hotel.length;
    					
    				} 
    			}
    			
    			else {
    				
    				break;
    			}			
    		}
    		
    	}
	}
}
