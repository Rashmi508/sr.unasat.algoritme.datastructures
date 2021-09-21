package linear.binary.search.dijkstra.algoritme;

import java.util.Scanner;

class weightParent {

    public int weight;
    public int parentVert;

    public weightParent (int parent, int w) {
        weight = w;
        parentVert = parent;
    }
}


     class GraphH {
        private final int MAX_VERTS = 10;
        private final int INFINITE = 0;
        private Vertex vertexList[];
        private int adjMat[][];
        private int Vertices;
        private int nTree;
        private weightParent sPath[];
        private int currentPost;
        private int startToCurrent;


        public GraphH() {
            vertexList = new Vertex[MAX_VERTS];
            adjMat = new int[MAX_VERTS][MAX_VERTS];
            Vertices = 0;
            nTree = 0;

            for (int j = 0; j < MAX_VERTS; j++)
                for (int k = 0; k < MAX_VERTS; k++)
                    adjMat[j][k] = INFINITE;
            sPath = new weightParent[MAX_VERTS];

        }

        public void addVeldPost(String post) {
            vertexList[Vertices++] = new Vertex(post);
        }

        public void addEdge(int start, int end, int weight) {
            adjMat[start][end] = weight;
        }


        public void expensivePath(int start) {

            int startTree = start;
            vertexList[startTree].isInTree = true;
            nTree = 1;


            for (int j = 0; j < Vertices; j++) {
                int tempW = adjMat[startTree][j];
                sPath[j] = new weightParent(startTree, tempW);
            }

            while (nTree < Vertices) {
                int indexMax = getMax();
                int maxDist = sPath[indexMax].weight;
                if (maxDist == INFINITE) {

                    System.out.println("De vertices zijn onbereikbaar");
                    break;

                } else {
                    currentPost = indexMax;
                    startToCurrent = sPath[indexMax].weight;

                }

                vertexList[currentPost].isInTree = true;
                nTree++;
                adjust_sPath_longest();
            }

            displayPaths();
            nTree = 0;
            for (int j = 0; j < Vertices; j++)
                vertexList[j].isInTree = false;
        }


        public int getMax() {
            int maxDist = INFINITE;
            int indexMax = 0;

            for (int j = 1; j < Vertices; j++) {
                if (!vertexList[j].isInTree && sPath[j].weight > maxDist) {
                    maxDist = sPath[j].weight;
                    indexMax = j;
                }
            }
            return indexMax;
        }


        public void adjust_sPath_longest() {
            int column = 1;
            while (column < Vertices) {

                if (vertexList[column].isInTree) {
                    column++;
                    continue;
                }


                int currentToFringe = adjMat[currentPost][column];


                int startToFringe = startToCurrent + currentToFringe;


                int sPathDist = sPath[column].weight;


                if (startToFringe > sPathDist && currentToFringe != 0) {

                    sPath[column].parentVert = currentPost;
                    sPath[column].weight = startToFringe;
                }
                column++;
            }
        }

         public int findIndexOf (String veldpost) {
             int item;
             for (item = 0; item < Vertices; item++) {
                 if (vertexList[item].label.equalsIgnoreCase(veldpost))
                     return item;
             }
             return item;
         }

        public void displayPaths() {
            for (int j = 0; j < Vertices; j++) {
                System.out.print(vertexList[j].label + "=");
                if (sPath[j].weight == INFINITE)
                    System.out.print("0");

                else
                    System.out.print(sPath[j].weight);

                String parent = vertexList[sPath[j].parentVert].label;
                System.out.print(" (" + parent + "), ");

            }
            System.out.println("");
        }
    }

     class LongestApp {
        public static void main(String[] args) {
            GraphH G = new GraphH();
            G.addVeldPost("Afobaka"); //0
            G.addVeldPost("Brownsweg"); //1
            G.addVeldPost("Apura"); //2
            G.addVeldPost("Moengo"); //3
            G.addVeldPost("Stichting Experimentele Landbouw"); //4
            G.addVeldPost("Nieuw Nickerie"); //5
            G.addVeldPost("Marowijne"); //6
            G.addVeldPost("Wanica"); //7
            G.addVeldPost("Paranam"); //8
            G.addVeldPost("Paramaribo"); //9

            G.addEdge(0, 1, 100);
            G.addEdge(0, 4, 80);
            G.addEdge(1, 2, 200);
            G.addEdge(1, 4, 40);
            G.addEdge(2, 3, 45);
            G.addEdge(3, 4, 420);
            G.addEdge(3, 7, 300);
            G.addEdge(3, 9, 250);
            G.addEdge(4, 5, 20);
            G.addEdge(5, 6, 100);
            G.addEdge(6, 7, 140);
            G.addEdge(6, 8, 560);
            G.addEdge(7, 8, 90);
            G.addEdge(8, 9, 100);

            Scanner scan = new Scanner(System.in);
            System.out.println("Voer uw huidige locatie in: ");
            String item = scan.next();

            System.out.println("De duurste weg is: ");
            G.findIndexOf(item);
            int userGiven = G.findIndexOf(item);
            G.expensivePath(userGiven);
            System.out.println();
            scan.close();
        }
    }

