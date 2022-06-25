package com.example.redditcloneapp.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.redditcloneapp.model.enums.ReactionType;
import com.example.redditcloneapp.model.enums.ReportReason;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Mokap implements Serializable {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("marko123", "marko123", "marko123@gmail.com", "Suplja prica"));
        users.add(new User("milana123", "milana123", "milana123@gmail.com", "Nije nego"));
        users.add(new User("kosta123", "kosta123", "milana123@gmail.com", "ma ajde sada"));
        users.add(new User("dragisa123", "dragisa123", "dragisa123@gmail.com", "A nije nego"));
        return users;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<User> getModerators() {
        ArrayList<User> moderators = new ArrayList<>();
        moderators.add(new Moderator("pera123", "pera123", "pera123@gmail.com", "Moderatorska posla", false));
        moderators.add(new Moderator("darko123", "darko123", "darko123@gmail.com", "Moderatorska posla", false));
        return moderators;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<Administrator> getAdinistrators() {
        ArrayList<Administrator> administrators = new ArrayList<>();
        administrators.add(new Administrator("admin123", "admin123", "admin123@gmail.com", "Admin kralj", false));
        return administrators;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<Reaction> getReactions() {
        ArrayList<Reaction> reactions = new ArrayList<>();
        reactions.add(new Reaction(1, ReactionType.UPVOTE, getUsers().get(0)));
        reactions.add(new Reaction(2, ReactionType.DOWNVOTE, getUsers().get(1)));
        reactions.add(new Reaction(3, ReactionType.UPVOTE, getUsers().get(2)));
        reactions.add(new Reaction(4, ReactionType.UPVOTE, getUsers().get(3)));
        return reactions;
    }

    public static ArrayList<Flair> getFlairs() {
        ArrayList<Flair> flairs = new ArrayList<>();
        flairs.add(new Flair(1, "Happy news"));
        flairs.add(new Flair(2, "Bad news"));
        flairs.add(new Flair(3, "Sad news"));
        return flairs;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<Comment> getComments() {
        ArrayList<Comment> comments = new ArrayList<>();
        Comment comment1 = new Comment(1, "Komentar 1", getUsers().get(1), null, 0);
        Comment comment2 = new Comment(2, "Komentar 2", getUsers().get(0), null, 0);
        ArrayList<Comment> subComments = new ArrayList<>();
        subComments.add(comment1);
        subComments.add(comment2);
        Comment comment3 = new Comment(3, "Komentar 3", getUsers().get(1), subComments, 0);
        Comment comment4 = new Comment(4, "Komentar 4", getUsers().get(1), null, 0);
        Comment comment5 = new Comment(5, "Komentar 5", getUsers().get(1), null, 0);

        comments.add(comment3);
        comments.add(comment4);
        comments.add(comment5);
        return comments;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<Community> getCommunities() {
        ArrayList<Community> communities = new ArrayList<>();
        ArrayList rules = new ArrayList();
        rules.add("rule1");
        rules.add("rule2");
        rules.add("rule3");
        rules.add("rule4");
        communities.add(new Community(1, "Community1", "Prvi community. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse a ornare mi. Sed ut mi eros. In hac habitasse platea dictumst. Mauris a pharetra urna. In posuere nisl in nulla sollicitudin viverra. Praesent congue, velit vitae condimentum porttitor, sapien elit fermentum felis, ac gravida metus arcu et lorem.", rules, getFlairs(), getModerators(), getPosts()));
        return communities;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<Post> getPosts() {
        ArrayList<Post> posts = new ArrayList<>();
        ArrayList<Comment> postComments1 = new ArrayList<>();
        ArrayList<Comment> postComments2 = new ArrayList<>();
        postComments1.add(getComments().get(0));
        postComments1.add(getComments().get(1));
        postComments2.add(getComments().get(2));
        posts.add(new Post(1, "Sivi soko", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", getUsers().get(0), getFlairs().get(0), postComments1, 3));
        posts.add(new Post(2, "Orao pao", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce suscipit laoreet ante ut rhoncus. Sed id urna in sem hendrerit tristique. Ut et sollicitudin odio. Ut vel diam vehicula, viverra mi id, varius massa. Nulla eu lacus a sem varius tristique eu a orci. Proin ligula velit, ultrices vel nunc vitae, luctus sodales lectus. Phasellus suscipit, erat porttitor ultricies varius, justo arcu dapibus dolor, sit amet aliquet nulla arcu nec nisl. Aliquam eget nisi dignissim, mollis ipsum at, imperdiet neque. Pellentesque non gravida quam. Suspendisse fringilla feugiat odio et malesuada.", getUsers().get(1), getFlairs().get(1), postComments2, 2));
        return posts;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<Post> getPostsForCommunity(Community community) {
        ArrayList<Post> allPosts = getPosts();
        ArrayList<Post> posts = new ArrayList<>();
        for (Post p : allPosts) {
//            System.out.println(p.getCommunity().getId() == community.getId());
//            System.out.println(p.getCommunity().getId() + " " + community.getId());

//            if (p.getCommunity().getId() == community.getId())
//                posts.add(p);
        }
        return posts;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<Community> getUsersCommunities(Moderator user) {
        ArrayList<Community> allCommunities = getCommunities();
        ArrayList<Community> usersCommunities = new ArrayList<>();
        for (Community c : allCommunities) {
            if (c.getModerators().contains(user))
                usersCommunities.add(c);
        }
        return usersCommunities;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<Report> getReports() {
        ArrayList<Report> reports = new ArrayList<>();
        reports.add(new Report(ReportReason.BREAKS_RULES, getPosts().get(0)));
        reports.add(new Report(ReportReason.BREAKS_RULES, getComments().get(0)));
        reports.add(new Report(ReportReason.COPYRIGHT_VIOLATION, getComments().get(1)));
        reports.add(new Report(ReportReason.SELF_HARM_OR_SUICIDE, getComments().get(2)));
        return reports;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static User login(String username, String password) {
        for (User u : getUsers()) {
            if (u.username.equals(username) && u.password.equals(password))
                return u;
        }
        for (User u : getModerators()) {
            if (u.username.equals(username) && u.password.equals(password))
                return u;
        }
        for (Administrator u : getAdinistrators()) {
            if (u.username.equals(username) && u.password.equals(password))
                return u;
        }
        return null;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Administrator findAdminByUsername(String username){
        ArrayList<Administrator> administrators = getAdinistrators();
        for (Administrator a: administrators) {
            if (a.getUsername().equals(username))
                return a;
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<Banned> getBanneds(){
        ArrayList<Banned> banneds = new ArrayList<>();
        banneds.add(new Banned(1, getModerators().get(0), getCommunities().get(0), getUsers().get(2)));
        return banneds;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Banned findBanned(Community community, User user){
        ArrayList<Banned> banneds = getBanneds();
        for (Banned ban:banneds) {
            if (ban.getCommunity().getId() == community.getId() && ban.getUser().getUsername().equals(user.getUsername())){
                return ban;
            }
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<Post>findUserPosts(User user){
        ArrayList<Post> allPosts = getPosts();
        ArrayList<Post> posts = new ArrayList<>();
        for (Post p:allPosts) {
            if (p.getUser().getUsername().equals(user.getUsername()))
                posts.add(p);
        }
        return posts;
    }
}
