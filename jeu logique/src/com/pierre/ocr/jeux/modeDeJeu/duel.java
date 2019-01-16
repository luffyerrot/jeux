package com.pierre.ocr.jeux.modeDeJeu;

import com.pierre.ocr.jeux.jeux;

public class duel extends jeux {

    private String nbOrdi;
    private String nbOrdiATrouver;
    private String nbJoueur;
    private String nbJoueurATrouver;
    private String verif;
    private String modeSaisi;

    public void duelInit(){

        setModeDeJeux("duel");
        nbOrdiATrouver = genNbOrdiATrouver();
        nbOrdi = genNbOrdi();
        System.out.println("Saisir un code à " + getTailleCode() + " chiffres pour l'ordinateur");
        try {
            nbJoueurATrouver = sc.next();
            modeSaisi = "init";
            saisi(nbJoueurATrouver);
        }catch (StringIndexOutOfBoundsException e){
            correctCode(nbJoueurATrouver);
            duelInit();
        }
        if (randomPourCommencer()){
            duelUpdateJoueur();
        }else {
            duelUpdateOrdi();
        }
    }

    public void duelUpdateJoueur(){
        System.out.println("Saisir votre code à " + getTailleCode() + " chiffres");
        try {
            nbJoueur = sc.next();
            modeSaisi = "joueur";
            saisi(nbJoueur);
        }catch (StringIndexOutOfBoundsException e){
            correctCode(nbJoueur);
            duelUpdateJoueur();
        }
        verifCodeJoueur(nbJoueur, nbOrdiATrouver);
        duelUpdateOrdi();
    }

    public void duelUpdateOrdi(){
        System.out.println("Votre adversaire propose : " + nbOrdi + getSautLigne() +
                "écrire un code à " + getTailleCode() + " chiffre(s) pour aider l'ordinateur : " + getSautLigne() +
                "0 - Mauvais" + getSautLigne() + "1 - Bon");
        try {
            verif = sc.next();
            verif(verif);
            nbOrdi = blacklist(verif, nbOrdi);
        }catch (StringIndexOutOfBoundsException e){
            System.out.println("Attention, entrer un code à " + getTailleCode() + " chiffre(s)");
            duelUpdateOrdi();
        }
        if (nbOrdi.equals(nbJoueurATrouver)){
            System.out.println("Perdu," + getSautLigne() + "L'ordinateur à trouvé votre code " + nbJoueur + getSautLigne());
            fin();
        }
        duelUpdateJoueur();
    }

    public String getModeSaisi() {
        return modeSaisi;
    }
}