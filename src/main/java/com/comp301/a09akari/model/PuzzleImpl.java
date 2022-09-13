package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle {
  private final int[][] board;

  public PuzzleImpl(int[][] board) {
    this.board = board;
  }

  @Override
  public int getWidth() {
    return this.board[0].length;
  }

  @Override
  public int getHeight() {
    return this.board.length;
  }

  @Override
  public CellType getCellType(int r, int c) {
    if (r >= getHeight() || c >= getWidth() || r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (this.board[r][c] >= 0 && this.board[r][c] <= 4) {
      return CellType.CLUE;
    }
    if (this.board[r][c] == 5) {
      return CellType.WALL;
    }
    if (this.board[r][c] == 6) {
      return CellType.CORRIDOR;
    }
    return null;
  }

  @Override
  public int getClue(int r, int c) {
    if (r >= getHeight() || c >= getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    return this.board[r][c];
  }
}
