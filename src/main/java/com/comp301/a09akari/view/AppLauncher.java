package com.comp301.a09akari.view;

import com.comp301.a09akari.SamplePuzzles;
import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.ModelImpl;
import com.comp301.a09akari.model.PuzzleImpl;
import com.comp301.a09akari.model.PuzzleLibraryImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    // TODO: Create your Model, View, and Controller instances and launch your GUI

    // Create the model
    PuzzleLibraryImpl puzzleLibrary = new PuzzleLibraryImpl();
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_01));
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_02));
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_03));
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_04));
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_05));
    Model model = new ModelImpl(puzzleLibrary);

    // Create the Controller
    AlternateMvcController controller = new ControllerImpl(model);

    // View
    View view = new View(controller);

    // Make scene
    Scene scene = new Scene(view.render());
    stage.setScene(scene);
    stage.setMinWidth(320);
    stage.setMinHeight(500);

    // Refresh view when model changes
    model.addObserver(
        (Model m) -> {
          scene.setRoot(view.render());
        });

    // Show the stage
    stage.setTitle("Akari");
    stage.show();
  }
}
