package lowatem;

/**
 * Une direction cardinale.
 * 
 * VOUS NE DEVEZ PAS MODIFIER CE FICHIER.
 */
enum Direction {
    
    NORD,
    SUD,
    EST,
    OUEST;

    /**
     * Renvoie toutes les directions dans un tableau.
     * 
     * @return tableau contenant toutes les directions.
     */
    static Direction[] toutes() {
        Direction[] directions = {NORD, SUD, EST, OUEST};
        return directions;
    }
    
    /**
     * Renvoie le nombre de cases parcourues horizontalement lorsqu'on suit
     * cette direction (0 pour Nord et Sud, -1 pour Ouest, 1 pour Est).
     * 
     * @return nombre de cases horizontales de cette direction
     */
    int mvtHoriz() {
        return switch(this) {
            case NORD, SUD -> 0;
            case EST -> 1;
            case OUEST -> -1;
        };
    }
    
    /**
     * Renvoie le nombre de cases parcourues verticalement lorsqu'on suit
     * cette direction (0 pour Est et Ouest, -1 pour Nord, 1 pour Sud).
     * 
     * @return nombre de cases verticales de cette direction
     */
    int mvtVertic() {
        return switch (this) {
            case EST, OUEST -> 0;
            case NORD -> -1;
            case SUD -> 1;
        };
    }
}
