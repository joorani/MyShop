package com.joorani.myshop;

import com.joorani.myshop.entity.Product;
import com.joorani.myshop.entity.ProductOption;
import com.joorani.myshop.entity.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Profile("local")
@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final InitDataService initDataService;

    @PostConstruct
    public void init() {
        initDataService.init();
    }

    @Component
    static class InitDataService {

        @PersistenceContext
        EntityManager em;

        @Transactional
        public void init() {

            //상점 등록
            Store storeA = Store.builder()
                    .registeredNumber("123-45-12340".replaceAll("-", ""))
                    .storeName("storeA")
                    .build();

            Store storeB = Store.builder()
                    .registeredNumber("123-45-12341".replaceAll("-", ""))
                    .storeName("storeB")
                    .build();

            em.persist(storeA);
            em.persist(storeB);

            //상품 옵션
            ProductOption productAOption1 = ProductOption.builder()
                    .name("S")
                    .stackQuantity(10)
                    .price(0)
                    .build();

            ProductOption productAOption2 = ProductOption.builder()
                    .name("M")
                    .stackQuantity(10)
                    .price(0)
                    .build();

            em.persist(productAOption1);
            em.persist(productAOption2);

            ProductOption productBOption1 = ProductOption.builder()
                    .name("M")
                    .stackQuantity(300)
                    .price(0)
                    .build();

            ProductOption productBOption2 = ProductOption.builder()
                    .name("L")
                    .stackQuantity(100)
                    .price(0)
                    .build();

            em.persist(productBOption1);
            em.persist(productBOption2);

            List<ProductOption> productAOptions = new ArrayList<>();
            productAOptions.add(productAOption1);
            productAOptions.add(productAOption2);

            List<ProductOption> productBOptions = new ArrayList<>();
            productAOptions.add(productBOption1);
            productAOptions.add(productBOption2);


            //상품 등록

            Product productA = Product.builder()
                    .store(storeA)
                    .name("베츠 어센틱 맨투맨 그레이")
                    .price(34500)
                    .imgPath("https://image.msscdn.net/images/goods_img/20200820/1557508/1557508_4_500.jpg?t=20220915165915")
                    .options(productAOptions)
                    .build();

            Product productB = Product.builder()
                    .store(storeB)
                    .name("오버핏 아치 로고 스웨트 셔츠-그린")
                    .price(29900)
                    .imgPath("https://image.msscdn.net/images/goods_img/20200820/1557508/1557508_4_500.jpg?t=20220915165915")
                    .options(productBOptions)
                    .build();

            em.persist(productA);
            em.persist(productB);
        }

    }
}
