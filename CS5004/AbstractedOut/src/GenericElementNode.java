
import java.util.function.Function;

/**
 * This is a non-empty node in a generic list. It contains the object data
 * and the nextNode of the list
 */
public class GenericElementNode<T> implements GenericListADTNode<T> {
  private T object;
  private GenericListADTNode<T> nextNode;

  public GenericElementNode(T p,GenericListADTNode<T> nextNode) {
    this.object = p;
    this.nextNode = nextNode;
  }

 
  @Override
  public int count() {return 1+this.nextNode.count();} 
  
  /*
  //Accumulator version of count
  @Override
  public int count() {return countHelp(0);}
  */
  
  @Override
  public int countHelp(int acc) {return nextNode.countHelp(1 + acc);}

  @Override
  public GenericListADTNode<T> addFront(T object) {
    return new GenericElementNode<T>(object,this);
  }

  @Override
  public GenericListADTNode<T> addBack(T object) {
    this.nextNode = this.nextNode.addBack(object);
    return this;
  }

  @Override
  public GenericListADTNode<T> add(int index,T object) {
    if (index==0) {
      return addFront(object);
    }
    else {
      this.nextNode = this.nextNode.add(index-1,object);
      return this;
    }
  }

  @Override
  public GenericListADTNode<T> remove(T object) {
    if (this.object.equals(object)) {
      return this.nextNode;
    }
    else {
      this.nextNode = this.nextNode.remove(object);
      return this;
    }
  }

  @Override
  public T get(int index) throws IllegalArgumentException{
    if (index==0) return this.object;
    return this.nextNode.get(index-1);
  }

  @Override
  public <R> GenericListADTNode<R> map(Function<T,R> converter) {
    /* Starting from this list of T, the resulting list of type R is an
    element that contains this data converted to T, followed by the nextNode of
    the converted list
     */
    return new GenericElementNode<R>(
            converter.apply(this.object),
            this.nextNode.map(converter));
  }

  @Override
  public String toString() {
    String objstring = this.object.toString();
    String nextNode = this.nextNode.toString();
    if (nextNode.length()>0)
      return objstring + " " + nextNode;
    else
      return objstring;
  }
}