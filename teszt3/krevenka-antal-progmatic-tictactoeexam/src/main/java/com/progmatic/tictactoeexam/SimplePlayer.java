/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.progmatic.tictactoeexam;

import com.progmatic.tictactoeexam.enums.PlayerType;
import com.progmatic.tictactoeexam.interfaces.AbstractPlayer;
import com.progmatic.tictactoeexam.interfaces.Board;

/**
 *
 * @author User
 */
public class SimplePlayer extends AbstractPlayer {

    public SimplePlayer(PlayerType p) {
        super(p);
    }

    @Override
    public Cell nextMove(Board b) {
        
        if (b.emptyCells().size() == 0) {
            return null;
        }
        Cell firstEmpty = b.emptyCells().get(0);
        firstEmpty.setCellsPlayer(getMyType());
        
        
        return firstEmpty;
               
    }

    
    
}
