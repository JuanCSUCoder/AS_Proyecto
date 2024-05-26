package com.pedidos.modelo;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "User", schema = "public", catalog = "defaultdb")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "userPod")
    private String userPod;
    @Basic
    @Column(name = "providerUrl")
    private String providerUrl;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<OrderEntity> ordersById;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserPod() {
        return userPod;
    }

    public void setUserPod(String userPod) {
        this.userPod = userPod;
    }

    public String getProviderUrl() {
        return providerUrl;
    }

    public void setProviderUrl(String providerUrl) {
        this.providerUrl = providerUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userPod != null ? !userPod.equals(that.userPod) : that.userPod != null) return false;
        if (providerUrl != null ? !providerUrl.equals(that.providerUrl) : that.providerUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userPod != null ? userPod.hashCode() : 0);
        result = 31 * result + (providerUrl != null ? providerUrl.hashCode() : 0);
        return result;
    }

    public Collection<OrderEntity> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<OrderEntity> ordersById) {
        this.ordersById = ordersById;
    }
}
