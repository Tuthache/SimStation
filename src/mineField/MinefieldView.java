package mineField;

import mvc.*;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class MinefieldView extends View {

    private static final int PATCH_SIZE = 20;
    private final int fieldSize;
    private Cell[][] cells;

    public MinefieldView(Minefield field) {
        super(field);
        fieldSize = field.FIELD_SIZE;
        Patch[][] patches = field.getField();
        cells = new Cell[fieldSize][fieldSize];
        this.setLayout(new GridLayout(fieldSize, fieldSize));
        this.setPreferredSize(new Dimension(fieldSize*PATCH_SIZE, fieldSize*PATCH_SIZE));

        // initialize cells
        for (int y = 0; y < fieldSize; y++) {
            for (int x = 0; x < fieldSize; x++) {
                Cell c = new Cell(patches[x][y]);
                this.add(c);
                cells[x][y] = c;
                c.updateCell();
            }
        }
    }

    // Updates all cells to the current model.
    private void updateCells() {
        Minefield field = (Minefield)model;
        for (Cell[] row : cells) {
            for (Cell c : row) {
                c.updateCell();
            }
        }
        cells[field.getPlayerX()][field.getPlayerY()].setWhiteBorder();
    }

    public void changeModel() {
        Minefield field = (Minefield)model;
        for (int x = 0; x < fieldSize; x++) {
            for (int y = 0; y < fieldSize; y++) {
                cells[x][y].setPatch(field.getPatch(x,y));
                cells[x][y].updateCell();
            }
        }
        cells[field.getPlayerX()][field.getPlayerY()].setWhiteBorder();
    }

    /*
    @Override
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        // TODO implement paintComponent
        Color oldColor = gc.getColor();
        Minefield field = (Minefield) model;
        PatchShape[][] patchShapes = field.generateShapes();
        for (PatchShape[] inner :
                patchShapes) {
            for (PatchShape shape :
                    inner) {
                shape.draw((Graphics2D) gc);
            }
        }
        gc.setColor(oldColor);
    }
    */



    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
        if(evt.getPropertyName() == "New"
                || evt.getPropertyName() == "Open") {
            // update all cells to new model
            changeModel();
        } else {
            updateCells();
        }
    }

    class Cell extends JPanel {
        private Patch patch;
        private JLabel label;

        public Cell(Patch patch) {
            super();
            this.patch = patch;
            this.label = new JLabel();
            add(label);
            updateCell();
        }

        public void setPatch(Patch patch) {
            this.patch = patch;
        }

        public void updateCell() {
            String newText = "";
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            if (patch.isRevealed()) {
                if (patch.hasMine()) {
                    setBackground(Color.RED);
                } else {
                    newText = "" + patch.getMinesAround();
                    if (patch.isGoal()) setBackground(Color.GREEN);
                    else setBackground(Color.LIGHT_GRAY);
                }
            } else {
                newText = "?";
                setBackground(Color.GRAY);
                if (patch.isGoal()) setBorder(BorderFactory.createLineBorder(Color.GREEN));
            }
            label.setText(newText);
        }

        public void setWhiteBorder() {
            setBorder(BorderFactory.createLineBorder(Color.WHITE));
        }

    }
}
