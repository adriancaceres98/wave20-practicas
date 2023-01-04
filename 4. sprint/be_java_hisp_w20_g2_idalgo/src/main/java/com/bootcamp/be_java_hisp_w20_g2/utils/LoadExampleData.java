package com.bootcamp.be_java_hisp_w20_g2.utils;

import com.bootcamp.be_java_hisp_w20_g2.model.Category;
import com.bootcamp.be_java_hisp_w20_g2.model.Post;
import com.bootcamp.be_java_hisp_w20_g2.model.Product;
import com.bootcamp.be_java_hisp_w20_g2.model.User;
import com.bootcamp.be_java_hisp_w20_g2.repository.CategoryRepository;
import com.bootcamp.be_java_hisp_w20_g2.repository.PostRepository;
import com.bootcamp.be_java_hisp_w20_g2.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LoadExampleData {
    private final User diego = new User("Diego");
    private final User flavio = new User("Flavio");
    private final User ale = new User("Ale");
    private final Logger logger = LoggerFactory.getLogger(LoadExampleData.class);
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadDataToRepositories() {
        initializeUsers();
        initializeCategories();
        initializePostsAndProducts();
        logger.info("Example data loaded in repositories");
    }

    private void initializeCategories() {
        categoryRepository.save(new Category(13, "Sillas"));
        categoryRepository.save(new Category(12, "Mesas"));
        categoryRepository.save(new Category(125, "Periféricos"));
        categoryRepository.save(new Category(200, "CPUs"));
    }

    private void initializePostsAndProducts() {
        Product silla = new Product(1000, "Silla gamer", "Gamer", "Redragon", "Negro", "");
        Product mesa = new Product(1001, "Mesa", "Mueble", "Vaca mistica", "Blanco", "");
        Product mouse = new Product(1001, "Mouse", "Gamer", "Logitech", "Negro", "");

        Post primerPost = new Post(LocalDate.now(), mesa, categoryRepository.findByCode(12).get(), 100000);
        Post segundoPost = new Post(LocalDate.of(2021, 5, 25), silla, categoryRepository.findByCode(13).get(), 120000);
        Post postConPromo = new Post(LocalDate.now(), mouse, categoryRepository.findByCode(125).get(), 7000, true, 0.07);

        postRepository.save(primerPost);
        postRepository.save(segundoPost);
        postRepository.save(postConPromo);

        flavio.addPost(primerPost);
        flavio.addPost(segundoPost);
        ale.addPost(postConPromo);
        userRepository.save(flavio);
        userRepository.save(ale);
    }


    private void initializeUsers() {
        diego.follow(flavio);
        ale.follow(flavio);
        flavio.addFollower(diego);
        flavio.addFollower(ale);

        userRepository.save(diego);
        userRepository.save(flavio);
        userRepository.save(ale);
    }
}
