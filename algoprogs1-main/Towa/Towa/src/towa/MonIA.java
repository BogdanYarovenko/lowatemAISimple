/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package towa;

/**
 *
 * @author erimonteil
 */
public class MonIA {

    char couleur;

    static String CoupPrecedent = null;

    MonIA(char uneCouleur) {
        couleur = uneCouleur;
    }

    String actionChoisie(Case[][] plateau, int nbToursJeu) {
        JoueurTowa joueurTowa = new JoueurTowa();
        String[] actionsPossibles = ActionsPossibles.nettoyerTableau(
                joueurTowa.actionsPossibles(plateau, couleur, 6));
        String actionJouee = null;
        int index = 0;
        long tempsDebut = System.currentTimeMillis();
        long tempsFin = tempsDebut + 1900;
        long tempsActuel = System.currentTimeMillis();
        int maximumTour = 0;
        int i = 0;
        if (couleur == 'B') {
            int mini = 100000;
            while (tempsActuel < tempsFin && i < actionsPossibles.length) {
                if (nbToursJeu == 39) {
                    if (JoueurTowa.calculNombreEnemiVoisines(actionEnCoord(plateau, actionsPossibles[i]),
                            plateau, actionEnCase(plateau, actionsPossibles[i])) > maximumTour) {
                        maximumTour = JoueurTowa.calculNombreEnemiVoisines(actionEnCoord(plateau, actionsPossibles[i]),
                                plateau, actionEnCase(plateau, actionsPossibles[i]));
                        index = i;
                        CoupPrecedent = actionsPossibles[i];
                    }
                } else if (nbToursJeu == 40) {
                    String coup = CoupPrecedent.substring(1);
                    actionJouee = "A" + coup;

                } else if (EvaluationPos.evaluation(actionsPossibles[i]) < mini) {
                    mini = EvaluationPos.evaluation(actionsPossibles[i]);
                    index = i;
                }
                i++;
                tempsActuel = System.currentTimeMillis();
            }
        } else {
            int max = -100000;
            i = 0;
            while (tempsActuel < tempsFin && i < actionsPossibles.length) {
                if (nbToursJeu == 39) {
                    if (JoueurTowa.calculNombreEnemiVoisines(actionEnCoord(plateau, actionsPossibles[i]),
                            plateau, actionEnCase(plateau, actionsPossibles[i])) > maximumTour) {
                        maximumTour = JoueurTowa.calculNombreEnemiVoisines(actionEnCoord(plateau, actionsPossibles[i]),
                                plateau, actionEnCase(plateau, actionsPossibles[i]));
                        index = i;
                        CoupPrecedent = actionsPossibles[i];
                    }
                } else if (nbToursJeu == 40) {
                    String coup = CoupPrecedent.substring(1);
                    actionJouee = "A" + coup;

                } else if (EvaluationPos.evaluation(actionsPossibles[i]) > max) {
                    max = EvaluationPos.evaluation(actionsPossibles[i]);
                    index = i;
                }
                i++;
                tempsActuel = System.currentTimeMillis();
            }
        }
        if (actionJouee == null) {
            actionJouee = ActionsPossibles.enleverVitalites(
                    actionsPossibles[index]);
        }
        return actionJouee;
    }

    static Case actionEnCase(Case[][] plateau, String action) {
        Coordonnees coord = Coordonnees.depuisCars(action.charAt(1), action.charAt(2));
        Case c = plateau[coord.ligne][coord.colonne];
        return c;
    }

    static Coordonnees actionEnCoord(Case[][] plateau, String action) {
        Coordonnees coord = Coordonnees.depuisCars(action.charAt(1), action.charAt(2));
        return coord;
    }
}
