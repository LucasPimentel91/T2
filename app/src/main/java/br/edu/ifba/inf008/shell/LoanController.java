package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.*;
import java.util.ArrayList;
import java.time.LocalDate;

public class LoanController implements ILoanController {

    public boolean isValidUser(IUser user) {
        var ioController = Core.getInstance().getIOController();
        var userController = Core.getInstance().getUserController();
        if(user != null && user instanceof IUser && userController.thisUserExists(ioController, user)) {
            return true;
        }
       return false;
    }

    public boolean isValidBook(IBook book) {
        var ioController = Core.getInstance().getIOController();
        var bookController = Core.getInstance().getBookController();
        if(book != null && book instanceof IBook && bookController.thisBookExists(ioController, book)) {
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
        return LocalDate.now().plusDays(14);
    }


    public ILoan setLoan(IUser user, IBook book, LocalDate dateLoan) {
        if (requestSetLoan(user, book)) {
            var dateReturn = setDateReturn();
            ILoan loan = new Loan(user, book, dateLoan, dateReturn);
            return loan;
        }
        return null;
    }

    public void updateStatusLate(LocalDate date) {
        var ioController = Core.getInstance().getIOController();
        for (ILoan loan : ioController.getListLoan()) {
            if (date.isAfter(loan.getDateReturn())) { 
                loan.late();
            }
        }
    }
    
    public ILoan findLoanByBook(IBook book){
        var ioController = Core.getInstance().getIOController();
        for (ILoan loan : ioController.getListLoan()){
            if (book != null && book.equals(loan.getBook())) {
                return loan;
            }
        }
        return null;
    }

}
