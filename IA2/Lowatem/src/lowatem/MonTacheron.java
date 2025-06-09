package lowatem;


import static lowatem.IALowatem.distance;

/**
 * Une IA qui choisit aléatoirement une action parmi celles renvoyées par
 * JoueurLowatem.actionsPossibles().
 */
class MonTacheron {

    /**
     * Couleur de mon joueur Tacheron.
     */
    char couleur;

    /**
     * Constructeur.
     *
     * @param uneCouleur couleur du tacheron
     */
    MonTacheron(char uneCouleur) {
        couleur = uneCouleur;
    }

    /**
     * L'action choisie par cette IA : au hasard parmi les actions possibles.
     *
     * @param plateau le plateau de jeu
     * @param nbToursJeu numéro du tour de jeu
     * @return l'action choisie sous forme de chaîne
     */
   /* String actionChoisie(Case[][] plateau, int nbToursJeu) {
        // on instancie votre implémentation
        JoueurLowatem joueurLowatem = new JoueurLowatem();
        // choisir aléatoirement une action possible
        String[] actionsPossibles = ActionsPossibles.nettoyerTableau(
                joueurLowatem.actionsPossibles(plateau, couleur, 9));
      //  System.out.println("ActionsPossibles pour MonTacheron : " + Arrays.toString(actionsPossibles));
        String actionJouee = null;
        if (actionsPossibles.length > 0) {
            Random r = new Random();
            int indiceAleatoire = r.nextInt(actionsPossibles.length);
            actionJouee = ActionsPossibles.enleverPointsDeVie(
                    actionsPossibles[indiceAleatoire]);
            System.out.println("On joue " + (couleur == Case.CAR_NOIR ? "N" : "R") + " : " + actionsPossibles[indiceAleatoire]);
        }

        return actionJouee;
    }*/
     public String actionChoisie(Case[][] plateau, int nbToursJeu) {
        long startTime = System.nanoTime(); // Début de la mesure

        JoueurLowatem joueurLowatem = new JoueurLowatem();
        String[] actionsPossibles = couleur == Case.CAR_NOIR
                ? ActionsPossibles.nettoyerTableau(joueurLowatem.actionsPossibles(plateau, 'N', 9))
                : ActionsPossibles.nettoyerTableau(joueurLowatem.actionsPossibles(plateau, 'R', 9));

      //  System.out.println("Actions Possbiles pour TACHERON : " + Arrays.deepToString(actionsPossibles));
        if (actionsPossibles.length > 0) {

            String meilleureAction = "";
            String[] meilleursScoresActions = new String[actionsPossibles.length];
            int meilleurScore = 0;
            int nbMeilleuresActions = 0;

            for (String action : actionsPossibles) {
                int scoreAction = evaluerAction(action, plateau);
                //   System.out.println(action + " son poids : " + scoreAction);

                if (scoreAction > meilleurScore) {

                    meilleurScore = scoreAction;
                    nbMeilleuresActions = 0;
                    meilleursScoresActions[nbMeilleuresActions++] = action;
                } else if (scoreAction == meilleurScore) {
                    meilleursScoresActions[nbMeilleuresActions++] = action;
                }
            }

            //System.out.println(Arrays.toString(meilleursScoresActions));
            int nbActions = 0;

            for (String action : meilleursScoresActions) {
                if (action != null) {
                    nbActions++;
                }
            }

            int scoreFinal = 0;
            for (int i = 0; i < nbActions; i++) {
                String actionAvecScore = meilleursScoresActions[i];
                String[] parts = actionAvecScore.split(",");
                // Выбираем значения в зависимости от цвета

                int score = couleur == Case.CAR_NOIR ? Integer.parseInt(parts[2]) : Integer.parseInt(parts[1]);

                if (score > scoreFinal) {
                    scoreFinal = score;
                    meilleureAction = actionAvecScore;
                }
            }

            System.out.println("On joue " + (couleur == Case.CAR_NOIR ? "N" : "R") + " : " + meilleureAction + " avec score: " + meilleurScore);

            long endTime = System.nanoTime(); // Fin de la mesure
            double durationInSeconds = (endTime - startTime) / 1e9;
            System.out.println("Temps d'exécution en secondes : " + durationInSeconds);

            return ActionsPossibles.enleverPointsDeVie(meilleureAction);
        }
        return "";
    }

