package wait;

public class Main2 {

	public static void main(String[] args) {
		int x=0;
       while(x<=100){//easy varianta
    	   System.out.println(x+"%");
    	   x++;//to je jako x=x+1;
      	 try {//stop
  			Thread.sleep(50);//v milisekundách 1 sekunda= 1000
  		} catch (InterruptedException e) {//zachicuje jestli nějaké podvlákno nepřerušilo proces autt generated
  			e.printStackTrace();
  		}
       }
	}

}