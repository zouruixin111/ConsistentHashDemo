package hash;

import java.util.HashMap;

public class Node {
    private HashMap<String,String> kv = new HashMap<String,String>();
    
    private String ip;
    private String name;
    public Node(String name , String ip){
        this.name = name;
        this.ip = ip;
    }
    public void cacheString(String key, String value){
       this.kv.put(key, value);
    }
    
    public String getCacheValue(String key){
        return this.kv.get(key);
    }
    
    public String getIp() {
        return ip;
    }

    
    public void setIp(String ip) {
        this.ip = ip;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }
}
