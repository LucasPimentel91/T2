package br.edu.ifba.inf008.plugins;

import java.time.LocalDate;
import java.util.Collections;

import br.edu.ifba.inf008.interfaces.*;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
        ILoanController loanController = ICore.getInstance().getLoanController();

        MenuItem menuItem = uiController.createMenuItem("Relatório", "Emprestados");
        menuItem.setOnAction(e -> { 
            showReportLoans(uiController, ioController);
    });
        MenuItem menuItem2 = uiController.createMenuItem("Relatório", "Atrasados");
        menuItem2.setOnAction(e -> { 
            showReportLoansLate(ioController, loanController);
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

    private void showReportLoansLate(IIOController ioController, ILoanController loanController) {
        Stage stage = new Stage();
        stage.setTitle("Informar data atual");
        
        DatePicker datePicker = new DatePicker(LocalDate.now());
        
        Button saveButton = new Button("Realizar Empréstimo");
        saveButton.setOnAction(e -> {
            
            LocalDate dateCurrent = datePicker.getValue();
            
            loanController.updateStatusLate(dateCurrent);

            Stage stage2 = new Stage();
            stage2.setTitle("Relatório de Empréstimos Atrasados");

            TableView<ILoan> reportLateTable = new TableView<>(ioController.getLoanLateListObs());
        
            TableColumn<ILoan, String> userCol = new TableColumn<>("Usuário");
            userCol.setCellValueFactory(data -> data.getValue().userProperty());
            
            TableColumn<ILoan, String> bookCol = new TableColumn<>("Livro");
            bookCol.setCellValueFactory(data -> data.getValue().bookProperty());
            
            TableColumn<ILoan, String> dateLoanCol = new TableColumn<>("Data do Empréstimo");
            dateLoanCol.setCellValueFactory(data -> data.getValue().dateLoanProperty());
            
            TableColumn<ILoan, String> dateReturnCol = new TableColumn<>("Data de Retorno");
            dateReturnCol.setCellValueFactory(data -> data.getValue().dateReturnProperty());

            Collections.addAll(reportLateTable.getColumns(), userCol, bookCol, dateLoanCol, dateReturnCol);

            VBox layout = new VBox(10, datePicker, saveButton, reportLateTable);
            Scene scene = new Scene(layout, 600, 400);
            stage.setScene(scene);
            stage.show();
        });
    }
}
