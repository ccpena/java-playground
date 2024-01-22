package org.kkpa.pgutil.models.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Profile {
  private String avatar;
  private String bio;
  private String website;
}