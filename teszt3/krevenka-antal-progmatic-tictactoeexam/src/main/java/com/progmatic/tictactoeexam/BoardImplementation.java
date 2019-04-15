/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.progmatic.tictactoeexam;

import com.progmatic.tictactoeexam.enums.PlayerType;
import com.progmatic.tictactoeexam.exceptions.CellException;
import com.progmatic.tictactoeexam.interfaces.Board;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class BoardImplementation implements Board {

    // játékos "jelek" tárolása
    String[][] table = new String[3][3];

    // üres tábla "E" >> empty
    public BoardImplementation() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = "E";
            }
        }
    }

    @Override
    public PlayerType getCell(int rowIdx, int colIdx) throws CellException {
        // megvizsgálom, hogy megfelelő intervallumban vannak-e az indexek??  0-2 !!
        if ((rowIdx >= 0 && rowIdx <= 2) && (colIdx >= 0 && colIdx <= 2)) {
            if (table[rowIdx][colIdx].equals("O")) {
                return PlayerType.O;
            }
            if (table[rowIdx][colIdx].equals("X")) {
                return PlayerType.X;
            }
        } else {
            throw new CellException(rowIdx, colIdx, "Nem léteztő cellát adtál meg!!");
        }
        return PlayerType.EMPTY;
    }

    @Override
    public void put(Cell cell) throws CellException {
        // meg kell nézni, hogy milyen típusú játékos szeretne rakni és melyik mezőre?
        int rowIdx = cell.getRow();
        int colIdx = cell.getCol();

        if ((rowIdx >= 0 && rowIdx <= 2) && (colIdx >= 0 && colIdx <= 2)) { // nem 0,1,2 az index értéke
                
            if (table[rowIdx][colIdx].equals("E")) {
                String goodToken = stringFromCellPlayer(cell);
                table[rowIdx][colIdx] = goodToken;
            } else {
                throw new CellException(rowIdx, colIdx, "Már foglalt ez a cella, válassz másikat");
            }

        } else {
            throw new CellException(rowIdx, colIdx, "Nem léteztő cellát adtál meg!!");
        }

    }
    
    // cell játékos típus átalakítása String típusba
    private String stringFromCellPlayer (Cell cell) {
        String myTableToken = "";
        if(cell.getCellsPlayer().equals(PlayerType.O)) {
            myTableToken = "O";
        }
        if(cell.getCellsPlayer().equals(PlayerType.X)) {
            myTableToken = "X";
        }
        
        return myTableToken;
    }

    @Override
    public boolean hasWon(PlayerType p) {
        String actType = "";
        
        if (p.equals(PlayerType.O)) {
            actType = "O";
        }
        if (p.equals(PlayerType.X)) {
            actType = "X";
        }
        // countActSymbol metódus 3-al vagy nem 3-al tér vissza
        int sameTokens = countActSymbol(actType);
        if ( sameTokens == 3 ) {
            return true;
        } else {
            return false;
        }
    }
    
    // ez a metódus futna végig a tömb celláin és számolná, hogy van-e a szabályoknak megfelelően 3 egymás melletti "X" v. "O"
    public int countActSymbol(String st) {
        int counterDiagonalR = 0; // balfölső >> jobbalsó
        int counterDiagonalL = 0; // jobbfölső >> balalsó
        int counterRow = 0;
        int counterCol = 0;
        
        // átlók ellenőrzése
        for (int i = 0; i < 3; i++) {
            if (table[i][i].equals(st)) {
                counterDiagonalR++;
            }
            if (table[i][2 - i].equals(st)) { 
                counterDiagonalL++;
            }
        }
        if ( counterDiagonalR == 3 ) {
            return 3;
        } 
        if ( counterDiagonalL == 3 ) {
            return 3;
        }
        // sorok ellenőrzése
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j].equals(st)) {
                    counterRow++;
                }
            }
            // sor végére értünk, vizsgálni counterRow??
            if (counterRow == 3) {
                return 3;
            } else { // ha nincs 3 egymás melletti akkor jön a kövi sor, Nullázni a countert
                counterRow = 0;
            }
        }
        // oszlopok ellenőrzése
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[j][i].equals(st)) {
                    counterCol++;
                }
            }
            // oszlop vége, counterCol ???
            if (counterCol == 3) {
                return 3;
            } else {  // ha nincs 3 egymás alatti akkor a következő oszlop ellenőrzése, Nullázni a countert
                counterCol = 0;
            }
        }
        return 0;  // akkor kerül,ide elvileg, ha nem talált 3 egymással szomszédost a szabályoknak megfelelően
    }

    @Override
    public List<Cell> emptyCells() {
        List<Cell> emptyCells = new ArrayList<>();
        // megnézem, hogy a táblában(tömb) hol található üres cella "E"
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (  !(table[i][j].equals("O") || table[i][j].equals("X")) ) {  // ha  üres cella akkor  adja ezt a listához
                    Cell ec = new Cell(i, j);
                    emptyCells.add(ec);
                }
            }
        }
        return emptyCells;
    }

}
