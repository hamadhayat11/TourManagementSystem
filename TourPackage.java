package tourManagementSystem2;
import java.util.List;
public class TourPackage {
	 private String packageName;
	    private double price;
	    private String duration;
	    private int maxPeople;
	    private List<String> accommodations;

	    public TourPackage(String packageName, double price, String duration, int maxPeople, List<String> accommodations) {
	        this.packageName = packageName;
	        this.price = price;
	        this.duration = duration;
	        this.maxPeople = maxPeople;
	        this.accommodations = accommodations;
	    }

	    // Getters
	    public String getPackageName() { return packageName; }
	    public double getPrice() { return price; }
	    public String getDuration() { return duration; }
	    public int getMaxPeople() { return maxPeople; }
	    public List<String> getAccommodations() { return accommodations; }

	    @Override
	    public String toString() {
	        return packageName + " - " + price + " AED (" + duration + ")";
	    }
}
