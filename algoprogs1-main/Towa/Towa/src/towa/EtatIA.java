/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package towa;

/**
 *
 * @author erimonteil
 */
public class EtatIA {
    
    String MiniOuMax;

    EtatIA(char deCouleur) {
        if(deCouleur == 'B'){
            this.MiniOuMax = "Min";
        }
        if(deCouleur == 'N'){
            this.MiniOuMax = "Max";
        }
    }
    
}
