package GameBot.MKBot;

public class Node{
    private Node parrent;
    private List children;
    private Move moveToGetHere;
    private Node bestChild;
    private int activePlayer; // The protagonist player is always 1 and the opponent is always 2
    private ReversiBoard board;
    private double nodeValue;
    private double branchValue;
    private static double DEFAULT_NODE_VALUE = -1;
    private static int traversedCounter;
    private static int TRAVERSE_THRESHOLD = 30;
    
    public Node(){
        parrent = null;
        children = new ArrayList<Node>();
        moveToGetHere = null;
        bestChild = null;
        activePlayer = 1;
        board = null;
        nodeValue = DEFAULT_NODE_VALUE;
        branchValue = DEFAULT_NODE_VALUE;
    }

    public Node(Node parrent, Move moveToGetHere, double activePlayer, ReversiBoard board){
        this.parrent = parrent;
        children = new ArrayList<Node>();
        this.moveToGetHere = moveToGetHere;
        bestChild = null;
        this.activePlayer = 1;
        this.board = board;
        this.nodeValue = DEFAULT_NODE_VALUE;
        this.branchValue = DEFAULT_NODE_VALUE;
    }
    
    public void spawnChildren(){
        ArrayList<Move> allPotentialMoves = board.allPotentialMoves(activePlayer);
        for(Move m : allPotentialMoves){
            ReversiBoard newBoard = board.copy();
            newBoard.doMove(m, activePlayer);
            children.add(new Node(this, m, 3-activePlayer, newBoard));
        }
    }
    
    public void spawnNewChildTier(){
        if(children.size() > 0){
            for(Node n : children){
                n.spawnNewChildTier();
            }
        }else{
            spawnChildren();
        }
    }
    
    public double computeNodeValue(){
        double scorePlayer1 = board.getScore(1);
        double scorePlayer2 = board.getScore(2);
        nodeValue = scorePlayer1/scorePlayer2;
        return nodeValue;
    }
    
    public boolean updateLeafNodeValues(){
        if(children.size() > 0){
            boolean done = 1;
            for(Node n : children){
                done = done * n.updateLeafNodeValues();
            }
            return done;
        }else if(nodeValue == DEFAULT_NODE_VALUE){
            computeNodeValue();
            traversedCounter++;
        }
        if(traversedCounter < TRAVERSE_THRESHOLD){
            return 1;
        }else{
            return 0;
        }
    }
    
    public void resetTraversedCounter(){
        traversedCounter = 0;
    }
    
    public double updateBranchValue(){
        double tmpValue = 0;
        if(activePlayer == 1){
            branchValue = -1 * Double.POSITIVE_INFINITY;
        }else{
            branchValue = Double.POSITIVE_INFINITY;
        }
        if(children.size() > 0){
            for(Node n : children){
                tmpValue = n.updateBranchValue();
                if((tmpValue > branchValue && activePlayer == 1) || (tmpValue < branchValue && activePlayer == 2)){
                    branchValue = tmpValue;
                    bestChild = n;
                }
            }
        }else{
            branchValue = nodeValue;
        }
        return branchValue;
    }
    
    public Node findChildByMove(Move m){
        for(Node child : children){
            if(child.moveToGetHere.equals(m)){
                return child;
            }
        }
        return null;
    }
    
    public Move getBestMove(){
        return bestChild.moveToGetHere.copy();
    }
    
    public int getNumberOfChildren(){
        return children.size();
    }
}