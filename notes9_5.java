class notes9_5 {

	public static void main(String[] args) {
		
		// data types
        // defined an integer variable with a value of 4 
		int x = 4;
		// redefine the variable. set it equal to 6 
		x = 6;

		// cast different types onto existing data 

		float y = (float) x;

		System.out.println(y);

		// strings are non-primitive and consist of characters 
		string favColour = "red";
		System.out.println(favcolour);
        
        // strings can be 'concatenated'  (added together)
        System.out.println(favcolour + " " + x);


       // operators built in to java
       // + , - , * , / , % , ++ , --

        int z = 10;
        z = z++;
	}
}