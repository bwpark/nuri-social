package com.nuri.social.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.nuri.social.domain.enumeration.IssueStatus;

/**
 * A Issue.
 */
@Document(collection = "issue")
public class Issue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Size(max = 128)
    @Field("category_name")
    private String categoryName;

    @NotNull
    @Size(max = 128)
    @Field("name")
    private String name;

    @Size(max = 1024)
    @Field("description")
    private String description;

    
    @Field("text")
    private String text;

    @NotNull
    @Field("coin")
    private Integer coin;

    @NotNull
    @Field("point")
    private Integer point;

    @NotNull
    @Field("respect")
    private Integer respect;

    @NotNull
    @Field("diss")
    private Integer diss;

    @NotNull
    @Size(max = 128)
    @Field("author")
    private String author;

    @NotNull
    @Field("views")
    private Integer views;

    @NotNull
    @Field("comments")
    private Integer comments;

    @NotNull
    @Field("status")
    private IssueStatus status;

    @NotNull
    @Field("created")
    private Instant created;

    @NotNull
    @Field("modified")
    private Instant modified;

    @DBRef
    @Field("option")
    private Set<IssueOption> options = new HashSet<>();

    @DBRef
    @Field("interact")
    private Set<Interact> interacts = new HashSet<>();

    @DBRef
    @Field("resource")
    private Set<IssueResource> resources = new HashSet<>();

    @DBRef
    @Field("emotion")
    private Set<Emotion> emotions = new HashSet<>();

    @DBRef
    @Field("category")
    @JsonIgnoreProperties(value = "issues", allowSetters = true)
    private Category category;

    @DBRef
    @Field("me")
    @JsonIgnoreProperties(value = "issues", allowSetters = true)
    private Avatar me;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Issue categoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public Issue name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Issue description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public Issue text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getCoin() {
        return coin;
    }

    public Issue coin(Integer coin) {
        this.coin = coin;
        return this;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getPoint() {
        return point;
    }

    public Issue point(Integer point) {
        this.point = point;
        return this;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getRespect() {
        return respect;
    }

    public Issue respect(Integer respect) {
        this.respect = respect;
        return this;
    }

    public void setRespect(Integer respect) {
        this.respect = respect;
    }

    public Integer getDiss() {
        return diss;
    }

    public Issue diss(Integer diss) {
        this.diss = diss;
        return this;
    }

    public void setDiss(Integer diss) {
        this.diss = diss;
    }

    public String getAuthor() {
        return author;
    }

    public Issue author(String author) {
        this.author = author;
        return this;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getViews() {
        return views;
    }

    public Issue views(Integer views) {
        this.views = views;
        return this;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getComments() {
        return comments;
    }

    public Issue comments(Integer comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public Issue status(IssueStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public Instant getCreated() {
        return created;
    }

    public Issue created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public Issue modified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public Set<IssueOption> getOptions() {
        return options;
    }

    public Issue options(Set<IssueOption> issueOptions) {
        this.options = issueOptions;
        return this;
    }

    public Issue addOption(IssueOption issueOption) {
        this.options.add(issueOption);
        issueOption.setIssue(this);
        return this;
    }

    public Issue removeOption(IssueOption issueOption) {
        this.options.remove(issueOption);
        issueOption.setIssue(null);
        return this;
    }

    public void setOptions(Set<IssueOption> issueOptions) {
        this.options = issueOptions;
    }

    public Set<Interact> getInteracts() {
        return interacts;
    }

    public Issue interacts(Set<Interact> interacts) {
        this.interacts = interacts;
        return this;
    }

    public Issue addInteract(Interact interact) {
        this.interacts.add(interact);
        interact.setIssue(this);
        return this;
    }

    public Issue removeInteract(Interact interact) {
        this.interacts.remove(interact);
        interact.setIssue(null);
        return this;
    }

    public void setInteracts(Set<Interact> interacts) {
        this.interacts = interacts;
    }

    public Set<IssueResource> getResources() {
        return resources;
    }

    public Issue resources(Set<IssueResource> issueResources) {
        this.resources = issueResources;
        return this;
    }

    public Issue addResource(IssueResource issueResource) {
        this.resources.add(issueResource);
        issueResource.setIssue(this);
        return this;
    }

    public Issue removeResource(IssueResource issueResource) {
        this.resources.remove(issueResource);
        issueResource.setIssue(null);
        return this;
    }

    public void setResources(Set<IssueResource> issueResources) {
        this.resources = issueResources;
    }

    public Set<Emotion> getEmotions() {
        return emotions;
    }

    public Issue emotions(Set<Emotion> emotions) {
        this.emotions = emotions;
        return this;
    }

    public Issue addEmotion(Emotion emotion) {
        this.emotions.add(emotion);
        emotion.setIssue(this);
        return this;
    }

    public Issue removeEmotion(Emotion emotion) {
        this.emotions.remove(emotion);
        emotion.setIssue(null);
        return this;
    }

    public void setEmotions(Set<Emotion> emotions) {
        this.emotions = emotions;
    }

    public Category getCategory() {
        return category;
    }

    public Issue category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Avatar getMe() {
        return me;
    }

    public Issue me(Avatar avatar) {
        this.me = avatar;
        return this;
    }

    public void setMe(Avatar avatar) {
        this.me = avatar;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Issue)) {
            return false;
        }
        return id != null && id.equals(((Issue) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Issue{" +
            "id=" + getId() +
            ", categoryName='" + getCategoryName() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", text='" + getText() + "'" +
            ", coin=" + getCoin() +
            ", point=" + getPoint() +
            ", respect=" + getRespect() +
            ", diss=" + getDiss() +
            ", author='" + getAuthor() + "'" +
            ", views=" + getViews() +
            ", comments=" + getComments() +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            "}";
    }
}
