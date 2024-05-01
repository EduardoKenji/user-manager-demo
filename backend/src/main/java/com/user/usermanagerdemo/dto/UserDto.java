package com.user.usermanagerdemo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class UserDto {

    private Long id;

    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String address;
    @NotNull
    private String phoneNumber;
}
