package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class View implements FXComponent {
  private final AlternateMvcController controller;

  public View(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox layout = new VBox();
    // Controls view
    ControlsView controlsView = new ControlsView(controller);
    layout.getChildren().add(controlsView.render());

    PuzzleView puzzleView = new PuzzleView(controller);
    layout.getChildren().add(puzzleView.render());

    MessageView messageView = new MessageView(controller);
    layout.getChildren().add(messageView.render());

    layout.setStyle("-fx-border-color: black; -fx-padding: 5px");

    return layout;
  }
}
