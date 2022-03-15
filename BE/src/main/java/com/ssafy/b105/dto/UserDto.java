package com.ssafy.b105.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.b105.entity.User;
import com.sun.istack.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  @NotNull
  private String principal;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @NotNull
  private String credential;

  @NotNull
  private String name;

  @NotNull
  private String nickname;

  @NotNull
  private String type;


  private LocalDateTime registDate;

//  private Set<AuthorityDto> authorityDtoSet;

  public static UserDto from(User user) {
    if(user == null) return null;

    return UserDto.builder()
      .principal(user.getPrincipal())
      .credential(user.getCredential())
      .name(user.getName())
      .nickname(user.getNickname())
      .type(user.getType())
      .registDate(user.getRegistDate())
//      .authorityDtoSet(user.getAuthorities().stream()
//        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
//        .collect(Collectors.toSet()))
      .build();
  }
}
