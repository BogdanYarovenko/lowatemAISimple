package lowatem;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Votre IA pour le jeu Lowatem.
 */
public class IALowatem {

    /**
     * Hôte du grand ordonnateur.
     */
    String hote = null;

    /**
     * Port du grand ordonnateur.
     */
    int port = -1;

    /**
     * Couleur de votre joueur (IA) : 'R'ouge ou 'N'oir.
     */
    final char couleur;

    /**
     * Interface pour le protocole du grand ordonnateur.
     */
    TcpGrandOrdonnateur grandOrdo = null;

    /**
     * Nombre maximal de tours de jeu.
     */
    static final int NB_TOURS_JEU_MAX = 40;

    /**
     * Constructeur.
     *
     * @param hote Hôte.
     * @param port Port.
     * @param uneCouleur Couleur de ce joueur
     */
    public IALowatem(String hote, int port, char uneCouleur) {
        this.hote = hote;
        this.port = port;
        this.grandOrdo = new TcpGrandOrdonnateur();
        this.couleur = uneCouleur;
    }

    /**
     * Connexion au Grand Ordonnateur.
     *
     * @throws IOException exception sur les entrées/sorties
     */
    void connexion() throws IOException {
        System.out.print(
                "Connexion au Grand Ordonnateur : " + hote + " " + port + "...");
        System.out.flush();
        grandOrdo.connexion(hote, port);
        System.out.println(" ok.");
        System.out.flush();
    }

    /**
     * Boucle de jeu : envoi des actions que vous souhaitez jouer, et réception
     * des actions de l'adversaire.
     *
     * @throws IOException exception sur les entrées/sorties
     */
    void toursDeJeu() throws IOException {
        // paramètres
        System.out.println("Je suis le joueur " + couleur + ".");
        // le plateau initial
        System.out.println("Réception du plateau initial...");
        Plateau pl = new Plateau(grandOrdo.recevoirPlateauInitial());
        System.out.println("Plateau reçu.");
        // compteur de tours de jeu (entre 1 et 40)
        int nbToursJeu = 1;
        // la couleur du joueur courant (change à chaque tour de jeu)
        char couleurTourDeJeu = Case.CAR_ROUGE;
        // booléen pour détecter la fin du jeu
        boolean fin = false;
        while (!fin) {
            boolean disqualification = false;
            if (couleurTourDeJeu == couleur) {
                // à nous de jouer !
                jouer(pl, nbToursJeu);
            } else {
                // à l'adversaire de jouer
                disqualification = adversaireJoue(pl, couleurTourDeJeu);
            }
            if (nbToursJeu == NB_TOURS_JEU_MAX || disqualification) {
                // fini
                fin = true;
            } else {
                // au suivant
                nbToursJeu++;
                couleurTourDeJeu = suivant(couleurTourDeJeu);
            }
        }
    }

    /**
     * Fonction exécutée lorsque c'est à notre tour de jouer. Cette fonction
     * envoie donc l'action choisie au serveur.
     *
     * @param plateau le plateau de jeu
     * @param nbToursJeu numéro du tour de jeu
     * @throws IOException exception sur les entrées / sorties
     */
    void jouer(Plateau pl, int nbToursJeu) throws IOException {
        String actionJouee = actionChoisie(pl.plateau, nbToursJeu);
        if (actionJouee != null) {
            // jouer l'action
            System.out.println("On joue : " + actionJouee);
            grandOrdo.envoyerAction(actionJouee);
            mettreAJour(pl, actionJouee);
        } else {
            // Problème : le serveur vous demande une action alors que vous n'en
            // trouvez plus...
            System.out.println("Aucun action trouvée : abandon...");
            grandOrdo.envoyerAction("ABANDON");
        }
    }

