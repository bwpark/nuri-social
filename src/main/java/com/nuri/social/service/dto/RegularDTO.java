package com.nuri.social.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.nuri.social.domain.enumeration.RegularStatus;

/**
 * A DTO for the {@link com.nuri.social.domain.Regular} entity.
 */
public class RegularDTO implements Serializable {
    
    private String id;

    @NotNull
    @Size(max = 128)
    private String name;

    @NotNull
    private RegularStatus status;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RegularStatus getStatus() {
        return status;
    }

    public void setStatus(RegularStatus status) {
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
        if (!(o instanceof RegularDTO)) {
            return false;
        }

        return id != null && id.equals(((RegularDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RegularDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            ", youId='" + getYouId() + "'" +
            ", meId='" + getMeId() + "'" +
            "}";
    }
}
