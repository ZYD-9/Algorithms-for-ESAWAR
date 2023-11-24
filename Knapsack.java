import java.util.ArrayList;
import java.util.List;

public class Knapsack{

    static int knapsack(int W, int wt[], int val[], int n, List<Integer> selectedItems){
        int i, w;
        int K[][] = new int[n+1][W+1];

        for(i = 0; i<=n;i++){
            for (w=0; w <=W; w++){
                if (i==0 || w==0)
                    K[i][w] = 0;
                else if(wt[i-1]<=w)
                    K[i][w] = Math.max(val[i-1] + K[i-1][w - wt[i-1]],K[i-1][w]);
                else
                    K[i][w] = K[i-1][w];

            }
        }

        i = n;
        w = W;

        while(i>0 && w > 0){
            if(K[i][w] != K[i-1][w]){
                selectedItems.add(i-1);
                w = w - wt[i-1];
            }

            i--;
        }
        return K[n][W]; // this will return the maximum value
    }

    public static void main(String args[]) {
        int val[] = new int[]{60, 100, 120}; // Values of items
        int wt[] = new int[]{10, 20, 30};    // Weights of items
        int W = 70;                          // Capacity of knapsack
        int n = val.length;                  // Number of items
        List<Integer> selectedItems = new ArrayList<>();
        System.out.println("Total value: " + knapsack(W, wt, val, n, selectedItems));

        // Print the indices of selected items
        System.out.println("Selected item indices: " + selectedItems);
    }

}
