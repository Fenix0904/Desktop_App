package model;

import java.util.ArrayList;
import java.util.Date;

public class Auction {
    private int _id;
    private int _userID;
    private int _itemID;
    private int _itemQuantity;
    private Date _startDate;
    private Date _terminationDate;
    private int _initialPrice;
    private int _bidRate;
    private ArrayList<Bid> bids; // for logging
    private Item item;
    private Traider traider;
}
