package rs.ac.uns.ftn.informatika.redditClone.model.entity;

import javax.persistence.*;

@Entity
public class Flair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name",nullable = false)
    private String name;
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "post_id",nullable = true)
//    private Post post;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "community_id",nullable = true)
    private Community community;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Post getPost() {
//        return post;
//    }
//
//    public void setPost(Post post) {
//        this.post = post;
//    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

//    public Flair(String name, Post post, Community community) {
//        this.name = name;
//        this.post = post;
//        this.community = community;
//    }
//    public Flair(String name, Post post) {
//        this.name = name;
//        this.post = post;
//    }
    public Flair(){
    }

    public Flair(String name, Community community) {
        this.name = name;
        this.community = community;
    }

    @Override
    public String toString() {
        return "Flair{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
