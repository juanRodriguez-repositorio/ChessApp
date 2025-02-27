/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelView;

/**
 *
 * @author kamus
 */
import javax.swing.*;
import java.util.List;
import Model.ChessResourceModel;
import View.ChessResourcesView;

public class ChessResourcesController {
    private ChessResourcesView view;
    private ChessResourceModel model;

    public ChessResourcesController(ChessResourcesView view, ChessResourceModel model) {
        this.view = view;
        this.model = model;
        loadResources();
    }

    private void loadResources() {
        List<ChessResource> openingResources = model.getResourcesByPhase("Apertura");
        List<ChessResource> middlegameResources = model.getResourcesByPhase("Medio Juego");
        List<ChessResource> endgameResources = model.getResourcesByPhase("Final");

        view.updateSection("Apertura", openingResources);
        view.updateSection("Medio Juego", middlegameResources);
        view.updateSection("Final", endgameResources);
    }

} 

