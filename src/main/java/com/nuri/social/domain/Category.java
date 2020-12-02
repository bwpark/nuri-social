package com.nuri.social.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.nuri.social.domain.enumeration.CategoryStatus;

/**
 * A Category.
 */
@Document(collection = "category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Size(max = 128)
    @Field("path")
    private String path;

    @NotNull
    @Size(max = 128)
    @Field("name")
    private String name;

    @Size(max = 1024)
    @Field("icon")
    private String icon;

    @Size(max = 1024)
    @Field("description")
    private String description;

    @Field("hit_and_sort")
    private Integer hitAndSort;

    @Field("respect")
    private Integer respect;

    @Field("diss")
    private Integer diss;

    @Field("join")
    private Integer join;

    @NotNull
    @Field("status")
    private CategoryStatus status;

    @NotNull
    @Field("created")
    private Instant created;

    @NotNull
    @Field("modified")
    private Instant modified;

    @DBRef
    @Field("me")
    @JsonIgnoreProperties(value = "categories", allowSetters = true)
    private Avatar me;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public Category path(String path) {
        this.path = path;
        return this;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public Category name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public Category icon(String icon) {
        this.icon = icon;
        return this;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public Category description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHitAndSort() {
        return hitAndSort;
    }

    public Category hitAndSort(Integer hitAndSort) {
        this.hitAndSort = hitAndSort;
        return this;
    }

    public void setHitAndSort(Integer hitAndSort) {
        this.hitAndSort = hitAndSort;
    }

    public Integer getRespect() {
        return respect;
    }

    public Category respect(Integer respect) {
        this.respect = respect;
        return this;
    }

    public void setRespect(Integer respect) {
        this.respect = respect;
    }

    public Integer getDiss() {
        return diss;
    }

    public Category diss(Integer diss) {
        this.diss = diss;
        return this;
    }

    public void setDiss(Integer diss) {
        this.diss = diss;
    }

    public Integer getJoin() {
        return join;
    }

    public Category join(Integer join) {
        this.join = join;
        return this;
    }

    public void setJoin(Integer join) {
        this.join = join;
    }

    public CategoryStatus getStatus() {
        return status;
    }

    public Category status(CategoryStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(CategoryStatus status) {
        this.status = status;
    }

    public Instant getCreated() {
        return created;
    }

    public Category created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public Category modified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public Avatar getMe() {
        return me;
    }

    public Category me(Avatar avatar) {
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
        if (!(o instanceof Category)) {
            return false;
        }
        return id != null && id.equals(((Category) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Category{" +
            "id=" + getId() +
            ", path='" + getPath() + "'" +
            ", name='" + getName() + "'" +
            ", icon='" + getIcon() + "'" +
            ", description='" + getDescription() + "'" +
            ", hitAndSort=" + getHitAndSort() +
            ", respect=" + getRespect() +
            ", diss=" + getDiss() +
            ", join=" + getJoin() +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            "}";
    }
}
