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
    public static final int POP_UP_TRIGGERED = 5;
    
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
    
    public static final String pathToLocal = "C:\\Users\\clayt\\projects\\csci310\\SheetMusicApp\\src\\main\\java\\mainApp\\com\\github\\johnsonclayton\\sheetmusicapp\\\\";
    
    public static final String bass_clef = pathToLocal + "media\\Bass-Clef.png";
    public static final String treble_clef = pathToLocal + "media\\Treble-Clef.png";

    static String getFileNameForNote(Note note) {
        String filename = pathToLocal + "media\\";
        switch(note.value) {
            case REST_BEAT:
                filename += "rest";
                break;
            case E_1:
                filename += "e_1";
                break;
            case F_1:
                filename += "f_1";
                break;
            case G_1:
                filename += "g_1";
                break;
            case A_1:
                filename += "a_1";
                break;
            case B_1:
                filename += "b_1";
                break;
            case C_1:
                filename += "c_1";
                break;
            case D_1:
                filename += "d_1";
                break;
            case E_2:
                filename += "e_2";
                break;
            case F_2:
                filename += "f_2";
                break;
            case G_2:
                filename += "g_2";
                break;
            case A_2:
                filename += "a_2";
                break;
            case B_2:
                filename += "b_2";
                break;
            case C_2:
                filename += "c_2";
                break;
            case D_2:
                filename += "d_2";
                break;
            case E_3:
                filename += "e_3";
                break;
            case F_3:
                filename += "f_3";
                break;
            case G_3:
                filename += "g_3";
                break;
            case A_3:
                filename += "a_3";
                break;
            case B_3:
                filename += "b_3";
                break;
            case C_3:
                filename += "c_3";
                break;
            case D_3:
                filename += "d_3";
                break;
            case E_4:
                filename += "e_4";
                break;
            case F_4:
                filename += "f_4";
                break;
            case G_4:
                filename += "g_4";
                break;
        }
        
        return filename + ".wav";
    }

    static char getChar(int value) {
        char note_char;
        switch(value) {
            case E_1:
                note_char = 'e';
                break;
            case F_1:
                note_char = 'f';
                break;
            case G_1:
                note_char = 'g';
                break;
            case A_1:
                note_char = 'a';
                break;
            case B_1:
                note_char = 'b';
                break;
            case C_1:
                note_char = 'c';
                break;
            case D_1:
                note_char = 'd';
                break;
            case E_2:
                note_char = 'e';
                break;
            case F_2:
                note_char = 'f';
                break;
            case G_2:
                note_char = 'g';
                break;
            case A_2:
                note_char = 'a';
                break;
            case B_2:
                note_char = 'b';
                break;
            case C_2:
                note_char = 'c';
                break;
            case D_2:
                note_char = 'd';
                break;
            case E_3:
                note_char = 'e';
                break;
            case F_3:
                note_char = 'f';
                break;
            case G_3:
                note_char = 'g';
                break;
            case A_3:
                note_char = 'a';
                break;
            case B_3:
                note_char = 'b';
                break;
            case C_3:
                note_char = 'c';
                break;
            case D_3:
                note_char = 'd';
                break;
            case E_4:
                note_char = 'e';
                break;
            case F_4:
                note_char = 'f';
                break;
            case G_4:
                note_char = 'g';
                break;
            default:
                note_char = 'r';
        }
        return note_char;
    }

    static String getFileNameForRect(Rectangle rect) {
        String filename = "C:\\Users\\clayt\\projects\\csci310\\SheetMusicApp\\src\\main\\java\\mainApp\\com\\github\\johnsonclayton\\sheetmusicapp\\\\media\\";
        switch(rect.note_val) {
            case REST_BEAT:
                filename += "rest";
                break;
            case E_1:
                filename += "e_1";
                break;
            case F_1:
                filename += "f_1";
                break;
            case G_1:
                filename += "g_1";
                break;
            case A_1:
                filename += "a_1";
                break;
            case B_1:
                filename += "b_1";
                break;
            case C_1:
                filename += "c_1";
                break;
            case D_1:
                filename += "d_1";
                break;
            case E_2:
                filename += "e_2";
                break;
            case F_2:
                filename += "f_2";
                break;
            case G_2:
                filename += "g_2";
                break;
            case A_2:
                filename += "a_2";
                break;
            case B_2:
                filename += "b_2";
                break;
            case C_2:
                filename += "c_2";
                break;
            case D_2:
                filename += "d_2";
                break;
            case E_3:
                filename += "e_3";
                break;
            case F_3:
                filename += "f_3";
                break;
            case G_3:
                filename += "g_3";
                break;
            case A_3:
                filename += "a_3";
                break;
            case B_3:
                filename += "b_3";
                break;
            case C_3:
                filename += "c_3";
                break;
            case D_3:
                filename += "d_3";
                break;
            case E_4:
                filename += "e_4";
                break;
            case F_4:
                filename += "f_4";
                break;
            case G_4:
                filename += "g_4";
                break;
        }
        return filename + ".wav";
    }

    
}
