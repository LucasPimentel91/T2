package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.*;
import java.util.ArrayList;
import java.time.LocalDate;

public class LoanController implements ILoanController {
    private ArrayList<ILoan> loanList;
    private int id = 1;

    public LoanController() {
        loanList = new ArrayList<ILoan>();
    }

    public boolean isValidUser(IUser user) {
        var userController = Core.getInstance().getUserController();
        if(user != null && user instanceof IUser && userController.thisUserExists(user)) {
            //O user não está sendo encontrado na lista de usuarios no userController.
            return true;
        }
       return false;
    }

    public boolean isValidBook(IBook book) {
        var bookController = Core.getInstance().getBookController();
        if(book != null && book instanceof IBook && bookController.thisBookExists(book)) {
            return true;
        }
        return false;
    }

    public boolean requestSetLoan(IUser user, IBook book) {
        if(isValidUser(user) && isValidBook(book)) {
            return true;
        }
        return false;
    }

    public LocalDate setDateReturn() {
        return LocalDate.now().plusDays(7);
    }


    public ILoan setLoan(IUser user, IBook book, LocalDate dateLoan) {
        if (requestSetLoan(user, book)) {
            var dateReturn = setDateReturn();
            ILoan loan = new Loan(user, book, dateLoan, dateReturn);
            addLoan(loan);
            return loan;
        }
        //System.out.println("Aqui deu nada!!!");
        return null;
    }

    public ArrayList<ILoan> getLoanList() {
        return loanList;
    }

    public void addLoan(ILoan loan) {
        if (loan != null) {
            loanList.add(loan);
        }
    }

    public void getDate(ILoan loan){
        loan.teste();
    }
}
