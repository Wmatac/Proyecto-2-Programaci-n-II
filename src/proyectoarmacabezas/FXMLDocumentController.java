package proyectoarmacabezas;

import archivos.AdministradorArchivosXml;
import dominio.Icono;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lógica.Lógica;

/**
 *
 * @author Wilmer Mata
 * @author Nicole Fonseca
 */
public class FXMLDocumentController implements Initializable {
    
    Lógica logica = new Lógica();
    @FXML private AnchorPane anchorPaneMapa;
    @FXML private TextField textFieldLargo;
    @FXML private TextField textFieldAncho;
    @FXML private MenuItem menuItemGuardar;
    @FXML private MenuItem menuItemAbrir;
    @FXML private MenuItem menuItemExportar;
    @FXML private MenuBar menuBar;
    private GridPane gridPaneMapa;
    @FXML private MenuItem menuItemSalir;
    @FXML private VBox vBoxIconos;
 
    @FXML
    public void buttonAceptarAccion(ActionEvent event) {
        
        if (Integer.parseInt(textFieldLargo.getText()) <= 0 || Integer.parseInt(textFieldAncho.getText()) <= 0) {
            System.out.println("Valor incorrecto");
        } else if (textFieldLargo.getText().equals(null) || textFieldAncho.getText().equals(null)) {
            System.out.println("Ingrese los dos valores.");
        } else {
            gridPaneMapa = logica.mostrarMapa(Integer.parseInt(textFieldLargo.getText()), Integer.parseInt(textFieldAncho.getText()));
            //Agregar mapa al AnchorPane
            anchorPaneMapa.getChildren().add(gridPaneMapa);
            //setupGestureTarget(gridPaneMapa);
        }
        
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Relleno y color de la barra de menú
        menuBar.setPadding(new Insets(10, 200, 10, 300));
        menuBar.setStyle("-fx-background-color: #CC9999");
        try {
//            copiaImagen(vBoxIconos());
               vBoxIconos();
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        try {
//            vBoxIconos();
//        } catch (Exception ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }
    
    @FXML
    private void guardar(ActionEvent event) {
        logica.fileChooserGuardar(menuItemGuardar);
    }
    
    @FXML
    private void salir(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void abrirDocumento(ActionEvent event) {
        logica.fileChooserAbrirDocumento(menuItemAbrir);
    }

    @FXML
    private void exportar(ActionEvent event) {
        logica.fileChooserExportar(menuItemExportar);
    }
    
    public void vBoxIconos() throws Exception {
        ImageView imageView = null;
        AdministradorArchivosXml archivo = new AdministradorArchivosXml();
        ArrayList lista = archivo.leerArchivo();
        for (int i = 0; i < lista.size(); i++) {
            Image imagen = new Image(archivo.leerArchivo().get(i).getUrl());
            imageView = new ImageView();
            imageView.setImage(imagen);
            //detectaImageView(imageView);
            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
    @Override
    
    public void handle(MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
            System.out.println("hola");
             }  
    }});
            vBoxIconos.getChildren().add(imageView);
    }
            
        }
    
}
