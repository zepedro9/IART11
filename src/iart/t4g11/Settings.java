package iart.t4g11;

// TODO: Ignorar por enquanto, copy paste do meu projeto de LPOO para o Lanterna

public class Settings {

    static final int INDENT = 1;

    static final int WIDTH = 110; //Width of the screen
    static final int HEIGHT = 45; //Height of the screen
    static final int ARENA_WIDTH = 55; //Width of the arena
    static final int ARENA_HEIGHT = 30; //Height of the arena

    static final int P_UI_WIDTH = 30; //Width of the Players UI
    static final int P_UI_HEIGHT = 20; //Height of the Players UI
    static final int P_UI_POS_WIDTH = ARENA_WIDTH + 8; //Left margin of the Players UI
    static final int P_UI_POS_HEIGHT = (ARENA_HEIGHT - P_UI_HEIGHT) / 2; //Top margin of the Players UI

    static final int E_UI_WIDTH = ARENA_WIDTH + P_UI_WIDTH + 7; //Width of the Players UI
    static final int E_UI_HEIGHT = 11; //Height of the Players UI
    static final int E_UI_POS_WIDTH = 1; //Left margin of the Players UI
    static final int E_UI_POS_HEIGHT = ARENA_HEIGHT + 2; //Top margin of the Players UI
}
