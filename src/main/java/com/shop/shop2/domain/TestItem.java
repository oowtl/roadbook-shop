package com.shop.shop2.domain;


import com.shop.shop2.constant.ItemSellStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "testitem")
@Getter
@Setter
@ToString
public class TestItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // Item ID

    @Column(nullable = false, length = 50)
    private String itemNm; // Item Name

    @Column(name = "price", nullable = false)
    private int price; // Item Price

    @Column(nullable = false)
    private int stockNumber; // Item Stock Number

    @Lob // CLOB, BLOB 타입 매핑 (대용량 데이터)
    @Column(nullable = false)
    private String itemDetail; // Item Detail

    @Enumerated(EnumType.STRING) // Enum 타입 매핑
    private ItemSellStatus itemSellStatus; // Item Sell Status

    private LocalDateTime regTime; // Item Registration Time

    private LocalDateTime updateTime; // Item Update Time
}

