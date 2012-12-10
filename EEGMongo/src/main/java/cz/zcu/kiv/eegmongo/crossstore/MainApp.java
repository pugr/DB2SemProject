package cz.zcu.kiv.eegmongo.crossstore;

import cz.zcu.kiv.eegmongo.crossstore.domain.Product;
import cz.zcu.kiv.eegmongo.crossstore.domain.ProductInfo;
import cz.zcu.kiv.eegmongo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

    public static void main(String[] args) {

        //get hold of the repo
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                "main-context.xml");
        ProductRepository store = ctx.getBean(ProductRepository.class);


        //create a product
        Product product = new Product();
        ProductInfo info = new ProductInfo();
        info.setName("Test");
        info.setDescription("Test Product");
        product.setInfo(info);

        product.setName("Nazef");

        System.out.println(product.getId() + " " + product.getName() + " "
                + product.getInfo().getName() + " " + product.getInfo().getDescription());

        //save the product
        store.save(product);

        //lookup all products
        for (Product p : store.findAll()) {
            System.out.println("info name: " + p.getInfo().getName());
        }

        //remove all
        store.deleteAll();

    }
}