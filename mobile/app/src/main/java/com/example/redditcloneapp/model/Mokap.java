package com.example.redditcloneapp.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.redditcloneapp.model.enums.ReactionType;
import com.example.redditcloneapp.model.enums.ReportReason;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Mokap implements Serializable {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static List<User> getUsers(){
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("marko123","marko123","marko123@gmail.com","Suplja prica", false));
        users.add(new User("milana123","milana123","milana123@gmail.com","Nije nego", false));
        users.add(new User("kosta123","kosta123","milana123@gmail.com","ma ajde sada", false));
        users.add(new User("dragisa123","dragisa123","dragisa123@gmail.com","A nije nego", false));
        return users;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static List<Moderator> getModerators(){
        ArrayList<Moderator> moderators = new ArrayList<>();
        moderators.add(new Moderator("pera123","pera123","pera123@gmail.com","Moderatorska posla", false));
        return moderators;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static List<Administrator> getAdinistrators(){
        ArrayList<Administrator> administrators = new ArrayList<>();
        administrators.add(new Administrator("admin123","admin123","admin123@gmail.com","Admin kralj", false));
        return administrators;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static List<Reaction> getReactions(){
        ArrayList<Reaction> reactions = new ArrayList<>();
        reactions.add(new Reaction(1, ReactionType.UPVOTE, getUsers().get(0)));
        reactions.add(new Reaction(2, ReactionType.DOWNVOTE, getUsers().get(1)));
        reactions.add(new Reaction(3, ReactionType.UPVOTE, getUsers().get(2)));
        reactions.add(new Reaction(4, ReactionType.UPVOTE, getUsers().get(3)));
        return reactions;
    }

    public static List<Flair> getFlairs(){
        ArrayList<Flair> flairs = new ArrayList<>();
        flairs.add(new Flair(1,"Happy news"));
        flairs.add(new Flair(2,"Bad news"));
        flairs.add(new Flair(3,"Sad news"));
        return flairs;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static List<Comment> getComments(){
        ArrayList<Comment> comments = new ArrayList<>();
        Comment comment1 = new Comment(1,"Komentar 1", getUsers().get(1),null, new HashSet<>());
        Comment comment2 = new Comment(2,"Komentar 2", getUsers().get(0),null, new HashSet<>());
        HashSet<Comment> subComments = new HashSet<>();
        subComments.add(comment1);
        subComments.add(comment2);
        Comment comment3 = new Comment(3,"Komentar 3", getUsers().get(1),subComments, (Set<Reaction>) getReactions());
        Comment comment4 = new Comment(4,"Komentar 4", getUsers().get(1),null, new HashSet<>());
        Comment comment5 = new Comment(5,"Komentar 5", getUsers().get(1),null, new HashSet<>());

        comments.add(comment3);
        comments.add(comment4);
        comments.add(comment5);
        return comments;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static List<Post> getPosts(){
        ArrayList<Post> posts = new ArrayList<>();
        HashSet<Comment> postComments1 = new HashSet<>();
        HashSet<Comment> postComments2 = new HashSet<>();
        postComments1.add(getComments().get(0));
        postComments1.add(getComments().get(1));
        postComments2.add(getComments().get(2));
        posts.add(new Post(1, "Sivi soko", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", getUsers().get(0), getFlairs().get(0), postComments1, (Set<Reaction>) getReactions()));
        posts.add(new Post(2, "Orao pao", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", getUsers().get(1), getFlairs().get(1), postComments2, (Set<Reaction>) getReactions().get(1)));
        return posts;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static List<Community> getCommunities(){
        ArrayList<Community> communities = new ArrayList<>();
        HashSet rules = new HashSet();
        rules.add("rule1");
        rules.add("rule2");
        rules.add("rule3");
        rules.add("rule4");
        communities.add(new Community(1, "Community1", "Prvi community", rules, getFlairs(), getPosts(),  getModerators()));
        return communities;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static List<Report> getReports(){
        ArrayList<Report> reports = new ArrayList<>();
        reports.add(new Report(1, ReportReason.BREAKS_RULES, getUsers().get(0), getPosts().get(0), null));
        reports.add(new Report(2, ReportReason.BREAKS_RULES, getUsers().get(1), null, getComments().get(0)));
        return reports;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static User login(String username, String password){
        for (User u:getUsers()) {
            if (u.username.equals(username) && u.password.equals(password))
                return u;
        }
        return null;
    }
}
