package com.comp301.a09akari.model;

import java.util.ArrayList;

public class ModelImpl implements Model {
  private final PuzzleLibrary library;
  private boolean[][] board;
  private ArrayList<ModelObserver> observers;
  private Puzzle currentPuzzle;

  public ModelImpl(PuzzleLibrary library) {
    this.library = library;
    this.currentPuzzle = library.getPuzzle(0);
    this.observers = new ArrayList<>();
    this.board = new boolean[this.currentPuzzle.getHeight()][this.currentPuzzle.getWidth()];
  }

  @Override
  public void addLamp(int r, int c) {
    if (this.currentPuzzle.getHeight() <= r
        || this.currentPuzzle.getWidth() <= c
        || r < 0
        || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (this.currentPuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    this.board[r][c] = true;
    notifyObservers();
  }

  @Override
  public void removeLamp(int r, int c) {
    if (this.currentPuzzle.getHeight() <= r
        || this.currentPuzzle.getWidth() <= c
        || r < 0
        || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (this.currentPuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    this.board[r][c] = false;
    notifyObservers();
  }

  @Override
  public boolean isLit(int r, int c) {
    if (this.currentPuzzle.getHeight() <= r
        || this.currentPuzzle.getWidth() <= c
        || r < 0
        || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (this.currentPuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    for (int x = r; x >= 0; x--) { // check below cell
      if (this.currentPuzzle.getCellType(x, c) == CellType.CORRIDOR) {
        if (this.board[x][c]) {
          return true;
        }
      } else {
        break;
      }
    }
    for (int i = r; i < this.currentPuzzle.getHeight(); i++) { // check above cell
      if (this.currentPuzzle.getCellType(i, c) == CellType.CORRIDOR) {
        if (this.board[i][c]) {
          return true;
        }
      } else {
        break;
      }
    }
    for (int k = c; k >= 0; k--) { // check left cell
      if (this.currentPuzzle.getCellType(r, k) == CellType.CORRIDOR) {
        if (this.board[r][k]) {
          return true;
        }
      } else {
        break;
      }
    }
    for (int j = c; j < this.currentPuzzle.getWidth(); j++) { // check right cell
      if (this.currentPuzzle.getCellType(r, j) == CellType.CORRIDOR) {
        if (this.board[r][j]) {
          return true;
        }
      } else {
        break;
      }
    }
    return false;
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (this.currentPuzzle.getHeight() <= r
        || this.currentPuzzle.getWidth() <= c
        || r < 0
        || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (this.currentPuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    return this.board[r][c];
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    if (this.currentPuzzle.getHeight() <= r
        || this.currentPuzzle.getWidth() <= c
        || r < 0
        || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (!this.board[r][c]) {
      throw new IllegalArgumentException();
    }
    int i = r + 1;
    int j = c + 1;
    int x = r - 1;
    int k = c - 1;
    while (i != this.currentPuzzle.getHeight()
        && this.currentPuzzle.getCellType(i, c) == CellType.CORRIDOR) {
      if (this.board[i][c]) {
        return true;
      }
      i++;
    }
    while (j != this.currentPuzzle.getWidth()
        && this.currentPuzzle.getCellType(r, j) == CellType.CORRIDOR) {
      if (this.board[r][j]) {
        return true;
      }
      j++;
    }
    while (x != -1 && this.currentPuzzle.getCellType(x, c) == CellType.CORRIDOR) {
      if (this.board[x][c]) {
        return true;
      }
      x--;
    }
    while (k != -1 && this.currentPuzzle.getCellType(r, k) == CellType.CORRIDOR) {
      if (this.board[r][k]) {
        return true;
      }
      k--;
    }
    return false;
  }

  @Override
  public Puzzle getActivePuzzle() {
    return this.currentPuzzle;
  }

  @Override
  public int getActivePuzzleIndex() {
    int i = 0;
    while (i < this.library.size()) {
      if (this.library.getPuzzle(i) == this.currentPuzzle) {
        return i;
      }
      i++;
    }
    return i;
  }

  @Override
  public void setActivePuzzleIndex(int index) {
    if (getPuzzleLibrarySize() < index) {
      throw new IllegalArgumentException();
    }
    this.currentPuzzle = library.getPuzzle(index);
    this.board = new boolean[this.currentPuzzle.getHeight()][this.currentPuzzle.getWidth()];
    notifyObservers();
  }

  @Override
  public int getPuzzleLibrarySize() {
    return library.size();
  }

  @Override
  public void resetPuzzle() {
    for (int i = 0; i < this.currentPuzzle.getHeight(); i++) {
      for (int j = 0; j < this.currentPuzzle.getWidth(); j++) {
        this.board[i][j] = false;
      }
    }
    notifyObservers();
  }

  @Override
  public boolean isSolved() {
    for (int i = 0; i < this.currentPuzzle.getHeight(); i++) {
      for (int j = 0; j < this.currentPuzzle.getWidth(); j++) {
        if (this.currentPuzzle.getCellType(i, j) == CellType.CORRIDOR) {
          if (!isLit(i, j)) {
            return false;
          }
          if (this.board[i][j]) {
            if (isLampIllegal(i, j)) {
              return false;
            }
          }
        } else if (this.currentPuzzle.getCellType(i, j) == CellType.CLUE) {
          if (!isClueSatisfied(i, j)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    if (this.currentPuzzle.getHeight() <= r
        || this.currentPuzzle.getWidth() <= c
        || r < 0
        || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (this.currentPuzzle.getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    int count = 0;
    if (c > 0) {
      if (this.board[r][c - 1]) {
        count++;
      }
    }
    if (c < this.currentPuzzle.getWidth() - 1) {
      if (this.board[r][c + 1]) {
        count++;
      }
    }
    if (r > 0) {
      if (this.board[r - 1][c]) {
        count++;
      }
    }
    if (r < this.currentPuzzle.getHeight() - 1) {
      if (this.board[r + 1][c]) {
        count++;
      }
    }
    return count == this.currentPuzzle.getClue(r, c);
  }

  public void notifyObservers() {
    for (ModelObserver o : observers) {
      o.update(this);
    }
  }

  @Override
  public void addObserver(ModelObserver observer) {
    this.observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    this.observers.remove(observer);
  }
}
