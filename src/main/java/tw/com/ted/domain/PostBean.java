package tw.com.ted.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "post", schema = "ted", catalog = "")
public class PostBean {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "SEQNO", nullable = false)
    private Integer seqno;
    @Basic
    @Column(name = "TITLE", nullable = false, length = 255)
    private String title;
    @Basic
    @Column(name = "CREATE_DATE", nullable = true)
    private Date createDate;
    @Basic
    @Column(name = "END_DATE", nullable = true)
    private Date endDate;
    @Basic
    @Column(name = "USER", nullable = true, length = 20)
    private String user;
    @Basic
    @Column(name = "CONTENT", nullable = true, length = -1)
    private String content;

    @Basic
    @Column(name = "ATTACH", nullable = true, length = 255)
    private String attach = null;

    public Integer getSeqno() {
        return seqno;
    }

    public void setSeqno(Integer seqno) {
        this.seqno = seqno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    @Override
    public String toString() {
        return "{" +
                "\"seqno\" : " + '\"' + seqno + '\"' +
                ", \"title\" : " + '\"'+ title + '\"' +
                ", \"createDate\" : " + '\"'+ createDate + '\"' +
                ", \"endDate\" : " + '\"'+ endDate + '\"' +
                ", \"user\" : " + '\"'+ user + '\"' +
                ", \"content\" : " + '\"'+ content + '\"' +
                ", \"attach\" : " + '\"'+ attach + '\"' +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostBean postBean = (PostBean) o;
        return seqno == postBean.seqno && Objects.equals(title, postBean.title) && Objects.equals(createDate, postBean.createDate) && Objects.equals(endDate, postBean.endDate) && Objects.equals(user, postBean.user) && Objects.equals(content, postBean.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seqno, title, createDate, endDate, user, content);
    }
}