    int evaluerAction(String action, Case[][] plateau) {
        return evaluerPosition(action, plateau) + evaluerMenace(action, plateau) - evaluerEnnemi(action, plateau);
    }

    int evaluerPosition(String action, Case[][] plateau) {
        Coordonnees coordSrc = Coordonnees.depuisCars(action.charAt(0), action.charAt(1));
        Coordonnees coordDst = Coordonnees.depuisCars(action.charAt(3), action.charAt(4));
        return 14 - distance(coordSrc, coordDst);
    }

    int evaluerMenace(String action, Case[][] plateau) {
        int menace = 0;
        if (action.length() > 5 && action.charAt(5) == 'A') {
            Coordonnees coordDst = Coordonnees.depuisCars(action.charAt(3), action.charAt(4));
            Coordonnees coordAtq = Coordonnees.depuisCars(action.charAt(6), action.charAt(7));
            Case cible = plateau[coordAtq.ligne][coordAtq.colonne];
            Case attaquant = plateau[coordDst.ligne][coordDst.colonne];

            switch (attaquant.typeUnite) {
                case 'N', 'A' ->
                    menace += 20;
                case 'C', 'L' ->
                    menace += 10;
                default ->
                    menace += 5;
            }

            if (attaquant.pointsDeVie > cible.pointsDeVie) {
                menace += 30;
            } else if (attaquant.pointsDeVie == cible.pointsDeVie) {
                menace += 15;
            } else {
                return 10;
            }
        }
        return menace;
    }

    int evaluerEnnemi(String action, Case[][] plateau) {
        int pointsPlus = 0;
        Coordonnees coordDst = Coordonnees.depuisCars(action.charAt(3), action.charAt(4));
        int ligne = coordDst.ligne;
        int colonne = coordDst.colonne;
        Case dst = plateau[ligne][colonne];

        for (int i = Math.max(0, ligne - 1); i <= Math.min(plateau.length - 1, ligne + 1); i++) {
            for (int j = Math.max(0, colonne - 1); j <= Math.min(plateau[0].length - 1, colonne + 1); j++) {
                if ((i == ligne && j == colonne) || plateau[i][j].couleurUnite == dst.couleurUnite) {
                    continue;
                }
                if (plateau[i][j].unitePresente()) {
                    if (plateau[i][j].pointsDeVie > dst.pointsDeVie) {
                        pointsPlus++;
                    }
                }
            }
        }

        return pointsPlus;
    }

