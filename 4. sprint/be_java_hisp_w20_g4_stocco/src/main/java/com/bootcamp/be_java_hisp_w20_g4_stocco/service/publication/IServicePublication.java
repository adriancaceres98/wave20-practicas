package com.bootcamp.be_java_hisp_w20_g4_stocco.service.publication;

import com.bootcamp.be_java_hisp_w20_g4_stocco.dto.request.PostDTO;
import com.bootcamp.be_java_hisp_w20_g4_stocco.dto.request.PostPromoDTO;
import com.bootcamp.be_java_hisp_w20_g4_stocco.dto.response.product.ProductTwoWeeksResponseDTO;
import com.bootcamp.be_java_hisp_w20_g4_stocco.dto.response.publication.PromoCountDTO;
import com.bootcamp.be_java_hisp_w20_g4_stocco.dto.response.publication.PublicationDTO;
import com.bootcamp.be_java_hisp_w20_g4_stocco.dto.response.publication.PublicationPromoDTO;
import com.bootcamp.be_java_hisp_w20_g4_stocco.dto.response.publication.PublicationSellerPromoDTO;

public interface IServicePublication {


    PublicationDTO addPublication(PostDTO publication);
    ProductTwoWeeksResponseDTO getLastTwoWeeksPublications(int userId, String order);

    PublicationPromoDTO addPublicationPromo(PostPromoDTO postPromoDTO);

    PromoCountDTO countPublicationPromo(int user_id);

    PublicationSellerPromoDTO publicationSellerPromo(int user_id);

}
