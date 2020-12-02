package com.nuri.social.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.nuri.social.domain.enumeration.ReportStatus;

/**
 * A DTO for the {@link com.nuri.social.domain.Report} entity.
 */
public class ReportDTO implements Serializable {
    
    private String id;

    @Size(max = 1024)
    private String description;

    @NotNull
    @Size(max = 128)
    private String name;

    @NotNull
    private ReportStatus status;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
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
        if (!(o instanceof ReportDTO)) {
            return false;
        }

        return id != null && id.equals(((ReportDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReportDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            ", youId='" + getYouId() + "'" +
            ", meId='" + getMeId() + "'" +
            "}";
    }
}
