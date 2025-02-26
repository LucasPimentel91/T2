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

    public int getId() {
        return id;
    }

    public void updateId() {
        id++;
    }

    public boolean isValidUser(IUser user) {
        var userController = Core.getInstance().getUserController();
        if(user != null && user instanceof User && userController.thisUserExists(user)) {
            return true;
        }
        return false;
    }

    public boolean isValidBook(IBook book) {
        var bookController = Core.getInstance().getBookController();
        if(book != null && book instanceof Book && bookController.thisBookExists(book)) {
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

    public String setDateReturn() {
        return LocalDate.now().plusDays(7).toString();
    }

    public String setDateLoan() {
        return LocalDate.now().toString();
    }

    public ILoan setLoan(IUser user, IBook book, LocalDate dateLoan, String dateReturn) {
        if (requestSetLoan(user, book)) {
            ILoan loan = new Loan(getId(), user, book, dateLoan, dateReturn);
            addLoan(loan);
            updateId();
            return loan;
        }
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
}
