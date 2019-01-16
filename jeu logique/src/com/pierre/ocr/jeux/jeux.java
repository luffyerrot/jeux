package com.pierre.ocr.jeux;

import com.pierre.ocr.jeux.Gestion.Menu;
import com.pierre.ocr.jeux.Gestion.Utils;
import com.pierre.ocr.jeux.modeDeJeu.challenger;
import com.pierre.ocr.jeux.modeDeJeu.defender;
import com.pierre.ocr.jeux.modeDeJeu.duel;
import com.pierre.ocr.main;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class jeux {

    protected main main = new main();
    private defender defender;
    private duel duel;

    protected Scanner sc = new Scanner(System.in);

    private Map<Integer,String> map = new HashMap<>();

    private String sautLigne = "\n";
    private String nbOrdiATrouver;
    private String nbOrdi;
    private String modeDeJeux;

    private int tailleCode = main.tailleCode();
    private int coupsMax = main.coupsMax();

    protected String getSautLigne() {
        return sautLigne;
    }

    protected int getTailleCode(){
        return tailleCode;
    }

    protected void setModeDeJeux(String modeDeJeux) {
        this.modeDeJeux = modeDeJeux;
    }

    protected void setCoupsMax(int coupsMax) {
        this.coupsMax = coupsMax;
    }

    protected int getCoupsMax() {
        return coupsMax;
    }

    protected String genNbOrdiATrouver(){
        nbOrdiATrouver = "";
        for (int i = 0; i < tailleCode; i++){
            nbOrdiATrouver += (int) (Math.random() * 9);
        }
        if (main.admin()){
            System.out.println("Nombre de l'ordinateur a trouver : " + nbOrdiATrouver);
        }
        return nbOrdiATrouver;
    }

    protected String genNbOrdi(){
        nbOrdi = "";
        map.clear();
        for (int i = 0; i < tailleCode; i++) {
            nbOrdi += (int) (Math.random() * 9);
            map.put(i, Character.toString(nbOrdi.charAt(i)));
        }
        if (main.admin() && modeDeJeux != "duel"){
            System.out.println("Nombre de l'ordinateur : " + nbOrdi);
        }
        return nbOrdi;
    }

    /**
     * Détermine aléatoirement qui commence entre l'ordinateur et le joueur
     **/
    protected Boolean randomPourCommencer(){
        boolean commence = true;
        int rand = (int) ((Math.random()) * 2);
        switch (rand){
            case 0:
                System.out.println("Vous commencer :");
                commence = true;
                break;
            case 1:
                System.out.println("Votre adversaire commence :");
                commence = false;
                break;
        }
        return commence;
    }

    protected void correctCode(String nbJoueur){

        if (nbJoueur.intern() == "stop"){
            main.jeuMenu();
        }
        String memoire1 = "";
        String memoire2 = "";
        for (int i = 0; i < getTailleCode(); i++){
            memoire1 += 0;
            memoire2 += 9;
        }
        System.out.println("Selectioner une combinaison comprise entre " + memoire1 + " et " + memoire2 + sautLigne);
    }
    protected void saisi(String nbJoueur){
        if (nbJoueur.intern() == "stop"){
            main.jeuMenu();
        }
            for (int i = 0; i < getTailleCode(); i++){
                if (nbJoueur.charAt(i) < '0' || nbJoueur.charAt(i) > '9'){
                    correctCode(nbJoueur);
                    switch (modeDeJeux){
                        case "defender" :
                            defender.defenderInit();
                            break;
                        case "duel" :
                            if (duel.getModeSaisi() == "init"){
                                duel.duelInit();
                            }else if (duel.getModeSaisi() == "joueur"){
                                duel.duelUpdateJoueur();
                            }
                    }
                }
            }
    }

    protected void verif(String verif){

        if (verif.intern() == "stop"){
            main.jeuMenu();
        }
        for (int i = 0; i < getTailleCode(); i++){
            if (verif.charAt(i) < '0' || verif.charAt(i) > '1'){
                String memoire1 = "";
                String memoire2 = "";
                for (int j = 0; j < tailleCode; j++){
                    memoire1 += 0;
                    memoire2 += 1;
                }
                System.out.println("Selectioner une combinaison comprise entre " + memoire1 + " et " + memoire2 + sautLigne);
                switch (modeDeJeux){
                    case "defender" :
                        defender.defenderUpdate();
                        break;
                    case "duel":
                        duel.duelUpdateOrdi();
                        break;
                }
            }
        }
    }

    protected String blacklist(String nbCode, String nbOrdi){
        for (int i = 0; i < tailleCode; i++){
            if (!main.admin() ? (nbCode.charAt(i) == '0') : (nbOrdi.charAt(i) != nbCode.charAt(i))){
                String blacklist = map.get(i);
                do {
                    int randomint = (int) (Math.random() * 9);
                    char change = Integer.toString(randomint).charAt(0) ;
                    StringBuilder builder = new StringBuilder(nbOrdi);
                    builder.setCharAt(i, change);
                    nbOrdi = builder.toString();
                }while(blacklist.contains(Character.toString(nbOrdi.charAt(i))));
            }
            String value = Character.toString(nbOrdi.charAt(i));
            map.put(i, map.get(i).concat(value));
        }
        return nbOrdi;
    }

    protected void verifCodeJoueur(String nbJoueur, String nbOrdiATrouver) {

        if (nbJoueur.equals(nbOrdiATrouver)) {
            System.out.println("Bravo," + getSautLigne() + "vous avez gagner le code secret est " + nbJoueur + getSautLigne());
            fin();
        }
        switch (main.getJeu()){
            case "logique" :
                String conclusion = "";
                Utils utils = new Utils();
                for (int i = 0; i < tailleCode; i++) {
                    if ((int) nbJoueur.charAt(i) < (int) nbOrdiATrouver.charAt(i)) {
                        conclusion = utils.plusGrand(conclusion, i);
                    } else if ((int) nbJoueur.charAt(i) > (int) nbOrdiATrouver.charAt(i)) {
                        conclusion = utils.plusPetit(conclusion, i);
                    } else if ((int) nbJoueur.charAt(i) == (int) nbOrdiATrouver.charAt(i)) {
                        conclusion = utils.Correct(conclusion, i);
                    }
                }
                System.out.println(conclusion);
                break;
            case "mastermind" :
                int nbConclusionCorrect = 0;
                int nbConclusion = 0;
                for (int i = 0; i < tailleCode; i++){
                    if (nbJoueur.charAt(i) == nbOrdiATrouver.charAt(i)){
                        nbConclusionCorrect += 1;
                    }else if (nbOrdiATrouver.contains(Character.toString(nbJoueur.charAt(i)))){
                        nbConclusion += 1;
                    }
                }
                System.out.println(nbConclusionCorrect + " chiffre" + (nbConclusionCorrect < 2 ? " " : "s ") + "bien Placé, " + nbConclusion + " chiffre" + (nbConclusion < 2 ? " " : "s ") + "mal Placé");
                break;
        }
        setCoupsMax(getCoupsMax() - 1);
    }


    protected void fin(){
        System.out.println("1 - Recommencer" + sautLigne + sautLigne + "2 - Menu mode de jeux" + sautLigne +
                "3 - Menu jeux" + sautLigne + "4 - quitter l'application" + sautLigne);
        int choix;
        try {
            do {
                choix = sc.nextInt();
                switch (choix) {
                    case 1:
                        switch (modeDeJeux){
                            case "challenger" :
                                challenger challenger = new challenger();
                                challenger.challengerInit();
                                break;
                            case "defender" :
                                defender defender = new defender();
                                defender.defenderInit();
                                break;
                            case "duel" :
                                duel duel = new duel();
                                duel.duelInit();
                                break;
                        }
                        break;
                    case 2:
                        Menu menu = new Menu();
                        menu.menu();
                        break;
                    case 3:
                        main.jeuMenu();
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    default :
                        System.out.println("Entrer un chiffre entre 1 et 4 :");
                        break;
                }
            }while (choix < 1 || choix > 4);
        }catch (InputMismatchException e){
            System.out.println("ERREUR, entrer un chiffre entre 1 et 4 pour séléctionner un jeu ou quitter !");
            fin();
        }
    }
}