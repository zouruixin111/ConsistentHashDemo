package nginx;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Weight {

    public static void main(String[] args) {
        
        Node node1= new Node(1, "192.168.1.1" , 0);
        Node node2= new Node(2, "192.168.1.2", 0);
        Node node3= new Node(1, "192.168.1.3", 0);
        Node node4= new Node(4, "192.168.1.4", 0);
        
        List<Node> nl = new LinkedList<Node>();
        nl.add(node1);
        nl.add(node2);
        nl.add(node3);
        nl.add(node4);
        
        for(int i=0;i<nl.size();i++){
            System.out.println(nl.get(i).getIp());
        }
        System.out.println("====================");
        for(int i=0;i<20;i++){
            Node node = nl.get(0);
            System.out.println(node.getIp());
            if(!node.increase()){
                nl.remove(0);
                nl.add(nl.size(), node);
            }
            
        }
    }
}
class Node{
    
    int weight;
    String ip;
    int weightVal;
    
    public Node(int weight , String ip, int weightVal){
        this.weight = weight;
        this.ip = ip;
        this.weightVal = weightVal;
    }
    
    public boolean increase(){
        return ++this.weightVal < this.weight;
    }
    
    public void reset(){
        this.weightVal = 0;
    }
    
    public int getWeight() {
        return weight;
    }
    
    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    public String getIp() {
        return ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getWeightVal() {
        return weightVal;
    }
    
    public void setWeightVal(int weightVal) {
        this.weightVal = weightVal;
    }
    
    public String toString(){
        return this.getIp();
    }
}