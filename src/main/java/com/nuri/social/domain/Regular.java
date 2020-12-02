package com.nuri.social.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.nuri.social.domain.enumeration.RegularStatus;

/**
 * A Regular.
 */
@Document(collection = "regular")
public class Regular implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Size(max = 128)
    @Field("name")
    private String name;

    @NotNull
    @Field("status")
    private RegularStatus status;

    @NotNull
    @Field("created")
    private Instant created;

    @NotNull
    @Field("modified")
    private Instant modified;

    @DBRef
    @Field("you")
    @JsonIgnoreProperties(value = "regulars", allowSetters = true)
    private Avatar you;

    @DBRef
    @Field("me")
    @JsonIgnoreProperties(value = "regulars", allowSetters = true)
    private Avatar me;

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

    public Regular name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RegularStatus getStatus() {
        return status;
    }

    public Regular status(RegularStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(RegularStatus status) {
        this.status = status;
    }

    public Instant getCreated() {
        return created;
    }

    public Regular created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public Regular modified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public Avatar getYou() {
        return you;
    }

    public Regular you(Avatar avatar) {
        this.you = avatar;
        return this;
    }

    public void setYou(Avatar avatar) {
        this.you = avatar;
    }

    public Avatar getMe() {
        return me;
    }

    public Regular me(Avatar avatar) {
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
        if (!(o instanceof Regular)) {
            return false;
        }
        return id != null && id.equals(((Regular) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Regular{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            "}";
    }
}
