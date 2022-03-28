package com.ssafy.b105.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HashtagDto {

    private Long id;

    private String name;

    @QueryProjection
    public HashtagDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
