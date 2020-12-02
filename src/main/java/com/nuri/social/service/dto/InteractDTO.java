package com.nuri.social.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.nuri.social.domain.enumeration.InteractStatus;

/**
 * A DTO for the {@link com.nuri.social.domain.Interact} entity.
 */
public class InteractDTO implements Serializable {
    
    private String id;

    private String text;

    @NotNull
    private Integer coin;

    @NotNull
    private Integer point;

    @NotNull
    private Integer respect;

    @NotNull
    private Integer diss;

    @NotNull
    @Size(max = 128)
    private String author;

    @NotNull
    private InteractStatus status;

    @NotNull
    private Instant created;

    @NotNull
    private Instant modified;


    private String youId;

    private String issueId;

    private String parentId;

    private String meId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public InteractStatus getStatus() {
        return status;
    }

    public void setStatus(InteractStatus status) {
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

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String interactId) {
        this.parentId = interactId;
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
        if (!(o instanceof InteractDTO)) {
            return false;
        }

        return id != null && id.equals(((InteractDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InteractDTO{" +
            "id=" + getId() +
            ", text='" + getText() + "'" +
            ", coin=" + getCoin() +
            ", point=" + getPoint() +
            ", respect=" + getRespect() +
            ", diss=" + getDiss() +
            ", author='" + getAuthor() + "'" +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            ", youId='" + getYouId() + "'" +
            ", issueId='" + getIssueId() + "'" +
            ", parentId='" + getParentId() + "'" +
            ", meId='" + getMeId() + "'" +
            "}";
    }
}
