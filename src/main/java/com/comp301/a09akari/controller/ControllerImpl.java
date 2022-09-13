package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.Puzzle;

import java.awt.*;
import java.util.Random;

public class ControllerImpl implements AlternateMvcController {
  private Model model;

  public ControllerImpl(Model model) {
    this.model = model;
  }

  @Override
  public void clickNextPuzzle() {
    if (model.getPuzzleLibrarySize() > model.getActivePuzzleIndex() + 1) {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() + 1);
    } else {
      model.setActivePuzzleIndex(0);
    }
  }

  @Override
  public void clickPrevPuzzle() {
    if (model.getActivePuzzleIndex() > 0) {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() - 1);
    } else {
      model.setActivePuzzleIndex(model.getPuzzleLibrarySize() - 1);
    }
  }

  @Override
  public void clickRandPuzzle() {
    Random rand = new Random();
    int randIndex = rand.nextInt(model.getPuzzleLibrarySize());
    if (!(randIndex == model.getActivePuzzleIndex())) {
      model.setActivePuzzleIndex(randIndex);
    } else {
      this.clickRandPuzzle();
    }
  }

  @Override
  public void clickResetPuzzle() {
    model.resetPuzzle();
  }

  @Override
  public void clickCell(int r, int c) {
    if (model.isLamp(r, c)) {
      model.removeLamp(r, c);
    } else {
      model.addLamp(r, c);
    }
  }

  @Override
  public boolean isLit(int r, int c) {
    return model.isLit(r, c);
  }

  @Override
  public boolean isLamp(int r, int c) {
    return model.isLamp(r, c);
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    return model.isClueSatisfied(r, c);
  }

  @Override
  public boolean isSolved() {
    return model.isSolved();
  }

  @Override
  public Puzzle getActivePuzzle() {
    return model.getActivePuzzle();
  }

  @Override
  public int getCurrentPuzzleIndex() {
    return model.getActivePuzzleIndex() + 1;
  }

  @Override
  public int getNumPuzzles() {
    return model.getPuzzleLibrarySize();
  }

  @Override
  public boolean getIsLampIllegal(int r, int c) {
    return model.isLampIllegal(r, c);
  }
}
