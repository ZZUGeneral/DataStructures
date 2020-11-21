package book.test.object;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName ArrayList
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/11/15 9:34
 * @Version 1.0
 * @Description TODO
 */
public class Pair<T> {
    private T first;
    private T second;

    public Pair(){ first=null; second=null;}
    public Pair(T first,T second){  this.first = first; this.second=second;}

    public T getFirst(){ return  first;}
    public T getSecond(){ return  second;}

    public void setFirst(T newValue){ first= newValue;}
    public void setSecond(T newValue){second = newValue;}

}
