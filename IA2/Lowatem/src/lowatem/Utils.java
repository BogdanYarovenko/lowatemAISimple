package lowatem;

/**
 * Quelques fonctions utiles au projet. Vous devez comprendre ce que font ces
 * méthodes (voir leur documentation), mais pas comment elles le font (leur
 * code).
 *
 * À faire évoluer en fonction des nouvelles natures de case, de nouveaux types
 * d'unités, etc.
 */
public class Utils {

    /**
     * Indice de la dernière COLONNE.
     */
    public final static int NUM_COLONNE_MAX = 13;
    /**
     * Caractère indiquant la nature "EAU" de la case (dans l'attribut nature).
     */
    public final static char CAR_EAU = 'E';
    /**
     * Caractère pour indiquer une case avec une unité de soldats (dans
     * l'attribut typeUnite).
     */
    public final static char UNITE_SOLDAT = 'S';
    /**
     * Caractère pour indiquer une case avec une unité de char (dans l'attribut
     * typeUnite).
     */
    public final static char UNITE_CHAR = 'C';
    /**
     * Caractère pour indiquer une case avec une unité de lance-missiles (dans
     * l'attribut typeUnite).
     */
    public final static char UNITE_LANCEMISSILES = 'L';
    /**
     * Caractère pour indiquer une case avec une unité de avions (dans
     * l'attribut typeUnite).
     */
    public final static char UNITE_AVION = 'A';
    /**
     * Caractère pour indiquer une case avec une unité de navires (dans
     * l'attribut typeUnite).
     */
    public final static char UNITE_NAVIRE = 'N';
    /**
     * Caractère pour indiquer un point cardinal((N)ord)
     */
    public final static char NORD = 'N';
    /**
     * Caractère pour indiquer un point cardinal((S)ud)
     */
    public final static char SUD = 'S';
    /**
     * Caractère pour indiquer un point cardinal((E)st)
     */
    public final static char EST = 'E';
    /**
     * Caractère pour indiquer un point cardinal((O)uest)
     */
    public final static char OUEST = 'O';

    /**
     * Construit un plateau à partir de sa représentation sour forme texte,
     * comme renvoyé par formatTexte(), avec coordonnées et séparateurs.
     *
     * @param texteOriginal le texte du plateau
     * @return le plateau
     */
    public static Case[][] plateauDepuisTexte(final String texteOriginal) {
        final Case[][] plateau = new Case[Coordonnees.NB_LIGNES][Coordonnees.NB_COLONNES];
        final String[] lignes = texteOriginal.split("\n");
        for (int lig = 0; lig < Coordonnees.NB_LIGNES; lig++) {
            final String ligne1 = lignes[2 * lig + 1];
            final String ligne2 = lignes[2 * lig + 2];
            for (int col = 0; col < Coordonnees.NB_COLONNES; col++) {
                final String codageLigne1 = ligne1.substring(2 + 4 * col, 2 + 4 * col + 3);
                final String codageLigne2 = ligne2.substring(2 + 4 * col, 2 + 4 * col + 3);
                plateau[lig][col] = caseDepuisCodage(codageLigne1, codageLigne2);
            }
        }
        return plateau;
    }

    /**
     * Construit une case depuis son codage.
     *
     * @param ligne1 codage de la case, première ligne
     * @param ligne2 codage de la case, deuxième ligne
     * @return case correspondante
     */
    public static Case caseDepuisCodage(final String ligne1, final String ligne2) {
        // vérification des arguments
        if (ligne1.length() != 3 || ligne2.length() != 3) {
            throw new IllegalArgumentException(
                    "Un codage de ligne doit être sur 3 caractères par ligne.");
        }
        Case laCase = new Case(Case.CAR_VIDE, Case.CAR_ROUGE, 0, 0, Case.CAR_TERRE);
        //
        // ligne 1
        //
        // 1er caractère : nature
        char carNature = ligne1.charAt(0);
        if (carNature == '-') {
            laCase.nature = Case.CAR_TERRE;
        } else {
            laCase.nature = carNature;
        }
        // 2ème caractère : rien
        // 3ème caractère : altitude
        char carAltitude = ligne1.charAt(2);
        if (carAltitude == '-') {
            laCase.altitude = 0;
        } else {
            laCase.altitude = Integer.parseInt("" + carAltitude);
        }
        //
        // ligne 2
        //
        // 1er caractère : type d'unité
        laCase.typeUnite = ligne2.charAt(0);
        // 2ème caractère : couleur
        char carCouleur = ligne2.charAt(1);
        if (laCase.typeUnite == Case.CAR_VIDE) {
            if (carCouleur != Case.CAR_VIDE) {
                throw new IllegalArgumentException("Cette case ne contient pas d'unité,"
                        + " donc ne devrait pas avoir de couleur associée.");
            }
            carCouleur = Case.CAR_NOIR;
        } else {
            if (carCouleur != Case.CAR_NOIR && carCouleur != Case.CAR_ROUGE) {
                throw new IllegalArgumentException(
                        "Caractère couleur non admis : " + carCouleur);
            }
        }
        laCase.couleurUnite = carCouleur;
        // 3ème caractère : points de vie
        char carPdV = ligne2.charAt(2);
        if (laCase.typeUnite == Case.CAR_VIDE) {
            if (carPdV != Case.CAR_VIDE) {
                throw new IllegalArgumentException("Cette case ne contient pas d'unité,"
                        + " donc ne devrait pas avoir de points de vie associés.");
            }
            laCase.pointsDeVie = 0;
        } else {
            laCase.pointsDeVie = Integer.parseInt("" + carPdV);
        }
        return laCase;
    }

    /**
     * Fonction qui renvoie une copie d'un tableau de coordonnéees sans les
     * cases non utilisées, c'est-à-dire contenant null.
     *
     * @param tab tableau de coordonnées
     * @param n nombre de cases utilisées
     *
     * @return tableau nettoyé
     */
    static Coordonnees[] nettoyerTableauCoord(Coordonnees[] tab, int n) {
        Coordonnees[] nouveau = new Coordonnees[n];
        for (int indice = 0; indice < n; indice++) {
            nouveau[indice] = tab[indice];
        }
        return nouveau;
    }

    /**
     * Vrai ssi cord est présente dans tab.
     *
     * @param tab tableau de coordonnées
     * @param coord la coordonnée à chercher
     *
     * @return vrai ssi cord est dans tab
     */
    static boolean coordDansTab(Coordonnees[] tab, Coordonnees coord) {
        boolean dans = false;
        int i = 0;
        while (!dans && i < tab.length) {
            if (tab[i].equals(coord)) {
                dans = true;
            }
            i++;
        }
        return dans;
    }

    /**
     * Couleur de l'unité adverse, 'I' si couleur Invalide.
     *
     * @param couleur couleur de l'unité
     * @return couleur de l'unité adverse^p
     */
    static char adverse(char couleur) {
        return switch (couleur) {
            case 'R' ->
                'N';
            case 'N' ->
                'R';
            default ->
                'I';
        };
    }

    /**
     * Valeur absolue d'un entier.
     *
     * @param unEntier entier relatif
     *
     * @return sa valeur absolue
     */
    static int abs(int unEntier) {
        if (unEntier < 0) {
            return (-1) * unEntier;
        }
        return unEntier;
    }

    /**
     * Caractère correspondant à la direction.
     *
     * @param d direction
     *
     * @return caractère associé
     */
    static char charDir(Direction d) {
        return switch (d) {
            case Direction.OUEST ->
                'O';
            case Direction.EST ->
                'E';
            case Direction.NORD ->
                'N';
            case Direction.SUD ->
                'S';
        };
    }
}
