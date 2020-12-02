package com.nuri.social.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.nuri.social.domain.enumeration.EmotionStatus;

/**
 * A DTO for the {@link com.nuri.social.domain.Emotion} entity.
 */
public class EmotionDTO implements Serializable {
    
    private String id;

    @NotNull
    private EmotionStatus status;

    @NotNull
    private Instant created;

    @NotNull
    private Instant modified;


    private String youId;

    private String issueId;

    private String meId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EmotionStatus getStatus() {
        return status;
    }

    public void setStatus(EmotionStatus status) {
        this.status = status;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public String getYouId() {
        return youId;
    }

    public void setYouId(String avatarId) {
        this.youId = avatarId;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getMeId() {
        return meId;
    }

    public void setMeId(String avatarId) {
        this.meId = avatarId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmotionDTO)) {
            return false;
        }

        return id != null && id.equals(((EmotionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmotionDTO{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            ", youId='" + getYouId() + "'" +
            ", issueId='" + getIssueId() + "'" +
            ", meId='" + getMeId() + "'" +
            "}";
    }
}
