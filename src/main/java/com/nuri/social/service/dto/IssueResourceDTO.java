package com.nuri.social.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.nuri.social.domain.enumeration.ResourceType;

/**
 * A DTO for the {@link com.nuri.social.domain.IssueResource} entity.
 */
public class IssueResourceDTO implements Serializable {
    
    private String id;

    @NotNull
    private ResourceType type;

    @NotNull
    private String link;

    @NotNull
    private Instant created;


    private String issueId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IssueResourceDTO)) {
            return false;
        }

        return id != null && id.equals(((IssueResourceDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IssueResourceDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", link='" + getLink() + "'" +
            ", created='" + getCreated() + "'" +
            ", issueId='" + getIssueId() + "'" +
            "}";
    }
}
