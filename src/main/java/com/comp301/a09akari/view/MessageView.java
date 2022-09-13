package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.HashMap;

public class MessageView implements FXComponent {

  private final AlternateMvcController controller;

  public MessageView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox layout = new HBox();
    layout.setSpacing(10);
    layout.setStyle("-fx-padding: 10px");
    layout.setAlignment(Pos.BOTTOM_CENTER);

    Label isSolved = new Label("Not Solved");
    if (controller.isSolved()) {
      isSolved.setText("Solved");
    }
    isSolved.setLineSpacing(10);
    isSolved.setStyle("-fx-label-padding: 10; -fx-border-color: black; -fx-border-width: 1");
    layout.getChildren().add(isSolved);

    Label puzzleNum =
        new Label(
            "Puzzle " + controller.getCurrentPuzzleIndex() + " / " + controller.getNumPuzzles());
    puzzleNum.setStyle("-fx-label-padding: 10; -fx-border-color: black; -fx-border-width: 1;");
    isSolved.setLineSpacing(10);
    layout.getChildren().add(puzzleNum);
    return layout;
  }
}
