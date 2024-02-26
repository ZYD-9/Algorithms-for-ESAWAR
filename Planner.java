import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Planner {


    private static List<Place> selectPlaces (List<Place> places,int budget, int days) {
        int N = places.size();
        int dp[][] = new int[N + 1][budget + 1];

        for (Place place: places){
            for(int d = days;d>0;d--){
                for (int w = budget;w>=place.cost;w--){
                    dp[d][w] = Math.max(dp[d][w],place.rating + dp[d-1][w-place.cost]);
                }
            }
        }

        List<Place>selectedPlaces = new ArrayList<>();
        int d = days;
        int w = budget;

        while (d>0 && w>0){
            if(dp[d][w]!=dp[d-1][w]){
                selectedPlaces.add(places.get(d-1));
                w-=places.get(d-1).cost;
                d--;
            }
            else{
                d--;
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
