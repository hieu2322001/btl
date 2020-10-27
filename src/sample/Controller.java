package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    Dictionary lis = new Dictionary();
    DictionaryManagement management = new DictionaryManagement();
    @FXML
    ListView<String> listView = new ListView();
    @FXML
    TextField TimText = new TextField();
    @FXML
    TextField AnhText = new TextField();
    @FXML
    TextField VietText = new TextField();
    @FXML
    TextField addV = new TextField();
    @FXML
    TextField addE = new TextField();

    @FXML
    Button buttonTim;
    @FXML
    Button buttonThem;
    @FXML
    Button buttonSua;
    @FXML
    Button buttonXoa;
    public Controller() {
    }

    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.management.insertFromFile(this.lis);
        } catch (FileNotFoundException var4) {
            var4.printStackTrace();
        }

        for(int i = 0; i < this.lis.words.size(); ++i) {
            this.listView.getItems().add(((Word)this.lis.words.get(i)).getWord_target());
        }

    }

    public void autocompelete(KeyEvent keyEvent) {
        this.listView.getItems().clear();
        String s = this.TimText.getText();

        for(int i = 0; i < this.lis.words.size(); ++i) {
            if (((Word)this.lis.words.get(i)).getWord_target().startsWith(s)) {
                this.listView.getItems().add(((Word)this.lis.words.get(i)).getWord_target());
            }
        }

    }

    public void Tim_tu(ActionEvent event) {
        this.listView.getItems().clear();
        String s = this.TimText.getText();

        for(int i = 0; i < this.lis.words.size(); ++i) {
            if (((Word)this.lis.words.get(i)).getWord_target().startsWith(s)) {
                this.listView.getItems().add(((Word)this.lis.words.get(i)).getWord_target());
            }
        }

    }

    public void Them_tu(ActionEvent event) throws FileNotFoundException, UnsupportedEncodingException {
        String tu = this.addE.getText();
        String nghia = this.addV.getText();
        this.addE.clear();
        this.addV.clear();
        this.management.insertFromCommandline(this.lis, tu, nghia);
        this.management.save_file(this.lis);
    }

    public void Xoa_tu() throws FileNotFoundException, UnsupportedEncodingException {
        String a = (String)this.listView.getSelectionModel().getSelectedItem();
        this.listView.getItems().remove(a);
        this.management.dictionaryRemove(this.lis, a);
        this.management.save_file(this.lis);
    }

    public void Fix_word(ActionEvent event) throws FileNotFoundException, UnsupportedEncodingException {
        String a = (String)this.listView.getSelectionModel().getSelectedItem();
        this.management.get_word(this.lis, a);
        this.management.dictionaryFix(this.management.get_word(this.lis, a), this.AnhText.getText(), this.VietText.getText());
        this.Tim_tu(event);
        this.management.save_file(this.lis);
    }

    public void Show_word(MouseEvent event) {
        String a = (String)this.listView.getSelectionModel().getSelectedItem();
        Word word_show = this.management.get_word(this.lis, a);
        this.AnhText.setText(word_show.getWord_target());
        this.VietText.setText(word_show.getWord_explain());
    }
    




}
