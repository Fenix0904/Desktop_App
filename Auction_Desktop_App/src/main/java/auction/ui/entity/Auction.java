package auction.ui.entity;


import java.util.Date;
import java.util.List;

public class Auction {

    private int id;


    private AuctionStatus auctionStatus;


    private Date startDate;

    private Date terminationDate;


    private int bidStep;

    /**
     * Id of user, who created auction.
     */

    private User user;


    private Category category;

    private List<Lot> lots;

    public int getId() {
        return id;
    }

    public AuctionStatus getAuctionStatus() {
        return auctionStatus;
    }

    public void setAuctionStatus(AuctionStatus auctionStatus) {
        this.auctionStatus = auctionStatus;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    public int getBidStep() {
        return bidStep;
    }

    public void setBidStep(int bidStep) {
        this.bidStep = bidStep;
    }

    public User getTraiderId() {
        return user;
    }

    public void setTraiderId(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Lot> getLots() {
        return lots;
    }

    public void setLots(List<Lot> lots) {
        this.lots = lots;
    }
}
