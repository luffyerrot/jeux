package com.pierre.ocr.jeux.Gestion;

import com.pierre.ocr.main;

public class Utils {

    String sautLigne = "\n";
    main main = new main();
    int tailleCode = main.tailleCode();

    /**
     * Regarde si le chiffre en position i est plus petit que celui à trouver.
     **/
    public String plusPetit(String conclusion, int i) {
       conclusion += ((i == 0 ? "Le" : "le") + " " + (i + 1) + " chiffre(s) est plus petit" + (i == (tailleCode - 1) ? "." : "," ) + sautLigne);
       return conclusion;
    }

    /**
     * Regarde si le chiffre en position i est plus grand que celui à trouver.
     **/
    public String plusGrand(String conclusion, int i) {
        conclusion += ((i == 0 ? "Le" : "le") + " " + (i + 1) + " chiffre(s) est plus grand" + (i == (tailleCode - 1) ? "." : "," ) + sautLigne);
        return conclusion;
    }

    /**
     * Regarde si le chiffre en position i est correct.
     **/
    public String Correct(String conclusion, int i) {
        conclusion += ((i == 0 ? "Le" : "le") + " " + (i + 1) + " chiffre(s) est correct" + (i == (tailleCode - 1) ? "." : "," ) + sautLigne);
        return conclusion;
    }
}
