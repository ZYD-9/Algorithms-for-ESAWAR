import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Planner {


    private static List<Place> selectPlaces (List<Place> places,int budget, int days) {
        int N = places.size();
        int dp[][] = new int[N + 1][budget + 1];

        for (int i = 0; i <= N; i++) {
            for (int w = 0; w <=budget;w++){
                if (i == 0 || w == 0)
                    dp[i][w] = 0;
                else if(places.get(i-1).cost<= w)
                    dp[i][w] = Math.max(places.get(i-1).rating + dp[i-1][w-places.get(i-1).cost],dp[i-1][w]);
                else
                    dp[i][w] = dp[i-1][w];

            }
        }
        int w = budget;
        List<Place> selectedPlaces = new ArrayList<>();
        for(int i = N; i> 0 && w > 0; i--){
            if(dp[i][w] != dp[i-1][w]){
                selectedPlaces.add(places.get(i-1));
                w -= places.get(i-1).cost;
                if(selectedPlaces.size()==days){
                    break;
                }
            }
        }
        return selectedPlaces;
    }



    private static void printItinerary(List<Place>selectedPlaces){
        System.out.println("Your Itinerary:");
        for (int i = 0; i< selectedPlaces.size();i++){
            System.out.println("Day" + (i+1) + ": " + selectedPlaces.get(i).name + "Cost " + selectedPlaces.get(i).cost + ", Rating: " + selectedPlaces.get(i).rating + ")" );
        }

    }


    public static void main (String args[]){
        Scanner scanner =  new Scanner(System.in);


        System.out.println("Please Enter your budget: ");
        int budget  = scanner.nextInt();

        System.out.println("Please Enter the number of days in your vacation");
        int days = scanner.nextInt();

        // Create a List of Places with cost and rating
        List<Place> places = new ArrayList<>();
        places.add(new Place("Crisologo Museum",300,70));
        places.add(new Place("Bantay Bell Tower",450,90));
        places.add(new Place("Bacaui Park",350,80));
        places.add(new Place("Calle Crisologo",500,90));
        places.add(new Place("Polo Beach",480,75));

        List<Place> selectedPlaces = selectPlaces(places, budget, days);
        printItinerary(selectedPlaces);
    }



}