    /**
     *
     * @param plateau
     * @param nbToursJeu
     * @return
     */
    public String actionChoisie(Case[][] plateau, int nbToursJeu) {
        long startTime = System.nanoTime(); // Начало измерения времени
        JoueurLowatem joueurLowatem = new JoueurLowatem();
        String[] actionsPossibles = couleur == Case.CAR_NOIR
                ? ActionsPossibles.nettoyerTableau(joueurLowatem.actionsPossibles(plateau, 'N', 9))
                : ActionsPossibles.nettoyerTableau(joueurLowatem.actionsPossibles(plateau, 'R', 9));
        //System.out.println("Actions possibles pour monIA : " + Arrays.deepToString(actionsPossibles));

        if (actionsPossibles.length == 0) {
            return "";
        }

        String meilleureAction = "";
        String[] meilleursScoresActions = new String[actionsPossibles.length];
        int[] scoresActions = new int[actionsPossibles.length];
        int meilleurScore = Integer.MIN_VALUE;
        int nbMeilleuresActions = 0;

        for (String action : actionsPossibles) {

            System.out.println("----------------------------------------------------------------------");
            int scoreAction = evaluerAction(action, plateau);
            System.out.println(action + " son poids : " + scoreAction);
            System.out.println("evaluerPosition(action, plateau) " + evaluerPosition(action, plateau));

            System.out.println("evaluerMenace(action, plateau) " + evaluerMenace(action, plateau));
            System.out.println("deplacementEtEvaluation: " + deplacementEtEvaluation(action, plateau));

            System.out.println("-----------------------------------------------------------------------");
            if (scoreAction > meilleurScore) {
                meilleurScore = scoreAction;
                nbMeilleuresActions = 0;
                meilleursScoresActions[nbMeilleuresActions] = action;
                scoresActions[nbMeilleuresActions++] = scoreAction;
            } else if (scoreAction == meilleurScore) {
                meilleursScoresActions[nbMeilleuresActions] = action;
                scoresActions[nbMeilleuresActions++] = scoreAction;
            }
        }
        System.out.println("Meilleurs actions pour monIA : " + Arrays.deepToString(meilleursScoresActions));

        System.out.println("Meilleurs scores et actions :");
        for (int i = 0; i < nbMeilleuresActions; i++) {
            System.out.println("Action : " + meilleursScoresActions[i] + ", Poids : " + scoresActions[i]);
        }
        int scoreFinal = Integer.MIN_VALUE;
        for (int i = 0; i < nbMeilleuresActions; i++) {
            String actionAvecScore = meilleursScoresActions[i];
            int score = calculerDifference(actionAvecScore, plateau);
            if (score > scoreFinal) {
                scoreFinal = score;
                meilleureAction = actionAvecScore;
            }
        }

        System.out.println("On joue " + (couleur == Case.CAR_NOIR ? "N" : "R") + " : " + meilleureAction + " avec score: " + meilleurScore);
        long endTime = System.nanoTime(); // Конец измерения времени
        double durationInSeconds = (endTime - startTime) / 1e9;
        System.out.println("Temps d'exécution en secondes : " + durationInSeconds);
        String actionJouee = ActionsPossibles.enleverPointsDeVie(meilleureAction);

        return actionJouee;
    }

    int deplacementEtEvaluation(String action, Case[][] plateau) {
        int score = 0;

        JoueurLowatem joueurLowatem = new JoueurLowatem();

        Coordonnees coordSrc = getCoordonneesFromAction(action, 0, 1);
        Coordonnees coordDst = getCoordonneesFromAction(action, 3, 4);
        Case unite = plateau[coordSrc.ligne][coordSrc.colonne];
        int oldPv = unite.pointsDeVie;
        int pdvApresDepl = oldPv - coutDeplacement(coordSrc, coordDst, unite.typeUnite);

        Plateau pl = new Plateau(clonePlateau(plateau));

        mettreAJour(pl, action);

        String[] actionsPossiblesAdversaire = couleur == Case.CAR_NOIR
                ? ActionsPossibles.nettoyerTableau(joueurLowatem.actionsPossibles(pl.plateau, 'R', 9))
                : ActionsPossibles.nettoyerTableau(joueurLowatem.actionsPossibles(pl.plateau, 'N', 9));
       // System.out.println("Adversaire " + Arrays.toString(actionsPossiblesAdversaire));
        boolean isSafe = true;
        int nbEnnemi = 0;
        for (String actionAdversaire : actionsPossiblesAdversaire) {
            if (isActionAttack(actionAdversaire)) {
                Coordonnees coordAtqAdversaire = getCoordonneesFromAction(actionAdversaire, 6, 7);
                if (coordAtqAdversaire.ligne == coordDst.ligne && coordAtqAdversaire.colonne == coordDst.colonne) {

                    System.out.println(actionAdversaire + " rend le déplacement dangereux pour " + action);
                    isSafe = false;
                    if (isActionAttack(action)) {
                        Coordonnees coordAtq = getCoordonneesFromAction(action, 6, 7);
                        Case attaque = plateau[coordAtq.ligne][coordAtq.colonne];
                        int[] nouveauxPointsDeVie = calculerNouveauxPointsDeVieAttaques(pdvApresDepl, attaque.pointsDeVie, unite.typeUnite);
                        int newPvAttaquant = nouveauxPointsDeVie[0];
                        int newPvAttaque = nouveauxPointsDeVie[1];
                        System.out.println("newPvAttaque dans cherche de ennemies " + newPvAttaque);
                        if (newPvAttaque <= 0) {
                            score += 150;
                            if (newPvAttaquant >= 1) {
                                score += 200;
                            } else {
                                score += 100;
                            }
                        } else {
                            nbEnnemi++;
                        }
                    } else {
                        nbEnnemi++;
                        score = -50 * nbEnnemi;
                        System.out.println("nbEnnemi = " + nbEnnemi);
                    }

                }
            }
        }

        if (isSafe) {
            for (String adversaires : actionsPossiblesAdversaire) {
                Coordonnees coordSrcAdversaire = getCoordonneesFromAction(adversaires, 0, 1);
                Case advCase = plateau[coordSrcAdversaire.ligne][coordSrcAdversaire.colonne];

                if ((coordSrc.ligne == coordSrcAdversaire.ligne || coordSrc.colonne == coordSrcAdversaire.colonne)) {
                    switch (unite.typeUnite) {
                        case Utils.UNITE_CHAR, Utils.UNITE_NAVIRE, Utils.UNITE_AVION -> {
                            if (isActionAttack(action)) {
                                score = 200;
                            }

                        }
                        case Utils.UNITE_LANCEMISSILES, Utils.UNITE_SOLDAT -> {
                            if (pdvApresDepl >= advCase.pointsDeVie) {
                                score = 200;

                            }
                        }
                        default ->
                            throw new AssertionError();
                    }

                }
            }
        }

        return score;
    }

