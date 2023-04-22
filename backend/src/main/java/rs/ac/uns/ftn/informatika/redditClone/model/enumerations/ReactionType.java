package rs.ac.uns.ftn.informatika.redditClone.model.enumerations;

public enum ReactionType {
    UPVOTE("UPVOTE"),
    DOWNVOTE("DOWNVOTE");

    private final String type;

    private ReactionType(String s){
        type = s;
    }

    public String toString(){return this.type;}
}
