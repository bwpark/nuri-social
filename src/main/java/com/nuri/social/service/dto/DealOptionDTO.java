package com.nuri.social.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.nuri.social.domain.enumeration.DealOptionStatus;

/**
 * A DTO for the {@link com.nuri.social.domain.DealOption} entity.
 */
public class DealOptionDTO implements Serializable {
    
    private String id;

    @NotNull
    @Size(max = 128)
    private String name;

    @NotNull
    private DealOptionStatus status;

    @NotNull
    private Instant created;

    @NotNull
    private Instant modified;


    private String packId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DealOptionStatus getStatus() {
        return status;
    }

    public void setStatus(DealOptionStatus status) {
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

    public String getPackId() {
        return packId;
    }

    public void setPackId(String dealId) {
        this.packId = dealId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DealOptionDTO)) {
            return false;
        }

        return id != null && id.equals(((DealOptionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DealOptionDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            ", packId='" + getPackId() + "'" +
            "}";
    }
}
