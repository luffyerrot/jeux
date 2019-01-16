package com.pierre.ocr.jeux.modeDeJeu;

import com.pierre.ocr.jeux.jeux;

public class defender extends jeux {

    private String nbOrdi;
    private String nbJoueur;
    private String verif;

    private int count = 0;

    public void defenderInit() {

        setModeDeJeux("defender");
        nbOrdi = genNbOrdi();
        System.out.println("Saisir un code à " + getTailleCode() + " chiffres pour l'ordinateur");
        try {
            nbJoueur = sc.next();
            saisi(nbJoueur);
        }catch (StringIndexOutOfBoundsException e){
            correctCode(nbJoueur);
            defenderInit();
        }
        if (main.admin()){
            defenderAdmin();
        } else {
            defenderUpdate();
        }
    }

    public void defenderUpdate(){
        if(nbOrdi.equals(nbJoueur)){
            System.out.println("L'ordinateur à trouvé votre code " + nbJoueur + " en " + count + " coup" + (count < 1 ? "." : "s.") + getSautLigne());
            fin();
        }
        System.out.println("Votre adversaire propose : " + nbOrdi + getSautLigne() +
                "écrire un code à " + getTailleCode() + " chiffre(s) pour aider l'ordinateur : " + getSautLigne() +
                "0 - Mauvais" + getSautLigne() + "1 - Bon");
        try {
            verif = sc.next();
            verif(verif);
            nbOrdi = blacklist(verif, nbOrdi);
        }catch (StringIndexOutOfBoundsException e){
            System.out.println("Attention, entrer un code à " + getTailleCode() + " chiffre(s)");
            defenderUpdate();
        }
        count += 1;
        defenderUpdate();
    }

    public  void defenderAdmin(){
        if (nbOrdi.equals(nbJoueur)){
            System.out.println("L'ordinateur à trouvé votre code " + nbJoueur + " en " + count + " coup" + (count < 1 ? "." : "s.") + getSautLigne());
            fin();
        }
        nbOrdi = blacklist(nbJoueur, nbOrdi);
        System.out.println(nbOrdi);
        count += 1;
        defenderAdmin();
    }
}

