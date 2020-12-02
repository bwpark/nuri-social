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

import com.nuri.social.domain.enumeration.InteractStatus;

/**
 * A Interact.
 */
@Document(collection = "interact")
public class Interact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

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
    @Field("status")
    private InteractStatus status;

    @NotNull
    @Field("created")
    private Instant created;

    @NotNull
    @Field("modified")
    private Instant modified;

    @DBRef
    @Field("child")
    private Set<Interact> children = new HashSet<>();

    @DBRef
    @Field("you")
    @JsonIgnoreProperties(value = "interacts", allowSetters = true)
    private Avatar you;

    @DBRef
    @Field("issue")
    @JsonIgnoreProperties(value = "interacts", allowSetters = true)
    private Issue issue;

    @DBRef
    @Field("parent")
    @JsonIgnoreProperties(value = "children", allowSetters = true)
    private Interact parent;

    @DBRef
    @Field("me")
    @JsonIgnoreProperties(value = "interacts", allowSetters = true)
    private Avatar me;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public Interact text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getCoin() {
        return coin;
    }

    public Interact coin(Integer coin) {
        this.coin = coin;
        return this;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getPoint() {
        return point;
    }

    public Interact point(Integer point) {
        this.point = point;
        return this;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getRespect() {
        return respect;
    }

    public Interact respect(Integer respect) {
        this.respect = respect;
        return this;
    }

    public void setRespect(Integer respect) {
        this.respect = respect;
    }

    public Integer getDiss() {
        return diss;
    }

    public Interact diss(Integer diss) {
        this.diss = diss;
        return this;
    }

    public void setDiss(Integer diss) {
        this.diss = diss;
    }

    public String getAuthor() {
        return author;
    }

    public Interact author(String author) {
        this.author = author;
        return this;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public InteractStatus getStatus() {
        return status;
    }

    public Interact status(InteractStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(InteractStatus status) {
        this.status = status;
    }

    public Instant getCreated() {
        return created;
    }

    public Interact created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public Interact modified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public Set<Interact> getChildren() {
        return children;
    }

    public Interact children(Set<Interact> interacts) {
        this.children = interacts;
        return this;
    }

    public Interact addChild(Interact interact) {
        this.children.add(interact);
        interact.setParent(this);
        return this;
    }

    public Interact removeChild(Interact interact) {
        this.children.remove(interact);
        interact.setParent(null);
        return this;
    }

    public void setChildren(Set<Interact> interacts) {
        this.children = interacts;
    }

    public Avatar getYou() {
        return you;
    }

    public Interact you(Avatar avatar) {
        this.you = avatar;
        return this;
    }

    public void setYou(Avatar avatar) {
        this.you = avatar;
    }

    public Issue getIssue() {
        return issue;
    }

    public Interact issue(Issue issue) {
        this.issue = issue;
        return this;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Interact getParent() {
        return parent;
    }

    public Interact parent(Interact interact) {
        this.parent = interact;
        return this;
    }

    public void setParent(Interact interact) {
        this.parent = interact;
    }

    public Avatar getMe() {
        return me;
    }

    public Interact me(Avatar avatar) {
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
        if (!(o instanceof Interact)) {
            return false;
        }
        return id != null && id.equals(((Interact) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Interact{" +
            "id=" + getId() +
            ", text='" + getText() + "'" +
            ", coin=" + getCoin() +
            ", point=" + getPoint() +
            ", respect=" + getRespect() +
            ", diss=" + getDiss() +
            ", author='" + getAuthor() + "'" +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            "}";
    }
}
