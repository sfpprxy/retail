package com.epam.retail.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "order")
public class Order {
    // order id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // Create time of the order, assuming WITHOUT TIME ZONE (SQL).
    // java.time.LocalDateTime is a modern way to represent time,
    // it avoids problems such as mutable issue of java.util.Date.
    @GeneratedValue(strategy = GenerationType.AUTO)
    private LocalDateTime createTime;
    // Info of books, simply represented as a string for demonstration.
    private String book;
    // total price of the order
    private Integer price;
    // ID of a member, indicates who placed the order.
    private Long memberId;

    public Order() {

    }

    public Order(Long id, LocalDateTime createTime, String book, Integer price, Long memberId) {
        this.id = id;
        this.createTime = createTime;
        this.book = book;
        this.price = price;
        this.memberId = memberId;
    }

    public Long getId() {
        return id;
    }

    public Order setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Order setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getBook() {
        return book;
    }

    public Order setBook(String book) {
        this.book = book;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public Order setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Order setMemberId(Long memberId) {
        this.memberId = memberId;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(createTime, order.createTime) &&
                Objects.equals(book, order.book) &&
                Objects.equals(price, order.price) &&
                Objects.equals(memberId, order.memberId);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + createTime.hashCode();
        result = 31 * result + book.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + memberId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", book='" + book + '\'' +
                ", price=" + price +
                ", memberId=" + memberId +
                '}';
    }
}
