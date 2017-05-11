package hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;

public class NormalHashNodeServiceImpl implements HashNodeService{

    private List<Node> nodes = new ArrayList<Node>();

    @Override
    public void addNode(Node node) {
        this.nodes.add(node);
    }
    @Override
    public Node lookupNode(String key) {
        long k = hash(key);
        int index = (int) (k % nodes.size());
        return nodes.get(index);
    }
    @Override
    public Long hash(String key) {
        CRC32 crc32 = new CRC32();
        crc32.update(key.getBytes());
        return crc32.getValue();
    }
    @Override
    public void removeNode(Node node) {
        nodes.remove(node);
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
        System.out.println("初始化数据分布：" + countmap);
    }
    
    public static void test2(){
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
        System.out.println("初始化数据分布：" + countmap);
        int hitcount = 0;
        for (int i = 1; i <= 100000; i++) {
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
            String value = node.getCacheValue(key);
            if(value != null){
                hitCount++ ;
            }
        }
        h = Double.parseDouble(String.valueOf(hitCount))/Double.parseDouble(String.valueOf(100000));
        System.out.println("增加节点后缓存命中率:"+ h);
        
    }
    
    public static void main(String[] args){
        test2();
    }
}  