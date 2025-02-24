package spacevisuals.spaces;

import java.util.function.Supplier;

public class SingletonSpace<T extends AbstractSpace> {

    public T instance;

    public SingletonSpace(){}

    public T getOrCreateSpace(Supplier<T> constructor){
        if(this.instance == null){this.instance = constructor.get();}
        return this.instance;
    }
}
