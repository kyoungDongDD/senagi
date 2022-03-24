package com.ssafy.b105.dto;

import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityDto {

  @NotNull
  private String authorityName;

}
