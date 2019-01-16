package com.pierre.ocr.jeux.modeDeJeu;

import com.pierre.ocr.jeux.jeux;

public class challenger extends jeux{

    private String nbOrdiATrouver;
    private String nbJoueur;

    public void challengerInit() {

        setModeDeJeux("challenger");
        nbOrdiATrouver = genNbOrdiATrouver();
        challengerUpdate();
    }

    public void challengerUpdate(){
        if (getCoupsMax() == 0){
            System.out.println("Perdu," + getSautLigne() + "il ne vous reste plus aucun coup");
            fin();
        }else{
            System.out.println("Il vous reste " + getCoupsMax() + " coups");
        }
        System.out.println("Saisir votre code à " + getTailleCode() + " chiffres");
        try {
            nbJoueur = sc.next();
            if (nbJoueur.intern() == "stop"){
                main.jeuMenu();
            }
            for (int i = 0; i < getTailleCode(); i++){
                if (nbJoueur.charAt(i) < '0' || nbJoueur.charAt(i) > '9'){
                    correctCode(nbJoueur);
                    challengerUpdate();
                }
            }
        }catch (StringIndexOutOfBoundsException e){
            correctCode(nbJoueur);
            challengerUpdate();
        }
        verifCodeJoueur(nbJoueur, nbOrdiATrouver);
        challengerUpdate();
    }
}
