package ie.atu.sw;

public class Runner {
	
	public static void main(String[] args) throws Exception {
		
			try {
				Menu m = new Menu();
				m.start();
			} catch (Exception e) {
				System.out.println("ERROR: N gram builder program failed");
			}
	}

}