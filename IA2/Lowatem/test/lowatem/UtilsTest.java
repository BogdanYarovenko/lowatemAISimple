package lowatem;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests sur la classe Utils.
 */
public class UtilsTest {

    /**
     * Test de la fonction caseDepuisCodage.
     */
    @Test
    public void testCaseDepuisCodage() {
        Case laCase;
        // case vide
        laCase = Utils.caseDepuisCodage("---", "   ");
        assertEquals(Case.CAR_TERRE, laCase.nature);
        assertEquals(0, laCase.altitude);
        assertEquals(Case.CAR_VIDE, laCase.typeUnite);
        // une unité de soldats rouges
        laCase = Utils.caseDepuisCodage("---", "SR4");
        assertEquals(Case.CAR_TERRE, laCase.nature);
        assertEquals(0, laCase.altitude);
        assertEquals(Case.CAR_SOLDATS, laCase.typeUnite);
        assertEquals(Case.CAR_ROUGE, laCase.couleurUnite);
        assertEquals(4, laCase.pointsDeVie);
        // une unité de soldats noirs
        laCase = Utils.caseDepuisCodage("---", "SN9");
        assertEquals(Case.CAR_TERRE, laCase.nature);
        assertEquals(0, laCase.altitude);
        assertEquals(Case.CAR_SOLDATS, laCase.typeUnite);
        assertEquals(Case.CAR_NOIR, laCase.couleurUnite);
        assertEquals(9, laCase.pointsDeVie);
    }

    /**
     * Test de la fonction plateauDepuisTexte().
     */
    @Test
    public void testPlateauDepuisTexte() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU1);
        Case laCase;
        // une case avec une unité noire
        laCase = plateau[0][0];
        assertEquals(Case.CAR_TERRE, laCase.nature);
        assertEquals(0, laCase.altitude);
        assertEquals(Case.CAR_SOLDATS, laCase.typeUnite);
        assertEquals(Case.CAR_NOIR, laCase.couleurUnite);
        assertEquals(4, laCase.pointsDeVie);
        // une case avec une unité rouge
        laCase = plateau[13][13];
        assertEquals(Case.CAR_TERRE, laCase.nature);
        assertEquals(0, laCase.altitude);
        assertEquals(Case.CAR_SOLDATS, laCase.typeUnite);
        assertEquals(Case.CAR_ROUGE, laCase.couleurUnite);
        assertEquals(1, laCase.pointsDeVie);
        // une case vide
        laCase = plateau[0][1];
        assertEquals(Case.CAR_TERRE, laCase.nature);
        assertEquals(0, laCase.altitude);
        assertEquals(Case.CAR_VIDE, laCase.typeUnite);
    }

    @Test
    public void testNettoyerTableauCoord() {
        Coordonnees[] vide = new Coordonnees[0];
        Coordonnees[] pleinDeNull = new Coordonnees[50];
        assertArrayEquals(vide, Utils.nettoyerTableauCoord(pleinDeNull, 0));
        
        Coordonnees[] listeUnitesPlateau1 = new Coordonnees[3]; // codée en dur
        listeUnitesPlateau1[0] = Coordonnees.depuisCars('b', 'A');
        listeUnitesPlateau1[1] = Coordonnees.depuisCars('f', 'B');
        listeUnitesPlateau1[2] = Coordonnees.depuisCars('n', 'N');
        
        Coordonnees[] listeUP1_pasNettoyee = new Coordonnees[50];
        listeUP1_pasNettoyee[0] = listeUnitesPlateau1[0];
        listeUP1_pasNettoyee[1] = listeUnitesPlateau1[1];
        listeUP1_pasNettoyee[2] = listeUnitesPlateau1[2];
        
        listeUP1_pasNettoyee = Utils.nettoyerTableauCoord(listeUP1_pasNettoyee, 3);
        assertEquals(3, listeUP1_pasNettoyee.length);
        for (int i = 0; i < listeUnitesPlateau1.length; i++){
            assertTrue(listeUnitesPlateau1[i].equals(listeUP1_pasNettoyee[i]));
        }
    } 
    
    @Test
    public void testCoordDansTab() {
        Coordonnees[] verveine = new Coordonnees[2];
        verveine[0] = new Coordonnees(8, 0);
        verveine[1] = new Coordonnees(4, 2);
        Coordonnees cordDansTab = new Coordonnees(4, 2);
        Coordonnees cordHorsTab = new Coordonnees(4, 9);
        assertTrue(Utils.coordDansTab(verveine, cordDansTab));
        assertFalse(Utils.coordDansTab(verveine, cordHorsTab));
    }
    
    @Test
    public void testAbs() {
        assertEquals(8, Utils.abs(8));
        assertEquals(8, Utils.abs(-8));
        assertEquals(0, Utils.abs(0));
    }
    
    @Test
    public void charDir() {
        assertEquals('N', Utils.charDir(Direction.NORD));
        assertEquals('S', Utils.charDir(Direction.SUD));
        assertEquals('O', Utils.charDir(Direction.OUEST));
        assertEquals('E', Utils.charDir(Direction.EST));
    }
    
    final String PLATEAU1
            = """
                 A   B   C   D   E   F   G   H   I   J   K   L   M   N 
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              a|SN4|   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              b|SR9|   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              c|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              d|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              e|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
               +---+---+---+---+---+---+---+---+---+---+---+---+---+---+
              f|   |SR3|   |   |   |   |   |   |   |   |   |   |   |   |
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
              n|   |   |   |   |   |   |   |   |   |   |   |   |   |SR1|
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
