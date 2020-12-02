package com.nuri.social.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A TOS.
 */
@Document(collection = "tos")
public class TOS implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Size(max = 1024)
    @Field("policy")
    private String policy;

    @Field("text")
    private String text;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPolicy() {
        return policy;
    }

    public TOS policy(String policy) {
        this.policy = policy;
        return this;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getText() {
        return text;
    }

    public TOS text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TOS)) {
            return false;
        }
        return id != null && id.equals(((TOS) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TOS{" +
            "id=" + getId() +
            ", policy='" + getPolicy() + "'" +
            ", text='" + getText() + "'" +
            "}";
    }
}
