package auction.ui.entity;


import java.util.Date;


public class Bid {

    private int id;


    private int bidValue;


    private User user;

    private Lot lot;

    private Date bidTime;

    public int getId() { return id; }

    public int getBidValue() { return bidValue; }

    public void setBidValue(int bidValue) { this.bidValue = bidValue; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Lot getLot() { return lot; }

    public void setLot(Lot lot) { this.lot = lot; }

    public Date getBidTime() { return bidTime; }

    public void setBidTime(Date bidTime) { this.bidTime = bidTime; }
}
