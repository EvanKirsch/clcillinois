import java.io.*;
import java.util.*;

public class BinarySearchTree {
    //Implements a binary search tree of ints stored in a random access file.
    //Duplicates are recorded by a count field associated with the int
    final int CREATE = 0;
    final int REUSE = 1;
    private RandomAccessFile f;
    long root; //address of first node
    long free; //address of first free node

    private class Node {
        private long address;
        private int data;
        private int count;
        private long left;
        private long right;

        private Node(long addr, long L, int d, long r) {
            address = addr;
            data = d;
            count = 0;
            right = r;
            left = L;
        }

        private Node(long addr) throws IOException {
            f.seek(addr);
            address = f.readLong();     //8 bytes
            data = f.readInt();         //4 bytes
            count = f.readInt();        //4 bytes
            right = f.readLong();       //8 bytes
            left = f.readLong();        //8 bytes
        } //32 total bytes

        private void writeNode(long addr) throws IOException {
            f.seek(addr);
            f.writeLong(address);
            f.writeInt(data);
            f.writeInt(count);
            f.writeLong(right);
            f.writeLong(left);
        }

        private String stringNode() { //for debugging
            String ans = this.address + "[";     //printing address
            ans = ans + this.data + " (";        //printing data
            ans = ans + this.count + ") : L";    //printing count
            ans = ans + this.left + "  R";       //printing right
            ans = ans + this.right + "]\n";      //printing left
            return ans;
        }

    }//end node class

    public BinarySearchTree(String fname, int mode) throws IOException { // mode==0 means create, mode==1 means Reuse
        File path = new File(fname);
        if(path.exists() && mode == 0){
            System.out.println("file path delete: " + path.delete());
        }
        f = new RandomAccessFile(fname, "rw");
        if(mode == 0){
            root = 0;
            free = 0;
            f.writeLong(root);
            f.writeLong(free);
        }
        else{
            f.seek(0);
            root = f.readLong();
            free = f.readLong();
        }
        //if mode is CREATE a new file is made elif file already exists, file is deleted and new empty file is made
        //if mode is REUSE use existing file
    }

    public void insert(int d) throws IOException {
        if(root == 0) {
            Node first = new Node(16, 0, d, 0);
            first.count++;
            root = 16;
            first.writeNode(this.getFree());
            return;
        }
        Node r = new Node(root);
        r = insert(r, d);
        r.writeNode(r.address);
    }

    private Node insert(Node r, int d) throws IOException {
        if(r.data > d){
            Node left;
            if(r.left == 0){
                left = new Node(this.getFree(),0,d,0);
            }
            else{
                left = new Node(r.left);
            }
            r.left = left.address;
            r.writeNode(r.address);
            left.writeNode(left.address);
            insert(left,d);
        } else if(r.data < d){
            Node right;
            if(r.right == 0){
                right = new Node(this.getFree(),0,d,0);
            }
            else{
                right = new Node(r.right);
            }
            r.right = right.address;
            r.writeNode(r.address);
            right.writeNode(right.address);
            insert(right,d);
        } else {
            r.count++;
            r.writeNode(r.address);
        }
        return r;
    }

    public int find(int d) throws IOException {
        return find(root,d);
    }

    private int find(long addr, int d) throws IOException{
        if (addr == 0){
            return 0;
        }
        Node temp = new Node(addr);
        if(temp.data > d){
            return find(temp.left,d);
        }
        if(temp.data < d){
            return find(temp.right,d);
        }
        return temp.count;
    }

    public void removeOne(int d) throws IOException {
        if(root==0){
            return;
        }
        Node r = new Node(root);
        while(r.right != 0 || r.left != 0) {
            if (r.data > d) {
                Node left = new Node(r.left);
                r = left;
            } else if (r.data < d) {
                Node right = new Node(r.right);
                r = right;
            } else {
                r.count--;
                if (r.count > 0) {
                    r.writeNode(r.address);
                    return;
                } else{
                    removeAll(d);
                    return;
                }
            }
        } if(r.data == d){
            r.count--;
            if (r.count > 0) {
                r.writeNode(r.address);
                return;
            } else{
                removeAll(d);
                return;
            }
        }
    }

    public void removeAll(int d) throws IOException {
        if(root==0){
            return;
        }
        Node r = new Node(root); //parent node thats pointer needs to be assigned
        Node ptr = r;
        while(r.right != 0 || r.left != 0) {
            if(r.data == 0){
                System.out.println("wtf");
                System.out.println(ptr.stringNode());
                return;
            }
            else if (r.data > d) {
                Node left = new Node(r.left);
                ptr = r;
                r = left;
            } else if (r.data < d) {
                Node right = new Node(r.right);
                ptr = r;
                r = right;
            } else{
                remove(ptr,r);
                return;
            }
        }
        if(r.data == d){
            remove(ptr,r);
            return;
        }
    }

    public void close() throws IOException {
        f.seek(0);
        f.writeLong(root);
        f.writeLong(free);
        f.close();
    }

    public void print() throws IOException{                     //used for debugging, more detailed print (calls stringNode() recursivly)
        String ans = "";
        f.seek(0);
        ans = ans + "root: " + root;
        ans = ans + " free: " + free + "\n";
        long addr = 16;
        Node temp;
        while((f.length() - addr) / 32 > 0){
            temp = new Node(addr);
            ans = ans + temp.stringNode();
            addr += 32;
        }
        System.out.println(ans);
    }

