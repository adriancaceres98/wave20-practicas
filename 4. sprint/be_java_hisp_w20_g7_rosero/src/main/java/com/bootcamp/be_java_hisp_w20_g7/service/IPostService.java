package com.bootcamp.be_java_hisp_w20_g7.service;

import com.bootcamp.be_java_hisp_w20_g7.dto.request.PostCreateDto;
import com.bootcamp.be_java_hisp_w20_g7.dto.request.ProductPromoDto;
import com.bootcamp.be_java_hisp_w20_g7.dto.response.UserPostFollowedDto;
import com.bootcamp.be_java_hisp_w20_g7.dto.response.UserProductsPromoCountDto;
import com.bootcamp.be_java_hisp_w20_g7.dto.response.UserProductsPromoDto;
import com.bootcamp.be_java_hisp_w20_g7.entity.Post;

public interface IPostService {

    /**
     * US 0005: Dar de alta una nueva publicación
     *
     * @param postCreateDto : body request para crear el post
     * @return String
     */
    String createPost(PostCreateDto postCreateDto);

    /**
     * US 0005: Calcula el id de una nueva publicacion
     *
     * @return int
     */
    void calculateId(Post post);

    /**
     * US 0006: Obtener un listado de las publicaciones realizadas por los vendedores que un usuario sigue en las últimas dos semanas (para esto tener en cuenta ordenamiento por fecha, publicaciones más recientes primero).
     *
     * @param userId : body id del usuario
     * @param order  : String que especifica el orden por fecha en el que se despliegan los posts
     * @return UserPostFollowedDto: retorna el id del ususario y una lista de los post de los usuarios seguidos en las ultimas 2 semanas oredeandos por fecha
     */
    UserPostFollowedDto postUsersFollowed(int userId, String order);




    /**
     * US 0010: Dar de alta una nueva publicación con un producto en promocion
     *
     * @param productPromoDto : body request para crear el post de un producto en promocion
     * @return String
     */
    String createPruductPromo(ProductPromoDto productPromoDto);



    /**
     * US 0011: Obtener la cantidad de productos en promoción de un determinado vendedor
     *
     * @param userId : id del usuario
     * @return UserProductsPromoCountDto : retorna la información del ususario mas el numero de productos que tiene en promocion
     */
    UserProductsPromoCountDto countProductsPromo(int userId);

    /**
     * US 0012: Obtener un listado de las publicaciones realizadas de los productos en promoción de un vendedor
     *
     * @param userId : id del usuario
     * @return UserProductsPromoDto : retorna la información del ususario mas el numero de productos que tiene en promocion
     */
    UserProductsPromoDto getUserProductsPromo(int userId);

}
