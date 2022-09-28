package com.joorani.myshop.product.domain.dtos;


import com.joorani.myshop.member.domain.entity.Store;
import com.joorani.myshop.product.domain.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductRegisterForm {

    //상품 이름
    private String name;

    // 가격
    private int price;

    // 수량
    private int quantity;

    // 이미지
    private String imagePath;

    @Builder
    public ProductRegisterForm(String name, int price, int quantity, String imagePath) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imagePath = imagePath;
    }

    public Product toEntity(Store store) {
        return Product.builder()
                .name(name)
                .price(price)
                .stockQuantity(quantity)
                .store(store)
                .imgPath(imagePath)
                .build();
    }
}
