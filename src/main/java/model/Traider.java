package model;

public interface Traider {

    /**
     * First parameter can be List<Items>, then second parameter will be Map<Item (or int - item id), Integer( itemQuantity ).
     */
    Auction createAuction(Item item, int itemQuantity, String startDate, String startTime,
                                 String terminationDate, String terminationTime, int initialPrice, int bidRate);

    void updateAuction(int auctionID, String startDate, String startTime,
                       String terminationDate, String terminationTime, int initialPrice, int bidRate);

    boolean deleteAuction(int auctionId);

}
