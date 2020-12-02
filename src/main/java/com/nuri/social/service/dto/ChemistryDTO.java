package com.nuri.social.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.nuri.social.domain.Chemistry} entity.
 */
public class ChemistryDTO implements Serializable {
    
    private String id;

    @NotNull
    @Size(max = 128)
    private String yourName;

    @NotNull
    private Integer toYou;

    @NotNull
    private Integer toMe;

    @NotNull
    private Instant created;


    private String youId;

    private String meId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYourName() {
        return yourName;
    }

    public void setYourName(String yourName) {
        this.yourName = yourName;
    }

    public Integer getToYou() {
        return toYou;
    }

    public void setToYou(Integer toYou) {
        this.toYou = toYou;
    }

    public Integer getToMe() {
        return toMe;
    }

    public void setToMe(Integer toMe) {
        this.toMe = toMe;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
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
        if (!(o instanceof ChemistryDTO)) {
            return false;
        }

        return id != null && id.equals(((ChemistryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChemistryDTO{" +
            "id=" + getId() +
            ", yourName='" + getYourName() + "'" +
            ", toYou=" + getToYou() +
            ", toMe=" + getToMe() +
            ", created='" + getCreated() + "'" +
            ", youId='" + getYouId() + "'" +
            ", meId='" + getMeId() + "'" +
            "}";
    }
}
