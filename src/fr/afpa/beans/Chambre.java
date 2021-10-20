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
    
    public int getAnEmptyReservation(Reservation[] reservation) {
    	for(int i = 0;i<3;i++){
    		if(reservation[i]==null) {
    			return i;
    		}
    	}
    	return 0;
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
    
    public void setReservations(LocalDate arrivee, LocalDate depart, int nRes, String loginClient) {
    	reservation[nRes] = null;
    	reservation[nRes] = new Reservation(arrivee, depart, loginClient);
    }

    public int setTotalChambres(int totalChambres){
        Chambre.totalChambres=totalChambres;
        return totalChambres;
    }
    
    // FREE ROOM
    
    public boolean AreReservationsFull(){
        if(reservation[0] == null || reservation[1] == null || reservation[2] == null) {
        	return true;
        }
        else {
        	return false;
        }
    }

    // TOSTRING
    
    public String toString(){
        return id + "\n" + type + "\n" + taille + "\n" + vue + "\n" + occupation + "\n" + tarif + " euros\n" + options + "\n";
    }
    
    // TESTS DE VERIFICATION DE CHAMBRES
    
    public boolean areWeOut(Reservation reservation, LocalDate dateA, LocalDate dateD){
    	if(reservation == null) {
    		return true;
    	}
    	else if((!dateA.isAfter(reservation.getDateA()) && !dateA.isEqual(reservation.getDateA()) && !dateD.isBefore(reservation.getDateD()) )){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    public boolean isFree(LocalDate dateA, LocalDate dateD){
    	if(areWeOut(reservation[0], dateA, dateD) && areWeOut(reservation[1], dateA, dateD) && areWeOut(reservation[2], dateA, dateD)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }

}
