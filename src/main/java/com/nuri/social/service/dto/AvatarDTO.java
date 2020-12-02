package com.nuri.social.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.nuri.social.domain.enumeration.AvatarStatus;

/**
 * A DTO for the {@link com.nuri.social.domain.Avatar} entity.
 */
public class AvatarDTO implements Serializable {
    
    private String id;

    @Size(max = 128)
    private String categoryName;

    @NotNull
    @Size(max = 128)
    private String name;

    @Size(max = 1024)
    private String description;

    private String text;

    
    private byte[] logo;

    private String logoContentType;
    
    private byte[] banner;

    private String bannerContentType;
    @NotNull
    private Integer coin;

    @NotNull
    private Integer point;

    @NotNull
    private Integer respect;

    @NotNull
    private Integer diss;

    @NotNull
    private Integer grade;

    @NotNull
    private Integer credit;

    @NotNull
    private Integer views;

    @NotNull
    private Integer comments;

    @NotNull
    private AvatarStatus status;

    @NotNull
    private Instant created;

    @NotNull
    private Instant modified;


    private String categoryId;

    private String userId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getLogoContentType() {
        return logoContentType;
    }

    public void setLogoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
    }

    public byte[] getBanner() {
        return banner;
    }

    public void setBanner(byte[] banner) {
        this.banner = banner;
    }

    public String getBannerContentType() {
        return bannerContentType;
    }

    public void setBannerContentType(String bannerContentType) {
        this.bannerContentType = bannerContentType;
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public AvatarStatus getStatus() {
        return status;
    }

    public void setStatus(AvatarStatus status) {
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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String avatarCategoryId) {
        this.categoryId = avatarCategoryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AvatarDTO)) {
            return false;
        }

        return id != null && id.equals(((AvatarDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AvatarDTO{" +
            "id=" + getId() +
            ", categoryName='" + getCategoryName() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", text='" + getText() + "'" +
            ", logo='" + getLogo() + "'" +
            ", banner='" + getBanner() + "'" +
            ", coin=" + getCoin() +
            ", point=" + getPoint() +
            ", respect=" + getRespect() +
            ", diss=" + getDiss() +
            ", grade=" + getGrade() +
            ", credit=" + getCredit() +
            ", views=" + getViews() +
            ", comments=" + getComments() +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            ", categoryId='" + getCategoryId() + "'" +
            ", userId='" + getUserId() + "'" +
            "}";
    }
}
