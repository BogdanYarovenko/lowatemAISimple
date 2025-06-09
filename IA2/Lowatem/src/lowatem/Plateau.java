package lowatem;

public class Plateau {

    /**
     * Tableau 2D contenant les cases du plateau.
     */
    Case[][] plateau;

    /**
     * Tableau contenant les unités présentes dans le plateau.
     */
    Unite[] unites;

    /**
     * Nombre de points de vie du plateau.
     */
    NbPointsDeVie nbPv;
    
    /**
     * Constructeur prenant en paramètre un tableau 2D contenant les cases du plateau.
     * 
     * @param unPlateau tableau contenant les cases du plateau
     */
    Plateau(Case[][] unPlateau){
        plateau = unPlateau;
        unites = tabUnites(unPlateau);
        nbPv = nbPointsDeVie(unPlateau);
    }
    
    /**
     * Constructeur prenant en paramètre une chaîne de caractères représentant un plateau.
     * 
     * @param plateauTexte chaîne de caractères représentant un plateau
     */
    Plateau(String plateauTexte){
        Case[][] pl = Utils.plateauDepuisTexte(plateauTexte);
        plateau = pl;
        unites = tabUnites(pl);
        nbPv = nbPointsDeVie(pl);
    }
    
    /**
     * Constructeur par défaut. Renvoie un plateau vide.
     */
    Plateau(){
        Case[][] plVide = Utils.plateauDepuisTexte(PLATEAU_VIDE);
        plateau = plVide;
        unites = tabUnites(plVide);
        nbPv = nbPointsDeVie(plVide);
    }

    /**
     * Tableau des unités présentes sur le plateau.
     *
     * @return        liste des unités du plateau
     */
    static Unite[] tabUnites(Case[][] unPlateau) {
        int nbLig = unPlateau.length;
        int nbCol = unPlateau[0].length;
        Unite[] unitesDansPlateau = new Unite[nbLig*nbCol];
        int nbUnites = 0;
        for (int lig = 0; lig < nbLig; lig++){
            for (int col = 0; col < nbCol; col++){
                if (unPlateau[lig][col].unitePresente()){
                    unitesDansPlateau[nbUnites] = new Unite(unPlateau[lig][col], new Coordonnees(lig, col));
                    nbUnites++;
                }
            }
        }
        if (unitesDansPlateau[unitesDansPlateau.length-1] != null) { // si le tableau est plein, pas besoin de le reconstruire
            return unitesDansPlateau;
        } else {                               // sinon, on "nettoie" le tableau
            Unite[] unitesSansCasesNull = new Unite[nbUnites];
            for (int indice = 0; indice < nbUnites; indice++) {
                unitesSansCasesNull[indice] = unitesDansPlateau[indice];
            }
            return unitesSansCasesNull;
        }
    }

    /**
     * Parcourt le plateau jusqu'à ce qu'une unité soit trouvée
     * puis renvoie ses coordonnées. Si aucune unité n'est trouvée,
     * renvoie les coordonnées (0,0).
     *
     * @return          première unité trouvée
     */
    Coordonnees trouverUneUnite() {
        boolean caseTrouvee = false;
        int lig = 0;
        int col = 0;
        while (!caseTrouvee && (lig < this.plateau.length && col < this.plateau[0].length)) {
            if (this.plateau[lig][col].unitePresente()) {
                caseTrouvee = true;
            } else {
                if (col != this.plateau[0].length - 1) {
                    col++;
                } else {
                    col = 0;
                    lig++;
                }
            }
        }
        if (caseTrouvee) {
            return new Coordonnees(lig, col);
        } else {
            return new Coordonnees(0, 0);
        }
    }

    /**
     * Nombre d'unités d'une couleur dans le plateau.
     * Si 'T' est passé en paramètre, omet le filtre de couleur.
     *
     * @param couleur couleur des unités cherchées
     *
     * @return        nombre d'unités dans ce plateau
     */
    int nbUnites(char couleur) {
        int nbUnites = 0;
        for (Case[] ligne : plateau) {
            for (Case c : ligne) {
                if (c.unitePresente()) {
                    if (couleur != 'T') {
                        if (c.couleurUnite == couleur) {
                            nbUnites++;
                        }
                    } else {
                        nbUnites++;
                    }
                }
            }
        }
        return nbUnites;
    }

    /**
     * Case du plateau aux coordonnées passées en paramètre.
     * 
     * @param l ligne
     * @param c colonne
     * 
     * @return case du plateau ligne l et colonne c
     */
    Case caseAt(Coordonnees coord){
        return this.plateau[coord.ligne][coord.colonne];
    }

