/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lowatem;

/**
 *
 * @author maxblanchard
 */
class Unite {
    
    /**
     * Couleur de l'unité.
     */
    char couleur;
    
    /**
     * Nombre de points de vie de l'unité.
     */
    int pv;
    
    /**
     * Coordonnées de l'unité.
     */
    Coordonnees coord;
    
    /**
     * Indique le type d'unité se trouvant sur cette case. 
     * L'absence d'unité est indiquée par le caractère Utils.CAR_VIDE.
     */
    char type;
    
    /**
     * Constructeur prenant en paramètre une case et des coordonnées,
     * particulièrement utile à partir d'un plateau.
     * 
     * @param uneCase        case contenant une unité
     * @param desCoordonnees coordonnées de la case
     */
    Unite(Case uneCase, Coordonnees desCoordonnees) {
        couleur = uneCase.couleurUnite;
        pv = uneCase.pointsDeVie;
        coord = desCoordonnees;
        type = uneCase.typeUnite;
    }
    
    /**
     * Constructeur prenant en paramètre les attributs de l'unité, soit
     * sa couleur, ses points de vie et ses coordonnées.
     * 
     * @param uneCouleur caractère représentant la couleur de l'unité
     * @param desPv      nombre de points de vie de l'unité
     * @param desCoord   coordonnées auxquelles se trouvent l'unité
     */
    Unite(char unType, char uneCouleur, int desPv, Coordonnees desCoord) {
        couleur = uneCouleur;
        pv = desPv;
        coord = desCoord;
        type = unType;
    }
    
    /**
     * Unité aux coordonnées c parmi le tableau d'unités unites
     * passé en paramètre.
     * On part du principe que les valeurs rentrées sont correctes,
     * c'est à dire que l'unité aux coordonnées c existe forcément
     * dans le tableau unites.
     * Sinon, renvoie null.
     * 
     * @param unites tableau d'unités
     * @param c      coordonnées de l'unité recherchée
     * 
     * @return       unité cherchée
     */
    static Unite uniteAuxCoord(Unite[] unites, Coordonnees c) {
        boolean trouve = false;
        Unite aTrouver = null;
        int i = 0;
        while (!trouve && i < unites.length) {
            if (unites[i].coord.equals(c)) {
                aTrouver = unites[i];
                trouve = true;
            }
            i++;
        }
        return aTrouver;
    }
    
    /**
     * PV théoriques d'une unité après un déplacement vers dst.
     * 
     * @param uni  unité qui se déplace
     * @param dst  destination du déplacement
     * 
     * @return pv si le déplacement arrivait
     */
    int pvPostDepl(Coordonnees dst){
        double coutDepl = switch (this.type) {
            case 'S' -> 0.3; // Soldats
            case 'L' -> 0.4; // Lance-missiles
            case 'N' -> 0.6; // Navire
            default -> 0.7;  // Chars et avions
        };
        return this.pv - (int)(coutDepl * JoueurLowatem.longueurDepl(this.coord, dst));
    }
    
    /**
     * Dommages par défaut sur l'unité attaquante.
     * 
     * @return dommages pa défaut sur l'unité attaquante
     */
    int dmgAtk(){
        return switch (this.type) {
            case 'C', 'L' -> 3; // Chars et lance-missiles
            default -> 2;       // Soldats, navires et avions
        };
    }
    
    /**
     * Dommages par défaut sur la cible attaquée.
     * 
     * @return dommages par défaut sur la cible attaquée
     */
    int dmgCib(){
        return switch (this.type) {
            case 'S' -> 4; // Soldats
            case 'C' -> 8; // Chars
            case 'L' -> 6; // Lance-missiles
            default -> 7;  // Avions et navires
        };
    }
    
}
