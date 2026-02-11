package andreapascarella.u5d8.payloads;

import andreapascarella.u5d8.enums.BlogCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class NewBlogPayload {

    public String cover;
    private BlogCategory blogCategory;
    private String title;
    private String content;
    private int readingTime;
    private Long authorId;
}
