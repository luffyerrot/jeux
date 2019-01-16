package com.pierre.ocr;

import com.pierre.ocr.jeux.Gestion.Menu;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class main {

    private static main main = new main();
    private static Menu menu = new Menu();
    private Properties prop = new Properties();
    private static Logger logger = Logger.getLogger(main.class);
    private InputStream input;

    private String sautLigne = "\n";
    static String jeu;
    private String indAdminVersion;

    static Boolean adminVersion;

    static int tailleCode;
    static int coupsMax;

    public static void main(String[] args) {
        DOMConfigurator.configure("log4j.xml");
        logger.info("Entrer dans le jeu");
        main.jeuMenu();
    }

    public void jeuMenu(){

        try {
            input = new FileInputStream("config.properties");
            prop.load(input);
            tailleCode = Integer.valueOf(prop.getProperty("tailleDuCode"));
            if (tailleCode() > 100){
                System.out.println("Erreur, la taille du code doit etre inferieur a 101 :");
                System.exit(0);
            }
            adminVersion = Boolean.valueOf(prop.getProperty("admin"));
            coupsMax = Integer.valueOf(prop.getProperty("nombreDeCoupsMax"));
        }catch (Exception e){
            System.out.println("Un problème est survenu avec le fichier configuration");
        }
        indAdminVersion = "";
        if (adminVersion){
            indAdminVersion = "activé";
        }else{
            indAdminVersion = "désactivé";
        }
        System.out.println("Jeux : " + sautLigne + sautLigne + "1 - Jeu de logique" + sautLigne +
                "2 - Mastermind" + sautLigne + "3 - fermer l'application" + sautLigne + sautLigne +
                "Version Admin (" + indAdminVersion + ")" + sautLigne + "Taille du code : " + tailleCode + sautLigne);
        jeuMenuChoix();
    }

    private void jeuMenuChoix(){
        Scanner sc = new Scanner(System.in);
        int choix;
        try {
            do {
                choix = sc.nextInt();
                switch (choix) {
                    case 1:
                        jeu = "logique";
                        menu.menu();
                        break;
                    case 2:
                        jeu = "mastermind";
                        menu.menu();
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Entrer un chiffre entre 1 et 3 :");
                        break;
                }
            }while (choix < 1 || choix > 3);
        }catch (InputMismatchException e){
            System.out.println("ERREUR, entrer un chiffre entre 1 et 3 pour séléctionner un jeu ou quitter !");
            jeuMenuChoix();
        }
    }

    public Boolean admin(){
        return adminVersion;
    }

    public int tailleCode(){
        return tailleCode;
    }

    public int coupsMax(){
        return coupsMax;
    }

    public String getJeu() {
        return jeu;
    }
}
