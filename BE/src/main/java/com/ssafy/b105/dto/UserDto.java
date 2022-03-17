package com.ssafy.b105.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.b105.entity.User;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

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
  private String type;

  private LocalDateTime registDate = LocalDateTime.now();

  @Nullable
  private String phone;

  private Set<AuthorityDto> authorityDtoSet;

  public static UserDto from(User user) {
    if(user == null) return null;

    return UserDto.builder()
      .principal(user.getPrincipal())
      .credential(user.getCredential())
      .name(user.getName())
      .type(String.valueOf(user.getType()))
      .registDate(user.getRegistDate())
      .phone(user.getPhone())
      .authorityDtoSet(user.getAuthorities().stream()
        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthority().getAuthorityName()).build())
        .collect(Collectors.toSet()))
      .build();
  }
}
