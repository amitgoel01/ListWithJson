package device.droidtv.org.vht;

/**
 * Created by amit.goyal on 8/29/2016.
 */
public class Item {
    private String imageName;
    private String title;
    private String description;

    Item(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
