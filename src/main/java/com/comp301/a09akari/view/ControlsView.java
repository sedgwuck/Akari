package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class ControlsView implements FXComponent {
  private final AlternateMvcController controller;

  public ControlsView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox layout = new HBox();
    layout.alignmentProperty().setValue(javafx.geometry.Pos.CENTER);
    layout.setSpacing(2);
    layout.setStyle("-fx-padding: 10;");

    Button nextButton = new Button("Next");
    nextButton.setFocusTraversable(false);
    nextButton.setOnAction(
        (ActionEvent event) -> {
          controller.clickNextPuzzle();
        });
    nextButton.setStyle("-fx-border-color: #000000; -fx-highlight-fill: green");
    layout.getChildren().add(nextButton);

    Button shuffleButton = new Button("Shuffle");
    shuffleButton.setFocusTraversable(false);
    shuffleButton.setOnAction(
        (ActionEvent event) -> {
          controller.clickRandPuzzle();
        });
    shuffleButton.setStyle("-fx-border-color: #000000; -fx-highlight-fill: blue");
    layout.getChildren().add(shuffleButton);

    Button prevButton = new Button("Prev");
    prevButton.setFocusTraversable(false);
    prevButton.setOnAction(
        (ActionEvent event) -> {
          controller.clickPrevPuzzle();
        });
    prevButton.setStyle("-fx-border-color: #000000; -fx-highlight-fill: gray");
    layout.getChildren().add(prevButton);

    Button resetButton = new Button("Reset");
    resetButton.setFocusTraversable(false);
    resetButton.setOnAction(
        (ActionEvent event) -> {
          controller.clickResetPuzzle();
        });
    resetButton.setStyle("-fx-border-color: #000000; -fx-highlight-fill: orange");
    layout.getChildren().add(resetButton);

    return layout;
  }
}
