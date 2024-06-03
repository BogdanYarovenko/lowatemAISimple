package towa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Tests sur la classe JoueurTowa.
 */
public class JoueurTowaTest {

    /**
     * Test de la fonction actionsPossibles. Commentez les appels aux tests des
     * niveaux inférieurs, n'activez que le test du niveau à valider.
     */
    @Test
    public void testActionsPossibles() {

        //testActionsPossibles_niveau1();
        //testActionsPossibles_niveau2();
        //testActionsPossibles_niveau3();
        //testActionsPossibles_niveau4();
        //testActionsPossibles_niveau5();
        //testActionsPossibles_niveau6();
    }

    /**
     * Test de la fonction actionsPossibles, au niveau 1.
     */
    public void testActionsPossibles_niveau1() {
        JoueurTowa joueur = new JoueurTowa();
        // un plateau sur lequel on veut tester actionsPossibles()
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU1);
        // on choisit la couleur du joueur
        char couleur = Case.CAR_NOIR;        // on choisit le niveau
        int niveau = 1;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles actionsPossibles
                = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // on peut afficher toutes les actions possibles calculées :
        actionsPossibles.afficher();
        // on peut aussi tester si une action est dans les actions possibles :
        assertTrue(actionsPossibles.contient("PaB,1,0"));
        // on peut aussi tester si une action n'est pas dans les actions 
        // possibles :
        assertFalse(actionsPossibles.contient("PaQ,1,0"));
        assertFalse(actionsPossibles.contient("PaA,0,0"));
        // testons les 4 coins :
        assertTrue(actionsPossibles.contient("PaA,1,0"));
        assertTrue(actionsPossibles.contient("PpA,1,0"));
        assertTrue(actionsPossibles.contient("PaP,1,0"));
        assertTrue(actionsPossibles.contient("PpP,1,0"));
        // vérifions s'il y a le bon nombre d'actions possibles :
        assertEquals(Coordonnees.NB_LIGNES * Coordonnees.NB_COLONNES,
                actionsPossibles.nbActions);
    }

    /**
     * Test de la fonction actionsPossibles, au niveau 2.
     */
    public void testActionsPossibles_niveau2() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        // sur le plateau initial : 27 pions noirs et 20 pions blancs
        int niveau = 2;
        // 1 - joueur noir
        char couleur = Case.CAR_NOIR;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles actionsPossibles
                = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // pose sur case vide : possible
        assertTrue(actionsPossibles.contient("PaB,28,20"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PbA,28,20"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PaG,28,20"));
        // pose sur case de même couleur et hauteur > 1 : possible
        assertTrue(actionsPossibles.contient("PcK,28,20"));
        // 2 - joueur blanc
        couleur = Case.CAR_BLANC;
        // on lance actionsPossibles
        actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        actionsPossibles = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // pose sur case vide : possible
        assertTrue(actionsPossibles.contient("PaB,27,21"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PaG,27,21"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PbA,27,21"));
        // pose sur case de même couleur et hauteur > 1 : possible
        assertTrue(actionsPossibles.contient("PlF,27,21"));

    }

    /**
     * Test de la fonction actionsPossibles, au niveau 3.
     */
    public void testActionsPossibles_niveau3() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU3);
        // sur le plateau initial : 20 pions noirs et 29 pions blancs
        int niveau = 3;
        // 1 - joueur noir
        char couleur = Case.CAR_NOIR;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles actionsPossibles
                = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // pose sur case vide : possible
        assertTrue(actionsPossibles.contient("PaB,21,29"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PaA,21,29"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PcM,21,29"));
        // pose sur case de même couleur et hauteur entre 1 et 4 : possible
        assertTrue(actionsPossibles.contient("PmM,21,29"));
        // pose sur case de même couleur ou hateur va depasser 4: impossible
        assertFalse(actionsPossibles.contient("PgD,21,29"));
        // 2 - joueur blanc
        couleur = Case.CAR_BLANC;
        // on lance actionsPossibles
        actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        actionsPossibles = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // pose sur case vide : possible
        assertTrue(actionsPossibles.contient("PaB,20,30"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PeE,20,30"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PaA,20,30 "));
        // pose sur case de même couleur et hauteur > 1 : possible
        assertTrue(actionsPossibles.contient("PdJ,20,30"));
        // pose sur case de même couleur ou hateur va depasser 4: impossible
        assertFalse(actionsPossibles.contient("PfD,20,30"));
    }

    /**
     * Test de la fonction actionsPossibles, au niveau 4.
     */
    public void testActionsPossibles_niveau4() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU4);
        // sur le plateau initial : 26 pions noirs et 32 pions blancs
        int niveau = 4;
        // 1 - joueur noir
        char couleur = Case.CAR_NOIR;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles actionsPossibles
                = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // pose sur case vide : possible
        assertTrue(actionsPossibles.contient("PaA,27,32"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PaB,27,32"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PaC,27,32"));
        // pose sur case de même couleur et hauteur entre 1 et 4 : possible
        assertTrue(actionsPossibles.contient("PeN,27,32"));
        // pose sur case de même couleur ou hateur va depasser 4: impossible
        assertFalse(actionsPossibles.contient("PeM,27,32"));
        // Activation
        assertTrue(actionsPossibles.contient("AdO,26,28"));
        // L'activation d'une tour détruit toutes les tours ennemies strictement plus basses
        assertTrue(actionsPossibles.contient("AeM,26,32"));
        assertFalse(actionsPossibles.contient("AeM,26,28"));
        // 2 - joueur blanc
        couleur = Case.CAR_BLANC;
        // on lance actionsPossibles
        actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        actionsPossibles = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // pose sur case vide : possible
        assertTrue(actionsPossibles.contient("PaD,26,33"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PaC,26,33"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PaB,26,33 "));
        // pose sur case de même couleur et hauteur > 1 : possible
        assertTrue(actionsPossibles.contient("PcO,26,33"));
        // pose sur case de même couleur ou hateur va depasser 4: impossible
        assertFalse(actionsPossibles.contient("PdN,26,33"));
        //Activation
        assertTrue(actionsPossibles.contient("AdN,21,32"));
        //L'activation d'une tour détruit toutes les tours ennemies strictement plus basses
        assertTrue(actionsPossibles.contient("AfJ,26,32"));
        assertFalse(actionsPossibles.contient("AfJ,25,32"));

    }

    /**
     * Test de la fonction actionsPossibles, au niveau 5.
     */
    public void testActionsPossibles_niveau5() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU5);
        // sur le plateau initial : 15 pions noirs et 14 pions blancs
        int niveau = 5;
        // 1 - joueur noir
        char couleur = Case.CAR_NOIR;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles actionsPossibles
                = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // pose sur case vide : possible
        assertTrue(actionsPossibles.contient("PaA,16,14"));
        // pose sur case vide à côté de pion ennemi(en ajoutant 2 points): possible
        assertTrue(actionsPossibles.contient("PdO,17,14"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PbC,16,14"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PdP,16,14"));
        // pose sur case de même couleur et hauteur entre 1 et 4 : possible
        assertTrue(actionsPossibles.contient("PgC,16,14"));
        // pose sur case de même couleur ou hateur va depasser 4: impossible
        assertFalse(actionsPossibles.contient("PeD,16,14"));
        // Activation 
        assertTrue(actionsPossibles.contient("AnO,15,13"));
        // L'activation d'une tour détruit toutes les tours ennemies strictement plus basses
        assertTrue(actionsPossibles.contient("AiM,15,14"));
        assertFalse(actionsPossibles.contient("AiM,15,9"));

        // 2 - joueur blanc
        couleur = Case.CAR_BLANC;
        // on lance actionsPossibles
        actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        actionsPossibles = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // pose sur case vide : possible
        assertTrue(actionsPossibles.contient("PbO,15,15"));
        // pose sur case vide à côté de pion ennemi(en ajoutant 2 points): possible
        assertTrue(actionsPossibles.contient("PbD,15,16"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PfK,15,15"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PbC,15,15"));
        // pose sur case de même couleur et hauteur > 1 : possible
        assertTrue(actionsPossibles.contient("PdP,15,15"));
        // pose sur case de même couleur ou hateur va depasser 4: impossible
        assertFalse(actionsPossibles.contient("PiN,15,15"));
        // Activation  
        assertTrue(actionsPossibles.contient("AiN,14,14"));
        // L'activation d'une tour détruit toutes les tours ennemies strictement plus basses
        assertTrue(actionsPossibles.contient("AiL,15,14"));
        assertFalse(actionsPossibles.contient("AiL,14,14"));

    }

    /**
     * Test de la fonction actionsPossibles, au niveau 6.
     */
    public void testActionsPossibles_niveau6() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU6);
        // sur le plateau initial : 11 pions noirs et 13 pions blancs
        int niveau = 6;
        // 1 - joueur noir
        char couleur = Case.CAR_NOIR;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles actionsPossibles
                = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // pose sur case vide : possible
        assertTrue(actionsPossibles.contient("PeA,12,13"));
        // pose sur case vide à côté de pion ennemi(en ajoutant 2 points): possible
        assertTrue(actionsPossibles.contient("PaF,13,13"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PaM,12,13"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PbB,12,13"));
        // pose sur case de même couleur et hauteur entre 1 et 4 : possible
        assertTrue(actionsPossibles.contient("PgF,12,13"));
        // pose sur case de même couleur ou hateur va depasser 4: impossible
        assertFalse(actionsPossibles.contient("PpG,12,13"));
        // Activation d'une tour niveau 6 
        assertTrue(actionsPossibles.contient("AbI,11,12"));
        // 2 - joueur blanc
        couleur = Case.CAR_BLANC;
        // on lance actionsPossibles
        actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        actionsPossibles = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // pose sur case vide : possible
        assertTrue(actionsPossibles.contient("PaA,11,14"));
        // pose sur case vide à côté de pion ennemi(en ajoutant 2 points): possible
        assertTrue(actionsPossibles.contient("PkK,11,15"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PhL,11,14"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PgF,11,14"));
        // pose sur case de même couleur et hauteur > 1 : possible
        assertTrue(actionsPossibles.contient("PmD,11,14"));
        // pose sur case de même couleur ou hateur va depasser 4: impossible
        assertFalse(actionsPossibles.contient("PmL,11,14"));
        //Activation d'une tour niveau 6
        assertTrue(actionsPossibles.contient("AmL,10,13"));
        
    }

    /**
     * Test les nombres de couleurs des joueurs
     */
    @Test
    public void testNbPions() {

        //plateau1 : 0 noir, 0 blanc
        Case[][] plateau1 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU1);
        assertEquals(0, JoueurTowa.nbPions(plateau1).nbPionsNoirs);
        assertEquals(0, JoueurTowa.nbPions(plateau1).nbPionsBlancs);
        // plateau2 : 27 noirs, 20 blancs
        Case[][] plateau2 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        assertEquals(27, JoueurTowa.nbPions(plateau2).nbPionsNoirs);
        assertEquals(20, JoueurTowa.nbPions(plateau2).nbPionsBlancs);
        //plateau3 : 20 noirs, 29 blancs 
        Case[][] plateau3 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU3);
        assertEquals(20, JoueurTowa.nbPions(plateau3).nbPionsNoirs);
        assertEquals(29, JoueurTowa.nbPions(plateau3).nbPionsBlancs);
        //plateau4 : 26 noirs, 32 blancs 
        Case[][] plateau4 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU4);
        assertEquals(26, JoueurTowa.nbPions(plateau4).nbPionsNoirs);
        assertEquals(32, JoueurTowa.nbPions(plateau4).nbPionsBlancs);
        //plateau 5: 15 noirs, 14 blancs
        Case[][] plateau5 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU5);
        assertEquals(15, JoueurTowa.nbPions(plateau5).nbPionsNoirs);
        assertEquals(14, JoueurTowa.nbPions(plateau5).nbPionsBlancs);
        //plateau 6: 11 noirs, 13 blancs  
        Case[][] plateau6 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU6);
        assertEquals(11, JoueurTowa.nbPions(plateau6).nbPionsNoirs);
        assertEquals(13, JoueurTowa.nbPions(plateau6).nbPionsBlancs);
    }

    /**
     * Test de la fonction ajoutActionPose.
     */
    public void testAjoutActionPose() {
        JoueurTowa joueur = new JoueurTowa();
        ActionsPossibles actions = new ActionsPossibles();
        Case[][] plateau1 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU6);
        NbPions nbPions = new NbPions(0, 0);
        // pour l'instant pas d'action possible
        assertEquals(0, actions.nbActions);

        // on crée le tableau d'actions et on en ajoute une
        joueur.ajoutActionPose(Coordonnees.depuisCars('f', 'D'), actions,
                nbPions, Case.CAR_NOIR, plateau1);
        // l'action est devenue possible
        assertTrue(actions.contient("PfD,1,0"));
        // une action possible mais qui n'a pas encore été ajoutée
        assertFalse(actions.contient("PbH,1,0"));
        // pour l'instant une seule action possible
        assertEquals(1, actions.nbActions);
        // ajout d'une deuxième action possible
        joueur.ajoutActionPose(Coordonnees.depuisCars('b', 'H'), actions,
                nbPions, Case.CAR_NOIR, plateau1);
        // l'action a bien été ajoutée
        assertTrue(actions.contient("PbH,1,0"));
        // désormais, deux actions possibles
        assertEquals(2, actions.nbActions);

    }

    /**
     * Un plateau de base, sous forme de chaîne. Pour construire une telle
     * chaîne depuis votre sortie.log, déclarez simplement : final String
     * MON_PLATEAU = ""; puis copiez le plateau depuis votre sortie.log, et
     * collez-le entre les guillemets. Puis Alt+Shift+f pour mettre en forme.
     */
    final String PLATEAU_NIVEAU1
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "o|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "p|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+";

    final String PLATEAU_NIVEAU2
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |   |   |   |   |   |B1 |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|N1 |   |   |   |   |   |   |B1 |   |   |   |B1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |B1 |   |B1 |   |   |   |   |   |N5 |B1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |   |B1 |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|B1 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |B1 |   |   |   |   |   |   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |   |   |   |   |N1 |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |N1 |N1 |   |   |   |   |   |   |   |   |   |N1 |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |   |N1 |   |   |   |B1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |N1 |   |   |   |   |N2 |   |   |   |   |B1 |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |   |N3 |B4 |B1 |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |   |   |B1 |B2 |N1 |   |   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |N1 |N1 |N2 |   |   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "o|   |N1 |   |   |   |   |   |N1 |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "p|   |   |   |   |   |   |B1 |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n";
    final String PLATEAU_NIVEAU3
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|N1 |   |   |   |   |   |   |   |   |   |   |   |   |   |N1 |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |   |   |   |   |   |   |   |   |N1 |   |B3 |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|N1 |N1 |   |   |   |   |   |   |   |B2 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|   |   |   |N3 |B1 |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |   |N1 |B4 |B4 |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |B1 |N4 |B3 |   |B1 |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |   |   |N1 |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |   |N1 |B1 |   |   |   |   |   |   |   |   |B1 |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |   |   |   |   |   |   |   |   |B2 |   |N2 |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |   |   |   |   |   |   |B3 |B1 |B1 |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "o|   |   |   |   |   |   |   |   |   |   |N2 |   |N1 |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "p|   |   |   |   |   |B1 |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n";

    final String PLATEAU_NIVEAU4
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |N1 |B1 |   |B1 |   |B1 |B1 |N1 |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|   |   |   |   |   |B1 |B1 |   |   |   |   |   |   |   |N1 |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |   |   |   |   |   |   |   |   |   |   |B1 |B4 |B2 |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |N1 |   |   |B1 |   |   |   |   |   |   |B4 |N3 |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|   |   |   |   |   |   |   |   |   |   |   |   |N4 |N2 |B2 |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |   |   |   |   |   |   |   |   |B1 |N1 |   |N1 |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |   |N1 |   |   |   |B1 |   |N1 |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |N1 |   |   |   |   |   |   |   |   |N1 |   |   |   |   |N1 |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |   |   |   |   |   |N1 |   |   |   |   |B1 |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |B1 |   |B1 |   |   |   |B1 |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|N1 |   |   |   |   |   |   |   |   |   |   |B1 |N1 |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |   |   |   |   |   |B1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "o|N1 |B1 |   |   |   |   |   |   |   |B1 |   |   |B1 |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "p|   |   |N1 |   |   |   |   |   |   |   |   |B1 |N1 |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n";
    final String PLATEAU_NIVEAU5
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|   |   |N1 |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |B2 |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|   |   |   |N4 |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |   |   |   |   |   |   |   |   |   |B1 |   |   |   |B1 |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |N1 |   |   |   |   |   |N1 |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |   |   |N1 |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |   |   |   |   |   |   |   |   |   |   |B1 |N1 |B4 |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |   |   |   |   |   |   |   |   |B1 |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |N1 |   |   |   |   |B1 |   |   |   |   |   |   |B1 |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |   |   |   |B1 |   |   |N1 |   |N1 |   |   |   |B1 |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |   |   |   |   |   |   |N1 |   |   |   |N2 |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "o|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "p|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n";
    final String PLATEAU_NIVEAU6
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |   |   |   |   |   |   |   |   |   |   |   |N1 |   |   |N1 |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|   |   |   |   |B1 |   |   |   |N2 |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |B1 |   |   |   |   |   |   |   |   |   |   |   |B1 |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |   |   |   |   |   |   |   |B1 |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |B1 |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |   |   |   |N1 |   |   |   |   |   |   |   |   |B1 |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |   |   |   |   |   |B1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |B1 |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |   |   |   |   |   |N1 |   |N1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |   |B1 |   |   |   |   |   |   |   |B4 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "o|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "p|   |   |   |   |   |   |N4 |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n";
}
