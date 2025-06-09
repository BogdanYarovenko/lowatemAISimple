package lowatem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Tests unitaires de la classe JoueurLowatem.
 */
public class JoueurLowatemTest {

    /**
     * Test de la fonction actionsPossibles. Commentez les appels aux tests des
     * niveaux inférieurs, n'activez que le test du niveau à valider.
     */
    @Test
    public void testActionsPossibles() {
        // testActionsPossibles_niveau1();
        // testActionsPossibles_niveau2();
        // testActionsPossibles_niveau3();
        // testActionsPossibles_niveau4();
        // testActionsPossibles_niveau5();
        // testActionsPossibles_niveau_six();
        // testActionsPossibles_niveau_sept();
        // testActionsPossibles_niveau_huit();
        // testActionsPossibles_niveau_9();
        testActionsPossibles_niveau_dix();
    }

    /**
     * Test de la fonction actionsPossibles, au niveau 1.
     */
    public void testActionsPossibles_niveau1() {
        JoueurLowatem joueur = new JoueurLowatem();
        // un plateau sur lequel on veut tester actionsPossibles()
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU1);
        // on choisit la couleur du joueur
        char couleur = 'R';
        // on choisit le niveau
        int niveau = 1;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau
                = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles actionsPossibles
                = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // on peut afficher toutes les actions possibles calculées :
        actionsPossibles.afficher();
        // on peut aussi tester si une action est dans les actions possibles :
        assertTrue(actionsPossibles.contient("gGDgA,9,0"));
        assertTrue(actionsPossibles.contient("gGDgB,9,0"));
        assertTrue(actionsPossibles.contient("gGDgG,9,0"));
        assertTrue(actionsPossibles.contient("gGDgA,9,0"));
        assertTrue(actionsPossibles.contient("gGDaG,9,0"));
        assertTrue(actionsPossibles.contient("gGDnG,9,0"));
        // on peut aussi tester si une action n'est pas dans les actions possibles :
        assertFalse(actionsPossibles.contient("gGDgO,9,0"));
        assertFalse(actionsPossibles.contient("gGDgA,8,0"));
        // vérifions s'il y a le bon nombre d'actions possibles :
        assertEquals(Coordonnees.NB_LIGNES + Coordonnees.NB_COLONNES - 1,
                actionsPossiblesDepuisPlateau.length);
    }

    /**
     * Test de la fonction actionsPossibles, au niveau 2.
     */
    public void testActionsPossibles_niveau2() {
        JoueurLowatem joueur = new JoueurLowatem();
        // un plateau sur lequel on veut tester actionsPossibles()
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        // on choisit la couleur du joueur
        char couleur = 'R';
        // on choisit le niveau
        int niveau = 2;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau
                = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles actionsPossibles
                = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // on peut afficher toutes les actions possibles calculées :
        actionsPossibles.afficher();
        // on peut aussi tester si une action est dans les actions possibles :
        assertTrue(actionsPossibles.contient("dADdA,9,0"));
        assertTrue(actionsPossibles.contient("dADdN,9,0"));
        assertTrue(actionsPossibles.contient("dADdG,9,0"));
        assertTrue(actionsPossibles.contient("dADaA,9,0"));
        assertTrue(actionsPossibles.contient("dADnA,9,0"));
        // on peut aussi tester si une action n'est pas dans les actions possibles :
        assertFalse(actionsPossibles.contient("dADnO,9,0"));
        assertFalse(actionsPossibles.contient("dADdA,8,0"));
        // vérifions s'il y a le bon nombre d'actions possibles :
        assertEquals(Coordonnees.NB_LIGNES + Coordonnees.NB_COLONNES - 1,
                actionsPossiblesDepuisPlateau.length);
    }
    
    /**
     * Test de la fonction actionsPossibles, au niveau 3.
     */
    public void testActionsPossibles_niveau3() {
        JoueurLowatem joueur = new JoueurLowatem();
        // un plateau sur lequel on veut tester actionsPossibles()
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU3);
        // on choisit la couleur du joueur
        char couleur = 'R';
        // on choisit le niveau
        int niveau = 3;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau
                = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles actionsPossibles
                = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // on peut afficher toutes les actions possibles calculées :
        actionsPossibles.afficher();
        // on peut aussi tester si une action est dans les actions possibles :
        assertTrue(actionsPossibles.contient("cGDcM,19,0"));
        assertTrue(actionsPossibles.contient("dADdN,19,0"));
        // on peut aussi tester si une action n'est pas dans les actions possibles :
        assertFalse(actionsPossibles.contient("cGDcM,9,0"));
        assertFalse(actionsPossibles.contient("cGDfG,19,0"));
    }
    
    /**
     * Test de la fonction actionsPossibles, au niveau 4.
     */
    public void testActionsPossibles_niveau4() {
        JoueurLowatem joueur = new JoueurLowatem();
        // un plateau sur lequel on veut tester actionsPossibles()
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NB_PV);
        // on choisit la couleur du joueur
        char couleur = 'R';
        // on choisit le niveau
        int niveau = 4;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau
                = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles actionsPossibles
                = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // on peut afficher toutes les actions possibles calculées :
        actionsPossibles.afficher();
        // on peut aussi tester si une action est dans les actions possibles :
        assertTrue(actionsPossibles.contient("nNDnC,14,9"));
        assertTrue(actionsPossibles.contient("cDDcN,14,9"));
        assertTrue(actionsPossibles.contient("nNDeN,14,9"));
        // on peut aussi tester si une action n'est pas dans les actions possibles :
        assertFalse(actionsPossibles.contient("aADaB,14,9"));
        assertFalse(actionsPossibles.contient("cDDcL,14,9"));
        // on teste avec une autre couleur :
        couleur = 'N';
        actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        actionsPossibles = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        assertTrue(actionsPossibles.contient("aNDaL,14,9"));
        assertTrue(actionsPossibles.contient("aNDgN,14,9"));

        assertFalse(actionsPossibles.contient("aADaN,14,9"));
        assertFalse(actionsPossibles.contient("aADnA,14,9"));
    }
    
    /**
     * Test de la fonction actionsPossibles, au niveau 5.
     */
    public void testActionsPossibles_niveau5() {
        JoueurLowatem joueur = new JoueurLowatem();
        // un plateau sur lequel on veut tester actionsPossibles()
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NB_PV);
        // on choisit la couleur du joueur
        char couleur = 'R';
        // on choisit le niveau
        int niveau = 5;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau
                = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles actionsPossibles
                = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // on peut afficher toutes les actions possibles calculées :
        actionsPossibles.afficher();
        // on peut aussi tester si une action est dans les actions possibles :
        assertTrue(actionsPossibles.contient("cDDcKAcL,14,9"));
        assertTrue(actionsPossibles.contient("cDDcN,14,9"));
        assertTrue(actionsPossibles.contient("nNDeN,14,9"));
        // on peut aussi tester si une action n'est pas dans les actions possibles :
        assertFalse(actionsPossibles.contient("aADaB,14,9"));
        assertFalse(actionsPossibles.contient("cDDcL,14,9"));

        // on teste avec une autre couleur :
        couleur = 'N';
        actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        actionsPossibles = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        assertTrue(actionsPossibles.contient("aNDaL,14,9"));
        assertTrue(actionsPossibles.contient("aNDgN,14,9"));
        assertTrue(actionsPossibles.contient("cLDcEAcD,14,9"));

        assertFalse(actionsPossibles.contient("aADaN,14,9"));
        assertFalse(actionsPossibles.contient("aADnA,14,9"));
        assertFalse(actionsPossibles.contient("aADaMAaN,14,9"));
    }
    
    /**
     * Test de la fonction actionsPossibles, au niveau six.
     */
    public void testActionsPossibles_niveau_six() {
        JoueurLowatem joueur = new JoueurLowatem();
        // un plateau sur lequel on veut tester actionsPossibles()
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NB_PV);
        // on choisit la couleur du joueur
        char couleur = 'R';
        // on choisit le niveau
        int niveau = 6;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau
                = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles actionsPossibles
                = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // on peut afficher toutes les actions possibles calculées :
        actionsPossibles.afficher();
        // on peut aussi tester si une action est dans les actions possibles :
        assertTrue(actionsPossibles.contient("cDDcKAcL,14,8"));
        assertTrue(actionsPossibles.contient("cDDcN,14,9"));
        assertTrue(actionsPossibles.contient("nNDeN,14,9"));
        // on peut aussi tester si une action n'est pas dans les actions possibles :
        assertFalse(actionsPossibles.contient("aADaB,14,9"));
        assertFalse(actionsPossibles.contient("cDDcL,14,9"));

        // on teste avec une autre couleur :
        couleur = 'N';
        actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        actionsPossibles = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        assertTrue(actionsPossibles.contient("aNDaL,14,9"));
        assertTrue(actionsPossibles.contient("aNDgN,14,9"));
        assertTrue(actionsPossibles.contient("cLDcEAcD,10,8"));

        assertFalse(actionsPossibles.contient("aADaN,14,9"));
        assertFalse(actionsPossibles.contient("aADnA,14,9"));
        assertFalse(actionsPossibles.contient("aADaMAaN,14,9"));
    }

    /**
     * Test de la fonction actionsPossibles, au niveau 7.
     */
    public void testActionsPossibles_niveau_sept() {
        JoueurLowatem joueur = new JoueurLowatem();
        // un plateau sur lequel on veut tester actionsPossibles()
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NB_PV_EAU);
        // on choisit la couleur du joueur
        char couleur = 'R';
        // on choisit le niveau
        int niveau = 7;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau
                = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles actionsPossibles
                = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // on peut afficher toutes les actions possibles calculées :
        actionsPossibles.afficher();
        // on peut aussi tester si une action est dans les actions possibles :
        assertTrue(actionsPossibles.contient("cDDcKAcL,14,8"));
        assertTrue(actionsPossibles.contient("cDDcN,14,9"));
        assertTrue(actionsPossibles.contient("nNDeN,14,9"));
        // on peut aussi tester si une action n'est pas dans les actions possibles :
        assertFalse(actionsPossibles.contient("aADaB,14,9"));
        assertFalse(actionsPossibles.contient("cDDcL,14,9"));
        // eau
        assertFalse(actionsPossibles.contient("nADnD,14,9"));
        assertFalse(actionsPossibles.contient("nADnE,14,9"));

        // on teste avec une autre couleur :
        couleur = 'N';
        actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        actionsPossibles = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        assertTrue(actionsPossibles.contient("aNDaL,14,9"));
        assertTrue(actionsPossibles.contient("aNDgN,14,9"));

        assertFalse(actionsPossibles.contient("aADaN,14,9"));
        assertFalse(actionsPossibles.contient("aADnA,14,9"));
        assertFalse(actionsPossibles.contient("aADaMAaN,14,9"));
    }
    
    /**
     * Test de la fonction actionsPossibles, au niveau 8.
     */
    public void testActionsPossibles_niveau_huit() {
        JoueurLowatem joueur = new JoueurLowatem();
        // un plateau sur lequel on veut tester actionsPossibles()
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NB_PV_EAU);
        // on choisit la couleur du joueur
        char couleur = 'R';
        // on choisit le niveau
        int niveau = 8;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau
                = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles actionsPossibles
                = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // on peut afficher toutes les actions possibles calculées :
        actionsPossibles.afficher();
        // on peut aussi tester si une action est dans les actions possibles :
        assertTrue(actionsPossibles.contient("cDDcKAcL,12,8"));
        assertTrue(actionsPossibles.contient("cDDcN,11,9"));
        assertTrue(actionsPossibles.contient("nNDeN,12,9"));
        // on peut aussi tester si une action n'est pas dans les actions possibles :
        assertFalse(actionsPossibles.contient("aADaB,14,9"));
        assertFalse(actionsPossibles.contient("cDDcL,14,9"));
        // on s'assure que les déplacements mettent des dégâts
        assertFalse(actionsPossibles.contient("cDDcN,14,9"));
        // eau
        assertFalse(actionsPossibles.contient("nADnD,14,9"));
        assertFalse(actionsPossibles.contient("nADnE,14,9"));

        // on teste avec une autre couleur :
        couleur = 'N';
        actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        actionsPossibles = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        assertTrue(actionsPossibles.contient("aNDaL,14,9"));
        assertTrue(actionsPossibles.contient("aNDgN,14,8"));

        assertFalse(actionsPossibles.contient("aADaN,14,9"));
        assertFalse(actionsPossibles.contient("aADnA,14,9"));
        assertFalse(actionsPossibles.contient("aADaMAaN,14,9"));
    }
    
    /**
     * Test de la fonction actionsPossibles, au niveau 9.
     */
    public void testActionsPossibles_niveau_9() {
        JoueurLowatem joueur = new JoueurLowatem();
        // un plateau sur lequel on veut tester actionsPossibles()
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_LVLNEUF);
        // on choisit la couleur du joueur
        char couleur = 'N';
        // on choisit le niveau
        int niveau = 9;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau
                = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles actionsPossibles
                = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // on peut afficher toutes les actions possibles calculées :
        actionsPossibles.afficher();
        assertFalse(actionsPossibles.contient("dEDmEAnE,52,45"));
        assertTrue(actionsPossibles.contient("eCDeIAeH,54,48"));
        assertTrue(actionsPossibles.contient("aIDbI,59,52"));
        assertTrue(actionsPossibles.contient("aIDbIAaB,53,48"));
        assertTrue(actionsPossibles.contient("aIDbIAbN,53,49"));
    }
    
    public void testActionsPossibles_niveau_dix() {
        testActionsPossibles_niveau_9();
        JoueurLowatem joueur = new JoueurLowatem();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_LVLNEUF);
        char couleur = 'R';
        int niveau = 10;
        String[] aPDP = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles ap = new ActionsPossibles(aPDP);
        Plateau pl = new Plateau(plateau);
        NbPointsDeVie nombrePV = pl.nbPv;
        ap.afficher();
        assertTrue(ap.contient("aBDaHAcN,52,46"));
    }
    
    @Test
    public void testAjoutDeplDepuis() {
        JoueurLowatem joueur = new JoueurLowatem();
        ActionsPossibles actions = new ActionsPossibles();
        Unite uniteUn = new Unite(   'S','R', 5, Coordonnees.depuisCars('f', 'D'));
        Unite uniteDeux = new Unite( 'S','R', 4, Coordonnees.depuisCars('f', 'C'));
        Unite uniteTrois = new Unite('S','N', 2, Coordonnees.depuisCars('e', 'I'));
        Plateau test = new Plateau();
        test.ajouterUnite(uniteUn);
        test.ajouterUnite(uniteDeux);
        test.ajouterUnite(uniteTrois);
        joueur.ajoutDeplDepuis(uniteUn, actions, test);
        // les horizontaux avec la case d'origine
        actions.afficher();
        assertTrue(actions.contient("fDDfA,9,2"));
        assertTrue(actions.contient("fDDfB,9,2"));
        assertTrue(actions.contient("fDDfD,9,2"));
        assertTrue(actions.contient("fDDfF,9,2"));
        assertTrue(actions.contient("fDDfH,9,2"));
        assertTrue(actions.contient("fDDfN,9,2"));
        // les verticaux
        assertTrue(actions.contient("fDDaD,9,2"));
        assertTrue(actions.contient("fDDhD,9,2"));
        // les attaques
        assertTrue(actions.contient("fDDfIAeI,8,0"));
        // des mauvais
        assertFalse(actions.contient("fDDaF,9,2"));
        assertFalse(actions.contient("fDDaA,9,2"));
        assertFalse(actions.contient("fDDfC,9,2"));
        // le bon nombre d'unités
        assertFalse(actions.contient("fDDfA,1,0"));
        // finalement on doit en avoir 1 + 13 + 13 - 1 + 1
        assertEquals(27, actions.nbActions);
    }

    @Test
    public void testChaineActionAttaque() {
        assertEquals("cEDfCAgC,9,2", JoueurLowatem.chaineActionAttaque(
                Coordonnees.depuisCars('c', 'E'),
                Coordonnees.depuisCars('f', 'C'),
                Coordonnees.depuisCars('g', 'C'),
                new NbPointsDeVie(9, 2)));
        assertEquals("nDDnDAnE,9,0", JoueurLowatem.chaineActionAttaque(
                Coordonnees.depuisCars('n', 'D'),
                Coordonnees.depuisCars('n', 'D'),
                Coordonnees.depuisCars('n', 'E'),
                new NbPointsDeVie(9, 0)));
    }
    
    @Test
    public void testChaineActionDepl() {
        assertEquals("cEDfC,9,0", JoueurLowatem.chaineActionDepl(
                Coordonnees.depuisCars('c', 'E'),
                Coordonnees.depuisCars('f', 'C'),
                new NbPointsDeVie(9, 0)));
        assertEquals("nDDnD,9,0", JoueurLowatem.chaineActionDepl(
                Coordonnees.depuisCars('n', 'D'),
                Coordonnees.depuisCars('n', 'D'),
                new NbPointsDeVie(9, 0)));
    }
    
    @Test
    public void testLongueurDepl() {
        Coordonnees origine = Coordonnees.depuisCars('b', 'B');
        Coordonnees bas = Coordonnees.depuisCars('f', 'B');
        Coordonnees gauche = Coordonnees.depuisCars('b', 'A');
        Coordonnees droite = Coordonnees.depuisCars('b', 'M');
        Coordonnees haut = Coordonnees.depuisCars('a', 'B');
        assertEquals(1, JoueurLowatem.longueurDepl(origine, gauche));
        assertEquals(11, JoueurLowatem.longueurDepl(origine, droite));
        assertEquals(4, JoueurLowatem.longueurDepl(origine, bas));
        assertEquals(1, JoueurLowatem.longueurDepl(origine, haut));
    }
    
    /**
     * Un plateau de base, sous forme de chaîne. Pour construire une telle
     * chaîne depuis votre sortie.log, déclarez simplement final String
     * MON_PLATEAU = ""; puis copiez le plateau depuis votre sortie.log, et
     * collez-le entre les guillemets. Puis Alt+Shift+f pour mettre en forme.
     */
    final String PLATEAU_NIVEAU1
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
              g|   |   |   |   |   |   |SR9|   |   |   |   |   |   |   |
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

    final String PLATEAU_NIVEAU2
            = """
                 A   B   C   D   E   F   G   H   I   J   K   L   M   N 
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              a|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              b|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              c|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              d|SR9|   |   |   |   |   |   |   |   |   |   |   |   |   |
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
    
    final String PLATEAU_NIVEAU3
            = """
                 A   B   C   D   E   F   G   H   I   J   K   L   M   N 
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              a|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              b|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              c|   |   |   |   |   |   |SR1|   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              d|SR9|   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              e|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              f|   |   |   |   |   |   |SR2|   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              g|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              h|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              i|   |   |   |   |   |   |   |   |   |SR7|   |   |   |   |
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

    final String PLATEAU_NB_PV
            = """
                 A   B   C   D   E   F   G   H   I   J   K   L   M   N 
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              a|SN3|   |   |   |   |   |   |   |   |   |   |   |   |SN5|
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              b|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              c|   |   |   |SR4|   |   |   |   |   |   |   |SN1|   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              d|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              e|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              f|   |   |   |   |   |   |   |   |   |   |   |   |   |SR2|
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
              n|SR3|   |   |   |   |   |   |   |   |   |   |   |   |SR5|
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              """;
    
    final String PLATEAU_NB_PV_EAU
            = """
                 A   B   C   D   E   F   G   H   I   J   K   L   M   N 
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              a|SN3|   |   |   |   |   |   |   |   |   |   |   |   |SN5|
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              b|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              c|   |   |   |SR4|   |   |   |   |   |   |   |SN1|   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              d|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              e|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              f|   |   |   |   |   |   |   |   |   |   |   |   |   |SR2|
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
               +---+---+---+E--+---+---+---+---+---+---+---+---+---+---+
              n|SR3|   |   |   |   |   |   |   |   |   |   |   |   |SR5|
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              """;
    
    final String PLATEAU_LVLNEUF
            = """
                 A   B   C   D   E   F   G   H   I   J   K   L   M   N 
               +---+---+---+---+---+---+---+---+---+---+---+---+---+E--+
              a|   |LR8|   |   |   |   |   |   |LN4|   |   |   |   |AN7|
               +---+---+---+---+---+---+---+---+---+---+---+---+---+E--+
              b|   |   |   |   |AN8|   |   |   |   |   |   |   |   |NR6|
               +---+---+---+---+---+---+---+---+---+---+---+---+E--+E--+
              c|   |   |   |   |   |   |   |   |   |   |   |   |NR9|NN9|
               +---+---+---+---+---+---+---+---+---+---+---+E--+E--+E--+
              d|   |   |   |   |LN9|   |   |   |   |   |   |   |   |NN6|
               +---+---+---+---+---+---+---+---+---+---+---+---+E--+E--+
              e|   |   |SN8|   |   |   |   |AR7|   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              f|   |   |   |   |   |   |   |   |   |   |   |CN1|   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              g|   |   |   |   |   |   |   |   |   |   |SR8|   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              h|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              i|   |SR8|   |   |   |   |   |   |   |LR5|   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              j|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              k|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              l|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              m|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              n|   |   |   |   |AR8|   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              """;

    final String PLATEAU_NIVEAU10 = """
                A   B   C   D   E   F   G   H   I   J   K   L   M   N 
              +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
             a|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
              +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
             b|   |   |   |   |   |   |AN3|   |   |   |   |   |   |   |
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
             i|   |   |   |   |   |   |   |   |   |AN1|   |   |   |   |
              +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
             j|SN1|   |   |   |   |   |   |   |   |   |   |   |   |   |
              +---+---+---+---+---+---+---+---+---+---+E--+---+---+---+
             k|   |   |   |   |   |AR1|   |   |   |   |NR3|   |   |   |
              +---+---+---+---+---+---+---+---+E--+---+E--+---+---+---+
             l|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
              +---+---+---+---+---+---+---+---+E--+---+---+---+---+---+
             m|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
              +---+---+---+---+---+---+---+---+E--+---+---+---+---+---+
             n|   |   |   |AR1|   |   |   |   |   |   |   |   |   |   |
              +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
             """;
            
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