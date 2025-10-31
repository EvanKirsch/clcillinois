import	java.io.*;
import	java.util.*;

public class TopologicalSort {
    //Adjacency list representation of a directed graph
    private class VertexNode {
        private String name;
        private VertexNode nextV;
        private EdgeNode edges;
        private int indegree;
        private VertexNode(String n, VertexNode v) {
            name = n;
            nextV = v;
            edges = null;
            indegree = 0;
        }

        private int compareTo(String n){
            return this.name.compareTo(n);             //neg if node called on is before node n
        }

    }
    private class EdgeNode {
        private VertexNode vertex1;
        private VertexNode vertex2;
        private EdgeNode nextE;
        private EdgeNode(VertexNode v1, VertexNode v2,EdgeNode e) {
            vertex1 = v1;
            vertex2 = v2;
            nextE = e;
        }
    }


    private VertexNode verEces;
    private int numVerEces;
    public TopologicalSort() {
        verEces = null;
        numVerEces = 0;
    }

    public void addVertex(String s) {
        //PRE: the vertex list is sorted in ascending order using the name as the key
        //POST:a vertex with names has been added to the vertex list and the vertex list
        if(verEces == null){
            verEces = new VertexNode(s,null);
            numVerEces++;
            return;
        }
        VertexNode tempV = verEces;
        VertexNode ptr = tempV;
        while(tempV.nextV != null){
            if(tempV.compareTo(s) > 0) {
                if(tempV.equals(verEces)) {
                    VertexNode newStart = new VertexNode(s, verEces);
                    verEces = newStart;
                    numVerEces++;
                    return;
                }
                VertexNode newNext = ptr.nextV;
                VertexNode insert = new VertexNode(s, newNext);
                ptr.nextV = insert;
                numVerEces++;
                return;
            }
            ptr = tempV;
            tempV = tempV.nextV;
        }
        if(tempV.compareTo(s) > 0) {
            if(tempV == ptr) {
                VertexNode newNext = verEces;
                verEces = new VertexNode(s,newNext);
                numVerEces++;
                return;
            }
            VertexNode newNext = ptr.nextV;
            VertexNode insert = new VertexNode(s, newNext);
            ptr.nextV = insert;
        }
        else {
            VertexNode insert = new VertexNode(s,null);        //add to end of list;
            tempV.nextV = insert;
        }
        numVerEces++;
    }

    public void addEdge(String n1, String n2) {
        VertexNode outNode = findVNode(n1);
        VertexNode inNode = findVNode(n2);
        inNode.indegree++;
        if(outNode.edges == null) {
            outNode.edges = new EdgeNode(outNode, inNode, null); //adds edge to empty list
        }else {
            EdgeNode temp = outNode.edges;
            while(temp.nextE != null) {
                temp = temp.nextE;
            }
            temp.nextE = new EdgeNode(outNode, inNode, null); //adds edge to end of list
        }

        //PRE: the vertices n1 and n2 have already been added
        //POST:the new edge (n1, n2) has been added to the n1 edge list
    }

    public String topoSort() {
        TopologicalSort tempTable = this;
        VertexNode temp;
        String ans = "";
        int c = 0;
        while((temp = tempTable.findZeroInd()) != null) {
            ans += temp.name;
            tempTable.decIndegree(temp);
            c++;
        }
        if(c == numVerEces){
            return ans;
        }
        else{
            return "No topological ordering exists for the graph";
        }

        //if the graph contains a cycle return null
        //otherwise return a string containing the names of vertices separated by blanks
        //in topological order.
    }

    private VertexNode findZeroInd() {
        VertexNode tempV = verEces;
        while(true) {
            if(tempV.indegree == 0) {
                tempV.indegree--;           //so that we never look at it again;
                return tempV;
            }
            if(tempV.nextV == null) {
                return null;
            }
            tempV = tempV.nextV;
        }
    }

    private void decIndegree(VertexNode n) {
        if(n.edges == null) {
            return;
        }
        EdgeNode tempE = n.edges;
        while(tempE.nextE != null) {
            tempE.vertex2.indegree--;
            tempE = tempE.nextE;
        }
        tempE.vertex2.indegree--;
    }


    private void readVertexNodes(String line){
        String node = "";
        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) == ' ' || i == line.length()-1){
                if(i == line.length()-1) {
                    node += line.charAt(i);
                    addVertex(node);
                    return;
                }
                addVertex(node);
                node = "";
            }else{
                node += line.charAt(i);
            }
        }
    }

    private void readEdge(String line){
        String tempN = "";
        String n1 = "";
        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) == ' '){
                n1 = tempN;
                tempN = "";
            }else if(i == line.length()-1){
                tempN += line.charAt(i);
                addEdge(n1,tempN);
            }else {
                tempN += line.charAt(i);
            }
        }
    }

    private VertexNode findVNode( String s ) {              //searches vertices to find one with name s
        if(verEces == null) {
            return null;
        }
        VertexNode temp = verEces;
        while(true){
            if(temp.name.equals(s)) {
                return temp;
            }
            if(temp.nextV == null) {
                return null;
            }
            temp = temp.nextV;
        }
    }

    public String toString() {
        String ans = "===String Rep of Graph===\n";
        VertexNode tempV = verEces;
        while(true) {
            ans += "[" + tempV.name +"] "+ tempV.indegree + ": ";
            if(tempV.edges != null) {
                EdgeNode tempE = tempV.edges;
                while(tempE.nextE != null) {
                    ans += tempE.vertex2.name + ", ";
                    tempE = tempE.nextE;
                }
                ans += tempE.vertex2.name + "\n";
            }else {
                ans += "\n";
            }
            if(tempV.nextV == null) {
                ans += "Number of Vertices: " + numVerEces;
                return ans;
            }
            tempV = tempV.nextV;
        }
    }


    public static void main(String args[]) throws IOException{
        //see problem statement
        TopologicalSort t = new TopologicalSort();
        Scanner sc = new Scanner(System.in);
        System.out.print("File Name: ");
        String fn = sc.nextLine();
        FileReader fileReader = new FileReader(fn);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        t.readVertexNodes(bufferedReader.readLine());
        String tempEdge;
        while((tempEdge = bufferedReader.readLine()) != null) {
            t.readEdge(tempEdge);
        }

        System.out.println(t.toString());
        System.out.println(t.topoSort());
    }
}
