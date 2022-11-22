package com.example.assignment4;

import com.example.assignment4.Controller.BlobController;
import com.example.assignment4.Model.BlobModel;
import com.example.assignment4.Model.InteractionModel;
import com.example.assignment4.View.BlobView;
import javafx.scene.layout.StackPane;

public class MainUI extends StackPane {
    public BlobController controller = new BlobController();;

    public MainUI() {

        BlobModel model = new BlobModel();
        BlobView view = new BlobView();
        InteractionModel iModel = new InteractionModel();

        controller.setModel(model);
        view.setModel(model);
        controller.setIModel(iModel);
        view.setIModel(iModel);
        model.addSubscriber(view);
        iModel.addSubscriber(view);

        view.setController(controller);

        this.getChildren().add(view);
    }
}
