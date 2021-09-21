package linear.binary.search.dijkstra.algoritme;

import java.util.Scanner;

class WeightParent{

    public int weight;
    public int parentVertex;

    public WeightParent(int pv, int w){
        this.weight = w;
        this.parentVertex = pv;
    }
}

class Vertex {              // om data op te slaan.
    public String label;    // om vertex te herkennen.
    public boolean isInTree;


    public Vertex(String post) {
        label = post;
        isInTree = false;


    }

}

 class Graphx {
    private final int MAX_POSTEN = 10;
    private final int INFINITY = 1000000;   //edge weights kunnrn niet hoger dan dit berdag.
    private Vertex vertexList[];
    private int adjMat[][];
    private int nVerts;
    private int Tree;                   // nummer van verts in tree.
    private WeightParent sPath[];      // array voor de data van cheapest path.
    private int currentPost;
    private int startToCurrent;      // distance tot current post.

    public Graphx(){
        vertexList = new Vertex[MAX_POSTEN];
        adjMat = new int[MAX_POSTEN][MAX_POSTEN];
        nVerts = 0;
        Tree = 0;

        for(int j=0; j<MAX_POSTEN; j++)
            for(int k=0; k<MAX_POSTEN; k++)
                adjMat[j][k] = INFINITY;       // adjacency matrix is infinity.
        sPath = new WeightParent[MAX_POSTEN];

    }

    public void addVeldpost(String post) {
        vertexList[nVerts++] = new Vertex(post);
    }

    public void addEdge(int start, int end, int weight) {
        adjMat[start][end] = weight;
    }

    public void cheapestpath(int start){

        int startTree = start;  //begint dus waar aangegeven is.
        vertexList[startTree].isInTree = true;
        Tree = 1;


        for(int j=0; j<nVerts; j++) {  // itereren over aantal vertices.
            int tempW= adjMat[startTree][j];
            sPath[j] = new WeightParent(startTree, tempW);
        }

        while(Tree < nVerts) {
            int indexMin = getMin();
            int minDist = sPath[indexMin].weight;

            if(minDist == INFINITY)
            {
                System.out.println("De vertices zijn onbereikbaar");
                break;
            }
            else
            {
                currentPost = indexMin;
                startToCurrent = sPath[indexMin].weight;  //distance van parent tot closest vert.
            }

            vertexList[currentPost].isInTree = true;
            Tree++;

            adjust_sPath();
        }

        displayPaths();
        Tree = 0;
        for(int j=0; j < nVerts; j++)
            vertexList[j].isInTree = false;
    }

    public int getMin() {
        int minDistance = INFINITY;
        int indexMin = 0;

        for(int j=1; j<nVerts; j++){

            if( !vertexList[j].isInTree && sPath[j].weight < minDistance ){
                minDistance = sPath[j].weight;
                indexMin = j;
            }
        }
        return indexMin;

    }

    public void adjust_sPath() {
        int column = 1;
        while(column < nVerts)

        {

            if( vertexList[column].isInTree )
            {
                column++;
                continue;
            }

            int currentToFringe = adjMat[currentPost][column]; //current vert na die kolom.

            int startToFringe = startToCurrent + currentToFringe; //claculatie van de vorige naar de huidige.

            int sPathDist = sPath[column].weight;

            if(startToFringe < sPathDist)
            {

                sPath[column].parentVertex = currentPost;
                sPath[column].weight = startToFringe;
            }
            column++;
        }
    }

    public int findIndexOf (String veldpost) {
        int item;
        for (item = 0; item < nVerts; item++) {
            if (vertexList[item].label.equalsIgnoreCase(veldpost))
                return item;
        }
        return item;
    }

    public void displayPaths() {
        for(int j=0; j<nVerts; j++) {
            System.out.print(vertexList[j].label + "=");

            if(sPath[j].weight == INFINITY)
                System.out.print("0");

            else
                System.out.print(sPath[j].weight);

            String parent = vertexList[ sPath[j].parentVertex ].label;
            System.out.print(" <-- " + parent + ", ");
        }
        System.out.println("");
    }

}

class PathApp {
    public static void main(String[] args) {
        Graphx g = new Graphx();
        g.addVeldpost("Afobaka"); //0
        g.addVeldpost("Brownsweg"); //1
        g.addVeldpost("Apura"); //2
        g.addVeldpost("Moengo"); //3
        g.addVeldpost("Stichting Experimentele Landbouw"); //4
        g.addVeldpost("Nieuw Nickerie"); //5
        g.addVeldpost("Marowijne"); //6
        g.addVeldpost("Wanica"); //7
        g.addVeldpost("Paranam"); //8
        g.addVeldpost("Paramaribo"); //9

        g.addEdge(0, 1, 100);
        g.addEdge(0, 4, 80);
        g.addEdge(1, 2, 200);
        g.addEdge(1, 4, 40);
        g.addEdge(2, 3, 45);
        //G.addEdge(2, 8, 350);
        //G.addEdge(2, 9, 250);
        g.addEdge(3, 4, 420);
        g.addEdge(3, 7, 300);
        g.addEdge(3, 9, 250);
        g.addEdge(4, 5, 20);
        //G.addEdge(4, 6, 60);
        //G.addEdge(4, 7, 250);
        g.addEdge(5, 6, 100);
        g.addEdge(6, 7, 140);
        g.addEdge(6, 8, 560);
        g.addEdge(7, 8, 90);
        g.addEdge(8, 9, 100);

        Scanner scan = new Scanner(System.in);
        System.out.println("Voer uw huidige locatie in: ");
        String start = scan.next();

        System.out.println("De goedkoopste weg is: ");
        g.findIndexOf(start);
        int userGiven = g.findIndexOf(start);
        g.cheapestpath(userGiven);

        System.out.println();
        scan.close();
    }
}
