package lowatem;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Joueur implémentant les actions possibles à partir d'un plateau, pour un
 * niveau donné.
 */
public class JoueurLowatem implements IJoueurLowatem {

    /**
     * Cette méthode renvoie, pour un plateau donné et un joueur donné, toutes
     * les actions possibles pour ce joueur.
     *
     * @param plateau         le plateau considéré
     * @param couleurJoueur   couleur du joueur
     * @param niveau          le niveau de la partie à jouer
     * 
     * @return                l'ensemble des actions possibles
     */
    @Override
    public String[] actionsPossibles(Case[][] plateau, char couleurJoueur, int niveau) {
        // afficher l'heure de lancement
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
//        System.out.println("actionsPossibles : lancement le " + format.format(new Date()));
        // se préparer à stocker les actions possibles
        ActionsPossibles actions = new ActionsPossibles();
        // initialiser le plateau
        Plateau pl = new Plateau(plateau);
        for (Unite unite : pl.unites) {
            if (unite.couleur == couleurJoueur) {
                ajoutDeplDepuis(unite, actions, pl);
            }
        }
//        System.out.println("actionsPossibles : fin");
        return actions.nettoyer();
    }
    
    /**
     * Ajouter tous les déplacements depuis une case donnée.
     *
     * @param uni          unité à la case
     * @param actions      ensemble des actions possibles, à compléter
     * @param p            le plateau sur lequel se déroule la partie
     */
    void ajoutDeplDepuis(Unite uni, ActionsPossibles actions, Plateau p) {
        // on part dans chacune des 4 directions
        for (Direction dir : Direction.toutes()) {
            ajoutDeplDansDirection(dir, uni, actions, p);
        }
        // on ajoute le déplacement "sur place"
        ajoutDepl(uni, uni.coord, actions, p.nbPv);
        for (Unite adv : p.jouxtees(uni.coord, uni.couleur)) {
            ajoutAttaque(uni, uni.coord, adv, actions, p.nbPv);
        }
    }

    /**
     * Ajouter tous les déplacements depuis une case donnée, dans une direction
     * donnée.
     *
     * @param dir       direction à suivre
     * @param uni       unité à la case d'origine
     * @param actions   ensemble des actions possibles, à compléter
     * @param p         le plateau sur lequel on souhaite se déplacer
     */
    void ajoutDeplDansDirection(Direction dir, Unite uni,
            ActionsPossibles actions, Plateau p) {
        Coordonnees dst = uni.coord.suivantes(dir);
        char couleurJoueur = uni.couleur;
        while (dst.estDansPlateau() && p.praticable(uni,dst.ligne,dst.colonne)) {
            if (!(p.unitePresenteACesCoordonnees(dst, 'T'))) {
                ajoutDepl(uni, dst, actions, p.nbPv);
                for (Unite adv : p.jouxtees(dst, couleurJoueur)) {
                    ajoutAttaque(uni, dst, adv, actions, p.nbPv);
                }
            }
           dst = dst.suivantes(dir);
        }
    }

    /**
     * Ajout d'une action de déplacement dans l'ensemble des actions possibles.
     *
     * @param src     unité qui se déplace
     * @param dst     coordonnées de la case destination du déplacement
     * @param actions l'ensemble des actions possibles (en construction)
     * @param nbPv    nombre de points de vie de chaque joueur sur le plateau
     *                initial
     */
    void ajoutDepl(Unite src, Coordonnees dst, ActionsPossibles actions,
            NbPointsDeVie nbPv) {
        int pvApres = src.pvPostDepl(dst);
        if (pvApres > 0) {
            NbPointsDeVie nvNombrePv = new NbPointsDeVie(nbPv);
            if (src.couleur == 'R'){
                nvNombrePv.nbPvRouge -= (src.pv - pvApres);
            }
            if (src.couleur == 'N'){
                nvNombrePv.nbPvNoir -= (src.pv - pvApres);
            }
            actions.ajouterAction(chaineActionDepl(src.coord, dst, nvNombrePv));
        }
    }
    
