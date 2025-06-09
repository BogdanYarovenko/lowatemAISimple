package lowatem;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class IALowatemTest {

    @Test
    public void testMettreAJour_niveau7() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU7);
        // une attaque sans réel déplacement, où l'attaqué meurt
        Coordonnees coordSrc = Coordonnees.depuisCars('e', 'A');
        Case src = plateau[coordSrc.ligne][coordSrc.colonne];
        Coordonnees coordAtq = Coordonnees.depuisCars('e', 'B');
        Case atq = plateau[coordAtq.ligne][coordAtq.colonne];
        assertEquals(3, src.pointsDeVie);
        assertEquals(3, atq.pointsDeVie);
        IALowatem.mettreAJour(new Plateau(plateau), "eADeAAeB");
        assertEquals(2, src.pointsDeVie);
        assertEquals(0, atq.pointsDeVie);
    }

    @Test
    public void testMettreAJour_niveau9() {
        JoueurLowatem joueur = new JoueurLowatem();

        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU9);

        char couleurR = 'R';
        char couleurN = 'N';

        int niveau = 9;
        // Actions possibles couleur R
        String[] actionsPossiblesDepuisPlateauRouge
                = joueur.actionsPossibles(plateau, couleurR, niveau);
        ActionsPossibles actionsPossiblesRouge
                = new ActionsPossibles(actionsPossiblesDepuisPlateauRouge);
        // Actions possibles couleur N
        String[] actionsPossiblesDepuisPlateauNoir
                = joueur.actionsPossibles(plateau, couleurN, niveau);
        ActionsPossibles actionsPossiblesNoir
                = new ActionsPossibles(actionsPossiblesDepuisPlateauNoir);
        System.out.println("Actions possibles pour Rouge:");
        actionsPossiblesRouge.afficher();

        System.out.println("Actions possibles pour Noir:");
        actionsPossiblesNoir.afficher();
        {
            // déplacement sur place, sans attaque
           Coordonnees coordSrc = Coordonnees.depuisCars('a', 'A');
            Case src = plateau[coordSrc.ligne][coordSrc.colonne];
            assertEquals(6, src.pointsDeVie);
            IALowatem.mettreAJour(new Plateau(plateau), "aADaA");
            assertEquals(6, src.pointsDeVie);
        }
        {
            // déplacement, sans attaque
            // jMDbM,29,27
            Coordonnees coordSrc = Coordonnees.depuisCars('j', 'M');
            Case src = plateau[coordSrc.ligne][coordSrc.colonne];
            Coordonnees coordDst = Coordonnees.depuisCars('b', 'M');
            Case dst = plateau[coordDst.ligne][coordDst.colonne];
            assertEquals(4, src.pointsDeVie);
            assertEquals(0, dst.pointsDeVie);
            IALowatem.mettreAJour(new Plateau(plateau), "jMDbM");
            assertEquals(0, src.pointsDeVie);
            assertEquals(2, dst.pointsDeVie);
        }
        {
            // attaque
            // nEDnGAnH,25,26
            Coordonnees coordSrc = Coordonnees.depuisCars('n', 'E');
            Case src = plateau[coordSrc.ligne][coordSrc.colonne];
            Coordonnees coordDst = Coordonnees.depuisCars('n', 'G');
            Case dst = plateau[coordDst.ligne][coordDst.colonne];
            Coordonnees coordAtq = Coordonnees.depuisCars('n', 'H');
            Case atq = plateau[coordAtq.ligne][coordAtq.colonne];
            assertEquals(5, src.pointsDeVie); // AN5
            assertEquals(0, dst.pointsDeVie); // nG = 0
            assertEquals(4, atq.pointsDeVie); // SR4
            IALowatem.mettreAJour(new Plateau(plateau), "nEDnGAnH");
            assertEquals(0, src.pointsDeVie); // nE = 0
            assertEquals(2, dst.pointsDeVie); // 4 - 2
            assertEquals(0, atq.pointsDeVie);
        }
    }

    public static final String PLATEAU_NIVEAU7
            = """
                 A   B   C   D   E   F   G   H   I   J   K   L   M   N 
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              a|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              b|   |   |   |   |   |   |   |   |SN4|   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              c|   |SN9|   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              d|   |   |   |   |   |   |   |   |SR6|   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              e|SN3|SR3|   |   |   |   |   |   |   |   |   |   |SN7|   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              f|   |   |   |SN5|   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              g|   |   |   |   |   |   |   |   |   |   |   |   |   |SR7|
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              h|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              i|   |   |   |   |   |   |   |   |   |   |   |SR5|   |SR4|
               +---+---+---+---+E--+---+---+---+---+---+---+---+---+---+
              j|   |   |   |SR5|   |   |   |   |   |   |   |   |   |SN7|
               +---+---+---+---+E--+E--+---+---+---+---+---+---+---+---+
              k|   |   |   |   |   |   |   |   |SN5|SR5|   |   |   |   |
               +---+---+---+E--+E--+---+---+---+---+---+---+---+---+---+
              l|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+E--+---+---+---+---+---+---+---+---+---+
              m|   |   |   |   |NN4|SR4|   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              n|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              """;

    final static String PLATEAU_NIVEAU9
            = """
                 A   B   C   D   E   F   G   H   I   J   K   L   M   N 
               +---+E--+E--+---+---+---+---+---+---+---+---+---+E--+E--+
              a|LN6|NN1|   |   |   |   |   |   |   |   |SR1|SN5|   |NR3|
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
              h|   |   |   |   |AR5|   |   |   |   |   |   |   |   |   |
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
              n|   |   |   |   |AN5|   |   |SR4|   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              """;
}
