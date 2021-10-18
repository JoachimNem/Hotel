package fr.afpa.beans;

import java.time.LocalDate;

public class Reservation{

    private LocalDate dateA;
    private LocalDate dateD;
    private String loginClient;
    
    // CONSTRUCTOR
    
    public Reservation(LocalDate arrivee,LocalDate depart,String loginClient){
    	
    	dateA= arrivee;
    	dateD= depart;
    	this.loginClient = loginClient;
    	
    }
    
    // GETTERS
    
    public LocalDate getDateA() {
    	return dateA;
    }
    
    public LocalDate getDateD() {
    	return dateD;
    }
    
    public String getLoginClient() {
    	return loginClient;
    }
    
    // SETTERS
    
    public LocalDate setDateA(LocalDate arrivee) {
    	this.dateA = arrivee;
    	return dateA;
    }
    
    public LocalDate setDateD(LocalDate depart) {
    	this.dateD = depart;
    	return dateD;
    }
    
    public String setLoginClient(String loginClient) {
    	this.loginClient = loginClient;
    	return loginClient;
    }
    
    // TOSTRING
    
    public String toString() {
    	return "Arriv�e : " + dateA + "\nD�part : " + dateD + "\nLogin Client : " + loginClient;
    }
}
