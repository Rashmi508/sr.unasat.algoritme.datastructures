package linear.binary.search.search;


 class Stack {

    int top;
    int Max = 10;
    int[] arr;

    public Stack() { // constructor
        arr = new int[Max];     // hoeveel elementen je array kan hebben.
        top = -1;   //wijst je dat je nog geen items hebt.
    }

    public void push(int val) {  //als je een item wilt zeggen in je stack
        arr[++top] = val;   //increment
    }

    public int pop() {
        return arr[top--];  //decrement
    }

    public int peek() {
        return arr[top];        //je kan zien wat boven is.
    }

    public boolean isEmpty() {
        return (top == -1);     //als er geen items meer zijn.
    }
}



class GRAPH {

    private final int MAX_POSTEN = 10;
    private VeldPost vertexList[];
    private int adjMat[][];
    private int nVeldPosten;
    private Stack s;

    public GRAPH() {
        vertexList = new VeldPost[MAX_POSTEN];
        adjMat = new int[MAX_POSTEN][MAX_POSTEN];
        nVeldPosten = 0;

        for(int j = 0; j < MAX_POSTEN; j++)
            for(int k = 0; k < MAX_POSTEN; k++)
                adjMat[j][k] = 0;   // geeft aan dat er in het begin geen edges geregistreerd zijn.
        s = new Stack();
    }

    public void addVeldPost(String lab) {
        vertexList[nVeldPosten++] = new VeldPost(lab);
    }

    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
    }

    public void displayVeldPost(int v) {
        System.out.print(vertexList[v].label + " ");
    }

    public int getAdjUnvisitedPost (int v) {
        for(int j = 0; j < nVeldPosten; j++) {
            if(adjMat[v][j] == 1 && vertexList[j].wasVisited == false) {
                return j;
            }
        }
        return -1;
    }

    public void dfs() {
        vertexList[0].wasVisited = true;
        displayVeldPost(0);
        s.push(0);

        while(!s.isEmpty()) {                       // zolang de stack niet leeg is, gaat het loopen en visiten.
            int v = getAdjUnvisitedPost(s.peek());

            if(v == -1) {
                s.pop();
            } else {
                vertexList[v].wasVisited = true;
                displayVeldPost(v);
                s.push(v);
            }
        }

        for (int j = 0; j < nVeldPosten; j++)
        vertexList[j].wasVisited = false;
    }
}

 class DFSApp {

    public static void main(String[] args) {

        GRAPH theGraph = new GRAPH();
        theGraph.addVeldPost("Afobaka"); //0
        theGraph.addVeldPost("Brownsweg"); //1
        theGraph.addVeldPost("Apura"); //2
        theGraph.addVeldPost("Moengo"); //3
        theGraph.addVeldPost("Stichting Experimentele Landbouw"); //4
        theGraph.addVeldPost("Nieuw Nickerie"); //5
        theGraph.addVeldPost("Marowijne"); //6
        theGraph.addVeldPost("Wanica"); //7
        theGraph.addVeldPost("Paranam"); //8
        theGraph.addVeldPost("Paramaribo"); //9

        theGraph.addEdge(0, 1);
        theGraph.addEdge(0, 4);
        theGraph.addEdge(1, 2);
        theGraph.addEdge(1, 4);
        theGraph.addEdge(2, 3);
        theGraph.addEdge(3, 4);
        theGraph.addEdge(3, 7);
        theGraph.addEdge(3, 9);
        theGraph.addEdge(4, 5);
        theGraph.addEdge(5, 6);
        theGraph.addEdge(6, 7);
        theGraph.addEdge(6, 8);
        theGraph.addEdge(7, 8);
        theGraph.addEdge(8, 9);


        System.out.println("De veldposten bestaan in: ");
        theGraph.dfs();
        System.out.println();
    }

}

