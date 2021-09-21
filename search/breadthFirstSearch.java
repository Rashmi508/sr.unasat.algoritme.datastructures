package linear.binary.search.search;


class Queue {
    private final int SIZE = 10;
    private int queArray[];
    private int front;
    private int rear;

    public Queue() {
        queArray = new int[SIZE];
        front = 0;  //queue is leeg
        rear = -1;
    }

    public void insert(int j) {
        if(rear == SIZE -1)
            rear = -1;
        queArray[++rear] = j;
    }

    public int remove() {
        int temp = queArray[front++];
        if(front == SIZE)
            front = 0;
        return temp;
    }

    public boolean isEmpty() {
        return (rear + 1 == front || (front + SIZE - 1 == rear));
    }

}

class VeldPost {
    public String label;
    public boolean wasVisited;

    public VeldPost(String post) {
        label = post;
        wasVisited = false;
    }
}

class Graph {

    private final int MAX_POSTEN= 10;
    private VeldPost vertexList[];
    private int adjMat[][];
    private int nVeldPosten;
    private Queue q;

    public Graph() {
        vertexList = new VeldPost[MAX_POSTEN];
        adjMat = new int[MAX_POSTEN][MAX_POSTEN];
        nVeldPosten = 0;

        for(int j = 0; j < MAX_POSTEN; j++)
            for(int k = 0; k < MAX_POSTEN; k++)
                adjMat[j][k] = 0;
            q = new Queue();
    }

    public void addVeldPost(String lab) {
        vertexList[nVeldPosten++] = new VeldPost(lab);
    }

    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
    }

    public void displayVertex(int vert) {   //de vertex op een bepaald index displayed.
        System.out.print(vertexList[vert].label + " ");
    }

    public int getAdjUnvisitedPost (int v) {
        for(int j=0; j<nVeldPosten; j++) {
            if(adjMat[v][j]==1 && vertexList[j].wasVisited==false) {
                return j;
            }
        }
        return -1;
    }

    public void bfs() {
        vertexList[0].wasVisited = true;
        displayVertex(0);
        q.insert(0);
        int vert2;

        while(!q.isEmpty()) {
            int vert1 = q.remove();
            while((vert2=getAdjUnvisitedPost(vert1)) != -1) {
                vertexList[vert2].wasVisited = true;
                displayVertex(vert2);
                q.insert(vert2);
            }
        }

        for (int j = 0; j < nVeldPosten; j++)
            vertexList[j].wasVisited = false;
    }
}

 class BFSApp {

    public static void main(String[] args) {

        Graph gr = new Graph();
        gr.addVeldPost("Afobaka"); //0
        gr.addVeldPost("Brownsweg"); //1
        gr.addVeldPost("Apura"); //2
        gr.addVeldPost("Moengo"); //3
        gr.addVeldPost("Stichting Experimentele Landbouw"); //4
        gr.addVeldPost("Nieuw Nickerie"); //5
        gr.addVeldPost("Marowijne"); //6
        gr.addVeldPost("Wanica"); //7
        gr.addVeldPost("Paranam"); //8
        gr.addVeldPost("Paramaribo"); //9

        gr.addEdge(0, 1);
        gr.addEdge(0, 4);
        gr.addEdge(1, 2);
        gr.addEdge(1, 4);
        gr.addEdge(2, 3);
        gr.addEdge(3, 4);
        gr.addEdge(3, 7);
        gr.addEdge(3, 9);
        gr.addEdge(4, 5);
        gr.addEdge(5, 6);
        gr.addEdge(6, 7);
        gr.addEdge(6, 8);
        gr.addEdge(7, 8);
        gr.addEdge(8, 9);


        System.out.println("De veldposten die adjacent aan elkaar zijn:  ");
        gr.bfs();
        System.out.println();
    }

}