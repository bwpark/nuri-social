package com.nuri.social.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.nuri.social.domain.enumeration.ResourceType;

/**
 * A IssueResource.
 */
@Document(collection = "issue_resource")
public class IssueResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("type")
    private ResourceType type;

    @NotNull
    @Field("link")
    private String link;

    @NotNull
    @Field("created")
    private Instant created;

    @DBRef
    @Field("issue")
    @JsonIgnoreProperties(value = "resources", allowSetters = true)
    private Issue issue;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ResourceType getType() {
        return type;
    }

    public IssueResource type(ResourceType type) {
        this.type = type;
        return this;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public IssueResource link(String link) {
        this.link = link;
        return this;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Instant getCreated() {
        return created;
    }

    public IssueResource created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Issue getIssue() {
        return issue;
    }

    public IssueResource issue(Issue issue) {
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
        if (!(o instanceof IssueResource)) {
            return false;
        }
        return id != null && id.equals(((IssueResource) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IssueResource{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", link='" + getLink() + "'" +
            ", created='" + getCreated() + "'" +
            "}";
    }
}
