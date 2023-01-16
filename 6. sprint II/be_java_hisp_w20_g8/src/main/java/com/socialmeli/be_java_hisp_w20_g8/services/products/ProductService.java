package com.socialmeli.be_java_hisp_w20_g8.services.products;


import com.socialmeli.be_java_hisp_w20_g8.dto.ProductDTO;
import com.socialmeli.be_java_hisp_w20_g8.exceptions.OperationFailedException;
import com.socialmeli.be_java_hisp_w20_g8.models.Product;
import com.socialmeli.be_java_hisp_w20_g8.repositories.products.IProductRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService {
    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    private IProductRepository productRepository;

    public ProductService() {
        mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    @Override
    public boolean createProduct(ProductDTO productDTO) {
        // Get the product if exists
        Product product = mapper.map(productDTO, Product.class);
        Optional<Product> existing = productRepository.getProductById(product.getProduct_id());

        // Check if the product is valid
        if(existing.isPresent() && !product.equals(existing.get())) {
            throw new OperationFailedException("Another product with the same identifier is already registered");
        }

        // Create the product
        if(existing.isEmpty())
            return productRepository.createProduct(product);

        return false;
    }

    @Override
    public ProductDTO getProductById(int productId) {
        Optional<Product> product = productRepository.getProductById(productId);

        if(product.isEmpty())
            throw new OperationFailedException("The product does not exist in the repository");

        return mapper.map(product, ProductDTO.class);
    }
}
