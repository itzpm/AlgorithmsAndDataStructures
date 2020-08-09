package com.zhu.day01;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZPM
 * @version 1.0
 */
public class QueueArray<T> {
    private int front;
    private int rear;
    private final T[] arr;
    private final int maxSize;

    /**
     * default capacity is ten
     */
    public QueueArray() {
        this(10);
    }

    public QueueArray(int maxSize) {
        this.maxSize = maxSize;
        this.arr = (T[]) new Object[maxSize];
        this.front = -1;
        this.rear = -1;
    }

    public boolean isEmpty() {
        checkBound();
        return rear == front;
    }

    private void checkBound() {
        if ((rear >= Integer.MAX_VALUE - maxSize) || (front >= Integer.MAX_VALUE - maxSize)){
            resize();
        }
    }

    public boolean isFull() {
        checkBound();
        return size() == maxSize;
    }

    private void resize() {
        rear = rear % maxSize;
        front = front % maxSize;
    }

    public void add(T data) {
        if (!isFull() && checkNull(data)) {
            arr[++rear % maxSize] = data;
        } else {
            throw new RuntimeException("队列已经满了不能再加入 " + data);
        }
    }

    public T peek() {
        if (size() == 0) {
            return null;
        }
        return arr[(front + 1) % maxSize];
    }

    public int size() {
        return rear - front;
    }

    public T poll() {
        if (!isEmpty()) {
            int temp = (++front % maxSize);
            T t = arr[temp];
            arr[temp] = null;
            return t;
        }
        throw new RuntimeException("队列已经空了");
    }


    private boolean checkNull(Object arg) {
        if (null == arg) {
            throw new NullPointerException();
        }
        return true;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder("[");
        for (int i = (front == -1 ? 0 : front) % maxSize; i <= rear % maxSize; i++) {
            builder.append(arr[i]).append(i == rear % maxSize ? "" : ", ");
        }
        builder.append("]");
        return builder.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        QueueArray<String> queue = new QueueArray<>(5);
        List<Object> list= new ArrayList<>();
    }
}
