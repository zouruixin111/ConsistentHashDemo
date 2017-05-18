package ringbuffer;


import java.util.Arrays;  
  
/** 
 *              ����һ��ѭ�����У����λ��塢RingBuffer����ʵ��Ԫ�ش���һ�������У����������ָ�룬���ƶ�Ԫ�� 
 */  
public class CircleQueue<T> {  
  
    /** 
     * ѭ������ �����飩Ĭ�ϴ�С 
     */  
    private final int DEFAULT_SIZE = 1000;  
  
    /** 
     * (ѭ������)��������� 
     */  
    public int capacity;  
  
    /** 
     * ���飺����ѭ�����е�Ԫ�� 
     */  
    public Object[] elementData;  
  
    /** 
     * ��ͷ(�ȼ��ȳ�) 
     */  
    public int head = 0;  
  
    /** 
     * ��β 
     */  
    public int tail = 0;  
  
    /** 
     * ��ѭ������ Ĭ�ϴ�С������ѭ������ 
     */  
    public CircleQueue() {  
        capacity = DEFAULT_SIZE;  
        elementData = new Object[capacity];  
    }  
  
    /** 
     * ��ָ�����ȵ�����������ѭ������ 
     *  
     * @param initSize 
     */  
    public CircleQueue(final int initSize) {  
        capacity = initSize;  
        elementData = new Object[capacity];  
    }  
  
    /** 
     * ��ȡѭ�����еĴ�С(����Ԫ�صĸ���) 
     */  
    public int size() {  
        if (isEmpty()) {  
            return 0;  
        } else if (isFull()) {  
            return capacity;  
        } else {  
            return tail + 1;  
        }  
    }  
  
    /** 
     * �����βһ��Ԫ�� 
     */  
    public void add(final T element) {  
        if (isEmpty()) {  
            elementData[0] = element;  
        } else if (isFull()) {  
            elementData[head] = element;  
            head++;  
            tail++;  
            head = head == capacity ? 0 : head;  
            tail = tail == capacity ? 0 : tail;  
        } else {  
            elementData[tail + 1] = element;  
            tail++;  
        }  
    }  
  
    public boolean isEmpty() {  
        return tail == head && tail == 0 && elementData[tail] == null;  
    }  
  
    public boolean isFull() {  
        return head != 0 && head - tail == 1 || head == 0 && tail == capacity - 1;  
    }  
  
    public void clear() {  
        Arrays.fill(elementData, null);  
        head = 0;  
        tail = 0;  
    }  
  
    /** 
     * @return ȡ ѭ���������ֵ���Ƚ���index=0�� 
     */  
    public Object[] getQueue() {  
        final Object[] elementDataSort = new Object[capacity];  
        final Object[] elementDataCopy = elementData.clone();  
        if (isEmpty()) {  
        } else if (isFull()) {  
            int indexMax = capacity;  
            int indexSort = 0;  
            for (int i = head; i < indexMax;) {  
                elementDataSort[indexSort] = elementDataCopy[i];  
                indexSort++;  
                i++;  
                if (i == capacity) {  
                    i = 0;  
                    indexMax = head;  
                }  
            }  
        } else {  
            // elementDataSort = elementDataCopy;//�����д�����������ѭ�����ڶ��и���ʱ������  
            for (int i = 0; i < tail; i++) {  
                elementDataSort[i] = elementDataCopy[i];  
            }  
        }  
        return elementDataSort;  
    }  
  
    /** 
     * ���Դ��� 
     */  
    public static void main(final String[] args) {  
        final CircleQueue<Integer> queue = new CircleQueue<Integer>(10);  
        for (int i = 0; i < 10; i++) {  
            queue.add(i);  
        }  
  
        final Object[] queueArray = queue.getQueue();  
        System.out.println("������е��Ⱥ�˳���ӡ������");  
        for (final Object o : queueArray) {  
            System.out.println(o);  
        }  
        System.out.println("capacity: " + queue.capacity);  
        System.out.println("size: " + queue.size());  
        System.out.println("head index: " + queue.head);  
        System.out.println("tail index: " + queue.tail);  
  
    }  
}  