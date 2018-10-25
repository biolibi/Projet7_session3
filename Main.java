import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start (Stage primaryStage) {
        List listeDeMois = new ArrayList();
        List listeDeTempérature = new ArrayList();
        Menu importer = new Menu("Importer");
        Menu exporter = new Menu("Exporter");
        MenuItem ligne = new MenuItem("Lignes");
        MenuItem régions = new MenuItem("Régions");
        MenuItem barres = new MenuItem("Barres");
        MenuItem pnj = new MenuItem("PNJ");
        MenuItem tiff = new MenuItem("TIFF");
        exporter.getItems().addAll(pnj,tiff);
        importer.getItems().addAll(ligne,régions,barres);
        MenuBar menuBar = new MenuBar(importer,exporter);
        ImageView imageView = new ImageView();
        BorderPane root = new BorderPane();

        ligne.setOnAction((event)->{
            root.setTop(menuBar);
            try {
                FileChooser file = new FileChooser();
                file.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier .dat" , "*.dat"));
                File fichier = file.showOpenDialog(primaryStage);
                Scanner sc = new Scanner(new FileReader(fichier) );
                Scanner dataScanner;
                while (sc.hasNextLine()){
                    dataScanner = new Scanner(sc.nextLine());
                    dataScanner.useDelimiter(",");

                    while (dataScanner.hasNext()){
                        String data = dataScanner.next();
                        String tempo = data.toString().replaceAll("\\s", "");

                        if (moisQuiExiste(tempo) == true){
                            listeDeMois.add(tempo);
                        }
                        else if (moisQuiExiste(tempo) != true){
                            listeDeTempérature.add(tempo);
                        }
                    }
                }
                final CategoryAxis xAxis = new CategoryAxis();
                final NumberAxis yAxis = new NumberAxis();
                xAxis.setLabel("Mois");
                yAxis.setLabel("Température");
               final LineChart<String,Number> rootLigne =new LineChart(xAxis,yAxis);
               rootLigne.setTitle("Températures moyennes");
                XYChart.Series series = new XYChart.Series();
               series.setName("Températures moyennes");
               for (int i =0 ; i < listeDeMois.size();i++ ){
                   series.getData().add(new XYChart.Data((String)listeDeMois.get(i),Integer.parseInt((String)listeDeTempérature.get(i))));
               }
                rootLigne.getData().addAll(series);
                root.setCenter(rootLigne);

            }
            catch (Exception e) {
                e.printStackTrace();
            }

        });

        régions.setOnAction((event)->{
            root.setTop(menuBar);
            try {
                FileChooser file = new FileChooser();
                file.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier .dat" , "*.dat"));
                File fichier = file.showOpenDialog(primaryStage);
                Scanner sc = new Scanner(new FileReader(fichier) );
                Scanner dataScanner;
                while (sc.hasNextLine()){
                    dataScanner = new Scanner(sc.nextLine());
                    dataScanner.useDelimiter(",");
                    while (dataScanner.hasNext()){
                        String data = dataScanner.next();
                        String tempo = data.toString().replaceAll("\\s", "");
                        if (moisQuiExiste(tempo) == true){
                            listeDeMois.add(tempo);
                        }
                        else if (moisQuiExiste(tempo) != true){
                            listeDeTempérature.add(tempo);
                        }
                    }
                }
                final CategoryAxis xAxis = new CategoryAxis();
                final NumberAxis yAxis = new NumberAxis();
                xAxis.setLabel("Mois");
                yAxis.setLabel("Température");
                final AreaChart<String,Number> rootLigne =new AreaChart(xAxis,yAxis);
                rootLigne.setTitle("Températures moyennes");
                XYChart.Series series = new XYChart.Series();
                series.setName("Températures moyennes");
                for (int i =0 ; i < listeDeMois.size();i++ ){
                    series.getData().add(new XYChart.Data((String)listeDeMois.get(i),Integer.parseInt((String)listeDeTempérature.get(i))));
                }
                rootLigne.getData().addAll(series);
                root.setCenter(rootLigne);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
        barres.setOnAction((event)->{
            root.setTop(menuBar);
            try {
                FileChooser file = new FileChooser();
                file.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier .dat" , "*.dat"));
                File fichier = file.showOpenDialog(primaryStage);
                Scanner sc = new Scanner(new FileReader(fichier) );
                Scanner dataScanner;
                while (sc.hasNextLine()){
                    dataScanner = new Scanner(sc.nextLine());
                    dataScanner.useDelimiter(",");
                    while (dataScanner.hasNext()){
                        String data = dataScanner.next();
                        String tempo = data.toString().replaceAll("\\s", "");
                        if (moisQuiExiste(tempo) == true){
                            listeDeMois.add(tempo);
                        }
                        else if (moisQuiExiste(tempo) != true){
                            listeDeTempérature.add(tempo);
                        }
                    }
                }
                final CategoryAxis xAxis = new CategoryAxis();
                final NumberAxis yAxis = new NumberAxis();
                xAxis.setLabel("Mois");
                yAxis.setLabel("Température");
                final BarChart<String,Number> rootLigne =new BarChart(xAxis,yAxis);
                rootLigne.setTitle("Températures moyennes");
                XYChart.Series series = new XYChart.Series();
                series.setName("Températures moyennes");
                for (int i =0 ; i < listeDeMois.size();i++ ){
                    series.getData().add(new XYChart.Data((String)listeDeMois.get(i),Integer.parseInt((String)listeDeTempérature.get(i))));
                }
                rootLigne.getData().addAll(series);
                root.setCenter(rootLigne);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });

        pnj.setOnAction((event)->{
            FileChooser sauvegarde = new FileChooser();
            sauvegarde.setTitle("Enregistrer le graphique");
            sauvegarde.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier .png" , "*.png"));
            File sauvegarderFichier = sauvegarde.showSaveDialog(primaryStage);
            WritableImage image = root.snapshot(new SnapshotParameters(), null);

            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png",sauvegarderFichier);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        tiff.setOnAction((event)->{
            FileChooser sauvegarde = new FileChooser();
            sauvegarde.setTitle("Enregistrer le graphique");
            sauvegarde.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier .tiff" , "*.tiff"));
            File sauvegarderFichier = sauvegarde.showSaveDialog(primaryStage);
            WritableImage image = root.snapshot(new SnapshotParameters(), null);

            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "tiff",sauvegarderFichier);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        root.setTop(menuBar);
        primaryStage.setScene(new Scene(root));
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.show();


    }

    public static boolean moisQuiExiste(String data)
    {
        boolean existe = false;
        ArrayList moisDeLannée = new ArrayList();
        moisDeLannée.add("Janvier");moisDeLannée.add("Février");moisDeLannée.add("Mars");moisDeLannée.add("Avril");moisDeLannée.add("Mai");moisDeLannée.add("Juin");moisDeLannée.add("Juillet");
        moisDeLannée.add("Août");moisDeLannée.add("Septembre");moisDeLannée.add("Octobre");moisDeLannée.add("Novembre");moisDeLannée.add("Décembre");
        for (int i =0; i < 12;i++ ){
            if (data.equals(moisDeLannée.get(i))){
                existe = true;
            }

        }

          return existe;
    }



}
