public class Pair<T,S> {
	public T elt1;
	public S elt2;
	public Pair(T elt1, S elt2){
		this.elt1 = elt1;
		this.elt2 = elt2;
	}
	public T getElementA(){
		return elt1;
	}
	public S getElementB(){
		return elt2;
	}

	// My Code
	public String toString() {
		return "(elt1 : " + this.elt1 + ", " +
				"elt2 : " + this.elt2 + ")\n";
	}
}
