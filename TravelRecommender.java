import java.util.List;
import java.util.ArrayList;


public class TravelRecommender {

    static List<TravelOption> knapsack (UserPreferences preferences, List<TravelOption> options) {
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
public static void main(String[] args){
        List<TravelOption> options = new ArrayList<>(); // List for travel options
        options.add(new TravelOption("Hotel A", 8.5, 150,1));
        options.add(new TravelOption("Restaurant B",9.0,50,2));
        options.add(new TravelOption("Destination C",9.5,200,4));
        options.add(new TravelOption("Crisologo Museum",8.0,350,2));
        options.add(new TravelOption("Bantay Bell Tower",9.0,200,2));
        options.add(new TravelOption("Hotel B",7.5,200,4));
        options.add(new TravelOption("Calle Crisologo",5.9,200,5));



    UserPreferences preferences = new UserPreferences(500,2); // budget and time of the user
        List<TravelOption> recommendedOptions = knapsack(preferences,options);

        for (TravelOption option:recommendedOptions){
            System.out.println("Recommended: " + option.name);
        }
     }


}
