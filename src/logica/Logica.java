package logica;

import Archivos.AdministradorArchivosXml;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import proyectoarmacabezas.FXMLDocumentController;
import proyectoarmacabezas.Icono;

/**
 *
 * @author Wilmer Mata 
 * @author Nicole Fonseca
 */

public class Logica {

    private GridPane gridPaneMapa;

    /*
    * Método para mostrar el mapa mediante una matriz, el tamaño es definido por el usuario con
    * los parámetros primerParametro y segundoParametro, el primer for recorre la matriz de Image
    * y el segundo for la matriz de ImageView, mientras que las variables m y n van aumentando para
    * llenar el GridPane con las imágenes
     */
    
    public GridPane mostrarMapa(int primerParametro, int segundoParametro) {

        gridPaneMapa = new GridPane();
        //Matriz de imágenes con tamaño definido por el usuario
        Image muestraMapa[][] = new Image[primerParametro][segundoParametro];
        ImageView imageViewMuestraMapa[][] = new ImageView[primerParametro][segundoParametro];

        int m = 0;
        int n = 0;
        for (int i = 0; i < muestraMapa.length; i++) {
            m = 0;
            for (int j = 0; j < muestraMapa[0].length; j++) {
                muestraMapa[i][j] = new Image("/iconos/cuadrado.jpg");
                imageViewMuestraMapa[i][j] = new ImageView();
                imageViewMuestraMapa[i][j].setImage(muestraMapa[i][j]);

                GridPane.setConstraints(imageViewMuestraMapa[i][j], m, n);
                m++;
                gridPaneMapa.getChildren().add(imageViewMuestraMapa[i][j]);
            }
            n++;
        }
        return gridPaneMapa;
    }
    
    double orgSceneX;
    double orgSceneY;
    double orgTranslateX;
    double orgTranslateY;
    
    public void moverIconos(ImageView imageView) {
        
        EventHandler<MouseEvent> imageViewOnMousePressedEventHandler
                = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                orgSceneX = event.getSceneX();
                orgSceneY = event.getSceneY();
                orgTranslateX = ((ImageView) (event.getSource())).getTranslateX();
                orgTranslateY = ((ImageView) (event.getSource())).getTranslateY();
            }
        };

        EventHandler<MouseEvent> imageViewOnMouseDraggedEventHandler
                = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                double offsetX = event.getSceneX() - orgSceneX;
                double offsetY = event.getSceneY() - orgSceneY;
                double newTranslateX = orgTranslateX + offsetX;
                double newTranslateY = orgTranslateY + offsetY;

                ((ImageView) (event.getSource())).setTranslateX(newTranslateX);
                ((ImageView) (event.getSource())).setTranslateY(newTranslateY);
            }
        };

        imageView.setOnMousePressed(imageViewOnMousePressedEventHandler);
        imageView.setOnMouseDragged(imageViewOnMouseDraggedEventHandler);
    }
    
    public void fileChooserGuardar(MenuItem menuItemGuardar) {
        FileChooser chooser = new FileChooser();

        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".json", "*.json"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        File file = chooser.showSaveDialog(menuItemGuardar.getParentPopup().getScene().getWindow());
        if (file != null) {
            guardarDocumento("", file);
        }
        
    }
    
    private void guardarDocumento(String content, File file) {
        try {
            FileWriter fileWriter = null;

            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private File archivo;
    private static boolean DEBUG = false;
    
    public void fileChooserAbrirDocumento(MenuItem menuItemAbrir) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".*xml", "*.xml", "*.json"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        if (archivo != null) {
            File existDirectory = archivo.getParentFile();
            chooser.setInitialDirectory(existDirectory);
        }

        File archivo = chooser.showOpenDialog(menuItemAbrir.getParentPopup().getScene().getWindow());

        String nombreArchivo = archivo.getPath();

//        if (DEBUG) {
//            princpalLogTextArea.appendText("Opening " + nombreArchivo + System.lineSeparator());
//        }
    }
    
    public void fileChooserExportar(MenuItem menuItemExportar) {
        FileChooser chooser = new FileChooser();

        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".jpg", "*.png"),
                new FileChooser.ExtensionFilter(".png", "*.png")
        );

        File file = chooser.showSaveDialog(menuItemExportar.getParentPopup().getScene().getWindow());
        if (file != null) {
            guardarDocumento("", file);
        }
    }
    
    //Guardar descripción de los íconos en archivo Xml
    public void guardarInformacionIconos() {
        
        Icono iconoTwitter = new Icono("Twitter", 64);
        Icono iconoFacebook = new Icono("Facebook", 64);
        Icono iconoInstagram = new Icono("Instagram", 64);
        Icono iconoSkype = new Icono("Skype", 64);
        Icono iconoSnapchat = new Icono("Snapchat", 64);
        Icono iconoSoundCloud = new Icono("SoundCloud", 64);
        Icono iconoTumblr = new Icono("Tumblr", 64);
        Icono iconoWhatsApp = new Icono("WhatsApp", 64);
        Icono iconoTelegram = new Icono("Telegram", 64);
        Icono iconoYouTube = new Icono("YouTube", 64);
        
        AdministradorArchivosXml archivoXml = new AdministradorArchivosXml();
        archivoXml.guardarIconos("ListadoIconos", iconoTwitter);
        archivoXml.guardarIconos("ListadoIconos", iconoFacebook);
        archivoXml.guardarIconos("ListadoIconos", iconoInstagram);
        archivoXml.guardarIconos("ListadoIconos", iconoSkype);
        archivoXml.guardarIconos("ListadoIconos", iconoSnapchat);
        archivoXml.guardarIconos("ListadoIconos", iconoSoundCloud);
        archivoXml.guardarIconos("ListadoIconos", iconoTumblr);
        archivoXml.guardarIconos("ListadoIconos", iconoWhatsApp);
        archivoXml.guardarIconos("ListadoIconos", iconoTelegram);
        archivoXml.guardarIconos("ListadoIconos", iconoYouTube);
    }
}
