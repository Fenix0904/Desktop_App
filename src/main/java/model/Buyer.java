package model;

import java.util.List;

/**
 * BUYER = USER
 */
public interface Buyer {

    /**
     * Buyer can see all the items that are on the auctions.
     */
    List<Item> getItems();

    List<Auction> getAuctions();

    void bidOnAuction(Auction auction, int sum);

    void registerToAuction(int auctionId);

    void unRegisterFromAuction(int auctionId);

    /**
     * Buyer can find auction through certain category.
     */
    List<Auction> exploreAuctions(Category category);
}
