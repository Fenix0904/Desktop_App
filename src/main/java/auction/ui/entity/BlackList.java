package auction.ui.entity;


public class BlackList {

    private int id;


    private User user;

    public BlackList() {
    }

    public BlackList(User user) {
        this();
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlackList blackList = (BlackList) o;

        return id == blackList.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
