package com.example.assignment4;

import com.example.assignment4.Controller.BlobController;
import com.example.assignment4.Controller.TrainerController;
import com.example.assignment4.Interface.AppModelListener;
import com.example.assignment4.Model.BlobModel;
import com.example.assignment4.Model.InteractionModel;
import com.example.assignment4.View.BlobView;
import com.example.assignment4.View.ReportView;
import com.example.assignment4.View.TrainerView;
import javafx.scene.layout.StackPane;

public class MainUI extends StackPane implements AppModelListener {
    public BlobController controller = new BlobController();;
    TrainerController trainerController = new TrainerController();
    TrainerView trainerView = new TrainerView();
    ReportView reportView = new ReportView();
    BlobView blobView = new BlobView();


    public MainUI() {

        BlobModel model = new BlobModel();
        InteractionModel iModel = new InteractionModel();

        controller.setModel(model);
        blobView.setModel(model);
        controller.setIModel(iModel);
        blobView.setIModel(iModel);
        model.addSubscriber(blobView);
        iModel.addSubscriber(blobView);
        iModel.addAppSubscriber(this);

        trainerController.setModel(model);
        trainerController.setIModel(iModel);
        trainerView.setModel(model);
        trainerView.setIModel(iModel);

        trainerView.setController(trainerController);
        blobView.setController(controller);

        this.getChildren().add(blobView);
    }

    @Override
    public void viewChanged(String mode) {
        System.out.println("View Changed");
        if (mode.equals("trainer")) {
            this.getChildren().clear();
            this.getChildren().addAll(trainerView);
        }else if (mode.equals("edit")){
            this.getChildren().clear();
            this.getChildren().addAll(blobView);
        }else {
            this.getChildren().clear();
            this.getChildren().addAll(reportView);
        }

    }
}
