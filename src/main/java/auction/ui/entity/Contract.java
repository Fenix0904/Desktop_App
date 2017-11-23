package auction.ui.entity;


/**
 * створювати новий контракт (`name`, `surname`, `personal id`, `term` та інші поля (за бажанням))
 *
 * @author Oprysko Svyatoslav
 */
public class Contract {

    private String term;

    private User user;

    public Contract() {
    }

    public Contract(String term,  User user) {
        this();
        this.term = term;
        this.user = user;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
