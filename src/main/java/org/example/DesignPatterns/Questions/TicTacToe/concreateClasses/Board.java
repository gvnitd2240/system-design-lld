package org.example.DesignPatterns.Questions.TicTacToe.concreateClasses;

import org.example.DesignPatterns.Questions.TicTacToe.dataclasses.Cell;
import org.example.DesignPatterns.Questions.TicTacToe.enums.Symbol;

public class Board{
        private Cell[][] grids;
        private final int size;

        public Board(int size) {
            this.size = size;
            this.grids = new Cell[size][size];
            initialiseBoard();
        }

        private void initialiseBoard(){
            for(int i = 0;i<size;i++){
                for(int j = 0;j<size;j++){
                    grids[i][j] = new Cell(Symbol.EMPTY);
                }
            }
        }

        public Cell getCell(int x, int y){
            return grids[x][y];
        }

        public int getSize(){
            return size;
        }

        public void placeSymbol(int x, int y, Symbol symbol) {
            if(x> size || y> size || x<0 || y<0){
                throw new RuntimeException("Invalid X, Y Value");
            }

            grids[x][y].setSymbol(symbol);
        }

        public boolean isEmpty(int x, int y){
            Cell cell = grids[x][y];
            return cell.isEmpty();
        }


        public boolean isFull(){
            boolean isFull = true;
            for(int i= 0;i<size;i++){
                for(int j = 0;j<size;j++){
                    isFull = isFull && !isEmpty(i,j);
                }
            }

            return isFull;
        }

        public void printBoard() {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (grids[i][j].isEmpty()) {
                        System.out.print(" _ ");
                    } else {
                        System.out.print(grids[i][j].getSymbol());
                    }

                    if (j != size - 1) {
                        System.out.print("  |  ");
                    }

                }
            }
        }
}

