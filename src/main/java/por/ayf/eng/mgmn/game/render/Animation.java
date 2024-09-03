package por.ayf.eng.mgmn.game.render;

import por.ayf.eng.mgmn.game.entd.Character;

/**
 * Class that define the animations will use in the game.
 *
 * @author: Ángel Yagüe Flor.
 * @version: 1.0 - Stable.
 * @version: 1.1 - Refactor the project.
 */

public class Animation {

    /**
     * Method that realize the animation of Megaman while move and execute each 100 milliseconds.
     */

    public static void movementAnimation(Character megaman, boolean shooting) {
        if (megaman.getCurrentImage() == megaman.getImages().get("Estatico_1") || megaman.getCurrentImage() == megaman.getImages().get("Deslizar_1") || megaman.getCurrentImage() == megaman.getImages().get("Disparo_1")) {
            if (shooting == true) {
                megaman.setCurrentImage(megaman.getImages().get("DMovimiento_1"));
            } else {
                megaman.setCurrentImage(megaman.getImages().get("Movimiento_1"));
            }
        } else {
            if (megaman.getCurrentImage() == megaman.getImages().get("Movimiento_1") || megaman.getCurrentImage() == megaman.getImages().get("DMovimiento_1")) {
                if (shooting == true) {
                    megaman.setCurrentImage(megaman.getImages().get("DMovimiento_2"));
                } else {
                    megaman.setCurrentImage(megaman.getImages().get("Movimiento_2"));
                }
            } else if (megaman.getCurrentImage() == megaman.getImages().get("Movimiento_2") || megaman.getCurrentImage() == megaman.getImages().get("DMovimiento_2")) {
                if (shooting == true) {
                    megaman.setCurrentImage(megaman.getImages().get("DMovimiento_3"));
                } else {
                    megaman.setCurrentImage(megaman.getImages().get("Movimiento_3"));
                }
            } else if (megaman.getCurrentImage() == megaman.getImages().get("Movimiento_3") || megaman.getCurrentImage() == megaman.getImages().get("DMovimiento_3")) {
                if (shooting == true) {
                    megaman.setCurrentImage(megaman.getImages().get("DMovimiento_4"));
                } else {
                    megaman.setCurrentImage(megaman.getImages().get("Movimiento_4"));
                }
            } else if (megaman.getCurrentImage() == megaman.getImages().get("Movimiento_4") || megaman.getCurrentImage() == megaman.getImages().get("DMovimiento_4")) {
                if (shooting == true) {
                    megaman.setCurrentImage(megaman.getImages().get("DMovimiento_5"));
                } else {
                    megaman.setCurrentImage(megaman.getImages().get("Movimiento_5"));
                }
            } else if (megaman.getCurrentImage() == megaman.getImages().get("Movimiento_5") || megaman.getCurrentImage() == megaman.getImages().get("DMovimiento_5")) {
                if (shooting == true) {
                    megaman.setCurrentImage(megaman.getImages().get("DMovimiento_6"));
                } else {
                    megaman.setCurrentImage(megaman.getImages().get("Movimiento_6"));
                }
            } else if (megaman.getCurrentImage() == megaman.getImages().get("Movimiento_6") || megaman.getCurrentImage() == megaman.getImages().get("DMovimiento_6")) {
                if (shooting == true) {
                    megaman.setCurrentImage(megaman.getImages().get("DMovimiento_7"));
                } else {
                    megaman.setCurrentImage(megaman.getImages().get("Movimiento_7"));
                }
            } else if (megaman.getCurrentImage() == megaman.getImages().get("Movimiento_7") || megaman.getCurrentImage() == megaman.getImages().get("DMovimiento_7")) {
                if (shooting == true) {
                    megaman.setCurrentImage(megaman.getImages().get("DMovimiento_8"));
                } else {
                    megaman.setCurrentImage(megaman.getImages().get("Movimiento_8"));
                }
            } else if (megaman.getCurrentImage() == megaman.getImages().get("Movimiento_8") || megaman.getCurrentImage() == megaman.getImages().get("DMovimiento_8")) {
                if (shooting == true) {
                    megaman.setCurrentImage(megaman.getImages().get("DMovimiento_9"));
                } else {
                    megaman.setCurrentImage(megaman.getImages().get("Movimiento_9"));
                }
            } else if (megaman.getCurrentImage() == megaman.getImages().get("Movimiento_9") || megaman.getCurrentImage() == megaman.getImages().get("DMovimiento_9")) {
                if (shooting == true) {
                    megaman.setCurrentImage(megaman.getImages().get("DMovimiento_10"));
                } else {
                    megaman.setCurrentImage(megaman.getImages().get("Movimiento_10"));
                }
            } else if (megaman.getCurrentImage() == megaman.getImages().get("Movimiento_10") || megaman.getCurrentImage() == megaman.getImages().get("DMovimiento_10")) {
                if (shooting == true) {
                    megaman.setCurrentImage(megaman.getImages().get("DMovimiento_1"));
                } else {
                    megaman.setCurrentImage(megaman.getImages().get("Movimiento_1"));
                }
            }
        }
    }

    /**
     * Method that realize the animation of the character to jump and execute each 100 milliseconds.
     */

    public static void jumpAnimation(Character character, boolean shooting) {
        if (character.getCurrentImage() != character.getImages().get("Salto_1") &&
                character.getCurrentImage() != character.getImages().get("Salto_2") &&
                character.getCurrentImage() != character.getImages().get("Salto_3") &&
                character.getCurrentImage() != character.getImages().get("Salto_4") &&
                character.getCurrentImage() != character.getImages().get("DSalto_1") &&
                character.getCurrentImage() != character.getImages().get("DSalto_2") &&
                character.getCurrentImage() != character.getImages().get("DSalto_3") &&
                character.getCurrentImage() != character.getImages().get("DSalto_4")) {

            if (shooting == true) {
                character.setCurrentImage(character.getImages().get("DSalto_1"));
            } else {
                character.setCurrentImage(character.getImages().get("Salto_1"));
            }
        } else if (character.getCurrentImage() == character.getImages().get("Salto_1") || character.getCurrentImage() == character.getImages().get("DSalto_1")) {
            if (shooting == true) {
                character.setCurrentImage(character.getImages().get("DSalto_2"));
            } else {
                character.setCurrentImage(character.getImages().get("Salto_2"));
            }
        } else if (character.getCurrentImage() == character.getImages().get("Salto_2") || character.getCurrentImage() == character.getImages().get("DSalto_2")) {
            if (shooting == true) {
                character.setCurrentImage(character.getImages().get("DSalto_3"));
            } else {
                character.setCurrentImage(character.getImages().get("Salto_3"));
            }
        } else if (character.getCurrentImage() == character.getImages().get("Salto_3") || character.getCurrentImage() == character.getImages().get("DSalto_3")) {
            if (shooting == true) {
                character.setCurrentImage(character.getImages().get("DSalto_4"));
            } else {
                character.setCurrentImage(character.getImages().get("Salto_4"));
            }
        } else if (character.getCurrentImage() == character.getImages().get("Salto_4") || character.getCurrentImage() == character.getImages().get("DSalto_4")) {
            if (shooting == true) {
                character.setCurrentImage(character.getImages().get("DSalto_4"));
            } else {
                character.setCurrentImage(character.getImages().get("Salto_4"));
            }
        }
    }
}
