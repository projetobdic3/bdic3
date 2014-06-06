package br.com.ita.bdic3.util;

public class Boxsplot {

	public Boxsplot(){
		
	}
	
	public float boxSplot(float valor_max) {
		float first_quartile = valor_max / 4;
		System.out.println("first_quartile = " + first_quartile);

		float third_quartile = first_quartile * 3;
		System.out.println("third_quartile = " + third_quartile);

		float upper_limit = (float) (third_quartile + 1.5 * (third_quartile - first_quartile));
		System.out.println("upper_limit = " + upper_limit);
        
		return upper_limit;
	}
	
}
