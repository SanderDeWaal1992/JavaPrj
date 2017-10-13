package remaining;

public class Combine3Objects<T1, T2, T3>{
    private T1 object1;
    private T2 object2;
    private T3 object3;
    public Combine3Objects( T1 object1, T2 object2, T3 object3){
        this.object1 = object1;
        this.object2 = object2;
        this.object3 = object3;
    }
    public T1 getObject1(){
        return object1;
    }
    public T2 getObject2(){
        return object2;
    }
    public T3 getObject3(){
        return object3;
    }
}

