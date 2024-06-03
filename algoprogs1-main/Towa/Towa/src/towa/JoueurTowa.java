package towa;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import java.util.Date;

/**
 * Joueur implémentant les actions possibles à partir d'un plateau, pour un
 * niveau donné.
 */
public class JoueurTowa implements IJoueurTowa {

    /**
     * Cette méthode renvoie, pour un plateau donné et un joueur donné, toutes
     * les actions possibles pour ce joueur.
     *
     * @param plateau le plateau considéré
     * @param couleurJoueur couleur du joueur
     * @param niveau le niveau de la partie à jouer
     * @return l'ensemble des actions possibles
     */
    @Override
    public String[] actionsPossibles(Case[][] plateau, char couleurJoueur, int niveau) {
        // afficher l'heure de lancement
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        System.out.println("actionsPossibles : lancement le " + format.format(new Date()));
        // se préparer à stocker les actions possibles
        ActionsPossibles actions = new ActionsPossibles();
        // on compte le nombre de pions sur le plateau avant action
        NbPions nbPions = nbPions(plateau);
        // pour chaque ligne
        for (int lig = 0; lig < Coordonnees.NB_LIGNES; lig++) {
            // pour chaque colonne
            for (int col = 0; col < Coordonnees.NB_COLONNES; col++) {
                Coordonnees coord = new Coordonnees(lig, col);
                // si la pose d'un pion de cette couleur est possible sur cette case
                if (posePossible(plateau, coord, couleurJoueur)) {
                    // on ajoute l'action dans les actions possibles
                    ajoutActionPose(coord, actions, nbPions, couleurJoueur, plateau);

                } // on ajoute l'action attaque dans les actions possibles 
                if (attaquePossible(plateau, coord, couleurJoueur)) {
                    ajoutActionAttaque(coord, actions, nbPions, couleurJoueur, plateau);
                }

            }
        }
        System.out.println("actionsPossibles : fin");
        return actions.nettoyer();
    }

    /**
     * Indique s'il est possible de poser un pion sur une case pour ce plateau,
     * ce joueur, dans ce niveau.
     *
     * @param plateau le plateau
     * @param coord coordonnées de la case à considérer
     * @param couleur couleur du joueur
     * @return vrai ssi la pose d'un pion sur cette case est autorisée dans ce
     * niveau
     */
    boolean posePossible(Case[][] plateau, Coordonnees coord, char couleur) {
        Case caseCourant = plateau[coord.ligne][coord.colonne];

        return !(caseCourant.tourPresente()) || couleur == caseCourant.couleur && caseCourant.hauteur < 4;
    }

    /**
     * Indique s'il est possible d'attaquer un pion ennemi sur une case pour ce
     * plateau, ce joueur , dans ce niveau.
     *
     * @param plateau le plateau
     * @param coord coordonnées de la case à considérer
     * @param couleur couleur du joueur
     * @return vrai ssi l'attaque d'un pion ennemi est autorisée dans ce niveau
     */
    boolean attaquePossible(Case[][] plateau, Coordonnees coord, char couleur) {
        Case caseCourant = plateau[coord.ligne][coord.colonne];
        return caseCourant.tourPresente() && couleur == caseCourant.couleur;
    }

