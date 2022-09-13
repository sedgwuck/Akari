package com.comp301.a09akari;

import static org.junit.Assert.assertTrue;

import com.comp301.a09akari.model.*;
import org.junit.Test;

/** Unit test for simple App. */
public class AppTest {
  public PuzzleLibraryImpl puzzles = new PuzzleLibraryImpl();

  public static int[][] PUZZLE_01 = {
          {6}
  };

  public static int[][] PUZZLE_02 = {
          {6, 0},
          {6, 5}
  };

  public static int[][] PUZZLE_03 = {
          {6, 6, 6},
          {6, 6, 6},
          {1, 6, 6}
  };

  public static int[][] PUZZLE_04 = {
          {6, 6, 6, 6, 1},
          {6, 5, 6, 5, 6},
          {6, 6, 6, 6, 6}
  };

  /** Rigorous Test :-) */
  @Test
  public void shouldAnswerWithTrue() {
    assertTrue(true);
  }

  @Test
  public void isSolved() {
    PuzzleImpl puzzle = new PuzzleImpl(PUZZLE_01);
    PuzzleImpl puzzle1 = new PuzzleImpl(PUZZLE_02);
    PuzzleImpl puzzle2 = new PuzzleImpl(PUZZLE_03);
    PuzzleImpl puzzle3 = new PuzzleImpl(PUZZLE_04);
    PuzzleLibraryImpl library = new PuzzleLibraryImpl();
    library.addPuzzle(puzzle);
    library.addPuzzle(puzzle1);
    library.addPuzzle(puzzle2);
    library.addPuzzle(puzzle3);
    ModelImpl model = new ModelImpl(library);
    model.setActivePuzzleIndex(2);
    model.addLamp(0, 1);
    model.addLamp(1,0);
    model.addLamp(2, 2);
    assertTrue(model.isSolved());
//    model.setActivePuzzleIndex(0);
//    model.addLamp(0,0);
//    assertTrue(model.isSolved());
//    model.resetPuzzle();
//    model.setActivePuzzleIndex(3);
//    model.addLamp(0, 3);
//    model.addLamp(1, 4);
//    model.addLamp(1, 0);
//    model.addLamp(1, 2);
//    System.out.println("banana");
//    assertTrue(model.isSolved());
  }
}
