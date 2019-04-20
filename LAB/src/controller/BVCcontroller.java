package controller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import model.Market;
import myException.completeDataException;
import view.Main;

public class BVCcontroller implements Initializable {

    @FXML
    private AnchorPane addPane;
    @FXML
    private TextField fileAddress;
    @FXML
    private TextArea dataAreaText;
    @FXML
    private AnchorPane selecPane;
    @FXML
    private ComboBox<String> sharesCombo;
    @FXML
    private ComboBox<String> currencysCombo;
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
    private Label stateLabel;
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
    private ListView<String> ListMarkets;
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
    private int current=-1;
    private int type;
    
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
    	refreshMarkets();
    	noVisiblePanes();
    }
	@FXML
    void clean(ActionEvent event) {

    }
    @FXML
    void currencyAction(ActionEvent event) {
    	sharesCombo.setDisable(true);
    	if(currencysCombo.getSelectionModel().getSelectedIndex()>-1) {
    		current = currencysCombo.getSelectionModel().getSelectedIndex();
    		type=0;
    	}
    }
    @FXML
    void deleteMarket(ActionEvent event) {
    	noVisiblePanes();
    	selecPane.setVisible(true);
    	state =3;
    	stateLabel.setText("Eliminar Mercado");
    	refreshMarkets();
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
    	selecPane.setVisible(true);
    	state =9;
    	stateLabel.setText("Graficar mercado");
    	refreshMarkets();
    }
    @FXML
    void intervalHigh(ActionEvent event) {
    	noVisiblePanes();
    	selecPane.setVisible(true);
    	state =7;
    	stateLabel.setText("Rango de mayor crecimiento");
    	refreshMarkets();
    }
    @FXML
    void nextSelecPane(ActionEvent event) {
    	switch (state) {
		case 2: goSetMarket();
			break;
		case 3: goDeleteMarket();
		break;
		case 4: goConsulHigh();
		break;
		case 5: goConsulLow();
		break;
		case 7: goIntervalHigh();
		break;
		case 9: goGraphs();
		break;
		default:
			break;
		}
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
    	selecPane.setVisible(true);
    	state =4;
    	stateLabel.setText("Consultar Precio Mas alto");
    	refreshMarkets();
    }
    @FXML
    void searchPriceLow(ActionEvent event) {
    	noVisiblePanes();
    	selecPane.setVisible(true);
    	state =5;
    	stateLabel.setText("Consultar Precio Mas bajo");
    	refreshMarkets();
    }
    @FXML
    void setMarket(ActionEvent event) {
    	noVisiblePanes();
    	selecPane.setVisible(true);
    	state =2;
    	stateLabel.setText("Modificar mercado");
    	refreshMarkets();
    }
    @FXML
    void sharesAction(ActionEvent event) {
    	currencysCombo.setDisable(true);
    	if(sharesCombo.getSelectionModel().getSelectedIndex()>-1) {
    		current = sharesCombo.getSelectionModel().getSelectedIndex();
    		type=1;
    	}
    }
    @FXML
    void topGreaterGrowth(ActionEvent event) {
    	noVisiblePanes();
    	increasePane.setVisible(true);
    	state =8;
    }
    @FXML
    void saveData(ActionEvent event) throws IOException {
    	Main.serealization();
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
	private void refreshMarkets() {
		numberMarkets.setText("Cantidad divisas: " +Main.getReception().getMarketCurrencys().size()
    			+ "           Cantidad acciones: "+ Main.getReception().getMarketShares().size());
		listMarkets();
		sharesCombo.setDisable(false);
		currencysCombo.setDisable(false);
	}
	private void listMarkets() {
		sharesCombo.getItems().clear();
		currencysCombo.getItems().clear();
		if( Main.getReception().getMarketShares().size()!=0) {
			ArrayList<String> a = new ArrayList<>();
			for (int i = 0; i < Main.getReception().getMarketShares().size(); i++) {
				a.add(Main.getReception().getMarketShares().get(i).getName());
			}
			sharesCombo.getItems().addAll(a);
		}
		if( Main.getReception().getMarketCurrencys().size()!=0) {
			ArrayList<String> a = new ArrayList<>();
			for (int i = 0; i < Main.getReception().getMarketCurrencys().size(); i++) {
				a.add(Main.getReception().getMarketCurrencys().get(i).getName());
			}
			currencysCombo.getItems().addAll(a);
		}
		
	}
	private ArrayList<Market> getCurrent() {
		if(type==0)
			return Main.getReception().getMarketCurrencys();
		else
			return Main.getReception().getMarketShares();
	}
	private void goConsulLow() {
		// TODO Auto-generated method stub
		
	}
	private void goConsulHigh() {
		// TODO Auto-generated method stub
		
	}
	private void goDeleteMarket() {
		getCurrent().remove(current);
		noVisiblePanes();
		refreshMarkets();
	}
	private void goSetMarket() {
		goDeleteMarket();
		addPane.setVisible(true);
	}
	private void goGraphs() {
		// TODO Auto-generated method stub
		
	}
	private void goIntervalHigh() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		refreshMarkets();
		
	}
    
}
