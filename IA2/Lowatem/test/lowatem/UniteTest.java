/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lowatem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author maxblanchard
 */
public class UniteTest {
    
    @Test
    public void testUniteAuxCoord() {
        Unite uniteUn = new Unite(   'S','R', 5, Coordonnees.depuisCars('f', 'D'));
        Unite uniteDeux = new Unite( 'S','R', 4, Coordonnees.depuisCars('f', 'C'));
        Unite uniteTrois = new Unite('S','N', 2, Coordonnees.depuisCars('e', 'I'));
        Plateau test = new Plateau();
        test.ajouterUnite(uniteUn);
        test.ajouterUnite(uniteDeux);
        test.ajouterUnite(uniteTrois);
        assertTrue(Unite.uniteAuxCoord(test.unites, Coordonnees.depuisCars('f', 'D')) != null);
        assertTrue(Unite.uniteAuxCoord(test.unites, Coordonnees.depuisCars('f', 'E')) == null);
    }
    
    @Test
    public void testPvPostDepl() {
        Unite soldat = new Unite('S', 'R', 8, Coordonnees.depuisCars('b', 'B'));
        assertEquals(8, soldat.pvPostDepl(Coordonnees.depuisCars('b', 'C')));
        assertEquals(7, soldat.pvPostDepl(Coordonnees.depuisCars('g', 'B')));
        assertEquals(6, soldat.pvPostDepl(Coordonnees.depuisCars('i', 'B')));
        assertEquals(5, soldat.pvPostDepl(Coordonnees.depuisCars('l', 'B')));
    }
    
}
