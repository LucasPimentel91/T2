package br.edu.ifba.inf008.interfaces;

import javafx.scene.control.MenuItem;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public interface IUIController
{
    public abstract MenuItem createMenuItem(String menuText, String menuItemText);
    public abstract boolean createTab(String tabText, Node contents);
    public void openBookTab();
    public void openUserTab();
    public void openLoanTab();
    public ObservableList<ILoan> getObListLoan();

}
