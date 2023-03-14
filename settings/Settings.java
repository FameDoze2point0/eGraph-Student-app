package settings;
import java.awt.Color;
import java.io.Serializable;
import javax.swing.JDialog;

public class Settings implements Serializable
{
    // ===== SETTINGS =====
        // === Vertexs settings ===
        Color vertexInsideColor     = Color.white;
        private Color vertexOutsideColor    = Color.black;
        private Color vertexNameColor       = Color.black;
        private int   vertexDiameter        = 50,
                      vertexStrokeWidth     = 5;

        // === Edges settings ===
        private Color edgeStrokeColor       = Color.black, 
                      edgeHighlightColor    = Color.black,
                      edgeArrowTipColor     = Color.black,
                      edgeWeightColor       = Color.white,
                      edgeWeightBorderColor = Color.black;
        private int   edgeStrokeWidth       = 5,
                      arrowLength           = 15;
    // ===== END SETTINGS =====

    // ==== JDIALOG ====
    private transient SettingsDialog window;

    public Color getVertexInsideColor() {
        return vertexInsideColor;
    }

    public void setVertexInsideColor(Color vertexInsideColor) {
        this.vertexInsideColor = vertexInsideColor;
    }

    public Color getVertexOutsideColor() {
        return vertexOutsideColor;
    }

    public void setVertexOutsideColor(Color vertexOutsideColor) {
        this.vertexOutsideColor = vertexOutsideColor;
    }

    public Color getVertexNameColor() {
        return vertexNameColor;
    }

    public void setVertexNameColor(Color vertexNameColor) {
        this.vertexNameColor = vertexNameColor;
    }

    public int getVertexDiameter() {
        return vertexDiameter;
    }

    public void setVertexDiameter(int vertexDiameter) {
        this.vertexDiameter = vertexDiameter;
    }

    public int getVertexStrokeWidth() {
        return vertexStrokeWidth;
    }

    public void setVertexStrokeWidth(int vertexStrokeWidth) {
        this.vertexStrokeWidth = vertexStrokeWidth;
    }

    public Color getEdgeStrokeColor() {
        return edgeStrokeColor;
    }

    public void setEdgeStrokeColor(Color edgeStrokeColor) {
        this.edgeStrokeColor = edgeStrokeColor;
    }

    public Color getEdgeHighlightColor() {
        return edgeHighlightColor;
    }

    public void setEdgeHighlightColor(Color edgeHighlightColor) {
        this.edgeHighlightColor = edgeHighlightColor;
    }

    public Color getEdgeArrowTipColor() {
        return edgeArrowTipColor;
    }

    public void setEdgeArrowTipColor(Color edgeArrowTipColor) {
        this.edgeArrowTipColor = edgeArrowTipColor;
    }

    public Color getEdgeWeightColor() {
        return edgeWeightColor;
    }

    public void setEdgeWeightColor(Color edgeWeightColor) {
        this.edgeWeightColor = edgeWeightColor;
    }

    public Color getEdgeWeightBorderColor() {
        return edgeWeightBorderColor;
    }

    public void setEdgeWeightBorderColor(Color edgeWeightBorderColor) {
        this.edgeWeightBorderColor = edgeWeightBorderColor;
    }

    public int getEdgeStrokeWidth() {
        return edgeStrokeWidth;
    }

    public void setEdgeStrokeWidth(int edgeStrokeWidth) {
        this.edgeStrokeWidth = edgeStrokeWidth;
    }

    public int getArrowLength() {
        return arrowLength;
    }

    public void setArrowLength(int arrowLength) {
        this.arrowLength = arrowLength;
    }

    public SettingsDialog getWindow() {
        return window;
    }

    public void setWindow(SettingsDialog window) {
        this.window = window;
    }
}