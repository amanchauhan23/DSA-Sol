// #1 Recursive
class Solution {
    public int change(int amount, int[] coins) { // O(n^m)
        return rec(amount, coins, coins.length);
    }
    
    int rec(int sum, int[] coins, int i){
        
        if(i == 0 || sum < 0)
            return 0;
        if(sum == 0)
            return 1;
        
        return rec(sum - coins[i - 1], coins, i) + rec(sum, coins, i - 1);
    }
}


// #2 Memoization + Recursion || Top-up
class Solution {
    public int change(int amount, int[] coins) { // O(mn) | O(mn)
        
        int [][] memo = new int[amount][coins.length];
        for(int i[] : memo)
            Arrays.fill(i, -1);
        
        return rec(amount, coins, coins.length, memo);
    }
    
    int rec(int sum, int[] coins, int i, int memo[][]){
        
        if(i == 0 || sum < 0)
                return 0;
        if(sum == 0)
                return 1;
        
        if(memo[sum - 1][i - 1] == -1)
            memo[sum - 1][i - 1] = rec(sum - coins[i - 1], coins, i, memo) + rec(sum, coins, i - 1, memo);
        
        return  memo[sum - 1][i - 1];
    }
}


// #3 Tabulation || Bottom-up O(mn) | O(mn)
class Solution {
    public int change(int amount, int[] coins) {
        int [][] tab = new int[amount + 1][coins.length + 1];
        
        for(int i = 0; i < tab[0].length; i++)
            tab[0][i] = 1;
        
        for(int i = 1; i < tab.length; i++){
            for(int j = 1; j < tab[0].length; j++){
                tab[i][j] = tab[i][j - 1];
                if(i >= coins[j - 1])
                    tab[i][j] += tab[i - coins[j - 1]][j];
            }
        }
        return tab[amount][coins.length];
    }
}