    /**
     * Ajout d'une action d'attaque dans l'ensemble des actions possibles.
     *
     * @param attaquant unité qui attaque
     * @param dst       coordonnées de la case destination du déplacement
     * @param victime   unité attaquée
     * @param actions   l'ensemble des actions possibles (en construction)
     * @param nbPv      nombre de points de vie de chaque joueur sur le plateau
     *                  initial
     */
    void ajoutAttaque(Unite attaquant, Coordonnees dst, Unite victime,
            ActionsPossibles actions, NbPointsDeVie nbPv) {
        int pvAtkApresDeplacement = attaquant.pvPostDepl(dst); // Points de vie de l'attaquant après le déplacement pré-attaque.
        if (pvAtkApresDeplacement > 0){
            NbPointsDeVie nvNombrePv = new NbPointsDeVie(nbPv);
            int pvAttaquant = pvAtkApresDeplacement;
            int pvVictime = victime.pv;
            if (attaquant.couleur == 'R') {
                nvNombrePv.nbPvRouge -= attaquant.pv;
                nvNombrePv.nbPvNoir -= pvVictime;
            } 
            if (attaquant.couleur == 'N') {
                nvNombrePv.nbPvNoir -= attaquant.pv;
                nvNombrePv.nbPvRouge -= pvVictime;
            }
            int temp = pvAttaquant;
            pvAttaquant = pvAttaquant - attaquant.dmgAtk() - (int)((pvVictime-5)/2);
            pvVictime = pvVictime - attaquant.dmgCib() - (int)((temp-5)/2);
            if (pvAttaquant < 0) {
                pvAttaquant = 0;
            }
            if (pvVictime < 0) {
                pvVictime = 0;
            }
            if (attaquant.couleur == 'R') {
                nvNombrePv.nbPvRouge += pvAttaquant;
                nvNombrePv.nbPvNoir += pvVictime;
            }
            if (attaquant.couleur == 'N') {
                nvNombrePv.nbPvNoir += pvAttaquant;
                nvNombrePv.nbPvRouge += pvVictime;
            }
            actions.ajouterAction(chaineActionAttaque(attaquant.coord, dst, victime.coord, nvNombrePv));
        }

    }
    
    /**
     * Chaîne de caractères correspondant à une action d'attaque.
     * 
     * @param src coordonnées de la case à l'origine du déplacement
     * @param dst coordonnées de la case destination du déplacement
     * @param cible coordonnées de l'unité attaquée
     * @param nbPv nombre de points de vie de chaque joueur après l'action
     * @return la chaîne codant cette action
     */
    static String chaineActionAttaque(Coordonnees src, Coordonnees dst, Coordonnees cible, NbPointsDeVie nbPv) {
        return "" + src.carLigne() + src.carColonne()
                + "D" + dst.carLigne() + dst.carColonne()
                + "A" + cible.carLigne() + cible.carColonne()
                + "," + nbPv.nbPvRouge + "," + nbPv.nbPvNoir;
    }
    
    /**
     * Chaîne de caractères correspondant à une action-mesure de déplacement.
     *
     * @param src coordonnées de la case à l'origine du déplacement
     * @param dst coordonnées de la case destination du déplacement
     * @param nbPv nombre de points de vie de chaque joueur après l'action
     * @return la chaîne codant cette action-mesure
     */
    static String chaineActionDepl(Coordonnees src, Coordonnees dst, NbPointsDeVie nbPv) {
        return "" + src.carLigne() + src.carColonne()
                + "D" + dst.carLigne() + dst.carColonne()
                + "," + nbPv.nbPvRouge + "," + nbPv.nbPvNoir;
    }
    
    /**
     * Longueur d'un déplacement vertical, horizontal, ou nul.
     * 
     * @param src   coordonnées de départ
     * @param dst   coordonnées d'arrivée
     * 
     * @return      longueur entre src et dst
     */
    static int longueurDepl(Coordonnees src, Coordonnees dst) {
        if (src.equals(dst)) {
            return 0;
        }
        if (src.ligne == dst.ligne) {
            return Utils.abs(src.colonne-dst.colonne);
        }
        if (src.colonne == dst.colonne) {
            return Utils.abs(src.ligne-dst.ligne);
        }
        System.out.println("Déplacement non pris en charge par cette fonction.");
        return -1;
    }
}
