public class Percolation2
{
    /*-------------------
    * Name: Shreya
    * Date: 28 June 2018

    * Compliation: javac Percolation.java
    * Excecution: java Percolation

    * Currently, I'm unaware of a commonplace way of excecuting code. 
    * I have a sample code and I am trying to turn in the code. 
    * This uses Standard Input. simply input the size as an int and an int[] of the next points.
    -------------------*/
    private int[] id;
    private int[] sz;
    private boolean[] tr;

    public void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] i = (scanner.nextInt()) - 1;
        //If i could be an int[].
        //Percolation object = new Percolation(N);
        QuickUnionUF(N);
        for (var ii = 0; ii < i.length; ii++) 
        {
            truth(i[ii]);
            //Changes truth array accordingly
            canUnion(i[ii], N);
            //Makes all possible unions.
            if (allConnected(N)) 
            {
                //Checks if there's a way through
                System.out.println(allConnected(N));
                break;
            }
        }
    }

    public QuickUnionUF(int N)
    {
        id = new int[N+2];
        sz = new int[N+2];
        tr = new boolean[N+2];
        int b = Math.pow(N, 2);
        for (int i = 0; i < N+2; i++)
        {
            id[i] = i;
            sz[i] = 1;
            tr[i] = false;
        }
        for (int c = 0; c < N; c++) {
            union(b, c);
        }
        for (int d = (b-x); d < b; d++) {
            union((b+1), d);
        }
    }

    public void truth(int i) 
    {
        tr[i] = true;
    }

    private int root(int i)
    {
        while (i != id[i]) 
        {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q) 
    {
        return root(p) == root(q);
    }

    public boolean allConnected(int N) 
    {
        int b = Math.pow(N, 2);
        return root(b) == root(b+1);
    }

    private void canUnion(int p, int N) 
    {
        if (truth[p - N] == true) 
        {
            union(p, (p-N));
        }
        if (truth[p+N] == true) 
        {
            union(p, p+N);
        }
        //These two commands check if the two (vertically) adjacent slots are open. If so, they create a union.
        if (p % N == 0) 
        {
            //This checks if it is on the leftmost edge. If it is, it would be incorrect to set p-1 to adjacent, as it is not.
            if (tr[p + 1] == true) 
            {
                union(p, (p+1));
            }
          } else {
          if (p % N == (N-1)) 
          {
              //Same thing as mentioned above, but, this time, it is for the rightmost column.
            if (tr[p - 1] == true) 
            {
              union(p, (p - 1));
            } 
          } else {
              //And, if it not the leftmost or rightmost, it will merely create a union (if open).
            if (tr[p - 1] == true) 
            {
              union(p, (p-1));
            }
            if (tr[p + 1] == true) 
            {
              union(p, (p+1));
            }
          }
          }
    }

    public void union(int p, int q) 
    {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (sz[i] < sz[j]) { id[i] = j; sz[j]+=sz[i]; }
        else {id[j]=i; sz[i]+=sz[j]; }
    }
}