package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.*;

public class Loan implements ILoan {
    private int id;
    private IUser userInstance;
    private IBook bookInstance;
    private String loanDate;
    private String returnDate;
    
    public Loan(int id, IUser userInstance, IBook bookInstance, String loanDate, String returnDate) {
        this.id = id;
        this.userInstance = userInstance;
        this.bookInstance = bookInstance;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

}
