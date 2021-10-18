package fr.afpa.main;

import fr.afpa.metier.HotelManager;

public class Main{
	
    public static void main(String[] args){	 	
    	new HotelManager();

    }
    
    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();}
    
}