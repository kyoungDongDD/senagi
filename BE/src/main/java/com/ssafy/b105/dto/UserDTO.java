package com.ssafy.b105.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
<<<<<<< HEAD
import com.ssafy.b105.entity.User;
=======
import com.ssafy.b105.entity.user.User;
>>>>>>> dev
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

  @NotNull
  private String principal;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @NotNull
  private String credential;

  @NotNull
  private String name;

  private String role;

  @Builder.Default
  private LocalDateTime registDate = LocalDateTime.now();

  @Nullable
  private String phone;

  public UserDTO(String username, String role) {
    super();
    this.name = username;
    this.role = role;
  }


  protected static UserDTO from(User user) {
    if(user == null) return null;

    return UserDTO.builder()
      .principal(user.getPrincipal())
      .credential(user.getCredential())
      .name(user.getName())
      .registDate(user.getRegistDate())
      .phone(user.getPhone())
      .build();
  }
}
