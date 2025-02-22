/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author kamus
 */
public class ChessExercises {
    private String fen;
    private String level;
    private String title;
    private int movesForward;
    
    public ChessExercises(String fen, String level, String title,int movesForward){
        this.fen=fen;
        this.level=level;
        this.title=title;
        this.movesForward=movesForward;
    
    }
    //getters
    public String getFen(){
        return this.fen;
    }
    public String getLevel(){
        return this.level;
    }
    public String getTitle(){
        return this.title;
    }
    public int getMovesForward(){
        return this.movesForward;
    }
}