    boolean natureEst(char nat, int li, int co){
        Case c = this.plateau[li][co];
        return c.nature == nat;
    }

    /**
     * Vrai ssi une unité du plateau est présente aux coordonnées rentrées
     * et est de couleur couleurJoueur
     * (si couleurJoueur est égal à 'T', ce filtre ne s'applique pas)
     *
     * @param aChercher     coordonnées
     * @param couleurJoueur couleur de l'unité cherchée
     *
     * @return              vrai ssi une unité de couleur demandée est présente aux coordonnées
     */
    boolean unitePresenteACesCoordonnees(Coordonnees aChercher, char couleurJoueur) {
        boolean presente = false;
        int i = 0;
        while (!presente && i < this.unites.length) {
            if (this.unites[i].coord.equals(aChercher)){
                if (couleurJoueur != 'T') {
                    if (this.unites[i].couleur == couleurJoueur) {
                        presente = true;
                    }
                }
                else {
                    presente = true;
                }
            }
            i++;
        }
        return presente;
    }


    /**
     * Liste des unitées adverses jouxtées par l'unité aux coordonnées coord.
     *
     * @param coord         coordonnéees de l'unité
     * @param couleur       couleur du joueur
     *
     * @return              liste des coordonnées des unités jouxtées
     */
    Unite[] jouxtees(Coordonnees coord, char couleur) {
        int nbUnitesJouxtees = 0;
        Unite[] tabJouxtees = new Unite[4];
        for (Direction dir : Direction.toutes()) {
            if (this.unitePresenteACesCoordonnees(coord.suivantes(dir), Utils.adverse(couleur))) {
                tabJouxtees[nbUnitesJouxtees] = Unite.uniteAuxCoord(unites, coord.suivantes(dir));
                nbUnitesJouxtees++;
            }
        }
        Unite[] jouxteesNettoye = new Unite[nbUnitesJouxtees];
        for (int i = 0; i < nbUnitesJouxtees; i++){
            jouxteesNettoye[i] = tabJouxtees[i];
        }
        return jouxteesNettoye;
    }

    /**
     * Nombre de points de vie de chaque joueur sur le plateau.
     * 
     * @return objet NbPointsDeVie correspondant au plateau
     */
    static NbPointsDeVie nbPointsDeVie(Case[][] unPlateau) {
        NbPointsDeVie pointsDeVie = new NbPointsDeVie();
        Case ca;
        for (int lig = 0; lig < unPlateau.length; lig++){
            for (int col = 0; col < unPlateau[0].length; col++){
                ca = unPlateau[lig][col];
                if (ca.unitePresente()){
                    if (ca.couleurUnite == 'R'){
                        pointsDeVie.nbPvRouge += ca.pointsDeVie;
                    }
                    if (ca.couleurUnite == 'N') {
                        pointsDeVie.nbPvNoir += ca.pointsDeVie;
                    }
                }
            }
        }
        return pointsDeVie;
    }
    
    /**
     * Ajoute une unité au plateau.
     * 
     * @param un unité à ajouter
     * 
     * @return   vrai ssi l'unité a bien été ajoutée.
     */
    boolean ajouterUnite(Unite un){
        int lig = un.coord.ligne;
        int col = un.coord.colonne;
        if (!this.plateau[lig][col].unitePresente()){
            // mettre à jour le tableau
            this.plateau[lig][col].typeUnite =    un.type;
            this.plateau[lig][col].couleurUnite = un.couleur;
            this.plateau[lig][col].pointsDeVie =  un.pv;
            // puis les points de vie...
            if (un.couleur == 'R'){
                this.nbPv.nbPvRouge += un.pv;
            }
            if (un.couleur == 'N'){
                this.nbPv.nbPvNoir += un.pv;
            }
            // et enfin le tableau d'unités
            this.unites = tabUnites(this.plateau);
            return true;
        }
        else {
            System.out.println("Unité déjà présente !");
            return false;
        }  
    }
    
    boolean praticable(Unite uni, int lig, int col){
        return switch (uni.type) {
            case 'S', 'C', 'L' -> this.plateau[lig][col].nature == Case.CAR_TERRE; // Soldats, chars, lance-missiles
            case 'N' -> this.plateau[lig][col].nature == Utils.CAR_EAU; // Navires
            default -> true; // Avion
        };
    }
    
    final String PLATEAU_VIDE
            = """
                 A   B   C   D   E   F   G   H   I   J   K   L   M   N 
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              a|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              b|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              c|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              d|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              e|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              f|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              g|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              h|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              i|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              j|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              k|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              l|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              m|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              n|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              """;
}
