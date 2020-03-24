
/*
   Library {} {

		someFunction <Num> = 2;
	
		someOtherFunction a<Num> -> b<Num> -> <Num> = 3;
	
		Color = {
			red<Num>;
			green<Num>;
			blue<Num>;
		}
	
		
	
	}
 */

public interface SampleLib {

	public static int someFunction() {
		return 2;
	}
	
	public static int someOtherFunction(int a, int b) {
		return 3;
	}
	
	static final class Color {
		final int red;
		final int green;
		final int blue;
		
		public Color(int red, int green, int blue) {
			this.red = red;
			this.green = green;
			this.blue = blue;
		}
	}
	
	public static Color colorRed() {
		return new Color(255, 0, 0);
	}
	
	public static void test(Color c) {
		int getBlue = c.blue;
	}
	
	
	
}
