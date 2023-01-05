package com.bootcamp.be_java_hisp_w20_g4_pereyra.service.publication;

import com.bootcamp.be_java_hisp_w20_g4_pereyra.dto.request.product.ProductDTORequest;
import com.bootcamp.be_java_hisp_w20_g4_pereyra.dto.request.publication.PostDTO;
import com.bootcamp.be_java_hisp_w20_g4_pereyra.dto.request.publication.PostDiscountDTO;
import com.bootcamp.be_java_hisp_w20_g4_pereyra.dto.response.product.ProductDTO;
import com.bootcamp.be_java_hisp_w20_g4_pereyra.dto.response.product.ProductTwoWeeksResponseDTO;
import com.bootcamp.be_java_hisp_w20_g4_pereyra.dto.response.product.PromoProductsCountDTO;
import com.bootcamp.be_java_hisp_w20_g4_pereyra.dto.response.publication.ListedPublicationDiscountDTO;
import com.bootcamp.be_java_hisp_w20_g4_pereyra.dto.response.publication.ListedPublicationsDTO;
import com.bootcamp.be_java_hisp_w20_g4_pereyra.dto.response.publication.PublicationDTO;
import com.bootcamp.be_java_hisp_w20_g4_pereyra.dto.response.publication.PublicationDiscountDTO;

public interface IServicePublication {
    PublicationDTO addPublication(PostDTO publication);
    ProductTwoWeeksResponseDTO getLastTwoWeeksPublications(int userId, String order);
    PublicationDiscountDTO addPromoPublication(PostDiscountDTO postDiscountDTO);
    PromoProductsCountDTO getCountProductsWithDiscount(int user_id);
    ListedPublicationDiscountDTO getProductsWithDiscount(int user_id);

    ProductDTO createProduct(ProductDTORequest productDTORequest);

    ListedPublicationsDTO getAllPublications();
}
