package hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.zip.CRC32;


public class ConsistentHashNodeServiceImpl implements HashNodeService {
    
    private Map<Long,Node> nodeMap = new TreeMap<Long,Node>();
    
    @Override
    public void addNode(Node node) {
        long crcKey = hash(node.getIp());
        nodeMap.put(crcKey, node);
    }

    @Override
    public Node lookupNode(String key) {
        long crcKey = hash(key);
        Node node = findValidNode(crcKey);
        if(node != null){
            return node;
        }
        return findValidNode(0);
    }

    private Node findValidNode(long crcKey) {
        Set<Map.Entry<Long,Node>> nodeSet = nodeMap.entrySet();
        for(Map.Entry<Long, Node> entry : nodeSet){
            
            if(crcKey <= entry.getKey()){
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public Long hash(String key) {
        CRC32 crc = new CRC32();
        crc.update(key.getBytes());
        return crc.getValue();
    }

    @Override
    public void removeNode(Node node) {
        
    }
    
    public static void test1(){
        HashNodeService nodeService = new NormalHashNodeServiceImpl();
        Node addNode1 = new Node("node1", "192.168.0.1");
        Node addNode2 = new Node("node2", "192.168.0.2");
        Node addNode3 = new Node("node3", "192.168.0.3");
        Node addNode4 = new Node("node4", "192.168.0.4");
        Node addNode5 = new Node("node5", "192.168.0.5");
        Node addNode6 = new Node("node6", "192.168.0.6");
        Node addNode7 = new Node("node7", "192.168.0.7");
        Node addNode8 = new Node("node8", "192.168.0.8");
        nodeService.addNode(addNode1);
        nodeService.addNode(addNode2);
        nodeService.addNode(addNode3);
        nodeService.addNode(addNode4);
        nodeService.addNode(addNode5);
        nodeService.addNode(addNode6);
        nodeService.addNode(addNode7);
        nodeService.addNode(addNode8);

        //用于检查数据分布情况
        Map<String, Integer> countmap = new HashMap<String, Integer>();
        Node node = null;
        for (int i = 1; i <= 10000; i++) {
            String key = String.valueOf(i);
            node = nodeService.lookupNode(key);
            node.cacheString(key, "value");
            String k = node.getIp();
            Integer count = countmap.get(k);
            if (count == null) {
                count = 1;
                countmap.put(k, count);
            } else {
                count++;
                countmap.put(k, count);
            }

        }
        System.out.println("初始化数据分布情况：" + countmap);
    }
    
    public static void test2(){
        HashNodeService nodeService = new ConsistentHashNodeServiceImpl();
        Node addNode1 = new Node("node1", "192.168.0.1");
        Node addNode2 = new Node("node2", "192.168.0.2");
        Node addNode3 = new Node("node3", "192.168.0.3");
        Node addNode4 = new Node("node4", "192.168.0.4");
        Node addNode5 = new Node("node5", "192.168.0.5");
        Node addNode6 = new Node("node6", "192.168.0.6");
        Node addNode7 = new Node("node7", "192.168.0.7");
        Node addNode8 = new Node("node8", "192.168.0.8");
        nodeService.addNode(addNode1);
        nodeService.addNode(addNode2);
        nodeService.addNode(addNode3);
        nodeService.addNode(addNode4);
        nodeService.addNode(addNode5);
        nodeService.addNode(addNode6);
        nodeService.addNode(addNode7);
        nodeService.addNode(addNode8);

        //用于检查数据分布情况
        Map<String, Integer> countmap = new HashMap<String, Integer>();
        Node node = null;
        for (int i = 1; i <= 10000; i++) {
            String key = String.valueOf(i);
            node = nodeService.lookupNode(key);
            node.cacheString(key, "value");
            String k = node.getIp();
            Integer count = countmap.get(k);
            if (count == null) {
                count = 1;
                countmap.put(k, count);
            } else {
                count++;
                countmap.put(k, count);
            }

        }
        System.out.println("初始化数据分布情况：" + countmap);
        // 正常情况下的去获取数据，命中率
        int hitcount = 0;
        for (int i = 1; i <= 10000; i++) {
            String key = String.valueOf(i);
            node = nodeService.lookupNode(key);
            if (node != null) {
                String value = node.getCacheValue(key);
                if (value != null) {
                    hitcount++;
                }
            }
        }
        double h = Double.parseDouble(String.valueOf(hitcount))/ Double.parseDouble(String.valueOf(100000));
        System.out.println("初始化缓存命中率:"+ h);
        // 移除一个节点
        Node addNode9 = new Node("node0", "192.168.0.9");
        nodeService.addNode(addNode9);
        int hitCount = 0;
        for(int i=00;i<100000;i++){
            String key = String.valueOf(i);
            node = nodeService.lookupNode(key);
            if(node != null ){
                String value = node.getCacheValue(key);
                if(value != null){
                    hitCount++ ;
                }
            }
        }
        h = Double.parseDouble(String.valueOf(hitCount))/Double.parseDouble(String.valueOf(100000));
        System.out.println("增加节点后缓存命中率:"+ h);
        
    }
    
    public static void main(String[] args){
        test2();
    }
}
