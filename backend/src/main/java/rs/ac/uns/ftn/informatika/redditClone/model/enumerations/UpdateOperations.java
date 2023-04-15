package rs.ac.uns.ftn.informatika.redditClone.model.enumerations;

public enum UpdateOperations {
    ADD ("add"),
    REMOVE ("remove");

    private final String name;

    private UpdateOperations(String s){
        name = s;
    }

    public String toString(){return this.name;}

}
