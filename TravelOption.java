public class TravelOption {
    String name;
    double rating;
    int cost;
    int timeRequired;

    int openingTime;
    int closingTime;

    public TravelOption(String name, double rating, int cost, int timeRequired,int openingTime,int closingTime){
        this.name = name;
        this.rating = rating;
        this.cost = cost;
        this.timeRequired = timeRequired;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

}
