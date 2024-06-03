/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package towa;

/**
 *
 * @author erimonteil
 */
public class EvaluationPos {

    static int evaluation(String action) {
        int nbPionBlanc;
        int nbPionNoir;
        String stPionTotal = action.substring(4, action.length());
        nbPionNoir = Integer.parseInt(stPionTotal.substring(0, stPionTotal.indexOf(",")));
        nbPionBlanc = Integer.parseInt(stPionTotal.substring(stPionTotal.indexOf(",") +1, stPionTotal.length()));
        return nbPionNoir - nbPionBlanc;
    }
}
