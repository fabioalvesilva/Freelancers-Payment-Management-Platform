package pt.ipp.isep.dei.esoft.pot.ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pt.ipp.isep.dei.esoft.pot.controller.POTAplication;
import pt.ipp.isep.dei.esoft.pot.model.Platform;

public class MainApp extends Application {
    private POTAplication m_oApp;
    private Platform m_oPlatform;
    public static final String TITLE = "APP Transaction";
    public static final String APLICATION_TITLE = "Warning";
    @Override
    public void start(Stage stage) throws Exception {
        this.m_oApp = POTAplication.getInstance();
        this.m_oPlatform = m_oApp.getPlatform();
        stage.setResizable(false);
        stage.getIcons().add(new Image ("/img/icon.png"));
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainScene.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainScene.fxml"));
        Parent root = loader.load();
        final MainSceneUI mainSceneUI = loader.getController();
        mainSceneUI.associateParentUI(this);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, APLICATION_TITLE,
                            "Confirm action.", "Are you sure?");
                    
                    if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                        event.consume();
                    }
                    else {
                        Platform platform = mainSceneUI.getMainApp().getPlatform();
                        if (!m_oApp.save(platform)) {
                            AlertaUI.criarAlerta(Alert.AlertType.ERROR, TITLE, APLICATION_TITLE,
                                    "Error on export organization!").show();
                        } else {
                            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITLE, "",
                                    "Thank you for use our app!").show();
                        }
                        
                    }
                }
            });
        stage.setTitle("T4J - Payments Managment Application");
        stage.setScene(scene);
        
        stage.show();
    }
    
    public Platform getPlatform() {
        return this.m_oPlatform;
    }
    
    public POTAplication getPOTAplication() {
        return this.m_oApp;
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
//        try {   //Exemplo
//            MenuUI uiMenu = new MenuUI();
//
//            uiMenu.run();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
