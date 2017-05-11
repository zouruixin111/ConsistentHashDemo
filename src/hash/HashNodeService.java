package hash;

public interface HashNodeService {

    public void addNode(Node node);

    public Node lookupNode(String key);

    public Long hash(String key);

    public void removeNode(Node node);
}