    Case[][] clonePlateau(Case[][] plateau) {
        Case[][] clone = new Case[plateau.length][];
        for (int i = 0; i < plateau.length; i++) {
            clone[i] = new Case[plateau[i].length];
            for (int j = 0; j < plateau[i].length; j++) {
                Case current = plateau[i][j];
                clone[i][j] = new Case(
                        current.typeUnite,
                        current.couleurUnite,
                        current.pointsDeVie,
                        current.altitude,
                        current.nature
                );
            }
        }
        return clone;
    }

    /**
     *
     * @param action
     * @param plateau
     * @return
     */
    int calculerDifference(String action, Case[][] plateau) {

        String[] parts = action.split(",");
        int opponentScore = couleur == Case.CAR_NOIR ? Integer.parseInt(parts[1]) : Integer.parseInt(parts[2]);
        int playerScore = couleur == Case.CAR_NOIR ? Integer.parseInt(parts[2]) : Integer.parseInt(parts[1]);

        return playerScore - opponentScore;

    }

    /**
     * Оценивает действие по нескольким параметрам, включая позицию, угрозу,
     * ликвидацию, оставшиеся очки жизни и врагов.
     *
     * @param action строка, представляющая действие.
     * @param plateau игровое поле.
     * @return итоговый вес действия.
     */
    int evaluerAction(String action, Case[][] plateau) {
        return evaluerPosition(action, plateau)
                + evaluerMenace(action, plateau)
                + deplacementEtEvaluation(action, plateau);

    }

    /**
     * Оценивает вес позиции в зависимости от перемещения юнита.
     *
     * @param action строка, представляющая действие.
     * @param plateau игровое поле.
     * @return значение, зависящее от позиции.
     */
    int evaluerPosition(String action, Case[][] plateau) {
        Coordonnees coordSrc = getCoordonneesFromAction(action, 0, 1);
        Coordonnees coordDst = getCoordonneesFromAction(action, 3, 4);
        Case unite = plateau[coordSrc.ligne][coordSrc.colonne];

        return 14 - coutDeplacement(coordSrc, coordDst, unite.typeUnite);
    }

    static int distance(Coordonnees src, Coordonnees dst) {
        return Math.abs(src.ligne - dst.ligne) + Math.abs(src.colonne - dst.colonne);
    }

    static int coutDeplacement(Coordonnees src, Coordonnees dst, char typeUnite) {
        return (int) (getCoutUnite(typeUnite) * distance(src, dst));
    }

    /**
     * Renvoie le multiplicateur de coût en fonction du type d'unité
     *
     * @param typeUnite le type de l'unité
     * @return le multiplicateur de coût associé au type d'unité.
     */
    static double getCoutUnite(char typeUnite) {
        return switch (typeUnite) {
            case Utils.UNITE_SOLDAT ->
                0.3;
            case Utils.UNITE_CHAR ->
                0.7;
            case Utils.UNITE_LANCEMISSILES ->
                0.4;
            case Utils.UNITE_AVION ->
                0.7;
            case Utils.UNITE_NAVIRE ->
                0.6;
            default ->
                0;
        };
    }

