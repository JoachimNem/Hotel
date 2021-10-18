package fr.afpa.beans;

public class User{

    private String nom;
    private String prenom;
    private String role;
    private String login;
    private String password;

    public User(){

    }

    public User(String nom, String prenom, String login, String password, String role){
        this.nom=nom;
        this.prenom=prenom;
        this.role=role;
        this.login=login;
        this.password=password;
    }

    // GETTERS
    
    public String getNom(){
        return nom;
    }

    public String getPrenom(){
        return prenom;
    }

    public String getRole(){
        return role;
    }

    public String getLogin(){
        return login;
    }

    public String getPassword(){
        return password;
    }

    // SETTERS

    public String setNom(String nom){
        this.nom=nom;
        return nom;
    }

    public String setPrenom(String prenom){
        this.prenom=prenom;
        return prenom;
    }

    public String setRole(String role){
        this.role=role;
        return role;
    }

    public String setLogin(String login){
        this.login=login;
        return login;
    }

    public String setPassword(String password){
        this.password=password;
        return password;
    }

}