import exceptions.InvalidPragmaException;

public class ForteLangPrimeHelper {

	public static enum Pragma {
		IF("IF");

		private String name;
	
		Pragma(String name) {
			this.name = name;
		}

		public static Pragma getPragma(String name) throws InvalidPragmaException {
			for(Pragma pragma : Pragma.values()) {
				if(pragma.name.equals(name)) {
					return pragma;
				}
			}
			throw new InvalidPragmaException(name);
		}
	}
	
}