    /**
     * Оценивает уровень угрозы, создаваемой атакующим юнитом в рамках действия.
     *
     * @param action строка, представляющая действие.
     * @param plateau игровое поле.
     * @return оценка угрозы.
     */
    int evaluerMenace(String action, Case[][] plateau) {
        int menace = 0;
        if (isActionAttack(action)) {
            Coordonnees coordSrc = getCoordonneesFromAction(action, 0, 1);
            Coordonnees coordDst = getCoordonneesFromAction(action, 3, 4);
            Coordonnees coordAtq = getCoordonneesFromAction(action, 6, 7);
            Case attaque = plateau[coordAtq.ligne][coordAtq.colonne];
            Case attaquant = plateau[coordDst.ligne][coordDst.colonne];
            int oldPvAttaquant = attaquant.pointsDeVie;

            int pdvApresDepl = oldPvAttaquant - coutDeplacement(coordSrc, coordDst, attaquant.typeUnite);

            switch (attaquant.typeUnite) {
                case Utils.UNITE_CHAR ->
                    menace += 30;
                case Utils.UNITE_AVION, Utils.UNITE_NAVIRE, Utils.UNITE_LANCEMISSILES ->
                    menace += 15;
                default ->
                    menace += 5;
            }
            int[] nouveauxPointsDeVie = calculerNouveauxPointsDeVieAttaques(pdvApresDepl, attaque.pointsDeVie, attaquant.typeUnite);
            int newPvAttaquant = nouveauxPointsDeVie[0];
            int newPvAttaque = nouveauxPointsDeVie[1];

            if (pdvApresDepl > attaque.pointsDeVie) {
                menace += 40;
            } else if (pdvApresDepl == attaque.pointsDeVie) {
                menace += 20;
            } else {
                menace += 0;
            }
            if (newPvAttaquant > newPvAttaque) {
                menace += 40;
            } else if (newPvAttaquant == newPvAttaque) {
                menace += 20;
            } else {
                menace += 0;
            }
        }
        return menace;
    }

    Coordonnees getCoordonneesFromAction(String action, int index1, int index2) {
        return Coordonnees.depuisCars(action.charAt(index1), action.charAt(index2));
    }

    boolean isActionAttack(String action) {
        return action.length() > 5 && action.charAt(5) == 'A';
    }


    /*
    
    
    
    
    
    
                                            TOUCHE PAS
    
    
    
    
    
    
    
     */
    public boolean adversaireJoue(Plateau pl, char couleurAdversaire) {
        boolean disqualification = false;
        System.out.println("Attente de réception action adversaire...");
        String actionAdversaire = grandOrdo.recevoirAction();
        System.out.println("Action adversaire reçue : " + actionAdversaire);
        if ("Z".equals(actionAdversaire)) {
            System.out.println("L'adversaire est disqualifié.");
            disqualification = true;
        } else {
            System.out.println("L'adversaire joue : " + actionAdversaire + ".");
            mettreAJour(pl, actionAdversaire);
        }
        return disqualification;
    }

    public static char suivant(char couleurCourante) {
        return couleurCourante == Case.CAR_ROUGE ? Case.CAR_NOIR : Case.CAR_ROUGE;
    }

    public static void mettreAJour(Plateau pl, String action) {
        if (pl.plateau == null || action == null || action.length() < 5 || action.charAt(2) != 'D') {
            return;
        }
        Coordonnees coordSrc = Coordonnees.depuisCars(action.charAt(0), action.charAt(1));
        Coordonnees coordDst = Coordonnees.depuisCars(action.charAt(3), action.charAt(4));

        if (!coordSrc.memeLigne(coordDst) || !coordSrc.memeColonne(coordDst)) {
            deplacerUnite(Unite.uniteAuxCoord(pl.unites, coordSrc), pl, coordDst, coordSrc);
        }

        if (action.length() == 8 && action.charAt(5) == 'A') {
            Coordonnees coordAtq = Coordonnees.depuisCars(action.charAt(6), action.charAt(7));

            attaquerUnite(coordSrc, coordDst, coordAtq, pl);

        }
    }

