/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.progmatic.tictactoeexam;

import com.progmatic.tictactoeexam.enums.PlayerType;
import com.progmatic.tictactoeexam.exceptions.CellException;
import com.progmatic.tictactoeexam.interfaces.AbstractPlayer;
import com.progmatic.tictactoeexam.interfaces.Board;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class VictoryAwarePlayer extends AbstractPlayer{

    public VictoryAwarePlayer(PlayerType p) {
        super(p);
    }

    
    // azt kell megvizsgálni, hogyha van üres cella akkor abban az
    // oszlopban/átlóban/sorban van-e kettő olyan jel ami az Ő típusa és akkor oda rakjon
    @Override
    public Cell nextMove(Board b) {
        int moveRowIdx = 0; // ide állítanám be azt az indexet ami a feladatnak megfelelő lenne
        int moveColIdx = 0;
        
        String st = "";
        // nincs üres hely már a táblában
        if (b.emptyCells().size() == 0) {
            return null;
        }
        
        if (myType.equals(PlayerType.O)) {
            st = "O";
        }
        if (myType.equals(PlayerType.X)) {
            st = "X";
        }
        // board üres cellák listáján végigmenni és ellenőrizni, hogy van-e kettő egymás melletti még mellette
        for (int i = 0; i < 3; i++) {
            
            
            
        }
        
        
        // ha 
        Cell myMove = new Cell(moveRowIdx, moveColIdx, myType);

        return myMove;
    }
    
}
