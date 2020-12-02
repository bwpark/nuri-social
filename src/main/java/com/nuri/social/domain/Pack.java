package com.nuri.social.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.nuri.social.domain.enumeration.PackStatus;

/**
 * A Pack.
 */
@Document(collection = "pack")
public class Pack implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Size(max = 1024)
    @Field("description")
    private String description;

    @NotNull
    @Field("coin")
    private Integer coin;

    @NotNull
    @Field("point")
    private Integer point;

    @Size(max = 1024)
    @Field("ship_to")
    private String shipTo;

    @NotNull
    @Field("status")
    private PackStatus status;

    @NotNull
    @Field("created")
    private Instant created;

    @NotNull
    @Field("modified")
    private Instant modified;

    @DBRef
    @Field("deal")
    private Set<Deal> deals = new HashSet<>();

    @DBRef
    @Field("me")
    @JsonIgnoreProperties(value = "buys", allowSetters = true)
    private Avatar me;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public Pack description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCoin() {
        return coin;
    }

    public Pack coin(Integer coin) {
        this.coin = coin;
        return this;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getPoint() {
        return point;
    }

    public Pack point(Integer point) {
        this.point = point;
        return this;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getShipTo() {
        return shipTo;
    }

    public Pack shipTo(String shipTo) {
        this.shipTo = shipTo;
        return this;
    }

    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }

    public PackStatus getStatus() {
        return status;
    }

    public Pack status(PackStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(PackStatus status) {
        this.status = status;
    }

    public Instant getCreated() {
        return created;
    }

    public Pack created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public Pack modified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public Set<Deal> getDeals() {
        return deals;
    }

    public Pack deals(Set<Deal> deals) {
        this.deals = deals;
        return this;
    }

    public Pack addDeal(Deal deal) {
        this.deals.add(deal);
        deal.setPack(this);
        return this;
    }

    public Pack removeDeal(Deal deal) {
        this.deals.remove(deal);
        deal.setPack(null);
        return this;
    }

    public void setDeals(Set<Deal> deals) {
        this.deals = deals;
    }

    public Avatar getMe() {
        return me;
    }

    public Pack me(Avatar avatar) {
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
        if (!(o instanceof Pack)) {
            return false;
        }
        return id != null && id.equals(((Pack) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Pack{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", coin=" + getCoin() +
            ", point=" + getPoint() +
            ", shipTo='" + getShipTo() + "'" +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            "}";
    }
}
