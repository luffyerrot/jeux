package com.pierre.ocr.jeux.Gestion;

import com.pierre.ocr.jeux.modeDeJeu.challenger;
import com.pierre.ocr.jeux.modeDeJeu.duel;
import com.pierre.ocr.jeux.modeDeJeu.defender;
import com.pierre.ocr.main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    main main = new main();

    public void menu() {

        String sautLigne = "\n";
        System.out.println((main.getJeu() == "logique" ? "Jeu de logique : " : "Mastermind : ") + sautLigne + sautLigne + "1 - Challenger" + sautLigne +
                "2 - Duel" + sautLigne + "3 - Defender" + sautLigne + sautLigne +
                "4 - Revenir au menu du jeu" + sautLigne + "5 - fermer l'application" + sautLigne);
        menuChoix();
    }
    private void menuChoix(){
        Scanner sc = new Scanner(System.in);
        int choix;
        try {
            try {
                do {
                    choix = sc.nextInt();
                    switch (choix) {
                        case 1:
                            challenger challenger = new challenger();
                            challenger.challengerInit();
                            break;
                        case 2:
                            duel duel = new duel();
                            duel.duelInit();
                            break;
                        case 3:
                            defender defender = new defender();
                            defender.defenderInit();
                            break;
                        case 4:
                            main.jeuMenu();
                            break;
                        case 5:
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Entrer un chiffre entre 1 et 5 :");
                            break;
                    }
                } while (choix < 1 || choix > 5) ;
            }catch (NullPointerException e) {
                System.out.println("ERREUR, durant l'instancialisation du menu");
                menuChoix();
            }
        }catch (InputMismatchException e){
            System.out.println("ERREUR, entrer un chiffre entre 1 et 5 pour séléctionner un jeu ou quitter !");
            menuChoix();
        }
    }
}

