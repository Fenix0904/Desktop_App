package auction.ui.entity;



public class Photo {


    private int id;


    private String url;


    private Lot lot;

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Lot getLot() {
        return lot;
    }
}
