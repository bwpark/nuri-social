package com.nuri.social.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.nuri.social.domain.enumeration.IssueOptionStatus;

/**
 * A IssueOption.
 */
@Document(collection = "issue_option")
public class IssueOption implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Size(max = 128)
    @Field("name")
    private String name;

    @Size(max = 1024)
    @Field("description")
    private String description;

    @NotNull
    @Field("coin")
    private Integer coin;

    @NotNull
    @Field("point")
    private Integer point;

    @NotNull
    @Field("status")
    private IssueOptionStatus status;

    @NotNull
    @Field("created")
    private Instant created;

    @NotNull
    @Field("modified")
    private Instant modified;

    @DBRef
    @Field("issue")
    @JsonIgnoreProperties(value = "options", allowSetters = true)
    private Issue issue;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public IssueOption name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public IssueOption description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCoin() {
        return coin;
    }

    public IssueOption coin(Integer coin) {
        this.coin = coin;
        return this;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getPoint() {
        return point;
    }

    public IssueOption point(Integer point) {
        this.point = point;
        return this;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public IssueOptionStatus getStatus() {
        return status;
    }

    public IssueOption status(IssueOptionStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(IssueOptionStatus status) {
        this.status = status;
    }

    public Instant getCreated() {
        return created;
    }

    public IssueOption created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public IssueOption modified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public Issue getIssue() {
        return issue;
    }

    public IssueOption issue(Issue issue) {
        this.issue = issue;
        return this;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IssueOption)) {
            return false;
        }
        return id != null && id.equals(((IssueOption) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IssueOption{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", coin=" + getCoin() +
            ", point=" + getPoint() +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            "}";
    }
}
