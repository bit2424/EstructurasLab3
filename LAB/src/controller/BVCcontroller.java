package controller;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import myException.completeDataException;
import view.Main;

public class BVCcontroller {

    @FXML
    private AnchorPane addPane;
    @FXML
    private TextField fileAddress;
    @FXML
    private TextArea dataAreaText;
    @FXML
    private AnchorPane selecPane;
    @FXML
    private ComboBox<?> sharesCombo;
    @FXML
    private ComboBox<?> currencysCombo;
    @FXML
    private AnchorPane consulPane;
    @FXML
    private Label highLowLabel;
    @FXML
    private Label numberMarkets;
    @FXML
    private Label nameConsulPane;
    @FXML
    private Label priceLabel;
    @FXML
    private Label dateConsulPane;
    @FXML
    private AnchorPane intervalPane;
    @FXML
    private Label nameIntervalPane;
    @FXML
    private Label startDateLabel;
    @FXML
    private Label endDateLabel;
    @FXML
    private Label percentLabel;
    @FXML
    private AnchorPane filterPane;
    @FXML
    private TextField priceSearchTextField;
    @FXML
    private ListView<?> ListMarkets;
    @FXML
    private AnchorPane increasePane;
    @FXML
    private Label AnameIncreasePane;
    @FXML
    private Label BnameIncreasePane;
    @FXML
    private Label CnameIncreasePane;
    @FXML
    private Label typeMarketIncreasePane;
    @FXML
    private AnchorPane graphPane;
    @FXML
    private Label typeMarketGraphPane;
    @FXML
    private LineChart<?, ?> graph;
    private int state;
    
    @FXML
    void addGraph(ActionEvent event) {

    }
    @FXML
    void addMarket(ActionEvent event) {
    	noVisiblePanes();
    	addPane.setVisible(true);
    	state =1;
    }
    @FXML
    void addMarketAddPane(ActionEvent event) throws IOException {
    	if(dataAreaText.getText().equals("")&&fileAddress.getText().equals("")) {
    		try {
				throw new completeDataException();
			} catch (completeDataException e) {
				JOptionPane.showMessageDialog(null,e.getMessage());
			}
    	}else if(dataAreaText.getText().equals("")) {
    		Main.getReception().createMarketTxt(fileAddress.getText());
    	}else {
    		Main.getReception().createMarketString(dataAreaText.getText());
    	}
    	dataAreaText.setText("");
    	fileAddress.setText("");
    	numberMarkets.setText("Cantidad divisas: " +Main.getReception().getMarketCurrencys().size()
    			+ "           Cantidad acciones: "+ Main.getReception().getMarketShares().size());
    }
	@FXML
    void clean(ActionEvent event) {

    }
    @FXML
    void currencyAction(ActionEvent event) {

    }
    @FXML
    void deleteMarket(ActionEvent event) {
    	noVisiblePanes();
    	selecPane.setVisible(true);
    	state =3;
    }
    @FXML
    void filterTypes(ActionEvent event) {
    	noVisiblePanes();
    	filterPane.setVisible(true);
    	state =6;
    }
    @FXML
    void goGraph(ActionEvent event) {
    	noVisiblePanes();
    	addPane.setVisible(true);
    	state =9;
    }
    @FXML
    void intervalHigh(ActionEvent event) {
    	noVisiblePanes();
    	intervalPane.setVisible(true);
    	state =7;
    }
    @FXML
    void nextSelecPane(ActionEvent event) {

    }
    @FXML
    void searchAdd(ActionEvent event) {
    	 FileChooser fileChooser = new FileChooser();
         fileChooser.setTitle("Buscar Archivo de texto");
         fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All notes","*.txt"));
         File file = fileChooser.showOpenDialog(null);
         if(file!= null)
        	 fileAddress.setText(file.getPath());

    }
    @FXML
    void searchFilterPane(ActionEvent event) {

    }
    @FXML
    void searchPriceHigh(ActionEvent event) {
    	noVisiblePanes();
    	consulPane.setVisible(true);
    	state =4;
    }
    @FXML
    void searchPriceLow(ActionEvent event) {
    	noVisiblePanes();
    	consulPane.setVisible(true);
    	state =5;
    }
    @FXML
    void setMarket(ActionEvent event) {
    	noVisiblePanes();
    	selecPane.setVisible(true);
    	state =2;
    }
    @FXML
    void sharesAction(ActionEvent event) {

    }
    @FXML
    void topGreaterGrowth(ActionEvent event) {
    	noVisiblePanes();
    	increasePane.setVisible(true);
    	state =8;
    }
    private void noVisiblePanes() {
    	addPane.setVisible(false);
    	selecPane.setVisible(false);
    	consulPane.setVisible(false);
    	intervalPane.setVisible(false);
    	filterPane.setVisible(false);
    	graphPane.setVisible(false);
    	increasePane.setVisible(false);
    }
    
}
