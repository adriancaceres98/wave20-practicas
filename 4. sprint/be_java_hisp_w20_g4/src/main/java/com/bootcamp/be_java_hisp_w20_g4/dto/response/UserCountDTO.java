package com.bootcamp.be_java_hisp_w20_g4.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserCountDTO {
    private int user_id;
    private String user_name;
    private Integer followers_count;
}