    static void toursDeJeu(char couleurIA, char couleurTacheron, Case[][] plateau) {
        // paramètres
       
        NbPointsDeVie newpv = nbPointsDeVie(plateau);
        System.out.println("**********************************");

        System.out.print("pv R Initial : " + newpv.nbPvRouge);
        System.out.println(" pv N Initial : " + newpv.nbPvNoir);

        System.out.println("**********************************");
        System.out.println("IALowatem est " + couleurIA + ".");
        // instantiation de mon IA
        IALowatem monIA = new IALowatem("", -1, couleurIA);
        MonTacheron monTacheron = new MonTacheron(couleurTacheron);
        // compteur de tours de jeu (entre 1 et 40)
        int nbToursJeu = 1;
        // la couleur du joueur courant (change à chaque tour de jeu)
        char couleurTourDeJeu = Case.CAR_NOIR;
        // booléen pour détecter la fin du jeu
        boolean fin = false;
        while (!fin) {
            System.out.println("ROUND " + nbToursJeu);
            // choisir l'action
            String actionChoisie;
            String joueur;
            if (couleurTourDeJeu == couleurIA) {
                actionChoisie = monIA.actionChoisie(plateau, nbToursJeu);
                joueur = "IALowatem";
            } else {
                actionChoisie = monTacheron.actionChoisie(plateau, nbToursJeu);
                joueur = "MonTacheron";
            }
            System.out.println(joueur + " joue : " + actionChoisie);
            IALowatem.mettreAJour(new Plateau(plateau), actionChoisie);
            NbPointsDeVie pvmaj = nbPointsDeVie(plateau);
            System.out.println("**********************************");
            System.out.print("nbPVrougeMAJ : " + pvmaj.nbPvRouge);
            System.out.println(" nbPVNoirMAJ : " + pvmaj.nbPvNoir);
            System.out.println("**********************************");
            if (nbToursJeu == IALowatem.NB_TOURS_JEU_MAX) {

                fin = true;
                if (pvmaj.nbPvRouge > pvmaj.nbPvNoir) {
                    System.out.println("Rouge gagne (" + pvmaj.nbPvRouge + " rouge  et " + pvmaj.nbPvNoir + " noir}");
                } else if (pvmaj.nbPvRouge == pvmaj.nbPvNoir) {
                    System.out.println("Egaux {" + pvmaj.nbPvRouge + " rouge  et " + pvmaj.nbPvNoir + " noir}");
                } else {
                    System.out.println("Noir gagne {" + pvmaj.nbPvRouge + " rouge  et " + pvmaj.nbPvNoir + " noir}");
                }
            } else {
                // au suivant
                nbToursJeu++;
                couleurTourDeJeu = IALowatem.suivant(couleurTourDeJeu);
            }
        }
    }

