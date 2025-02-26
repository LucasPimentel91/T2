package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;
import br.edu.ifba.inf008.interfaces.ILoan;

import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.ObservableList;

public class ReportPlugin implements IPlugin {

    @Override
    public boolean init() {
        IUIController uiController = ICore.getInstance().getUIController();

        MenuItem menuItem = uiController.createMenuItem("Relatório", "Emprestados");
        menuItem.setOnAction(e -> showReport(uiController));

        return true;
    }

    private void showReport(IUIController uiController) {
        ObservableList<ILoan> loanList = uiController.getObListLoan();
        Stage reportStage = new Stage();
        reportStage.setTitle("Relatório de Empréstimos");

        TableView<ILoan> reportTable = new TableView<>(loanList);

        TableColumn<ILoan, String> userCol = new TableColumn<>("Usuário");
        userCol.setCellValueFactory(data -> data.getValue().usuarioProperty());

        TableColumn<ILoan, String> bookCol = new TableColumn<>("Livro");
        bookCol.setCellValueFactory(data -> data.getValue().livroProperty());

        TableColumn<ILoan, String> dateCol = new TableColumn<>("Data");
        dateCol.setCellValueFactory(data -> data.getValue().dataEmprestimoProperty());

        reportTable.getColumns().addAll(userCol, bookCol, dateCol);

        VBox layout = new VBox(10, reportTable);
        Scene scene = new Scene(layout, 500, 400);
        reportStage.setScene(scene);
        reportStage.show();
    }
}