    /**
     * Nombre de pions sur le plateau, de chaque couleur.
     *
     * @param plateau le plateau
     * @return le nombre de pions sur le plateau, de chaque couleur
     */
    static NbPions nbPions(Case[][] plateau) {
        int nbPionsNoirs = 0;
        int nbPionsBlancs = 0;
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[0].length; j++) {
                if (plateau[i][j].couleur == Case.CAR_BLANC) {
                    nbPionsBlancs += plateau[i][j].hauteur;

                } else if (plateau[i][j].couleur == Case.CAR_NOIR) {
                    nbPionsNoirs += plateau[i][j].hauteur;

                }

            }

        }

        return new NbPions(nbPionsNoirs, nbPionsBlancs);
    }

    /**
     * Ajout d'une action de pose dans l'ensemble des actions possibles.
     *
     * @param coord coordonnées de la case où poser un pion
     * @param actions l'ensemble des actions possibles (en construction)
     * @param nbPions le nombre de pions par couleur sur le plateau avant de
     * jouer l'action
     * @param couleur la couleur du pion à ajouter
     */
    void ajoutActionPose(Coordonnees coord, ActionsPossibles actions,
            NbPions nbPions, char couleur, Case[][] plateau) {

        int pointB = nbPions.nbPionsBlancs;
        int pointN = nbPions.nbPionsNoirs;
        Coordonnees[] coordVoisines = voisines(coord, 16);
        boolean couleurEnnemi = false;
        int bonus = 0;
        for (int i = 0; i < coordVoisines.length; i++) {
            Case caseVoisines = plateau[coordVoisines[i].ligne][coordVoisines[i].colonne];
            if (caseVoisines.tourPresente() && caseVoisines.couleur != couleur) {
                couleurEnnemi = true;
            }
        }
        if (couleurEnnemi && !plateau[coord.ligne][coord.colonne].tourPresente()) {
            bonus += 2;
        } else {
            bonus += 1;
        }

        if (couleur == Case.CAR_BLANC) {
            pointB += bonus;
        } else {
            pointN += bonus;
        }

        String action = "P" + coord.carLigne() + coord.carColonne() + ","
                + (pointN) + ","
                + (pointB);
        actions.ajouterAction(action);

    }

    /**
     * Ajout d'une action d'attaque dans l'ensemble des actions possibles.
     *
     * @param coord coordonnées de la case où poser un pion
     * @param actions l'ensemble des actions possibles (en construction)
     * @param nbPions le nombre de pions par couleur sur le plateau avant de
     * jouer l'action
     * @param couleur la couleur du pion à ajouter
     */
    void ajoutActionAttaque(Coordonnees coord, ActionsPossibles actions,
            NbPions nbPions, char couleur, Case[][] plateau
    ) {

        int pointN = nbPions.nbPionsNoirs;
        int pointB = nbPions.nbPionsBlancs;
        int nbPionsAEnlever = 0;
        Case caseCourant = plateau[coord.ligne][coord.colonne];
        nbPionsAEnlever += calculNombreEnemiVoisines(coord, plateau, caseCourant);
        nbPionsAEnlever += calculNombreEnemiDirection(coord, plateau, caseCourant);
        if (couleur == Case.CAR_BLANC) {
            pointN -= nbPionsAEnlever;
        } else {
            pointB -= nbPionsAEnlever;
        }
        String action = "A" + coord.carLigne() + coord.carColonne() + ","
                + (pointN) + ","
                + (pointB);
        actions.ajouterAction(action);
        System.out.println(action);
    }

    /**
     * Calcule le nombre de pièces ennemies dans les directions spécifiées à
     * partir de la position donnée.
     *
     * @param coord Les coordonnées de la position actuelle.
     * @param plateau Le plateau de jeu représenté sous forme d'un tableau 2D
     * d'objets Case.
     * @param caseCourant L'objet Case actuel représentant la position.
     * @return Le nombre total de pièces ennemies à retirer en fonction de la
     * comparaison des hauteurs.
     */
    static int calculNombreEnemiDirection(Coordonnees coord, Case[][] plateau, Case caseCourant) {
        int nbPionsAEnlever = 0;
        Coordonnees[] coordDirection = direction(coord, 16);
        for (int i = 0; i < coordDirection.length; i++) {
            Case caseDirection = plateau[coordDirection[i].ligne][coordDirection[i].colonne];

            if (caseDirection.tourPresente() && caseDirection.couleur != caseCourant.couleur) {
                if (caseDirection.hauteur < caseCourant.hauteur) {
                    nbPionsAEnlever += caseDirection.hauteur;

                }
            }
        }
        return nbPionsAEnlever;
    }

    /**
     * Calcule le nombre de pièces ennemies dans les cases adjacents à partir de
     * la position donnée
     *
     * @param coord Les coordonnées de la position actuelle.
     * @param plateau Le plateau de jeu représenté sous forme d'un tableau 2D
     * d'objets Case.
     * @param caseCourant L'objet Case actuel représentant la position.
     * @return Le nombre total de pièces ennemies à retirer en fonction de la
     * comparaison des hauteurs.
     */
    static int calculNombreEnemiVoisines(Coordonnees coord, Case[][] plateau, Case caseCourant) {
        int nbPionsAEnlever = 0;
        Coordonnees[] coordVoisines = voisines(coord, 16);
        for (int i = 0; i < coordVoisines.length; i++) {
            Case caseVoisines = plateau[coordVoisines[i].ligne][coordVoisines[i].colonne];

            if (caseVoisines.tourPresente() && caseVoisines.couleur != caseCourant.couleur) {
                if (caseVoisines.hauteur < caseCourant.hauteur) {
                    nbPionsAEnlever += caseVoisines.hauteur;

                }
            }

        }
        return nbPionsAEnlever;
    }

    /**
     * Renvoie les coordonnées de la case suivante, en suivant une direction
     * donnée.
     *
     * @param d la direction à suivre
     * @return les coordonnées de la case suivante
     */
    static Coordonnees suivante(Coordonnees c, Direction d) {
        return new Coordonnees(c.ligne + Direction.mvtVertic(d),
                c.colonne + Direction.mvtHoriz(d));
    }

    /**
     * Indique si ces coordonnées sont dans le plateau.
     *
     * @param coord coordonnées à tester
     * @param taille taille du plateau (carré)
     * @return vrai ssi ces coordonnées sont dans le plateau
     */
    static boolean estDansPlateau(Coordonnees coord, int taille) {

        return (coord.ligne >= 0 && coord.ligne < taille)
                && (coord.colonne >= 0 && coord.colonne < taille);
    }

    /**
     * Retourne les coordonnées de toutes les cases voisines.
     *
     * @param coord coordonnées de la case considérée
     * @param taille taille du plateau (carré)
     * @return les coordonnées de toutes les cases voisines
     */
    static Coordonnees[] voisines(Coordonnees coord, int taille) {
        Coordonnees[] voisines = new Coordonnees[8];
        int nbVoisines = 0;

        for (Direction d : Direction.values()) {
            Coordonnees c = suivante(coord, d);
            if (estDansPlateau(c, taille)) {
                voisines[nbVoisines] = c;
                nbVoisines++;
            }
        }

        return Arrays.copyOf(voisines, nbVoisines);
    }

    /**
     * Retourne les coordonnées de toutes les cases positionnés
     *
     * @@param coord coordonnées de la case considérée
     * @param taille taille du plateau (carré)
     * @return les coordonnées de toutes les cases.
     */
    static Coordonnees[] direction(Coordonnees coord, int taille) {
        Coordonnees[] length = new Coordonnees[256];
        int nbTours = 0;
        for (Direction d : Direction.cardinales()) {
            Coordonnees temp = suivante(coord, d);
            Coordonnees c = suivante(temp, d);
            while (estDansPlateau(c, taille)) {
                length[nbTours] = c;
                c = suivante(c, d);
                nbTours++;
            }
        }
        return Arrays.copyOf(length, nbTours);
    }
}
