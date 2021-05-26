package ex02;

public class Monetary{
	private int amount;
	
	public Monetary(int am){
            this.amount = am;
	}
	
	public Monetary(){}//Empty constructor
	
	public String getQuantitiesOfNotes(){
            int usableAmount = this.amount,fiftyQuant = 0,tenQuant = 0,
            fiveQuant = 0,oneQuant = 0;
            while(true){
		if(usableAmount <= 0) break;
		if(usableAmount >= 50){
                    usableAmount -= 50;
                    fiftyQuant++;
		}else if(usableAmount >= 10){
                    usableAmount -= 10;
                    tenQuant++;
		}else if(usableAmount >= 5){
                    usableAmount -= 5;
                    fiveQuant++;
		}else if(usableAmount >= 1){
                    usableAmount -= 1;
                    oneQuant++;
		}
            }
            return String.format("%d %d %d %d%n",fiftyQuant,tenQuant,fiveQuant,oneQuant);
	}
        
        public String getNotesEasier(){
            int usableAmount=this.amount,fiftyQuant=0,tenQuant=0,fiveQuant=0,
                    oneQuant=0;
            fiftyQuant=usableAmount/50;
            tenQuant=usableAmount%50/10;
            fiveQuant=usableAmount%50%10/5;
            oneQuant=usableAmount%50%10%5;
            return String.format("%d %d %d %d%n",fiftyQuant,tenQuant,fiveQuant,oneQuant);
        }
}
