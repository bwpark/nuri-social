package com.nuri.social.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.nuri.social.domain.enumeration.CategoryStatus;

/**
 * A DTO for the {@link com.nuri.social.domain.Category} entity.
 */
public class CategoryDTO implements Serializable {
    
    private String id;

    @NotNull
    @Size(max = 128)
    private String path;

    @NotNull
    @Size(max = 128)
    private String name;

    @Size(max = 1024)
    private String icon;

    @Size(max = 1024)
    private String description;

    private Integer hitAndSort;

    private Integer respect;

    private Integer diss;

    private Integer join;

    @NotNull
    private CategoryStatus status;

    @NotNull
    private Instant created;

    @NotNull
    private Instant modified;


    private String meId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHitAndSort() {
        return hitAndSort;
    }

    public void setHitAndSort(Integer hitAndSort) {
        this.hitAndSort = hitAndSort;
    }

    public Integer getRespect() {
        return respect;
    }

    public void setRespect(Integer respect) {
        this.respect = respect;
    }

    public Integer getDiss() {
        return diss;
    }

    public void setDiss(Integer diss) {
        this.diss = diss;
    }

    public Integer getJoin() {
        return join;
    }

    public void setJoin(Integer join) {
        this.join = join;
    }

    public CategoryStatus getStatus() {
        return status;
    }

    public void setStatus(CategoryStatus status) {
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
        if (!(o instanceof CategoryDTO)) {
            return false;
        }

        return id != null && id.equals(((CategoryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategoryDTO{" +
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
            ", meId='" + getMeId() + "'" +
            "}";
    }
}
