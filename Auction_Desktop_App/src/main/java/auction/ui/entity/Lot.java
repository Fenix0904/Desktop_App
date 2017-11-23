package auction.ui.entity;


import java.util.List;


public class Lot {


    private int id;


    private int initialPrice;


    private int lotQuantity;

    private String description;


    private Auction auction;

    private List<Photo> photos;

    private List<Bid> bids;

    public int getId() { return id; }

    public int getInitialPrice() { return initialPrice; }

    public void setInitialPrice(int initialPrice) { this.initialPrice = initialPrice; }

    public int getLotQuantity() { return lotQuantity; }

    public void setLotQuantity(int lotQuantity) { this.lotQuantity = lotQuantity; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public List<Photo> getPhotos() { return photos; }

    public void setPhotos(List<Photo> photos) { this.photos = photos; }

    public Auction getAuction() { return auction; }

    public void setAuction(Auction auction) { this.auction = auction; }

    public List<Bid> getBids() { return bids; }

    public void setBids(List<Bid> bids) { this.bids = bids; }
}
