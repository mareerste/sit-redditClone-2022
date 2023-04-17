package rs.ac.uns.ftn.informatika.redditClone.util;

public enum SearchType {

		TERM("term"),
		FUZZY("fuzzy"),
		PHRASE("phrase"),
		RANGE("range"),
		PREFIX("prefix"),
		MATCH("match");

	public final String label;

	SearchType(String label) {
		this.label = label;
	}
}