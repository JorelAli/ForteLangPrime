class Color {
		int red;
		int green;
		int blue;
		
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Color other = (Color) obj;
			if (blue != other.blue)
				return false;
			if (green != other.green)
				return false;
			if (red != other.red)
				return false;
			return true;
		}
	}