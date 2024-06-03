/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package towa;

/**
 *
 * @author erimonteil
 */
public class EtatPlateau {
    
    Case[][] plateauActuel;
    Case[][] plateauAvecAction;

    EtatPlateau(Case[][] plateauActuel) {
        this.plateauActuel = plateauActuel;
        this.plateauAvecAction = null;
    }
    
    EtatPlateau(Case[][] plateau, String action, char couleurJoueur){
        EtatPlateau etat = new EtatPlateau(plateau);
    }
}
