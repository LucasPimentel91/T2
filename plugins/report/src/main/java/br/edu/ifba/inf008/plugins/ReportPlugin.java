package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;

import java.util.Collections;

import br.edu.ifba.inf008.interfaces.*;

import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;

public class ReportPlugin implements IPlugin {

    @Override
    public boolean init() {
        IUIController uiController = ICore.getInstance().getUIController();
        IIOController ioController = ICore.getInstance().getIOController();

        MenuItem menuItem = uiController.createMenuItem("Relatório", "Emprestados");
        menuItem.setOnAction(e -> { 
            showReportLoans(uiController, ioController);
    });

        return true;
    }

    private void showReportLoans(IUIController uiController, IIOController ioController) {
        ObservableList<ILoan> loanList = ioController.getLoanListObs();
        Stage reportStage = new Stage();
        reportStage.setTitle("Relatório de Empréstimos");

        TableView<ILoan> reportTable = new TableView<>(loanList);

        TableColumn<ILoan, String> userCol = new TableColumn<>("Usuário");
        userCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser().toString()));

        TableColumn<ILoan, String> bookCol = new TableColumn<>("Livro");
        bookCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBook().toString()));

        TableColumn<ILoan, String> dateLoanCol = new TableColumn<>("Data do Empréstimo");
        dateLoanCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDateLoan().toString()));
        
        TableColumn<ILoan, String> dateReturnCol = new TableColumn<>("Data de Retorno");
        dateReturnCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDateReturn().toString()));

        Collections.addAll(reportTable.getColumns(), userCol, bookCol, dateLoanCol, dateReturnCol);

        VBox layout = new VBox(10, reportTable);
        Scene scene = new Scene(layout, 500, 400);
        reportStage.setScene(scene);
        reportStage.show();
    }
}
