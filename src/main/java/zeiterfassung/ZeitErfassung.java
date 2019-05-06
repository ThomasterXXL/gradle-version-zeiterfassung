package zeiterfassung;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class ZeitErfassung {
    private Stage stage;
    private DataStore store;

    public ZeitErfassung(Stage stage) {
        this.stage = stage;

        // init data store
        try {
            this.store = new DataStore();
            this.store.load();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        // load scene

        Parent page = null;
        try {
            page = FXMLLoader.load(getClass().getResource("/zeiterfassung/views/Base.fxml"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(page));
        stage.setTitle("Zeiterfassung");
        stage.setMaximized(true);
        stage.show();
    }

    /**
     * stop ZeitErfassung (unload data initstore)
     */
    public void stop() {
        try {
            store.save();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
