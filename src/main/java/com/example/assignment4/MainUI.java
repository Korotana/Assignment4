package com.example.assignment4;

import com.example.assignment4.Controller.BlobController;
import com.example.assignment4.Interface.AppModelListener;
import com.example.assignment4.Model.BlobModel;
import com.example.assignment4.Model.InteractionModel;
import com.example.assignment4.View.BlobView;
import com.example.assignment4.View.Edit;
import com.example.assignment4.View.Trainer;
import javafx.scene.layout.StackPane;

public class MainUI extends StackPane implements AppModelListener {
    public BlobController controller = new BlobController();;
    Trainer trainerView = new Trainer();
    Edit editView = new Edit();
    BlobView view = new BlobView();


    public MainUI() {

        BlobModel model = new BlobModel();
        InteractionModel iModel = new InteractionModel();

        controller.setModel(model);
        view.setModel(model);
        controller.setIModel(iModel);
        view.setIModel(iModel);
        model.addSubscriber(view);
        iModel.addSubscriber(view);
        iModel.addAppSubscriber(this);

        view.setController(controller);

        this.getChildren().add(view);
    }

    @Override
    public void viewChanged(String mode) {
        System.out.println("View Changed");
        if (mode.equals("trainer")) {
            this.getChildren().clear();
            this.getChildren().addAll(trainerView);
        }else if (mode.equals("edit")){
            this.getChildren().clear();
            this.getChildren().addAll(editView);
        }else {
            this.getChildren().clear();
            this.getChildren().addAll(view);
        }

    }
}
