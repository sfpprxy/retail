package com.epam.retail.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Member {
    // member id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // Member name, representing user information.
    private String name;
    // Member type: Gold, Silver, Copper.
    private Type type;
    // member point
    private Long point;

    public Member() {

    }

    public Member(Long id, String name, Type type, Long point) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.point = point;
    }

    public Long getId() {
        return id;
    }

    public Member setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Member setName(String name) {
        this.name = name;
        return this;
    }

    public Type getType() {
        return type;
    }

    public Member setType(Type type) {
        this.type = type;
        return this;
    }

    public Long getPoint() {
        return point;
    }

    public Member setPoint(Long point) {
        this.point = point;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id) &&
                Objects.equals(name, member.name) &&
                type == member.type &&
                Objects.equals(point, member.point);
    }

    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + point.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", point=" + point +
                '}';
    }
}