    public static void deplacerUnite(Unite src, Plateau pl, Coordonnees coordDst, Coordonnees coordSrc) {

        Case srcUnit = pl.plateau[coordSrc.ligne][coordSrc.colonne];
        Case dst = pl.plateau[coordDst.ligne][coordDst.colonne];
        int pdvAvant = srcUnit.pointsDeVie;
        int pdvApres = pdvAvant - coutDeplacement(coordSrc, coordDst, srcUnit.typeUnite);
        dst.typeUnite = srcUnit.typeUnite;
        dst.couleurUnite = srcUnit.couleurUnite;
        dst.pointsDeVie = pdvApres;
        if (pdvApres <= 0) {
            retirerUnite(dst);

        }

        retirerUnite(srcUnit);
    }

    public static void attaquerUnite(Coordonnees coordSrc, Coordonnees coordDst, Coordonnees coordAtq, Plateau pl) {
        Case dst = pl.plateau[coordDst.ligne][coordDst.colonne];
        Case atq = pl.plateau[coordAtq.ligne][coordAtq.colonne];
        int oldPvAttaquant = dst.pointsDeVie;
        int oldPvAttaque = atq.pointsDeVie;

        if (oldPvAttaquant <= 0) {
            retirerUnite(atq);
        } else {
            int[] nouveauxPointsDeVie = calculerNouveauxPointsDeVieAttaques(oldPvAttaquant, oldPvAttaque, dst.typeUnite);
            int newPvAttaquant = nouveauxPointsDeVie[0];
            int newPvAttaque = nouveauxPointsDeVie[1];
            dst.pointsDeVie = newPvAttaquant;
            atq.pointsDeVie = newPvAttaque;
            if (dst.pointsDeVie <= 0) {
                retirerUnite(dst);
            }
            if (atq.pointsDeVie <= 0) {
                retirerUnite(atq);
            }
        }
    }

    static int[] calculerNouveauxPointsDeVieAttaques(int pdvApresDepl, int oldPvAttaque, char typeUniteAttaquant) {
        int dommageParDefautAttaque = 0;
        int dommageParDefautAttaquant = 0;
        switch (typeUniteAttaquant) {
            case Utils.UNITE_SOLDAT -> {
                dommageParDefautAttaquant = 2;
                dommageParDefautAttaque = 4;
            }
            case Utils.UNITE_CHAR -> {
                dommageParDefautAttaquant = 3;
                dommageParDefautAttaque = 8;
            }
            case Utils.UNITE_LANCEMISSILES -> {
                dommageParDefautAttaquant = 3;
                dommageParDefautAttaque = 6;
            }
            case Utils.UNITE_AVION -> {
                dommageParDefautAttaquant = 2;
                dommageParDefautAttaque = 7;
            }
            case Utils.UNITE_NAVIRE -> {
                dommageParDefautAttaquant = 2;
                dommageParDefautAttaque = 7;
            }
        }

        int newPvAttaquant = pdvApresDepl - dommageParDefautAttaquant - (int) ((oldPvAttaque - 5) / 2);
        int newPvAttaque = oldPvAttaque - dommageParDefautAttaque - (int) ((pdvApresDepl - 5) / 2);

        return new int[]{newPvAttaquant, newPvAttaque};
    }

    public static void retirerUnite(Case laCase) {
        laCase.typeUnite = Case.CAR_VIDE;
        laCase.couleurUnite = Case.CAR_NOIR;
        laCase.pointsDeVie = 0;
    }

    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        System.out.println("Démarrage le " + format.format(new Date()));
        System.out.flush();

        final String USAGE = System.lineSeparator() + "\tUsage : java " + IALowatem.class.getName() + " <hôte> <port> <ordre>";
        if (args.length != 3) {
            System.out.println("Nombre de paramètres incorrect." + USAGE);
            System.out.flush();
            System.exit(1);
        }
        String hote = args[0];
        int port = -1;
        try {
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Le port doit être un entier." + USAGE);
            System.out.flush();
            System.exit(1);
        }
        int ordre = -1;
        try {
            ordre = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("L'ordre doit être un entier." + USAGE);
            System.out.flush();
            System.exit(1);
        }
        try {
            char couleurJoueur = (ordre == 1 ? 'R' : 'N');
            IALowatem iaLowatem = new IALowatem(hote, port, couleurJoueur);
            iaLowatem.connexion();
            iaLowatem.toursDeJeu();
        } catch (IOException e) {
            System.out.println("Erreur à l'exécution du programme : \n" + e);
            System.out.flush();
            System.exit(1);
        }
    }
}
