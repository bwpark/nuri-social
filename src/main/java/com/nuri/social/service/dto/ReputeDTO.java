package com.nuri.social.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.nuri.social.domain.enumeration.ReputeStatus;

/**
 * A DTO for the {@link com.nuri.social.domain.Repute} entity.
 */
public class ReputeDTO implements Serializable {
    
    private String id;

    @Size(max = 1024)
    private String description;

    @NotNull
    private Integer grade;

    @NotNull
    private Integer credit;

    @NotNull
    private ReputeStatus status;

    @NotNull
    private Instant created;

    @NotNull
    private Instant modified;


    private String youId;

    private String meId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public ReputeStatus getStatus() {
        return status;
    }

    public void setStatus(ReputeStatus status) {
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
        if (!(o instanceof ReputeDTO)) {
            return false;
        }

        return id != null && id.equals(((ReputeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReputeDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", grade=" + getGrade() +
            ", credit=" + getCredit() +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            ", youId='" + getYouId() + "'" +
            ", meId='" + getMeId() + "'" +
            "}";
    }
}
