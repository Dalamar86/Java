class FreshJuice {

   enum FreshJuiceSize{ SMALL, MEDIUM, LARGE, EXTRA_LARGE }
   FreshJuiceSize size;
}

public class FreshJuiceTest {

   public static void main(String args[]){
      FreshJuice juice = new FreshJuice();
      juice.size = FreshJuice. FreshJuiceSize.EXTRA_LARGE ;
 /*     if  (juice.size == EXTRA_LARGE)
		  System.out.println("Size: EXTRA LARGE" );
		else */
	  System.out.println("Size: " + juice.size);
   }
}