    private long getFree() throws IOException{              //returns first free value or the last postion in the file
        if( free != 0 ){                                    //if no free nodes are avalable
            long temp = free;
            Node fr = new Node(temp);
            free = fr.right;
            return temp;
        }
        else{
            return f.length();
        }
    }

    private void remove(Node parent, Node remove) throws IOException{//takes parent, node removing and removes node handles cases of no children, one child and two children
        if(remove.right == 0 && remove.left == 0){
            if(parent.data < remove.data){
                parent.right = 0;
            }
            else{
                parent.left = 0;
            }
            parent.writeNode(parent.address);
            remove.right = free;
            remove.left = 0;
            remove.count = 0;
            remove.data = 0;
            remove.writeNode(remove.address);
            free = remove.address;
            return;
        } else if(remove.right == 0 && remove.left != 0){
            if(parent.data < remove.data){
                parent.right = remove.left;
            }
            else{
                parent.left = remove.left;
            }
            parent.writeNode(parent.address);
            remove.right = free;
            remove.left = 0;
            remove.count = 0;
            remove.data = 0;
            remove.writeNode(remove.address);
            free = remove.address;
            return;
        } else if(remove.right != 0 && remove.left == 0){
            if(parent.data < remove.data){
                parent.right = remove.right;
            }
            else{
                parent.left = remove.right;
            }
            parent.right = remove.right;
            parent.writeNode(parent.address);
            remove.right = free;
            remove.left = 0;
            remove.count = 0;
            remove.data = 0;
            remove.writeNode(remove.address);
            free = remove.address;
            return;
        } else{
            boolean imLeft = true;
            Node target = new Node(remove.left);
            while (target.right != 0){
                target = new Node(target.right);                              //target is the farthest right node in the left tree
                imLeft = false;
            }//have node replacing with                                      //target needs to be removed and it should overwrite remove
            int data = target.data;                                         //saves data of target
            int count = target.count;                                       //saves count of target
            long left = target.left;                                        //saves left of target incase target is the imediate left of remove
            removeAll(target.data);
            if(imLeft){
                remove.left = left;
            }
            remove.data = data;
            remove.count = count;
            remove.writeNode(remove.address);
            return;
        }
    }

    public void dataPrint() throws IOException{             //for debugging
        Node r = new Node(root);
        System.out.println("");
        dataPrint(r);
        System.out.println("");
    }

    private void dataPrint(Node r) throws IOException{                  //for debugging (called recursivly)
        if(r.left != 0){
            Node left = new Node(r.left);
            dataPrint(left);
        }
        System.out.print(r.data + ", ");
        if(r.right != 0){
            Node right = new Node(r.right);
            dataPrint(right);
        }
    }

    public static void main(String[] args) throws IOException {
        BinarySearchTree test = new BinarySearchTree("test.txt",0);
        System.out.println("----New File/Insert Tests----");
        test.insert(59);
        test.insert(27);
        test.insert(19);
        test.dataPrint();
        test.close();

        System.out.println("----Overwrite File/Insert Tests----");
        test = new BinarySearchTree("test.txt",0);
        System.out.println("----Beginning----");
        test.print();
        System.out.println("----End----");
        test.insert(59);
        test.insert(27);
        test.insert(19);
        test.insert(69);
        test.insert(68);
        test.insert(57);
        test.insert(18);
        test.insert(45);
        test.insert(10);
        test.insert(345);
        test.dataPrint();
        test.close();

        System.out.println("----Reuse File/Insert Tests----");
        test = new BinarySearchTree("test.txt",1);
        test.insert(19);
        test.insert(54);
        test.insert(83);
        test.insert(69);
        test.insert(96);
        test.dataPrint();

        System.out.println("----Find Tests----");
        System.out.println("59  == 1 : " + (test.find(59) == 1));
        System.out.println("18  == 1 : " + (test.find(18) == 1));
        System.out.println("19  == 2 : " + (test.find(19) == 2));
        System.out.println("69  == 2 : " + (test.find(69) == 2));
        System.out.println("1   == 0 : " + (test.find(1) == 0));
        System.out.println("600 == 0 : " + (test.find(600) == 0));
        System.out.println("53  == 0 : " + (test.find(53) == 0));
        System.out.println("54  == 1 : " + (test.find(54) == 1));

        System.out.println("----Remove Tests----");
        System.out.print("base");
        test.dataPrint();
        System.out.print("345 removed");
        test.removeAll(345);
        test.dataPrint();
        System.out.print("96 removed");
        test.removeAll(96);
        test.dataPrint();
        System.out.print("one 19 removed");
        test.removeOne(19);
        test.dataPrint();
        System.out.print("19 removed");
        test.removeOne(19);
        test.dataPrint();
        System.out.print("nothing removed 1");
        test.removeOne(999);
        test.dataPrint();
        System.out.print("nothing removed 2");
        test.removeAll(999);
        test.dataPrint();
        System.out.print("nothing removed 3");
        test.removeAll(1);
        test.dataPrint();
        System.out.print("nothing removed 4");
        test.removeOne(1);
        test.dataPrint();
        System.out.print("27 removed");
        test.removeAll(27);
        test.dataPrint();
        System.out.print("one 69 removed");
        test.removeOne(69);
        test.dataPrint();
        System.out.print("69 removed");
        test.removeOne(69);
        test.dataPrint();
        System.out.println("----Insert after Remove Tests----");
        test.insert(345);
        test.insert(96);
        test.dataPrint();
        System.out.println("----Remove Root Tests----");
        test.removeAll(59);
        test.dataPrint();
        test.close();
    }
}
