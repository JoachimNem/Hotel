package fr.afpa.beans;

import java.time.LocalDate;

public class Chambre{

    private int id;
    private String type;
    private String taille;
    private String vue;
    private String occupation;
    private int tarif;
    private Reservation reservation[] = new Reservation[3];

    public static int totalChambres = 0;

    private Options options = new Options();
    
    boolean isFree;

    // CONSTRUCTEUR 

    public Chambre(){

    }

    public Chambre(int id,String type,String taille,String vue,String occupation,int tarif, Options options){
        this.id=totalChambres;
        this.type=type;
        this.taille=taille;
        this.vue=vue;
        this.occupation=occupation;
        this.tarif=tarif;

        this.options=options;
        totalChambres++;
    }

    // GETTERS

    public int getId(){
        return id;
    }

    public String getType(){
        return type;
    }
    
    public String getTaille(){
        return taille;
    }

    public String getVue(){
        return vue;
    }

    public String getOccupation(){
        return occupation;
    }

    public int getTarif(){
        return tarif;
    }
    
    public Reservation[] getReservations() {
    	return reservation;
    }

    public int getTotalChambres(){
        return totalChambres;
    }

    // SETTERS

    public int setId(int id){
        this.id=id;
        return id;
    }

    public String setType(String type){
        this.type=type;
        return type;
    }
    
    public String setTaille(String taille){
        this.taille=taille;
        return taille;
    }

    public String setVue(String vue){
        this.vue=vue;
        return vue;
    }

    public String setOccupation(String occupation){
        this.occupation=occupation;
        return occupation;
    }

    public int setTarif(int tarif){
        this.tarif=tarif;
        return tarif;
    }
    
    public String setReservations(LocalDate arrivee, LocalDate depart, int nRes, String loginClient) {
    	reservation[nRes] = new Reservation(arrivee, depart, loginClient);
    	return "Votre r�servation : " + reservation[nRes].toString();
    }

    public int setTotalChambres(int totalChambres){
        Chambre.totalChambres=totalChambres;
        return totalChambres;
    }

    // TOSTRING
    
    public String toString(){
        return id + "\n" + type + "\n" + taille + "\n" + vue + "\n" + occupation + "\n" + tarif + " euros\n" + options + "\n";
    }
    
    // TESTS DE VERIFICATION DE CHAMBRES
    
    public boolean areWeOut(Reservation reservation){
    	if( reservation == null) {
    		return true;
    	}
    	else if((!LocalDate.now().isAfter(reservation.getDateA()) && ( !LocalDate.now().isBefore(reservation.getDateD()) || LocalDate.now().isEqual(reservation.getDateD()) ))){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    public boolean isFree(){
    	if(areWeOut(reservation[0]) || areWeOut(reservation[1]) || areWeOut(reservation[2])){
    		return true;
    	}
    	else{
    		return false;
    	}
    }

}