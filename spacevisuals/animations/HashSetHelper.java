package spacevisuals.animations;

import java.util.HashSet;
import java.util.function.Function;

public class HashSetHelper implements PointSetHelper{
    
    HashSet<Double[]> set;

    public HashSetHelper(HashSet<Double[]> set){
        this.set = set;
    }
    public HashSetHelper(){
        this.set = new HashSet<Double[]>();
    }

    public void setSet(HashSet<Double[]> newSet){
        this.set = newSet;
    }
    public HashSet<Double[]> getSet(){
        return this.set;
    }

    public void traverseDomain(java.util.function.Consumer<Double[]> handleImage){
            for(Double[] input : this.set){
                handleImage.accept(input);
            }
        }
    }
