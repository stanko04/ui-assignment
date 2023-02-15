//import com.sun.tools.javac.Main;

import java.util.*;

public class Tree {
    private Node root = null;
    private Node end = null;

    private static class Node{
        String[][] value;
        Node left;
        Node right;
        Node up;
        Node down;
        Node parent;
        String move;
        int heuristicValue;

        public Node(String[][] value, Node left, Node right, Node up, Node down, Node parent) {
            this.value = value;
            this.left = left;
            this.right = right;
            this.up = up;
            this.down = down;
            this.parent = parent;
        }
    }

    // method for move up
    public static String[][] moveUp(String[][] array) {
        String[][] copy = copy(array);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(array[i][j].equals("*")) {
                    if(i != 0) {
                        String tmp = copy[i][j];
                        copy[i][j] = copy[i-1][j];
                        copy[i-1][j] = tmp;
                        return copy;
                    }
                }
            }
        }
        return null;
    }

    // method for move down
    public static String[][] moveDown(String[][] array) {
        String[][] copy = copy(array);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(copy[i][j].equals("*")) {
                    if(i != 2) {
                        String tmp = copy[i][j];
                        copy[i][j] = copy[i+1][j];
                        copy[i+1][j] = tmp;
                        return copy;
                    }
                }
            }
        }
        return null;
    }

    // method for move right
    public static String[][] moveRight(String[][] array) {
        String[][] copy = copy(array);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(copy[i][j].equals("*")) {
                    if(j != 2) {
                        String tmp = copy[i][j];
                        copy[i][j] = copy[i][j+1];
                        copy[i][j+1] = tmp;
                        return copy;
                    }
                }
            }
        }
        return null;
    }

    // method for move left
    public static String[][] moveLeft(String[][] array) {
        String[][] copy = copy(array);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(copy[i][j].equals("*")) {
                    if(j != 0) {
                        String tmp = copy[i][j];
                        copy[i][j] = copy[i][j-1];
                        copy[i][j-1] = tmp;
                        return copy;
                    }
                }
            }
        }
        return null;
    }

    // method for copy 2D array
    public static String[][] copy(String[][] src) {
        if (src == null) {
            return null;
        }
        return Arrays.stream(src).map(String[]::clone).toArray(String[][]::new);
    }

    // method for do work in first heuristic
    public void doAllStuff(Node node, Node end, HashSet<List> set, int createdNodes, int typeOfHeuristic) {
        // initialize int arraylist for first heuristic
        ArrayList<Integer> heuristicArrayList = new ArrayList<Integer>();

        // left move
        String[][] left = moveLeft(node.value);
        if(left != null) {
            if(set.add(twoDArrayToList(left))) {
                node.left = new Node(left, null, null, null, null, node);
                node.left.move = "left";
//                System.out.println("left");
//                HelpClass.printArray2D(node.left.value);
                if(typeOfHeuristic == 1) {
                    node.left.heuristicValue = firstHeuristic(node.left.value, end.value);
                }
                else {
                    node.left.heuristicValue = secondHeuristic(node.left.value, end.value);
                }
//                System.out.println(node.left.firstHeuristic);
                heuristicArrayList.add(node.left.heuristicValue);
                createdNodes++;
            }
        }
        else {
            node.left = null;
        }

        // up move
        String[][] up = moveUp(node.value);
        if(up != null) {
            if(set.add(twoDArrayToList(up))) {
                node.up = new Node(up, null, null, null, null, node);
                node.up.move = "up";
//                System.out.println("up");
//                HelpClass.printArray2D(node.up.value);
                if(typeOfHeuristic == 1) {
                    node.up.heuristicValue = firstHeuristic(node.up.value, end.value);
                }
                else {
                    node.up.heuristicValue = secondHeuristic(node.up.value, end.value);
                }
//                System.out.println(node.up.firstHeuristic);
                heuristicArrayList.add(node.up.heuristicValue);
                createdNodes++;
            }
        }
        else {
            node.up = null;
        }

        // right move
        String[][] right = moveRight(node.value);
        if (right != null) {
            if(set.add(twoDArrayToList(right))) {
                node.right = new Node(right, null, null, null, null, node);
                node.right.move = "right";
//                System.out.println("right");
//                HelpClass.printArray2D(node.right.value);
                if(typeOfHeuristic == 1) {
                    node.right.heuristicValue = firstHeuristic(node.right.value, end.value);
                }
                else {
                    node.right.heuristicValue = secondHeuristic(node.right.value, end.value);
                }
//                System.out.println(node.right.firstHeuristic);
                heuristicArrayList.add(node.right.heuristicValue);
                createdNodes++;
            }
        }
        else {
            node.right = null;
        }

        // down move
        String[][] down = moveDown(node.value);
        if(down != null) {
            if(set.add(twoDArrayToList(down))) {
                node.down = new Node(down, null, null, null, null, node);
                node.down.move = "down";
//                System.out.println("down");
//                HelpClass.printArray2D(node.down.value);
                if(typeOfHeuristic == 1) {
                    node.down.heuristicValue = firstHeuristic(node.down.value, end.value);
                }
                else {
                    node.down.heuristicValue = secondHeuristic(node.down.value, end.value);
                }
//                System.out.println(node.down.firstHeuristic);
                heuristicArrayList.add(node.down.heuristicValue);
                createdNodes++;
            }
        }
        else {
            node.down = null;
        }

//        System.out.println("Heuristic arraylist " + heuristicArrayList);
        if(node.left == null && node.up == null && node.right == null && node.down == null) {
            System.out.println("Heuristika " + typeOfHeuristic +" nenasla riesenie");
            return;
        }
        Node bestNodeFirstHeuristic = returnBestNode(heuristicArrayList, node.left, node.up, node.right, node.down);
//        System.out.println("Best node in first heuristic: ");
        assert bestNodeFirstHeuristic != null;
        System.out.println(bestNodeFirstHeuristic.move);
        printArray2D(bestNodeFirstHeuristic.value);
        System.out.println("------------------");

        // if actual best node not equals end node, do this function recursively
        if(!twoDArrayToList(bestNodeFirstHeuristic.value).equals(twoDArrayToList(end.value))) {
            doAllStuff(bestNodeFirstHeuristic, end, set, createdNodes, typeOfHeuristic);
        }
        else{
            System.out.println("Number of created nodes: " + createdNodes);
        }

    }

    // method for create a first heuristic
    public static int firstHeuristic(String[][] node, String[][] endPosition) {
        int value = 0;
        String[] first = new String[9];
        String[] second = new String[9];
        int pom = 0;
        // for loop across two new arrays, and copy values from actual node and end position
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                first[pom] = node[i][j];
                second[pom] = endPosition[i][j];
                pom++;
            }
        }
        // compare first and second array and compare if its value are not equals
        for(int i = 0; i < 9; i++) {
            if(!first[i].equals("*")) {
                if (!first[i].equals(second[i])) {
                    value++;
                }
            }
        }
        return value;
    }

    // method for create a second heuristic
    public static int secondHeuristic(String[][] node, String[][] endPosition) {
        int pom = 1;
        int value = 0;
        while(pom < 9) {
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if (!node[i][j].equals("*")) {
                        if (Integer.parseInt(node[i][j]) == pom) {
                            for (int k = 0; k < 3; k++) {
                                for (int l = 0; l < 3; l++) {
                                    if (!endPosition[k][l].equals("*")) {
                                        if (Integer.parseInt(endPosition[k][l]) == Integer.parseInt(node[i][j])) {
                                            int x = Math.abs(k - i);
                                            int y = Math.abs(l - j);
                                            value = value + (x + y);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            pom++;
        }
        return value;
    }

    // method for return best Node in both heuristic
    // method will return Node with the smallest heuristic value
    public static Node returnBestNode(ArrayList<Integer> arrayList, Node left, Node up, Node right, Node down) {
        int min = Collections.min(arrayList);
        if(left != null && left.heuristicValue == min) {
            return left;
        }
        else if(up != null && up.heuristicValue == min) {
            return up;
        }
        else if(right != null && right.heuristicValue == min) {
            return right;
        }
        else if(down != null && down.heuristicValue == min) {
            return down;
        }
        return null;
    }

    // method for create root node
    public Node addRoot(String[][] value) {
        return new Node(value, null, null,null, null, null);
    }

    // method for create end position node
    public Node addEnd(String[][] value) {
        return new Node(value, null, null,null, null, null);
    }

    // method for convert 2D array into List
    public static List<String> twoDArrayToList(String[][] twoDArray) {
        List<String> list = new ArrayList<String>();
        for (String[] array : twoDArray) {
            list.addAll(Arrays.asList(array));
        }
        return list;
    }

    // method for print 2D array
    public static void printArray2D(String[][] array) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                System.out.print(array[i][j] + "  ");
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {

        // initialization of starting and ending position

        // must uncomment arrays for testing !!!!!!!
        // yes // yes // tested
        String[][] startPosition = {{"2","8","3"}, {"1","6","4"},{"7","*","5"}};
        String[][] endPosition = {{"1","2","3"}, {"8","*","4"},{"7","6","5"}};

        // yes // yes // tested
//        String[][] startPosition = {{"*","6","4"}, {"5","7","3"},{"2","8","1"}};
//        String[][] endPosition = {{"5","2","6"}, {"4","3","8"},{"7","*","1"}};

        // yes // yes // tested
//        String[][] startPosition = {{"3","5","*"}, {"4","2","8"},{"1","6","7"}};
//        String[][] endPosition = {{"1","7","3"}, {"4","8","5"},{"*","2","6"}};

        // yes // yes // tested
//        String[][] startPosition = {{"6","3","8"}, {"*","7","1"},{"2","4","5"}};
//        String[][] endPosition = {{"2","8","3"}, {"5","7","1"},{"*","6","4"}};

        // yes // yes
//        String[][] startPosition = {{"1","2","3"}, {"4","5","6"},{"7","8","*"}};
//        String[][] endPosition = {{"1","2","3"}, {"4","6","8"},{"7","5","*"}};

        // yes // yes
//        String[][] startPosition = {{"1","2","3"}, {"5","6","*"},{"7","8","4"}};
//        String[][] endPosition = {{"1","2","3"}, {"5","8","6"},{"*","7","4"}};

        // yes // yes
//        String[][] startPosition = {{"1","2","3"}, {"*","4","6"},{"7","5","8"}};
//        String[][] endPosition = {{"1","2","3"}, {"4","5","6"},{"7","8","*"}};

        // yes //yes
//        String[][] startPosition = {{"5","1","8"}, {"6","3","7"},{"*","4","2"}};
//        String[][] endPosition = {{"6","*","2"}, {"4","5","8"},{"7","3","1"}};

        // some special cases
        // yes // no  // FOR TEST !!
//        String[][] startPosition = {{"8","6","3"}, {"1","2","4"},{"5","*","7"}};
//        String[][] endPosition = {{"3","*","5"}, {"8","4","7"},{"2","1","6"}};

        // no // yes
//        String[][] startPosition = {{"4","6","2"}, {"5","1","*"},{"7","8","3"}};
//        String[][] endPosition = {{"3","6","*"}, {"8","2","7"},{"4","1","5"}};

        System.out.println("Vyber heuristiku (1 alebo 2): ");
        Scanner s = new Scanner(System.in);
        int heuristic = s.nextInt();

        // initialize tree, and root as a start position and end as an end position
        Tree tree = new Tree();
        tree.root = tree.addRoot(startPosition);
        tree.end = tree.addEnd(endPosition);

        // print starting and ending position
        System.out.println("root");
        printArray2D(tree.root.value);
        System.out.println("end");
        printArray2D(tree.end.value);
        System.out.println("-----------------");

        // initialize hashtable for eliminate duplicate nodes
        HashSet<List> set = new HashSet<>();
        set.add(twoDArrayToList(startPosition));

        // call method to do all stuff
        int createdNodes = 1;
        long startTime = System.nanoTime();
        tree.doAllStuff(tree.root, tree.end, set, createdNodes, heuristic);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Duration of program: " + duration + " milliseconds");
    }

}