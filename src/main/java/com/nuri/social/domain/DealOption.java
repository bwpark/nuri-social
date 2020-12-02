package com.nuri.social.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.nuri.social.domain.enumeration.DealOptionStatus;

/**
 * A DealOption.
 */
@Document(collection = "deal_option")
public class DealOption implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Size(max = 128)
    @Field("name")
    private String name;

    @NotNull
    @Field("status")
    private DealOptionStatus status;

    @NotNull
    @Field("created")
    private Instant created;

    @NotNull
    @Field("modified")
    private Instant modified;

    @DBRef
    @Field("pack")
    @JsonIgnoreProperties(value = "deals", allowSetters = true)
    private Deal pack;

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

    public DealOption name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DealOptionStatus getStatus() {
        return status;
    }

    public DealOption status(DealOptionStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(DealOptionStatus status) {
        this.status = status;
    }

    public Instant getCreated() {
        return created;
    }

    public DealOption created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public DealOption modified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public Deal getPack() {
        return pack;
    }

    public DealOption pack(Deal deal) {
        this.pack = deal;
        return this;
    }

    public void setPack(Deal deal) {
        this.pack = deal;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DealOption)) {
            return false;
        }
        return id != null && id.equals(((DealOption) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DealOption{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            "}";
    }
}
