public class Percolation {
    private boolean[][] tr;
    private int[][] sz;
    private int[][] id;
    private int width = 0;
    private int open = 0;
    public Percolation(int n)
   {
       width = n;
       tr = new boolean[n + 1][n];
       int t = 0;
       for (int i = 1; i <= n+1; i++) {
           for (int ii = 1; ii <= n; ii++) {
               tr[i][ii] = false;
               sz[i][ii] = 1;
               id[i][ii] = t;
               t++;
               //Creates grid. Id is a increasing number, 0 to (Math.pow(n, 2)-1).
           }
       }
       for (int i = 0; i < n; i++) {
           int p = id[(n-1)][i];
           int q = id[n][1];
           union(p, q);
           //Creates an imaginary element. It is connected to all the top sites.
       }
       for (int i = 0; i < n; i++) {
        int p = id[0][i];
        int q = id[n][0];
        union(p, q);
        //Same thing as above, only connected to all the bottom sites.
    }
   }
   public    void open(int row, int col)    // open site (row, col) if it is not open already
   {
       row--;
       col--;
       if (tr[row][col] == false) {
           tr[row][col] = true;
           open++;
       }
       canUnion(row, col);
   }
   public boolean isOpen(int row, int col)  // is site (row, col) open?
   {
       row--;
       col--;
       return tr[row][col] == true;
   }
   public boolean isFull(int row, int col)  // is site (row, col) full?
   {
       row--;
       col--;
       return connected(id[width][0], id[row][col]);
   }
   public     int numberOfOpenSites()       // number of open sites
   {
       return open;
   }
   public boolean percolates()              // does the system percolate?
   {
       return connected(id[width][0], id[width][1]);
   }

   private void canUnion(int row, int col) 
   {
    if (tr[row-width][col] == true) 
    {
        union(id[row-width][col], id[row][col]);
    }
    if (tr[row+width][col] == true) 
    {
        union(id[row+width][col], id[row][col]);
    }
    //These two commands check if the two (vertically) adjacent slots are open. If so, they create a union.
        if (tr[row][col - 1] == true) 
        {
          union(id[row][col], id[row][col-1]);
        }
        if (tr[row][col+1] == true) 
        {
          union(id[row][col], id[row][col+1]);
        }
   }

   private void union(int p, int q) 
    {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        int coli = i % width;
        int rowi = (i/width) - (i/width) % 1;
        int colj = j % width;
        int rowj = (j/width) - (j/width) % 1;  
        int ii = sz[rowi][coli];
        int jj = sz[rowj][colj];
        if (ii < jj) { id[rowi][coli] = j; sz[rowj][colj]+=sz[rowi][coli]; }
        else {id[rowj][colj] = i; sz[rowi][coli]+=sz[rowj][colj]; }
    }

    private int root(int i)
    {
        int coli = i % width;
        int rowi = (i/width) - (i/width) % 1;
        while (i != id[rowi][coli]) 
        {
            id[rowi][coli] = id[rowi][coli];
            i = id[rowi][coli];
        }
        return i;
    }

    private boolean connected(int p, int q) 
    {
        return root(p) == root(q);
    }

/*
    private int[] convert(int i)
    {
        int[] re = new int[2];
        int coli = (i % width) + 1;
        if (i % width == 0) {int rowi = ((i/width) - (i/width) % 1) + 1; }
        else {int rowi = ((i/width) - (i/width) % 1); };
        //re = new int[]{rowi, coli};
        return re;
    }
*/
/*
   public static void main(String[] args)   // test client (optional) 
   {
       Percolation name = new Percolation();
       name.Percolation(3);
       name.union(1, 1);
       boolean z = name.isOpen(1, 1);
       System.out.println(z);
   }
   */
}