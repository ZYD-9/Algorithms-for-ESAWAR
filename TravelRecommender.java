import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;


public class TravelRecommender {

    static List<TravelOption> knapsack ( List<TravelOption> options,UserPreferences preferences) {

        int n = options.size();
        int W = preferences.budget;
        int K[][] = new int[n+1][W+1];
        List<TravelOption> selectedOptions = new ArrayList<>();

        for (int i = 0; i<= n; i++){
            for(int w = 0; w <= W; w++){
                    if(i==0||w==0){
                        K[i][w] = 0;
                    }
                    else if (options.get(i-1).cost<=w){
                        K[i][w] = Math.max((int)options.get(i-1).rating + K[i-1][w - options.get(i-1).cost],K[i-1][w]);
                    }
                    else {
                        K[i][w] = K[i-1][w];
                    }
            }
        }

        int res = K[n][W];
        int w = W;
        for (int i = n; i>0 && res > 0; i--){
            if(res != K[i-1][w]){
                selectedOptions.add(options.get(i-1));
                res -=(int)options.get(i-1).rating;
                w -= options.get(i-1).cost;
            }
        }

        return selectedOptions;
    }


    static List<List<TravelOption>> planDailyItinerary(List<TravelOption> selectedOptions, UserPreferences preferences) {
        List<List<TravelOption>> dailyItinerary = new ArrayList<>();
        int totalDays = preferences.availableTimeInDays; // Assuming this is the total number of days available

        int dayIndex = 0;
        while (dayIndex < totalDays && !selectedOptions.isEmpty()) {
            int dailyAvailableTime = preferences.availableTime; // Reset available time for each day
            List<TravelOption> daySchedule = new ArrayList<>();

            // Iterator to safely remove elements while iterating
            Iterator<TravelOption> iterator = selectedOptions.iterator();
            while (((Iterator<?>) iterator).hasNext() && dailyAvailableTime > 0) {
                TravelOption option = iterator.next();
                if (option.timeRequired <= dailyAvailableTime) {
                    daySchedule.add(option);
                    dailyAvailableTime -= option.timeRequired;
                    iterator.remove(); // Remove the option as it's been scheduled
                }
            }

            if (!daySchedule.isEmpty()) {
                dailyItinerary.add(daySchedule);
            }
            dayIndex++; // Move to the next day
        }

        return dailyItinerary;
    }


    public static void main(String[] args){
        List<TravelOption> options = new ArrayList<>(); // List for travel options
        options.add(new TravelOption("Hotel A", 8.5, 150,1,800,1900));
        options.add(new TravelOption("Restaurant B",9.0,50,2,900,1800));
        options.add(new TravelOption("Destination C",9.5,200,4,800,1900));
        options.add(new TravelOption("Crisologo Museum",8.0,350,2,800,1900));
        options.add(new TravelOption("Bantay Bell Tower",9.0,200,2,800,1900));
        options.add(new TravelOption("Hotel B",7.5,200,4,800,1900));
        options.add(new TravelOption("Calle Crisologo",5.9,200,5,800,1900));



    UserPreferences preferences = new UserPreferences(500,3,900,2100); // budget and time of the user
        List<TravelOption> recommendedOptions = knapsack(options,preferences);
        List<List<TravelOption>> dailyItinerary = planDailyItinerary(recommendedOptions,preferences);


        for (int day = 0;day< dailyItinerary.size();day++){
            System.out.println("Day" + (day + 1) + " :");
            for(TravelOption option : dailyItinerary.get(day)){
                System.out.println("  " + option.name + " (" + option.timeRequired + " hours)");
            }
        }
     }


}
