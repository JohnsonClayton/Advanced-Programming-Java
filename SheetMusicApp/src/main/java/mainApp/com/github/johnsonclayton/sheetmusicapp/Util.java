/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainApp.com.github.johnsonclayton.sheetmusicapp;

/**
 *
 * @author clayt
 */
public class Util {
    public static final int PLAY_MUSIC = 0;
    public static final int PAUSE_MUSIC = 1;
    public static final int STOP_MUSIC = 2;
    public static final int ADD_MEASURE = 3;
    public static final int ADD_NOTE = 4;
    
    public static final int REST_BEAT = 99;
    public static final int E_1 = 100;
    public static final int F_1 = 101;
    public static final int G_1 = 102;
    public static final int A_1 = 103;
    public static final int B_1 = 104;
    public static final int C_1 = 105;
    public static final int D_1 = 106;
    public static final int E_2 = 107;
    public static final int F_2 = 108;
    public static final int G_2 = 109;
    public static final int A_2 = 110;
    public static final int B_2 = 111;
    public static final int C_2 = 112;
    public static final int D_2 = 113;
    public static final int E_3 = 114;
    public static final int F_3 = 115;
    public static final int G_3 = 116;
    public static final int A_3 = 117;
    public static final int B_3 = 118;
    public static final int C_3 = 119;
    public static final int D_3 = 120;
    public static final int E_4 = 121;
    public static final int F_4 = 122;
    public static final int G_4 = 123;
    public static final int A_4 = 124;

    static String getFileNameForNote(Note note) {
        switch(note.value) {
            case REST_BEAT:
            case E_1:
            case F_1:
            case G_1:
            case A_1:
            case B_1:
            case C_1:
            case D_1:
            case E_2:
            case F_2:
            case G_2:
            case A_2:
            case B_2:
            case C_2:
            case D_2:
            case E_3:
            case F_3:
            case G_3:
            case A_3:
            case B_3:
            case C_3:
            case D_3:
            case E_4:
            case F_4:
            case G_4:
            case A_4:      
        }
        return "";
    }
}
