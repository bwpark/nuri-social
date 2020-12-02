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

import com.nuri.social.domain.enumeration.DealStatus;

/**
 * A Deal.
 */
@Document(collection = "deal")
public class Deal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Size(max = 128)
    @Field("name")
    private String name;

    @Size(max = 1024)
    @Field("description")
    private String description;

    @NotNull
    @Field("quantity")
    private Integer quantity;

    @NotNull
    @Field("unit_price")
    private Integer unitPrice;

    @NotNull
    @Field("coin")
    private Integer coin;

    @NotNull
    @Field("point")
    private Integer point;

    @NotNull
    @Field("status")
    private DealStatus status;

    @NotNull
    @Field("created")
    private Instant created;

    @NotNull
    @Field("modified")
    private Instant modified;

    @DBRef
    @Field("deal")
    private Set<DealOption> deals = new HashSet<>();

    @DBRef
    @Field("with")
    @JsonIgnoreProperties(value = "deals", allowSetters = true)
    private Issue with;

    @DBRef
    @Field("pack")
    @JsonIgnoreProperties(value = "deals", allowSetters = true)
    private Pack pack;

    @DBRef
    @Field("to")
    @JsonIgnoreProperties(value = "sales", allowSetters = true)
    private Avatar to;

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

    public Deal name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Deal description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Deal quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public Deal unitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getCoin() {
        return coin;
    }

    public Deal coin(Integer coin) {
        this.coin = coin;
        return this;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getPoint() {
        return point;
    }

    public Deal point(Integer point) {
        this.point = point;
        return this;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public DealStatus getStatus() {
        return status;
    }

    public Deal status(DealStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(DealStatus status) {
        this.status = status;
    }

    public Instant getCreated() {
        return created;
    }

    public Deal created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public Deal modified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public Set<DealOption> getDeals() {
        return deals;
    }

    public Deal deals(Set<DealOption> dealOptions) {
        this.deals = dealOptions;
        return this;
    }

    public Deal addDeal(DealOption dealOption) {
        this.deals.add(dealOption);
        dealOption.setPack(this);
        return this;
    }

    public Deal removeDeal(DealOption dealOption) {
        this.deals.remove(dealOption);
        dealOption.setPack(null);
        return this;
    }

    public void setDeals(Set<DealOption> dealOptions) {
        this.deals = dealOptions;
    }

    public Issue getWith() {
        return with;
    }

    public Deal with(Issue issue) {
        this.with = issue;
        return this;
    }

    public void setWith(Issue issue) {
        this.with = issue;
    }

    public Pack getPack() {
        return pack;
    }

    public Deal pack(Pack pack) {
        this.pack = pack;
        return this;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public Avatar getTo() {
        return to;
    }

    public Deal to(Avatar avatar) {
        this.to = avatar;
        return this;
    }

    public void setTo(Avatar avatar) {
        this.to = avatar;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deal)) {
            return false;
        }
        return id != null && id.equals(((Deal) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Deal{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", quantity=" + getQuantity() +
            ", unitPrice=" + getUnitPrice() +
            ", coin=" + getCoin() +
            ", point=" + getPoint() +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            "}";
    }
}