    /**
     * Nombre de points de vie de chaque joueur sur le plateau.
     *
     * @param plateau le plateau
     * @return le nombre de pions de cette couleur sur le plateau
     */
    static NbPointsDeVie nbPointsDeVie(Case[][] plateau) {
        int nbPointsRouge = 0;
        int nbPointsNoir = 0;
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[0].length; colonne++) {
                Case casePlateau = plateau[ligne][colonne];
                if (casePlateau.unitePresente()) {
                    switch (casePlateau.couleurUnite) {
                        case Case.CAR_ROUGE ->
                            nbPointsRouge += casePlateau.pointsDeVie;
                        case Case.CAR_NOIR ->
                            nbPointsNoir += casePlateau.pointsDeVie;
                    }
                }
            }
        }
        return new NbPointsDeVie(nbPointsRouge, nbPointsNoir);
    }

    /**
     * Lancer une partie entre votre IA et votre tacheron.
     *
     * @param args arguments de la ligne de commande (inutilisés)
     */
    public static void main(String[] args) {
        // choisir la couleur de l'IA
        char couleurIA = Case.CAR_NOIR;
        // monTacheron prend l'autre couleur
        char couleurTacheron = IALowatem.suivant(couleurIA);
        // plateau initial
        Case[][] plateauInitial = Utils.plateauDepuisTexte(PLATEAU_NIVEAU99);
        // lancement
        toursDeJeu(couleurIA, couleurTacheron, plateauInitial);
         String nomFichier = "resultats_parties.txt";

       /* try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier))) {
            for (int i = 1; i <= 9900; i++) {
                // Créer une copie du plateau initial pour chaque partie
                Case[][] plateauCopie = Utils.plateauDepuisTexte(PLATEAU_NIVEAU9);

                // Capture des logs de la partie
                ResultLogger logger = new ResultLogger();
                System.setOut(logger);

                // Lancer une partie
                toursDeJeu(couleurIA, couleurTacheron, plateauCopie);

                // Analyser les logs pour extraire uniquement la ligne indiquant le gagnant
                String gagnant = extraireDerniereLigneGagnant(logger.getLogs());
                writer.write("Partie " + i + " : " + gagnant + "\n");
                writer.write("===========================\n");
            }
            System.out.println("Résultats enregistrés dans " + nomFichier);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());
        }
    }

    private static String extraireDerniereLigneGagnant(String logs) {
        String[] lignes = logs.split("\n");
        for (int i = lignes.length - 1; i >= 0; i--) {
            if (lignes[i].contains("gagne") || lignes[i].contains("Egaux")) {
                return lignes[i];
            }
        }
        return "Aucun gagnant détecté.";
    }

    private static class ResultLogger extends java.io.PrintStream {

        private final StringBuilder logs = new StringBuilder();

        public ResultLogger() {
            super(System.out);
        }

        @Override
        public void println(String x) {
            logs.append(x).append(System.lineSeparator());
            super.println(x);
        }

        public String getLogs() {
            return logs.toString();
        }*/
    }

    final static String PLATEAU_NIVEAU9
            = """
                 A   B   C   D   E   F   G   H   I   J   K   L   M   N 
               +---+E--+E--+---+---+---+---+---+---+---+---+---+E--+E--+
              a|LN6|   |   |   |   |   |   |   |   |   |SR1|SN5|   |NR3|
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              b|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+E--+E--+---+---+---+---+---+---+---+---+---+---+---+
              c|   |NN3|   |   |   |   |   |   |   |   |   |   |   |   |
               +E--+E--+E--+E--+---+---+---+---+---+---+---+---+---+---+
              d|NR3|   |NR1|   |   |   |   |   |   |   |   |   |   |   |
               +---+E--+E--+---+---+---+---+---+---+---+---+---+---+---+
              e|   |NN3|   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              f|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              g|   |   |   |   |   |SN1|   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              h|   |   |   |   |AR6|   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              i|   |   |CR1|   |   |   |   |   |   |   |   |SR5|   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              j|   |   |   |   |   |   |   |   |   |   |   |   |SN4|   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              k|   |   |   |   |   |   |   |   |CN1|   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              l|   |   |   |   |   |   |   |   |   |   |   |   |LR6|   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              m|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              n|   |   |   |   |AN6|   |   |SR4|   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              """;
    static final String PLATEAU_NIVEAU10 = """
                                       A   B   C   D   E   F   G   H   I   J   K   L   M   N 
                                     +---+E--+E--+E--+E--+E--+---+---+---+---+---+---+---+---+
                                    a|   |   |   |   |NR8|   |   |   |   |   |   |   |   |   |
                                     +---+E--+E--+E--+---+---+---+---+---+---+---+---+---+---+
                                    b|   |   |   |   |   |   |   |   |   |AR9|   |   |   |   |
                                     +---+E--+E--+E--+---+---+---+---+---+---+---+---+---+---+
                                    c|   |   |   |   |   |   |AR9|   |   |   |   |   |   |   |
                                     +---+---+E--+E--+---+---+---+---+---+---+---+---+---+---+
                                    d|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                                     +---+E--+E--+E--+---+---+---+---+---+---+---+---+---+---+
                                    e|   |   |NN9|   |   |   |LR7|   |   |   |   |   |   |   |
                                     +---+E--+E--+E--+---+---+---+---+---+---+---+---+---+---+
                                    f|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                                     +---+E--+E--+E--+---+---+---+---+---+---+---+---+---+---+
                                    g|   |NN8|   |NR9|   |   |   |   |   |   |   |   |   |   |
                                     +---+---+E--+---+---+---+---+---+---+---+---+---+---+---+
                                    h|   |   |   |   |   |   |   |AN9|   |   |   |   |   |   |
                                     +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
                                    i|   |   |   |   |AN7|   |   |   |   |   |   |   |   |   |
                                     +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
                                    j|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                                     +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
                                    k|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                                     +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
                                    l|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                                     +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
                                    m|   |   |   |   |   |   |   |   |   |   |   |   |   |SN9|
                                     +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
                                    n|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                                     +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
                                    
                                    """;
    static final String PLATEAU_NIVEAU = """
                                      A   B   C   D   E   F   G   H   I   J   K   L   M   N 
                                    +E--+---+---+---+---+---+---+---+---+---+---+---+---+---+
                                   a|AN9|   |AN9|   |AN9|   |AN9|   |AN9|   |AN9|   |   |AN5|
                                    +E--+E--+---+---+---+---+---+---+---+---+---+---+---+---+
                                   b|   |AN9|   |AN9|   |   |AN5|   |   |   |   |   |   |AN6|
                                    +E--+E--+E--+---+---+---+---+---+---+E--+---+---+---+---+
                                   c|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                                    +E--+E--+E--+E--+---+---+---+---+E--+E--+---+---+---+---+
                                   d|   |   |   |   |   |   |   |   |   |   |   |   |   |AR8|
                                    +E--+E--+E--+---+---+---+---+E--+E--+E--+---+---+---+---+
                                   e|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                                    +E--+E--+---+---+---+---+E--+E--+E--+---+---+---+---+---+
                                   f|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                                    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
                                   g|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                                    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
                                   h|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                                    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
                                   i|   |   |   |   |   |   |   |   |   |   |   |   |   |AN3|
                                    +---+---+---+---+---+---+---+---+---+---+---+E--+E--+---+
                                   j|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                                    +---+---+---+---+---+---+---+---+---+---+E--+E--+---+---+
                                   k|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                                    +---+---+---+---+---+---+---+---+---+---+E--+---+---+---+
                                   l|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                                    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
                                   m|   |   |   |   |   |   |   |   |   |   |   |   |   |AN9|
                                    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
                                   n|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                                    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
                                   
                            
                                   
                                   """;
    static final String PLATEAU_NIVEAU99 = """
                                      A   B   C   D   E   F   G   H   I   J   K   L   M   N 
                                    +E--+---+---+---+---+---+---+---+---+---+---+---+---+---+
                                   a|   |   |   |   |   |SR5|   |   |   |   |   |   |   |   |
                                    +E--+E--+---+---+---+---+---+---+---+---+---+---+---+---+
                                   b|   |   |   |   |   |   |   |   |   |   |   |   |   |AR9|
                                    +E--+E--+E--+---+---+---+---+---+---+E--+---+---+---+---+
                                   c|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                                    +E--+E--+E--+E--+---+---+---+---+E--+E--+---+---+---+---+
                                   d|NR9|   |   |   |   |   |   |   |   |   |   |   |   |   |
                                    +E--+E--+E--+---+---+---+---+E--+E--+E--+---+---+---+---+
                                   e|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                                    +E--+E--+---+---+---+---+E--+E--+E--+---+---+---+---+---+
                                   f|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                                    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
                                   g|SN8|   |   |   |   |   |   |LR7|   |   |   |   |SN5|AR6|
                                    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
                                   h|   |   |   |   |   |   |   |   |   |   |   |   |   |SN6|
                                    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
                                   i|SN9|   |   |   |AN6|   |   |   |   |   |   |   |   |   |
                                    +---+---+---+---+---+---+---+---+---+---+---+E--+E--+---+
                                   j|   |   |   |   |   |   |   |   |   |   |AN9|   |   |   |
                                    +---+---+---+---+---+---+---+---+---+---+E--+E--+---+---+
                                   k|LR4|   |   |   |   |   |   |   |   |   |   |   |   |   |
                                    +---+---+---+---+---+---+---+---+---+---+E--+---+---+---+
                                   l|SR6|   |   |   |   |   |   |   |   |   |   |   |   |   |
                                    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
                                   m|   |   |   |   |   |   |   |   |   |LN7|LN4|   |   |   |
                                    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
                                   n|   |   |   |   |   |   |   |   |   |   |SR8|   |   |   |
                                    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
                                   
                                   
                                   
                                   """;
}
