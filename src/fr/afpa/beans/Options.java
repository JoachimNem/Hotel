package fr.afpa.beans;

public class Options{

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

    public Options(boolean fer, boolean tel, boolean television, boolean clim, boolean bouilloire, boolean concierge, boolean internet, boolean dvd, boolean services, boolean sechecheveux, boolean coffre, boolean minibar){
        this.fer=fer;
        this.tel=tel;
        this.television=television;
        this.clim=clim;
        this.bouilloire=bouilloire;
        this.concierge=concierge;
        this.internet=internet;
        this.dvd=dvd;
        this.services=services;
        this.sechecheveux=sechecheveux;
        this.coffre=coffre;
        this.minibar=minibar;
    }

    public Options(){

    }

    // GETTERS

    public boolean getFer(){
        return fer;
    }

    public boolean getTel(){
        return tel;
    }

    public boolean getTelevision(){
        return television;
    }

    public boolean getClim(){
        return clim;
    }

    public boolean getBouilloire(){
        return bouilloire;
    }

    public boolean getConcierge(){
        return concierge;
    }

    public boolean getInternet(){
        return internet;
    }

    public boolean getDvd(){
        return dvd;
    }

    public boolean getServices(){
        return services;
    }

    public boolean getSecheCheveux(){
        return sechecheveux;
    }

    public boolean getCoffre(){
        return coffre;
    }

    public boolean getMinibar(){
        return minibar;
    }

    // SETTERS

    public boolean setFer(boolean fer){
        this.fer=fer;
        return fer;
    }

    public boolean setTel(boolean tel){
        this.tel=tel;
        return tel;
    }

    public boolean setTelevision(boolean television){
        this.television=television;
        return television;
    }

    public boolean setClim(boolean clim){
        this.clim=clim;
        return clim;
    }

    public boolean setBouilloire(boolean bouilloire){
        this.bouilloire=bouilloire;
        return bouilloire;
    }

    public boolean setConcierge(boolean concierge){
        this.concierge=concierge;
        return concierge;
    }

    public boolean setInternet(boolean internet){
        this.internet=internet;
        return internet;
    }

    public boolean setDvd(boolean dvd){
        this.dvd=dvd;
        return dvd;
    }

    public boolean setServices(boolean services){
        this.services=services;
        return services;
    }

    public boolean setSecheCheveux(boolean sechecheveux){
        this.sechecheveux=sechecheveux;
        return sechecheveux;
    }

    public boolean setCoffre(boolean coffre){
        this.coffre=coffre;
        return coffre;
    }

    public boolean setMinibar(boolean minibar){
        this.minibar=minibar;
        return minibar;
    }

    public String toString(){
        return "\n Fer et planche à repasser sur demande : " + fer + "\n\n" +
        " Téléphone :                            " + tel + "\n\n" +
        " Télévision par câble :                " + television + "\n\n" +
        " Climatisation :                          " + clim + "\n\n" +
        " Bouilloire électrique :                 " + bouilloire + "\n\n" +
        " Concierge 24h/24 :                       " + concierge + "\n\n" +
        " Accès Internet haut débit sans fil :   " + internet + "\n\n" +
        " Lecteur DVD sur demande :                " + dvd + "\n\n" +
        " Service aux chambres 24h/24 :            " + services + "\n\n" +
        " Minibar :                                " + minibar + "\n\n" +
        " Sèche-cheveux :                         " + sechecheveux + "\n\n" +
        " Coffre-fort dans la chambre :            " + coffre + "\n\n" ;
    }
}