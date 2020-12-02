package com.nuri.social.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.nuri.social.domain.TOS} entity.
 */
public class TOSDTO implements Serializable {
    
    private String id;

    @Size(max = 1024)
    private String policy;

    private String text;

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TOSDTO)) {
            return false;
        }

        return id != null && id.equals(((TOSDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TOSDTO{" +
            "id=" + getId() +
            ", policy='" + getPolicy() + "'" +
            ", text='" + getText() + "'" +
            "}";
    }
}
