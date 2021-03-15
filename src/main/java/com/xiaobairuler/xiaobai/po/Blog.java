package com.xiaobairuler.xiaobai.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author 小白
 * @create 2020/4/15 21:51
 */
@Entity
@Table(name = "t_blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                //博客id
    private String flag;            //博客flag
    private String title;           //博客标题
    private String firstPicture;    //博客首图
    private String description;     //博客简要描述
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;         //博客主体内容
    private Integer views;          //博客浏览次数
    private boolean shareStatment;  //开否开启知识共享
    private boolean commentabled;   //是否开启评论
    private boolean published;      //是否发布状态(1发布0不发布)
    private boolean recommend;      //是否推荐
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;        //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;        //更新时间

    @ManyToOne
    private Type type;              //博客分类(多对一)
    @ManyToOne
    private Tag tag;                //博客标签(多对一)
    @ManyToOne
    private User user;              //小白
    @OneToMany(mappedBy = "blog")
    private List<Comment> commentList = new ArrayList<>();  //博客评论(1对多)

    public Blog() {
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isShareStatment() {
        return shareStatment;
    }

    public void setShareStatment(boolean shareStatment) {
        this.shareStatment = shareStatment;
    }

    public boolean isCommentabled() {
        return commentabled;
    }

    public void setCommentabled(boolean commentabled) {
        this.commentabled = commentabled;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", flag='" + flag + '\'' +
                ", title='" + title + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", views=" + views +
                ", shareStatment=" + shareStatment +
                ", commentabled=" + commentabled +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", type=" + type +
                ", tag=" + tag +
                ", user=" + user +
                ", commentList=" + commentList +
                '}';
    }
}
