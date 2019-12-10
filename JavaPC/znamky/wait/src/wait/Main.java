package wait;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		int x=0;
      while(x<=100) {//když menší nebo rovno stu neboli procenta
    	 if(x>=90) {// dá se to udělat jednodušeji to je v druhé class
    		 System.out.println("|++++++++++| "+x+"%\n\n\n\n");
    	 }else {
    		 if(x>=80) {
        		 System.out.println("|+++++++++-| "+x+"%\n\n\n\n");
    			 
    		 }else{
    			 if(x>=70) {
    	    		 System.out.println("|++++++++--| "+x+"%\n\n\n\n");
    			 }else {
    				 if(x>=60) {
    		    		 System.out.println("|+++++++---| "+x+"%\n\n\n\n");
    				 }else {
    					 if(x>=50) {
    			    		 System.out.println("|++++++----| "+x+"%\n\n\n\n");
    						 
    					 }else {
    						 if(x>=40) {
    				    		 System.out.println("|+++++-----| "+x+"%\n\n\n\n");
    							 
    						 }else {
    							 if(x>=30) {
    					    		 System.out.println("|++++------| "+x+"%\n\n\n\n");
    								 
    							 }else {
    								 if(x>=20) {
    						    		 System.out.println("|+++-------| "+x+"%\n\n\n\n");
    									 
    								 }else {
    									 if(x>=10) {
    							    		 System.out.println("|++--------| "+x+"%\n\n\n\n");
    										 
    									 }else {
    							    		 System.out.println("|+---------| "+x+"%\n\n\n\n");
    										 
    									 }
    								 }
    							 }
    						 }
    					 }
    				 }
    			 }
    		 }
    	 }
    	 x++;//to je jako x=x+1;
    	 try {//stop
			Thread.sleep(50);//v milisekundách 1 sekunda= 1000
		} catch (InterruptedException e) {//zachicuje jestli nějaké podvlákno nepřerušilo proces autt generated
			e.printStackTrace();
		}
      }

	}

}
