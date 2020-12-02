package com.nuri.social.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Chemistry.
 */
@Document(collection = "chemistry")
public class Chemistry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Size(max = 128)
    @Field("your_name")
    private String yourName;

    @NotNull
    @Field("to_you")
    private Integer toYou;

    @NotNull
    @Field("to_me")
    private Integer toMe;

    @NotNull
    @Field("created")
    private Instant created;

    @DBRef
    @Field("you")
    @JsonIgnoreProperties(value = "chemistries", allowSetters = true)
    private Avatar you;

    @DBRef
    @Field("me")
    @JsonIgnoreProperties(value = "chemistries", allowSetters = true)
    private Avatar me;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYourName() {
        return yourName;
    }

    public Chemistry yourName(String yourName) {
        this.yourName = yourName;
        return this;
    }

    public void setYourName(String yourName) {
        this.yourName = yourName;
    }

    public Integer getToYou() {
        return toYou;
    }

    public Chemistry toYou(Integer toYou) {
        this.toYou = toYou;
        return this;
    }

    public void setToYou(Integer toYou) {
        this.toYou = toYou;
    }

    public Integer getToMe() {
        return toMe;
    }

    public Chemistry toMe(Integer toMe) {
        this.toMe = toMe;
        return this;
    }

    public void setToMe(Integer toMe) {
        this.toMe = toMe;
    }

    public Instant getCreated() {
        return created;
    }

    public Chemistry created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Avatar getYou() {
        return you;
    }

    public Chemistry you(Avatar avatar) {
        this.you = avatar;
        return this;
    }

    public void setYou(Avatar avatar) {
        this.you = avatar;
    }

    public Avatar getMe() {
        return me;
    }

    public Chemistry me(Avatar avatar) {
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
        if (!(o instanceof Chemistry)) {
            return false;
        }
        return id != null && id.equals(((Chemistry) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Chemistry{" +
            "id=" + getId() +
            ", yourName='" + getYourName() + "'" +
            ", toYou=" + getToYou() +
            ", toMe=" + getToMe() +
            ", created='" + getCreated() + "'" +
            "}";
    }
}
