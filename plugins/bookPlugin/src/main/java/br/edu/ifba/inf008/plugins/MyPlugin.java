package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;
import br.edu.ifba.inf008.plugins.BookPlugin;

import javafx.scene.control.MenuItem;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class MyPlugin implements IPlugin
{
    public boolean init() {
        /*IUIController uiController = ICore.getInstance().getUIController();

        MenuItem menuItem = uiController.createMenuItem("Menu 1", "New Table");
        menuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                uiController.createTab("new tab", new Rectangle(200,200, Color.LIGHTSTEELBLUE));
            }
        });*/

        BookPlugin bookPlugin = new BookPlugin();
        bookPlugin.init();

        

        return true;
    }
}




