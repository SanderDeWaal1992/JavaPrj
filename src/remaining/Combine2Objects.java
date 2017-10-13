package remaining;

public class Combine2Objects<T1, T2>{
    private T1 object1;
    private T2 object2;
    public Combine2Objects( T1 object1, T2 object2){
        this.object1 = object1;
        this.object2 = object2;
    }
    public T1 getObject1(){
        return object1;
    }
    public T2 getObject2(){
        return object2;
    }
}
