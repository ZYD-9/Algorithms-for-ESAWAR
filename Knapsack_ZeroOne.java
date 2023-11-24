import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Knapsack_ZeroOne {

    private int[] value; // this represents quality, ratings from customers and user preferences
    private int[] Newweights; // this represents the budget or amount


    public Knapsack_ZeroOne(int[] value,int[] Newweights){
      this.value = value;
      this.Newweights = Newweights;
    }

    public List<Integer> solveKnapsack(int scope){

        int[][] cacheTable = new int[value.length + 1][scope + 1];

        for (int i = 1;i<value.length;i++) {
            for (int j = 1; j <= scope; j++) {
                if (Newweights[i-1] > j) {
                    cacheTable[i][j] = cacheTable[i-1][j];
                }
                else{
                    cacheTable[i][j] = Math.max(cacheTable[i-1][j], value[i-1] + cacheTable[i-1][j-Newweights[i-1]]);
                }
            }
        }

        List<Integer> selectedItems = new ArrayList<>();
        int i = value.length;
        int j = scope;

        while(i > 0 && j > 0){
            if(cacheTable[i][j]!=cacheTable[i-1][j]){
                selectedItems.add(i-1);
                j-=Newweights[i-1];
            }
            i--;
        }
        return selectedItems;
    }

    public static int[] weightsWithDays(int[] arr, int numofPeople){

        int [] weightsDays = new int[arr.length];
        for(int i = 0;i< arr.length;i++){
            weightsDays[i] = arr[i] * numofPeople;

        }

        return weightsDays;
    }

    public static void main(String args[]) {
        int[] values = {100, 20, 60, 40};
        int[] weights = {3, 2, 4, 1};



        System.out.println("Item 1 is PHP 3.00 , item 2 is PHP 2.00,item 3 is PHP 4.00,item 4 is PHP 1.00");

        System.out.println("Input Amount");
        Scanner scan = new Scanner(System.in);
        int user_budget = scan.nextInt();
        int  people = scan.nextInt();
        int[] Newweights = weightsWithDays(weights, people);
        Knapsack_ZeroOne testData = new Knapsack_ZeroOne(values, Newweights);
        List<Integer> selectedIndices = testData.solveKnapsack(user_budget);
        System.out.print("Recommended Items: ");
        for (int index : selectedIndices) {
            System.out.print("Item " + (index + 1) + " ");
        }
    }

}
