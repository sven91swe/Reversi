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
    
    public Node(){
        parrent = null;
        children = new ArrayList<Node>();
        moveToGetHere = null;
        bestChild = null;
        activePlayer = 1;
        board = null;
        nodeValue = 0;
        branchValue = 0;
    }

    public Node(Node parrent, Move moveToGetHere, double activePlayer, ReversiBoard board){
        this.parrent = parrent;
        children = new ArrayList<Node>();
        this.moveToGetHere = moveToGetHere;
        bestChild = null;
        this.activePlayer = 1;
        this.board = board;
        this.nodeValue = 0;
        this.branchValue = 0;
    }
    
    public Node(Node parrent, Move moveToGetHere, double activePlayer, ReversiBoard board, double nodeValue){
        this.parrent = parrent;
        children = new ArrayList<Node>();
        this.moveToGetHere = moveToGetHere;
        bestChild = null;
        this.activePlayer = 1;
        this.board = board;
        this.nodeValue = nodeValue;
        this.branchValue = 0;
    }
    
    public void spawnChildren(){
        ArrayList<Move> allPotentialMoves = board.allPotentialMoves(activePlayer);
        for(Move m : allPotentialMoves){
            ReversiBoard newBoard = board.copy();
            newBoard.doMove(m, activePlayer);
            children.add(new Node(this, m, 3-activePlayer, newBoard));
        }
    }
    
    public void spawnChildrenAndComputeNodeValue(){
        ArrayList<Move> allPotentialMoves = board.allPotentialMoves(activePlayer);
        for(Move m : allPotentialMoves){
            ReversiBoard newBoard = board.copy();
            newBoard.doMove(m, activePlayer);
            double scorePlayer1 = newBoard.getScore(1);
            double scorePlayer2 = newBoard.getScore(2);
            double newNodeValue = scorePlayer1/scorePlayer2;
            children.add(new Node(this, m, 3-activePlayer, newBoard, newNodeValue));
        }
    }
    
    public double computeNodeValue(){
        double scorePlayer1 = board.getScore(1);
        double scorePlayer2 = board.getScore(2);
        nodeValue = scorePlayer1/scorePlayer2;
        return nodeValue;
    }
    
    public double updateBranchValue(Node node){
        double tmpValue = 0;
        if(children.size() > 0){
            for(Node n : children){
                tmpValue = updateBranchValue(n);
            }
            if((tmpValue > branchValue && activePlayer == 1) || (tmpValue < branchValue && activePlayer == 2)){ // TODO Check if this is correct
                branchValue = tmpValue;
                bestChild = n; // TODO check scope, maybee should be protected?
            }
        }else{
            branchValue = nodeValue;
        }
        return branchValue;
    }
    
    public void pruneTree(){
        // TODO remove past time nodes and their subtrees
    }
    
}