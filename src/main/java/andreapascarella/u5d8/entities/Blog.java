package andreapascarella.u5d8.entities;

import andreapascarella.u5d8.enums.BlogCategory;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "blogs")
@NoArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Enumerated(EnumType.STRING)
    private BlogCategory blogCategory;
    private String title;
    private String cover;
    private String content;
    private int readingTime;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    public Blog(BlogCategory blogCategory, String title, String cover, String content, int readingTime, User author) {
        this.blogCategory = blogCategory;
        this.title = title;
        this.cover = "https://www.google.com/url?q=https://picsum.photos/200/300";
        this.content = content;
        this.readingTime = readingTime;
        this.author = author;
    }

}
