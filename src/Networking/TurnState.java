package Networking;

import java.io.Serializable;

/**
 * Constants to represent the state of a player in the game
 */
public enum TurnState implements Serializable {

    WAITING(), IN_TURN(), END_TURN();

    TurnState() {

    }
}
