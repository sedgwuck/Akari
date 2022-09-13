package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.model.CellType;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Shape;

import static javafx.geometry.Pos.CENTER;

public class PuzzleView implements FXComponent {

  private final AlternateMvcController controller;

  public PuzzleView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    GridPane layout = new GridPane();
    layout.setAlignment(CENTER);
    for (int r = 0; r < controller.getActivePuzzle().getHeight(); r++) {
      for (int c = 0; c < controller.getActivePuzzle().getWidth(); c++) {
        if (controller.getActivePuzzle().getCellType(r, c) == CellType.CORRIDOR) {
          Button button = new Button();
          button.setFocusTraversable(false);
          int finalC = c;
          int finalR = r;
          button.setOnAction((ActionEvent event) -> controller.clickCell(finalR, finalC));
          button.setPrefSize(30, 30);
          if (controller.isLamp(r, c)) {
            if (controller.getIsLampIllegal(r, c)) {
              button.setText("X");
              button.setStyle("-fx-background-color: #ff0000; -fx-border-color: #000000");
            } else {
              button.setStyle("-fx-background-color: brown; -fx-border-color: #000000");
            }
          } else if (controller.isLit(r, c)) {
            button.setStyle("-fx-background-color: yellow; -fx-border-color: BLACK");
          } else {
            button.setStyle("-fx-border-color: #000000");
          }
          layout.add(button, c, r);
        } else if (controller.getActivePuzzle().getCellType(r, c) == CellType.WALL) {
          Button button2 = new Button();
          button2.setFocusTraversable(false);
          button2.setPrefSize(30, 30);
          button2.setStyle("-fx-background-color: BLACK; -fx-border-color: BLACK");
          layout.add(button2, c, r);
        } else if (controller.getActivePuzzle().getCellType(r, c) == CellType.CLUE) {
          Button button3 = new Button(String.valueOf(controller.getActivePuzzle().getClue(r, c)));
          button3.setFocusTraversable(false);
          if (controller.isClueSatisfied(r, c)) {
            button3.setStyle("-fx-background-color: #00ff00; -fx-border-color: BLACK");
          } else {
            button3.setStyle(
                "-fx-background-color: BLACK; -fx-text-fill: white; -fx-border-color: BLACK");
          }
          button3.setPrefSize(30, 30);
          layout.add(button3, c, r);
        }
      }
    }
    return layout;
  }
}
