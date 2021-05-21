package ex01;

public class Price{
	private float gas;
	private float etanol;
	
	public Price(float gs,float et){
		this.gas = gs;
		this.etanol = et;
	}
	
	public Price(){}//Empty constructor
	
	public String whatIsBetter(){
		if(this.etanol/this.gas<=0.7) return "Abasteca com etanol";
		else return "Abasteca com gasolina";
	}
}
