package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.*;
import javafx.application.Application;
import javafx.application.Platform;

public class Core extends ICore
{
    private Core() {}

    public static boolean init() {
	if (instance != null) {
	    System.out.println("Fatal error: core is already initialized!");
	    System.exit(-1);
	}

	instance = new Core();
        UIController.launch(UIController.class);

        return true;
    }
    public IUIController getUIController() {
        return UIController.getInstance();
    }
    public IAuthenticationController getAuthenticationController() {
        return authenticationController;
    }
    public IIOController getIOController() {
        return ioController;
    }
    public IPluginController getPluginController() {
        return pluginController;
    }

    public IBookController getBookController() {
        return bookController;
    }

    public IUserController getUserController() {
        return userController;
    }

    public ILoanController getLoanController(){
        return loanController;
    }

    private IAuthenticationController authenticationController = new AuthenticationController();
    private IIOController ioController = new IOController();
    private IPluginController pluginController = new PluginController();
    private IBookController bookController = new BookController();
    private IUserController userController = new UserController();
    private ILoanController loanController = new LoanController();
}
