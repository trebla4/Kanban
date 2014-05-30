package UMG;


public enum State {
		BACKLOG("RESERVA"), DO_TO("A REALIZAR"), IN_PROGRESS("EN PROGRESO"), DONE("REALIZADO!");
		private String description;

		State(String description) {
			this.description = description;
		}

		public String toString() {
			return description;
		}
}
