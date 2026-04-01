package com.jpstechno.edumate_backend.modeles.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OtpRequestDto {

    private String tokenTemporaire;
    private String otp;
}
